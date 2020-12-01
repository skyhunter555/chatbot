package simple.chatbot.entity;

/**
 * Type of phrase
 *
 * @author Skyhunter
 * @date 01.12.2020
 */
public enum PhraseTypeEnum {

    TEXT(""),                        //Any text
    HELLO("message.hello"),          //Any greeting
    AUTHOR("message.author"),        //Creator of this code
    NAME("message.name"),            //Name of this code
    TASK_LIST("message.task.list"),  //Task list
    QUESTION("message.question");    //Any question

    private final String messageCode;

    PhraseTypeEnum(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
