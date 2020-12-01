package simple.chatbot.dataprovider.telegram.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SendResult
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendResponseApiModel {

    private Boolean ok;
    private MessageApiModel result;

    public SendResponseApiModel() {

    }

    public SendResponseApiModel(Boolean ok, MessageApiModel result) {
        this.ok = ok;
        this.result = result;
    }

    public Boolean getOk() {
        return ok;
    }

    public MessageApiModel getResult() {
        return result;
    }
}
