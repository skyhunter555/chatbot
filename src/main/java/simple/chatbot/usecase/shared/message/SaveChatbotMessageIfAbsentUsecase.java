package simple.chatbot.usecase.shared.message;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotMessage;
import simple.chatbot.dataprovider.postgres.repository.ChatbotMessageRepository;
import simple.chatbot.entity.response.MessageReceived;
import simple.chatbot.entity.MessageTypeEnum;
import java.sql.Timestamp;

/**
 * Save message entity to DB if absent
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class SaveChatbotMessageIfAbsentUsecase {

    private final ChatbotMessageRepository chatbotMessageRepository;
    private final TryGetChatbotMessageUsecase tryGetChatbotMessageUsecase;

    public SaveChatbotMessageIfAbsentUsecase(ChatbotMessageRepository chatbotMessageRepository, TryGetChatbotMessageUsecase tryGetChatbotMessageUsecase) {
        this.chatbotMessageRepository = chatbotMessageRepository;
        this.tryGetChatbotMessageUsecase = tryGetChatbotMessageUsecase;
    }

    public ChatbotMessage execute(MessageReceived messageReceived, MessageTypeEnum messageTypeCode, String responseText) {

        //Have already saved
        ChatbotMessage messageOptional = tryGetChatbotMessageUsecase.execute(
                messageReceived.getMessageId(),
                messageReceived.getChat().getChatId()
        );

        if (messageOptional != null) {
            return messageOptional;
        }
        ChatbotMessage message = new ChatbotMessage(
            messageReceived.getMessageId(),
            messageReceived.getChat().getChatId(),
            messageReceived.getAuthor().getUserId(),
            new Timestamp(messageReceived.getSendDatetime().getTime()),
            messageReceived.getMessageText(),
            messageTypeCode,
            responseText
        );

        return chatbotMessageRepository.save(message);
    }
}
