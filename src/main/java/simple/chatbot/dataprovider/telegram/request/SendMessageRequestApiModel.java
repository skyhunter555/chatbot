package simple.chatbot.dataprovider.telegram.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * https://core.telegram.org/bots/api#user
 * sendMessage
 *
 * chat_id 	        Integer or String 	Yes 	Unique identifier for the target chat or username of the target channel (in the format @channelusername)
 * text 	        String 	Yes 	Text of the message to be sent, 1-4096 characters after entities parsing
 * parse_mode 	    String 	Optional 	Mode for parsing entities in the message text. See formatting options for more details.
 * disable_web_page_preview 	Boolean 	Optional 	Disables link previews for links in this message
 * disable_notification 	    Boolean 	Optional 	Sends the message silently. Users will receive a notification with no sound.
 * reply_to_message_id 	        Integer 	Optional 	If the message is a reply, ID of the original message
 * reply_markup 	InlineKeyboardMarkup     Optional 	Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.Optional 	Additional interface options. A JSON-serialized object for an inline keyboard, custom reply keyboard, instructions to remove reply keyboard or to force a reply from the user.
 *                  or ReplyKeyboardMarkup
 *                  or ReplyKeyboardRemove
 *                  or ForceReply
 * @date 01.12.2020
 * @author Skyhunter
 */
public class SendMessageRequestApiModel {

    @JsonProperty(value = "chat_id")
    private final String chatId;

    @JsonProperty(value = "text")
    private final String text;    //Size: 1-4096

    @JsonProperty(value = "reply_to_message_id")
    private final Integer replyToMessageId;

    public SendMessageRequestApiModel(String chatId, String text, Integer replyToMessageId) {
        this.chatId = chatId;
        this.text = text;
        this.replyToMessageId = replyToMessageId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public Integer getReplyToMessageId() {
        return replyToMessageId;
    }
}
