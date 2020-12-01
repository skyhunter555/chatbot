package simple.chatbot.dataprovider.telegram.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://core.telegram.org/bots/api#user
 * User
 *
 * id 	        Integer 	Unique identifier for this user or bot
 * is_bot 	    Boolean 	True, if this user is a bot
 * first_name 	String 	User's or bot's first name
 * last_name 	String 	Optional. User's or bot's last name
 * username 	String 	Optional. User's or bot's username
 * language_code 	String 	Optional. IETF language tag of the user's language
 * can_join_groups 	Boolean 	Optional. True, if the bot can be invited to groups. Returned only in getMe.
 * can_read_all_group_messages 	Boolean 	Optional. True, if privacy mode is disabled for the bot. Returned only in getMe.
 * supports_inline_queries 	Boolean 	Optional. True, if the bot supports inline queries. Returned only in getMe.
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserApiModel {

    @JsonProperty(value = "id")
    private Integer userId;

    @JsonProperty(value = "is_bot")
    private Boolean isBot;

    @JsonProperty(value = "first_name")
    private String firstName;

    @JsonProperty(value = "username")
    private String userName;

    @JsonProperty(value = "language_code")
    private String languageCode;

    public UserApiModel() {

    }

    public UserApiModel(Integer userId, Boolean isBot, String firstName, String userName, String languageCode) {
        this.userId = userId;
        this.isBot = isBot;
        this.firstName = firstName;
        this.userName = userName;
        this.languageCode = languageCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public Boolean getIsBot() {
        return isBot;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
