package simple.chatbot.usecase;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.TelegramProvider;
import simple.chatbot.dataprovider.postgres.entity.ChatbotChat;
import simple.chatbot.dataprovider.postgres.entity.ChatbotMessage;
import simple.chatbot.entity.MessageTypeEnum;
import simple.chatbot.entity.reaction.IChatbotReaction;
import simple.chatbot.entity.reaction.IChatbotSendReaction;
import simple.chatbot.entity.request.UpdateMessageRequest;
import simple.chatbot.entity.response.MessageReceived;
import simple.chatbot.entity.response.UpdateMessageResponse;
import simple.chatbot.exceptions.ChatbotException;
import simple.chatbot.usecase.shared.message.GetLastChatbotMessageUsecase;
import simple.chatbot.usecase.shared.chat.SaveChatbotChatIfAbsentUsecase;
import simple.chatbot.usecase.shared.message.SaveChatbotMessageIfAbsentUsecase;
import simple.chatbot.usecase.shared.user.SaveChatbotUserIfAbsentUsecase;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Base message processing
 *
 * @author Skyhunter
 * @date 01.12.2020
 */
@Service
public class MessageProcessingUsecase {

    private final TelegramProvider provider;
    private final GetLastChatbotMessageUsecase getLastChatbotMessageUsecase;
    private final SaveChatbotMessageIfAbsentUsecase saveChatbotMessageIfAbsentUsecase;
    private final SaveChatbotUserIfAbsentUsecase saveChatbotUserIfAbsentUsecase;
    private final SaveChatbotChatIfAbsentUsecase saveChatbotChatIfAbsentUsecase;
    private final GetReactionUsecase getReactionUsecase;

    private final static Logger LOG = Logger.getLogger(MessageProcessingUsecase.class.getName());
    private Integer lastMessageUpdateId = 0;
    private Integer offset = 0;

    public MessageProcessingUsecase(TelegramProvider provider, GetLastChatbotMessageUsecase getLastChatbotMessageUsecase, SaveChatbotMessageIfAbsentUsecase saveChatbotMessageIfAbsentUsecase, SaveChatbotUserIfAbsentUsecase saveChatbotUserIfAbsentUsecase, SaveChatbotChatIfAbsentUsecase saveChatbotChatIfAbsentUsecase, GetReactionUsecase getReactionUsecase) {
        this.provider = provider;
        this.getLastChatbotMessageUsecase = getLastChatbotMessageUsecase;
        this.saveChatbotMessageIfAbsentUsecase = saveChatbotMessageIfAbsentUsecase;
        this.saveChatbotUserIfAbsentUsecase = saveChatbotUserIfAbsentUsecase;
        this.saveChatbotChatIfAbsentUsecase = saveChatbotChatIfAbsentUsecase;
        this.getReactionUsecase = getReactionUsecase;
    }

    @PostConstruct
    public void postConstruct() {
        Optional<ChatbotMessage> optionalLastMessage = getLastChatbotMessageUsecase.execute();
        optionalLastMessage.ifPresent(message ->
                lastMessageUpdateId = message.getMessageId()
        );
    }

    /**
     * Get updates messages
     * Save messages to DB
     * Get reaction on message: response to user, or execute some action
     */
    public void execute() {

        List<MessageReceived> messageList;
        try {
            UpdateMessageResponse response = provider.getUpdates(new UpdateMessageRequest(offset));
            offset = response.getUpdateId();
            messageList = response.getMessageList();
        } catch (ChatbotException ce) {
            LOG.warning(ce.getMessage());
            return;
        }
        //LOG.info(String.format("Message updated successfully. Message count=%s", messageList.size()));

        for (MessageReceived message : messageList) {
            if (message.getMessageId() <= lastMessageUpdateId || message.getMessageText() == null) {
                continue;
            }
            lastMessageUpdateId = message.getMessageId();

            saveChatbotUserIfAbsentUsecase.execute(message.getAuthor());
            ChatbotChat chat = saveChatbotChatIfAbsentUsecase.execute(message.getChat());
            if (chat != null && chat.getChatEnable()) {
                //Generate chatbot reaction: not to react, send message to user etc.
                IChatbotReaction reaction = getReactionUsecase.execute(message);
                saveMessageToDB(message, reaction);
                reactionImplementation(reaction);
            }
        }
    }

    private void saveMessageToDB(MessageReceived messageReceived, IChatbotReaction reaction) {
        String responseText;
        if (reaction instanceof IChatbotSendReaction) {
            responseText = ((IChatbotSendReaction) reaction).getMessageText();
        } else {
            responseText = null;
        }
        saveChatbotMessageIfAbsentUsecase.execute(messageReceived, MessageTypeEnum.TEXT, responseText);
    }

    /**
     * TODO usecase
     *
     * @param reaction
     */
    private void reactionImplementation(IChatbotReaction reaction) {
        if (reaction instanceof IChatbotSendReaction) {
            try {
                provider.sendMessage((IChatbotSendReaction) reaction);
            } catch (ChatbotException ce) {
                LOG.warning(ce.getMessage());
            }
        }
    }

}
