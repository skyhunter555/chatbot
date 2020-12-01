package simple.chatbot.usecase;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import simple.chatbot.dataprovider.TelegramProvider;
import simple.chatbot.entity.request.SendMessageRequest;
import simple.chatbot.entity.response.MessageReceived;
import simple.chatbot.entity.response.MessengerChat;
import simple.chatbot.entity.response.MessengerUser;
import simple.chatbot.entity.response.SendMessageResponse;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ProcessingUsecaseTest {

    @Configuration
    class Config {

        @Value("${chatbot.telegram.url}")
        private String chatbotTelegramUrl = "127.0.0.1";

        @Value("${chatbot.telegram.token}")
        private String chatbotTelegramToken = "";

        @Value("${chatbot.telegram.chatId}")
        private Long chatbotTelegramChatId = 0L;

        @Bean
        public TelegramProvider telegramProvider() {
            return new TelegramProvider();
        }
    }

    @Autowired
    private TelegramProvider telegramProvider;

    @Test
    public void sendTelegramMessageTest() {

        SendMessageRequest request = new SendMessageRequest(
                new MessageReceived(
                       0,
                        "test",
                        new Date(),
                        new MessengerChat("393050350", "test"),
                        new MessengerUser(1, false, "test", "RU")
                ),
                "test",
                false);

        SendMessageResponse response = telegramProvider.sendMessage(request);
        Assert.assertTrue(response.getResultText().equals("Ok"));
    }

}
