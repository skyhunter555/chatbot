package simple.chatbot.usecase.shared.message;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotMessage;
import simple.chatbot.dataprovider.postgres.repository.ChatbotMessageRepository;
import java.util.Optional;

/**
 * Try to get message entity from DB
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class TryGetChatbotMessageUsecase {

    private final ChatbotMessageRepository chatbotMessageRepository;

    public TryGetChatbotMessageUsecase(ChatbotMessageRepository chatbotMessageRepository) {
        this.chatbotMessageRepository = chatbotMessageRepository;
    }

    public ChatbotMessage execute(Integer messageId, String messageChatId) {
        Optional<ChatbotMessage> messageOptional = chatbotMessageRepository.findByMessageIdAndMessageChatId(messageId, messageChatId);
        return messageOptional.orElse(null);
    }
}
