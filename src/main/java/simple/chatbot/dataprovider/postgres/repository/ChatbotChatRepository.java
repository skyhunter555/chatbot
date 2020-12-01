package simple.chatbot.dataprovider.postgres.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simple.chatbot.dataprovider.postgres.entity.ChatbotChat;
import java.util.List;

/**
 * ChatbotChat Repository
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Repository
public interface ChatbotChatRepository extends CrudRepository<ChatbotChat, String> {

    List<ChatbotChat> findAllByChatEnableTrue();

}
