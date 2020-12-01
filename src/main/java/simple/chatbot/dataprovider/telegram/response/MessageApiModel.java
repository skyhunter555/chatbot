package simple.chatbot.dataprovider.telegram.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://core.telegram.org/bots/api#message
 * Message
 *
 * message_id 	Integer 	Unique message identifier inside this chat
 * offset 	    Integer 	Offset in UTF-16 code units to the start of the entity
 * length 	    Integer 	Length of the entity in UTF-16 code units
 * from 	    User 	    Optional. Sender, empty for messages sent to channels
 * date 	    Integer 	Date the message was sent in Unix time
 * chat 	    Chat 	    Conversation the message belongs to
 * ....
 * text 	    String 	    Optional. For text messages, the actual UTF-8 text of the message, 0-4096 characters
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageApiModel {

    @JsonProperty(value = "message_id")
    private Integer messageId;
    private Integer length;
    private UserApiModel from;
    private Integer date;
    private ChatApiModel chat;
    private String text;

    public MessageApiModel() {

    }

    public MessageApiModel(Integer messageId, Integer offset, Integer length, UserApiModel from, ChatApiModel chat, Integer date, String text) {
        this.messageId = messageId;
        this.length = length;
        this.from = from;
        this.chat = chat;
        this.date = date;
        this.text = text;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public Integer getLength() {
        return length;
    }

    public UserApiModel getFrom() {
        return from;
    }

    public ChatApiModel getChat() {
        return chat;
    }

    public Integer getDate() {
        return date;
    }

    public String getText() {
        return text;
    }


}
