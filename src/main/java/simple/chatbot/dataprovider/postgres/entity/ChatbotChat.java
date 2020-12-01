package simple.chatbot.dataprovider.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import simple.chatbot.entity.MessageTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@Table(name = "chatbot_chat")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatbotChat {

    @Id
    @Column(name = "chat_id")
    @NotNull
    private String chatId;

    @Column(name = "chat_name")
    private String chatName;

    @Column(name = "chat_create_date")
    @NotNull
    private Timestamp chatCreateDate;

    @Column(name = "chat_enable")
    private Boolean chatEnable;

    public ChatbotChat() {

    }

    public ChatbotChat(@NotNull String chatId, String chatName, @NotNull Timestamp chatCreateDate, Boolean chatEnable) {
        this.chatId = chatId;
        this.chatName = chatName;
        this.chatCreateDate = chatCreateDate;
        this.chatEnable = chatEnable;
    }

    public String getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public Timestamp getChatCreateDate() {
        return chatCreateDate;
    }

    public Boolean getChatEnable() {
        return chatEnable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotChat that = (ChatbotChat) o;
        return chatId.equals(that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId);
    }
}
