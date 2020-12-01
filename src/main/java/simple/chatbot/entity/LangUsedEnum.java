package simple.chatbot.entity;

/**
 * IETF language used tag
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public enum LangUsedEnum {

    RU;

    public static Boolean contains(String lang) {
        for (LangUsedEnum each : values()) {
            if (each.name().equalsIgnoreCase(lang)) {
                return true;
            }
        }
        return false;
    }
}
