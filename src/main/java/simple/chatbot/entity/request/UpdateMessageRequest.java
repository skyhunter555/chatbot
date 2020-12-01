package simple.chatbot.entity.request;

/**
 * UpdateMessageRequest
 * Integer offset   - Offset in UTF-16 code units to the start of the entity
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class UpdateMessageRequest {

    private final Integer offset;

    public UpdateMessageRequest(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }
}
