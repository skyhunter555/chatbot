package simple.chatbot.usecase.shared.chat;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotChat;
import simple.chatbot.dataprovider.postgres.repository.ChatbotChatRepository;
import java.util.Optional;

/**
 * Try to get chat entity from DB
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class TryGetChatbotChatUsecase {

    private final ChatbotChatRepository chatbotChatRepository;

    public TryGetChatbotChatUsecase(ChatbotChatRepository chatbotChatRepository) {
        this.chatbotChatRepository = chatbotChatRepository;
    }

    public ChatbotChat execute(String chatId) {
        Optional<ChatbotChat> chatOptional = chatbotChatRepository.findById(chatId);
        return chatOptional.orElse(null);
    }
}
