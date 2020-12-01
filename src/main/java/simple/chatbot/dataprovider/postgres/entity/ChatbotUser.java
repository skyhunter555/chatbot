package simple.chatbot.dataprovider.postgres.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import simple.chatbot.entity.LangUsedEnum;
import simple.chatbot.entity.PhraseTypeEnum;
import simple.chatbot.entity.UserTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * Users
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Entity
@Table(name = "chatbot_user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatbotUser {

    @Id
    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "user_type_code")
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserTypeEnum userTypeCode = UserTypeEnum.GUEST;

    @Column(name = "user_create_date")
    @NotNull
    private Timestamp userCreateDate = new Timestamp(new Date().getTime());

    @Column(name = "user_lang")
    @Enumerated(EnumType.STRING)
    @NotNull
    private LangUsedEnum userLang = LangUsedEnum.RU;

    public ChatbotUser() {

    }

    public ChatbotUser(@NotNull Integer userId, @NotNull String userName, @NotNull UserTypeEnum userTypeCode, @NotNull LangUsedEnum userLang) {
        this.userId = userId;
        this.userName = userName;
        this.userTypeCode = userTypeCode;
        this.userLang = userLang;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public UserTypeEnum getUserTypeCode() {
        return userTypeCode;
    }

    public Timestamp getUserCreateDate() {
        return userCreateDate;
    }

    public LangUsedEnum getUserLang() {
        return userLang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatbotUser that = (ChatbotUser) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.userId);
        return hash;
    }
}
