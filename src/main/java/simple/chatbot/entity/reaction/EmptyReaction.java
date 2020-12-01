package simple.chatbot.entity.reaction;

import simple.chatbot.entity.response.MessageReceived;

/**
 * EmptyReaction   - not to react
 * originalMessage - originalMessage
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class EmptyReaction implements IChatbotReaction {

    private final MessageReceived originalMessage;

    public EmptyReaction(MessageReceived originalMessage) {
        this.originalMessage = originalMessage;
    }

    @Override
    public MessageReceived getOriginalMessage() {
        return originalMessage;
    }

}
