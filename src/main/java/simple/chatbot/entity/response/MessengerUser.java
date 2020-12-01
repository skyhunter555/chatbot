package simple.chatbot.entity.response;

/**
 *  MessengerUser - represents a user or bot.
 *  userId   - Unique identifier for this user or bot
 *  isBot    - True, if this user is a bot
 *  username - User's or bot's first name or nickname and etc.
 *  languageCode - IETF language tag of the user's language
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class MessengerUser {

    private final Integer userId;
    private final boolean isBot;
    private final String username;
    private final String languageCode;

    public MessengerUser(Integer userId, boolean isBot, String username, String languageCode) {
        this.userId = userId;
        this.isBot = isBot;
        this.username = username;
        this.languageCode = languageCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public boolean getIsBot() {
        return isBot;
    }

    public String getUsername() {
        return username;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
