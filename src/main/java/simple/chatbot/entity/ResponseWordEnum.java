package simple.chatbot.entity;

/**
 * Bot responses to standard events
 * @date 01.12.2020
 * @author Skyhunter
 */
public enum ResponseWordEnum {

    HELLO(1),
    YES(11);

    private final Integer code;

    ResponseWordEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static ResponseWordEnum parseCode(String code) {
        for (ResponseWordEnum each : values()) {
            if (each.code.equals(code)) {
                return each;
            }
        }
        throw new RuntimeException("Код \"" + code + "\" не соответствует перечислению ответов");
    }
}
