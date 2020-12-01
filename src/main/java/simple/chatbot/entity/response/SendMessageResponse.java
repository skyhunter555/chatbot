package simple.chatbot.entity.response;

/**
 * SendMessageResponse
 * resultText  - result text of sending ("Ok" or error and etc)
 * messageId   - identity of message (on error = -1)
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class SendMessageResponse {

    private final String resultText;
    private final Integer messageId;

    public SendMessageResponse(Integer messageId, String resultText) {
        this.resultText = resultText;
        this.messageId = messageId;
    }

    public String getResultText() {
        return resultText;
    }

    public Integer getMessageId() {
        return messageId;
    }

}
