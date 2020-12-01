package simple.chatbot.entity.response;

import java.util.Date;

/**
 * MessageReceived
 * messageId    - Unique message identifier inside this chat
 * messageText  - message text fro send
 * chat         - chat entity
 * sendDatetime - Date the message was sent in Unix time
 * author       - Author of sending message
 * responseText -
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class MessageReceived {

    private final Integer messageId;
    private final String messageText;
    private final Date sendDatetime;
    private final MessengerChat chat;
    private final MessengerUser author;

    public MessageReceived(Integer messageId, String messageText, Date sendDatetime, MessengerChat chat, MessengerUser author) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.sendDatetime = sendDatetime;
        this.chat = chat;
        this.author = author;
    }

    public String getMessageText() {
        return messageText;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public MessengerChat getChat() {
        return chat;
    }

    public Date getSendDatetime() {
        return sendDatetime;
    }

    public MessengerUser getAuthor() {
        return author;
    }

}
