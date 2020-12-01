package simple.chatbot.dataprovider.telegram.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://core.telegram.org/bots/api#chat
 * Chat
 *
 * id 	    Integer 	Unique identifier for this chat.
 *                      This number may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it.
 *                      But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier.
 * type 	String 	    Type of chat, can be either “private”, “group”, “supergroup” or “channel”
 * title 	String 	    Optional. Title, for supergroups, channels and group chats
 * username 	String 	Optional. Username, for private chats, supergroups and channels if available
 * ....
 * first_name 	String 	Optional. First name of the other party in a private chat
 * last_name 	String 	Optional. Last name of the other party in a private chat
 * photo 	ChatPhoto 	Optional. Chat photo. Returned only in getChat.
 * description 	String 	Optional. Description, for groups, supergroups and channel chats. Returned only in getChat.
 * invite_link 	String 	Optional. Chat invite link, for groups, supergroups and channel chats. Each administrator in a chat generates their own invite links, so the bot must first generate the link using exportChatInviteLink. Returned only in getChat.
 * pinned_message 	Message 	Optional. Pinned message, for groups, supergroups and channels. Returned only in getChat.
 * permissions 	ChatPermissions 	Optional. Default chat member permissions, for groups and supergroups. Returned only in getChat.
 * slow_mode_delay 	Integer 	Optional. For supergroups, the minimum allowed delay between consecutive messages sent by each unpriviledged user. Returned only in getChat.
 * sticker_set_name 	String 	Optional. For supergroups, name of group sticker set. Returned only in getChat.
 * can_set_sticker_set 	Boolean 	Optional. True, if the bot can change the group sticker set. Returned only in getChat.
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatApiModel {

    @JsonProperty(value = "id")
    private String chatId;

    @JsonProperty(value = "type")
    private String chatType;

    private String title;
    private String username;

    public ChatApiModel() {

    }

    public ChatApiModel(String chatId, String chatType, String title, String username) {
        this.chatId = chatId;
        this.chatType = chatType;
        this.title = title;
        this.username = username;
    }

    public String getChatId() {
        return chatId;
    }

    public String getChatType() {
        return chatType;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }
}
