package simple.chatbot.entity.reaction;

public interface IChatbotSendReaction {
    String getMessageText();
    String getChatId();
    Integer getReplyToMessageId();
}
