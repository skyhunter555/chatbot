package simple.chatbot.usecase.shared;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotPhrase;
import simple.chatbot.dataprovider.postgres.repository.ChatbotPhraseRepository;
import simple.chatbot.entity.LangUsedEnum;
import simple.chatbot.entity.PhraseTypeEnum;
import java.util.List;

/**
 * Get Phrases by phraseType and phraseLang
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class GetChatbotPhrasesUsecase {

    final private ChatbotPhraseRepository chatbotPhraseRepository;

    public GetChatbotPhrasesUsecase(ChatbotPhraseRepository chatbotPhraseRepository) {
        this.chatbotPhraseRepository = chatbotPhraseRepository;
    }

    public List<ChatbotPhrase> execute(PhraseTypeEnum phraseType, LangUsedEnum phraseLang) {
        return chatbotPhraseRepository.findAllByPhraseTypeCodeAndPhraseLang(phraseType, phraseLang);
    }
}
