package simple.chatbot.usecase.shared.chat;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotChat;
import simple.chatbot.dataprovider.postgres.repository.ChatbotChatRepository;
import simple.chatbot.entity.response.MessengerChat;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Save chat entity to DB if absent
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class SaveChatbotChatIfAbsentUsecase {

    private final ChatbotChatRepository chatbotChatRepository;
    private final TryGetChatbotChatUsecase tryGetChatbotChatUsecase;

    public SaveChatbotChatIfAbsentUsecase(ChatbotChatRepository chatbotChatRepository, TryGetChatbotChatUsecase tryGetChatbotChatUsecase) {
        this.chatbotChatRepository = chatbotChatRepository;
        this.tryGetChatbotChatUsecase = tryGetChatbotChatUsecase;
    }

    public ChatbotChat execute(MessengerChat messengerChat) {

        //Have already saved
        ChatbotChat chatOptional = tryGetChatbotChatUsecase.execute(messengerChat.getChatId());
        if (chatOptional != null) {
            return chatOptional;
        }
        Date now = new Date();
        ChatbotChat chat = new ChatbotChat(
                messengerChat.getChatId(),
                messengerChat.getChatName(),
                new Timestamp(now.getTime()),
                true
        );
        return chatbotChatRepository.save(chat);
    }
}
