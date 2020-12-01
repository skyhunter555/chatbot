package simple.chatbot.dataprovider.telegram.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * incoming update.
 * At most one of the optional parameters can be present in any given update.
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUpdatesResponseApiModel {

    private Boolean ok;
    private List<UpdateResultApiModel> result;

    public GetUpdatesResponseApiModel() {

    }

    public GetUpdatesResponseApiModel(Boolean ok, List<UpdateResultApiModel> result) {
        this.ok = ok;
        this.result = result;
    }

    public Boolean getOk() {
        return ok;
    }

    public List<UpdateResultApiModel> getResult() {
        return result;
    }
}
