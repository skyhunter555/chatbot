package simple.chatbot.dataprovider.telegram.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  https://core.telegram.org/bots/api#update
 * Update
 *
 * update_id 	    Integer 	The update's unique identifier.
 *                              Update identifiers start from a certain positive number and increase sequentially. \
 *                              This ID becomes especially handy if you're using Webhooks, since it allows you to ignore
 *                              repeated updates or to restore the correct update sequence, should they get out of order.
 *                              If there are no new updates for at least a week, then identifier of the next update will
 *                              be chosen randomly instead of sequentially.
 *
 * message 	        Message 	Optional. New incoming message of any kind — text, photo, sticker, etc.
 * edited_message 	Message 	Optional. New version of a message that is known to the bot and was edited
 * ....
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateResultApiModel {

    @JsonProperty(value = "update_id")
    private Integer updateId;

    @JsonProperty(value = "message")
    private MessageApiModel message;

    @JsonProperty(value = "edited_message")
    private MessageApiModel editedMessage;

    public UpdateResultApiModel() {

    }

    public UpdateResultApiModel(Integer updateId, MessageApiModel message, MessageApiModel editedMessage) {
        this.updateId = updateId;
        this.message = message;
        this.editedMessage = editedMessage;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public MessageApiModel getMessage() {
        return message;
    }

    public MessageApiModel getEditedMessage() {
        return editedMessage;
    }
}
