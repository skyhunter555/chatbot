package simple.chatbot.dataprovider;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import simple.chatbot.dataprovider.telegram.request.SendMessageRequestApiModel;
import simple.chatbot.dataprovider.telegram.response.GetUpdatesResponseApiModel;
import simple.chatbot.dataprovider.telegram.response.MessageApiModel;
import simple.chatbot.dataprovider.telegram.response.SendResponseApiModel;
import simple.chatbot.dataprovider.telegram.response.UpdateResultApiModel;
import simple.chatbot.dataprovider.telegram.request.GetUpdatesRequestApiModel;
import simple.chatbot.entity.reaction.IChatbotSendReaction;
import simple.chatbot.entity.request.UpdateMessageRequest;
import simple.chatbot.entity.response.MessageReceived;
import simple.chatbot.entity.response.SendMessageResponse;
import simple.chatbot.entity.response.UpdateMessageResponse;
import simple.chatbot.exceptions.ChatbotException;
import simple.chatbot.util.TelegramConverter;
import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 *  Telegram provider
 *  https://core.telegram.org/bots/api
 *  Last update November 4, 2020
 *  Introducing Bot API 5.0
 *
 *  Implemented methods:
 *  sendMessage
 *  getUpdates
 *  https://core.telegram.org/bots/api#getting-updates
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Service
public class TelegramProvider {

    @Value("${chatbot.telegram.url}")
    private String telegramUrl = "default";

    @Value("${chatbot.telegram.token}")
    private String telegramToken = "default";

    private WebClient webClient;

    private final static Logger LOG = Logger.getLogger(TelegramProvider.class.getName());

    private final static String REQUEST_GET_UPDATES = "/getUpdates";
    private final static String REQUEST_SEND_MESSAGE = "/sendMessage";

    @PostConstruct
    public void postConstruct() {

        Duration connectTimeout = Duration.ofSeconds(30);
        Duration soTimeout = Duration.ofSeconds(30);

        //Setup all timeouts
        TcpClient timeoutClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(connectTimeout.toMillis()))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(soTimeout.toMillis(), TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(soTimeout.toMillis(), TimeUnit.MILLISECONDS)));

        webClient = WebClient.builder()
                .baseUrl(String.format("%sbot%s", telegramUrl, telegramToken))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(timeoutClient)))
                .build();
    }

    /**
     * sendMessage Use this method to send text messages.
     * On success, the sent Message is returned.
     * @param request
     * @return
     */
    public SendMessageResponse sendMessage(IChatbotSendReaction request) throws ChatbotException {

        ClientResponse clientResponse = sendPostRequest(
            REQUEST_SEND_MESSAGE,
            new SendMessageRequestApiModel(
                request.getChatId(),
                request.getMessageText(),
                request.getReplyToMessageId()
            )
        );

        SendResponseApiModel response = clientResponse.bodyToMono(SendResponseApiModel.class).block();

        if (response == null || !response.getOk()) {
            throw new ChatbotException(String.format("Bad response from Telegram on %s: not Ok!", REQUEST_SEND_MESSAGE));
        }

        return new SendMessageResponse(
                response.getResult().getMessageId(),
                "Ok"
              );
    }

    /**
     * For receive use getUpdates.
     * Incoming updates are stored on the server until the bot receives them either way, but they will not be kept longer than 24 hours.
     * @param request
     * @return
     */
    public UpdateMessageResponse getUpdates(UpdateMessageRequest request) throws ChatbotException {

        ClientResponse clientResponse = sendPostRequest(
            REQUEST_GET_UPDATES,
            new GetUpdatesRequestApiModel(
                request.getOffset()
            )
        );

        GetUpdatesResponseApiModel response = clientResponse.bodyToMono(GetUpdatesResponseApiModel.class).block();

        if (response == null || !response.getOk()) {
            throw new ChatbotException(String.format("Bad response from Telegram on %s: not Ok!", REQUEST_GET_UPDATES));
        }

        List<MessageReceived> messageList = new ArrayList<>();
        Integer lastUpdateId = 0;
        for (UpdateResultApiModel result: response.getResult()) {
            MessageApiModel resultMessage = result.getMessage();
            if (result.getUpdateId() != null) {
                lastUpdateId = result.getUpdateId();
            }
            if (resultMessage != null) {
                messageList.add(TelegramConverter.messageFromApi(resultMessage));
            }
        }

        return new UpdateMessageResponse(lastUpdateId,"Ok", messageList);
    }

    private ClientResponse sendPostRequest(String requestName, Object requestApiModel) throws ChatbotException {
        try {

            ClientResponse clientResponse = webClient.post()
                    .uri(requestName)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromObject(requestApiModel))
                    .exchange().block();

            if (clientResponse == null) {
                throw new ChatbotException(String.format("Bad response from Telegram on %s: clientResponse is null!", requestName));
            }
            if (clientResponse.statusCode() != HttpStatus.OK) {
                String responseWarnResult = clientResponse.bodyToMono(String.class).block();
                throw new ChatbotException(String.format("Bad response from Telegram on %s: %s", requestName, responseWarnResult));
            }

            return clientResponse;

        } catch (Exception ex) {
            throw new ChatbotException(String.format("Error read send response from Telegram on %s: %s", requestName, ex.getMessage()));
        }

    }

}
