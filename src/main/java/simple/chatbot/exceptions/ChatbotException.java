package simple.chatbot.exceptions;

/**
 * Wrapper over RuntimeException. Includes additional options for formatting message text.
 *
 * @author Skyhunter
 * @date 01.12.2020
 */
public class ChatbotException extends RuntimeException {

    public ChatbotException(String message) {
        super(message);
    }

    public ChatbotException(String messageFormat, Object... messageArgs) {
        super(String.format(messageFormat, messageArgs));
    }

    public ChatbotException(Throwable throwable) {
        super(throwable);
    }

}