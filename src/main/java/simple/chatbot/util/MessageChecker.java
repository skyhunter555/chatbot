package simple.chatbot.util;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotPhrase;
import simple.chatbot.entity.LangUsedEnum;
import simple.chatbot.entity.PhraseTypeEnum;
import simple.chatbot.usecase.shared.GetChatbotPhrasesUsecase;
import java.util.List;

@Service
public class MessageChecker {

    private final GetChatbotPhrasesUsecase getChatbotPhrasesUsecase;

    public MessageChecker(GetChatbotPhrasesUsecase getChatbotPhrasesUsecase) {
        this.getChatbotPhrasesUsecase = getChatbotPhrasesUsecase;
    }

    public Boolean checkIsPhraseType(PhraseTypeEnum phraseType, String messageText, LangUsedEnum lang) {
        List<ChatbotPhrase> helloPhrases = getChatbotPhrasesUsecase.execute(phraseType, lang);
        String[] messageWordsArray = messageText.split("[^A-Za-z\\u0410-\\u042f\\u0430-\\u044f]+");
        //For single word
        if (messageWordsArray.length > 0 ) {
            for (String messageWord: messageWordsArray) {
                for (ChatbotPhrase phrase : helloPhrases) {
                    if (phrase.getPhraseContent().equalsIgnoreCase(messageWord)) {
                        return true;
                    }
                }
            }
        }
        //For complex word
        if (messageWordsArray.length > 1 ) {
            for (int i = 0; i < messageWordsArray.length - 1; i++) {
                String complex = String.format("%s %s", messageWordsArray[i], messageWordsArray[i + 1]);
                for (ChatbotPhrase phrase : helloPhrases) {
                    if (phrase.getPhraseContent().equalsIgnoreCase(complex)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean checkIsQuestion(String messageText) {
        return messageText.endsWith("?");
    }

}
