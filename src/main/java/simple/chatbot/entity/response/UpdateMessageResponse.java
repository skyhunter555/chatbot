package simple.chatbot.entity.response;

import java.util.List;

/**
 * UpdateMessageResponse
 * updateId 	Integer - The update's unique identifier
 * resultText  - result text of sending ("Ok" and etc)
 * messageList - received message list
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class UpdateMessageResponse {

    private final Integer updateId;
    private final String resultText;
    private final List<MessageReceived> messageList;

    public UpdateMessageResponse(Integer updateId, String resultText, List<MessageReceived> messageList) {
        this.updateId = updateId;
        this.resultText = resultText;
        this.messageList = messageList;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public String getResultText() {
        return resultText;
    }

    public List<MessageReceived> getMessageList() {
        return messageList;
    }

}
