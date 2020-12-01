package simple.chatbot.usecase.shared.user;

import org.springframework.stereotype.Service;
import simple.chatbot.dataprovider.postgres.entity.ChatbotUser;
import simple.chatbot.dataprovider.postgres.repository.ChatbotUserRepository;
import simple.chatbot.entity.LangUsedEnum;
import simple.chatbot.entity.UserTypeEnum;
import simple.chatbot.entity.response.MessengerUser;

/**
 * Save user entity to DB if absent
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class SaveChatbotUserIfAbsentUsecase {

    private final ChatbotUserRepository chatbotUserRepository;
    private final TryGetChatbotUserUsecase tryGetChatbotUserUsecase;

    public SaveChatbotUserIfAbsentUsecase(ChatbotUserRepository chatbotUserRepository, TryGetChatbotUserUsecase tryGetChatbotUserUsecase) {
        this.chatbotUserRepository = chatbotUserRepository;
        this.tryGetChatbotUserUsecase = tryGetChatbotUserUsecase;
    }

    public ChatbotUser execute(MessengerUser messageUser) {

        //Have already saved
        ChatbotUser userOptional = tryGetChatbotUserUsecase.execute(messageUser.getUserId());
        if (userOptional != null) {
            return userOptional;
        }

        UserTypeEnum userTypeCode;
        if (messageUser.getIsBot()) {
            userTypeCode = UserTypeEnum.BOT;
        } else {
            userTypeCode = UserTypeEnum.GUEST;
        }

        ChatbotUser user = new ChatbotUser(
                messageUser.getUserId(),
                messageUser.getUsername(),
                userTypeCode,
                LangUsedEnum.valueOf(messageUser.getLanguageCode().toUpperCase())
        );
        return chatbotUserRepository.save(user);
    }
}
