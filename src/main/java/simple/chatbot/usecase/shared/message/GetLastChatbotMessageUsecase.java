package simple.chatbot.usecase.shared.message;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotMessage;
import simple.chatbot.dataprovider.postgres.repository.ChatbotMessageRepository;
import java.util.Optional;

/**
 * Get last saved message
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class GetLastChatbotMessageUsecase {

    private final ChatbotMessageRepository chatbotMessageRepository;

    public GetLastChatbotMessageUsecase(ChatbotMessageRepository chatbotMessageRepository) {
        this.chatbotMessageRepository = chatbotMessageRepository;
    }

    public Optional<ChatbotMessage> execute() {

        Optional<ChatbotMessage> lastMessageOptional = chatbotMessageRepository.findLastMessage();
        return lastMessageOptional;
    }
}
