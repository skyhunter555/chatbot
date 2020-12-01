package simple.chatbot.dataprovider.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simple.chatbot.dataprovider.postgres.entity.ChatbotPhrase;
import simple.chatbot.entity.LangUsedEnum;
import simple.chatbot.entity.PhraseTypeEnum;

import java.util.List;

@Repository
public interface ChatbotPhraseRepository extends CrudRepository<ChatbotPhrase, Integer> {

    List<ChatbotPhrase> findAllByPhraseTypeCodeAndPhraseLang(PhraseTypeEnum phraseTypeCode, LangUsedEnum phraseLang);

}
