package simple.chatbot.util;

import io.netty.util.internal.StringUtil;
import simple.chatbot.dataprovider.telegram.response.ChatApiModel;
import simple.chatbot.dataprovider.telegram.response.MessageApiModel;
import simple.chatbot.dataprovider.telegram.response.UserApiModel;
import simple.chatbot.entity.response.MessageReceived;
import simple.chatbot.entity.response.MessengerChat;
import simple.chatbot.entity.response.MessengerUser;
import java.sql.Timestamp;

/**
 * Convert api models to entity
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
public class TelegramConverter {

    public static MessageReceived messageFromApi(MessageApiModel messageApi) {

        return new MessageReceived(
                messageApi.getMessageId(),
                messageApi.getText(),
                new Timestamp(messageApi.getDate() * 1000L),
                getChatFromApi(messageApi.getChat()),
                getUserFromApi(messageApi.getFrom())
        );
    }

    private static MessengerChat getChatFromApi(ChatApiModel chat) {
        String chatName = "unknown";
        if (!StringUtil.isNullOrEmpty(chat.getTitle())) {
            chatName = chat.getTitle();
        } else if (!StringUtil.isNullOrEmpty(chat.getUsername())) {
            chatName = chat.getUsername();
        }
        return new MessengerChat(
            chat.getChatId(),
            chatName
        );
    }

    private static MessengerUser getUserFromApi(UserApiModel user) {
        String userName = "unknown";
        if (!StringUtil.isNullOrEmpty(user.getUserName())) {
            userName = user.getUserName();
        } else if (!StringUtil.isNullOrEmpty(user.getFirstName())) {
            userName = user.getFirstName();
        }
        return new MessengerUser(
                user.getUserId(),
                user.getIsBot(),
                userName,
                user.getLanguageCode()
        );
    }
}
