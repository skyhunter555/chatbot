package simple.chatbot.dataprovider.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simple.chatbot.dataprovider.postgres.entity.ChatbotUser;

@Repository
public interface ChatbotUserRepository extends CrudRepository<ChatbotUser, Integer> {

   // List<ChatbotPhrase> findAllByPhraseTypeCodeAndPhraseLang(PhraseTypeEnum phraseTypeCode, LangUsedEnum phraseLang);

}
