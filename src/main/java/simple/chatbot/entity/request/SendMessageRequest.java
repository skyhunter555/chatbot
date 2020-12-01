package simple.chatbot.entity.request;

import simple.chatbot.entity.reaction.IChatbotReaction;
import simple.chatbot.entity.reaction.IChatbotSendReaction;
import simple.chatbot.entity.response.MessageReceived;

/**
 * SendMessageReaction
 * originalMessage - originalMessage
 * messageText     - message text for send
 * replyToMessage  - If the message is a reply
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class SendMessageRequest implements IChatbotReaction, IChatbotSendReaction {

    private final MessageReceived originalMessage;
    private final String messageText;
    private final Boolean replyToMessage;

    public SendMessageRequest(MessageReceived originalMessage, String messageText, Boolean replyToMessage) {
        this.messageText = messageText;
        this.originalMessage = originalMessage;
        this.replyToMessage = replyToMessage;
    }

    @Override
    public MessageReceived getOriginalMessage() {
        return originalMessage;
    }

    @Override
    public String getMessageText() {
        return messageText;
    }

    @Override
    public String getChatId() {
        return originalMessage.getChat().getChatId();
    }

    @Override
    public Integer getReplyToMessageId() {
        if (replyToMessage) {
            return originalMessage.getMessageId();
        }
        return null;
    }

}
