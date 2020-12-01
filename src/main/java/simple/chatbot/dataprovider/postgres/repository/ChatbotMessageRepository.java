package simple.chatbot.dataprovider.postgres.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import simple.chatbot.dataprovider.postgres.entity.ChatbotMessage;
import java.util.Optional;
import java.util.UUID;

/**
 * ChatbotMessage Repository
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Repository
public interface ChatbotMessageRepository extends CrudRepository<ChatbotMessage, UUID> {

    Optional<ChatbotMessage> findByMessageIdAndMessageChatId(Integer messageId, String messageChatId);

    @Query(value = "SELECT t.* FROM chatbot_message t ORDER BY message_id DESC LIMIT 1", nativeQuery = true)
    Optional<ChatbotMessage> findLastMessage();

}
