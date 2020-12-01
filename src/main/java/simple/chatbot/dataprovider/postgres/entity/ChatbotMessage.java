package simple.chatbot.dataprovider.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import simple.chatbot.entity.MessageTypeEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * Message chat RAW
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Entity
@Table(name = "chatbot_message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatbotMessage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "message_uuid")
    @NotNull
    private UUID messageUUID = UUID.randomUUID();

    @Column(name = "message_id")
    @NotNull
    private Integer messageId;

    @Column(name = "message_chat_id")
    @NotNull
    private String messageChatId;

    @Column(name = "message_user_id")
    @NotNull
    private Integer messageUserId;

    @Column(name = "message_time")
    @NotNull
    private Timestamp messageTime;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "message_type_code")
    @Enumerated(EnumType.STRING)
    private MessageTypeEnum messageTypeCode = MessageTypeEnum.TEXT;

    @Column(name = "response_text")
    private String responseText;

    public ChatbotMessage() {

    }

    public ChatbotMessage(@NotNull Integer messageId, @NotNull String messageChatId, @NotNull Integer messageUserId,
                          @NotNull Timestamp messageTime, String messageText, MessageTypeEnum messageTypeCode, String responseText) {
        this.messageId = messageId;
        this.messageChatId = messageChatId;
        this.messageUserId = messageUserId;
        this.messageTime = messageTime;
        this.messageText = messageText;
        this.messageTypeCode = messageTypeCode;
        this.responseText = responseText;
    }

    public UUID getMessageUUID() {
        return messageUUID;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public String getMessageChatId() {
        return messageChatId;
    }

    public Integer getMessageUserId() {
        return messageUserId;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public MessageTypeEnum getMessageTypeCode() {
        return messageTypeCode;
    }

    public String getResponseText() {
        return responseText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotMessage that = (ChatbotMessage) o;
        return messageUUID.equals(that.messageUUID);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.messageUUID);
        hash = 79 * hash + Objects.hashCode(this.messageId);
        return hash;
    }
}
