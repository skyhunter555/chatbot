package simple.chatbot.entity.response;

/**
 * https://core.telegram.org/bots/api#chat
 * MessengerChat
 * chatId 	    Integer 	Unique identifier for this chat.
 *                          This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it.
 *                          But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
 * chatName 	String 	    Optional. Title, for supergroups, channels and group chats or username.
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class MessengerChat {

    private final String chatId;
    private final String chatName;

    public MessengerChat(String chatId, String chatName) {
        this.chatId = chatId;
        this.chatName = chatName;
    }

    public String getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }
}
