package simple.chatbot.usecase.shared.user;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotUser;
import simple.chatbot.dataprovider.postgres.repository.ChatbotUserRepository;
import java.util.Optional;

/**
 * Try to get user entity from DB
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class TryGetChatbotUserUsecase {

    private final ChatbotUserRepository chatbotUserRepository;

    public TryGetChatbotUserUsecase(ChatbotUserRepository chatbotUserRepository) {
        this.chatbotUserRepository = chatbotUserRepository;
    }

    public ChatbotUser execute(Integer userId) {
        Optional<ChatbotUser> userOptional = chatbotUserRepository.findById(userId);
        return userOptional.orElse(null);
    }
}
