package simple.chatbot.usecase;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import simple.chatbot.entity.*;
import simple.chatbot.entity.reaction.EmptyReaction;
import simple.chatbot.entity.reaction.IChatbotReaction;
import simple.chatbot.entity.request.SendMessageRequest;
import simple.chatbot.entity.response.MessageReceived;
import simple.chatbot.util.MessageChecker;

/**
 * Generate reaction on message
 *
 * @author Skyhunter
 * @date 01.12.2020
 */
@Service
public class GetReactionUsecase {

    private final MessageChecker messageChecker;
    private final MessageSource messagesSource;

    public GetReactionUsecase( MessageChecker messageChecker, MessageSource messagesSource) {
        this.messageChecker = messageChecker;
        this.messagesSource = messagesSource;
    }

    public IChatbotReaction execute(MessageReceived message) {

        String messageResponseText = null;
        Boolean isReplayToMessage = false;

        //We can response only on used lang
        if (!LangUsedEnum.contains(message.getAuthor().getLanguageCode())) {
            return new EmptyReaction(message);
        }

        LangUsedEnum lang = LangUsedEnum.valueOf(message.getAuthor().getLanguageCode().toUpperCase());

        if (messageChecker.checkIsQuestion(message.getMessageText())) {

            PhraseTypeEnum responsePhraseType = getResponsePhraseType(message.getMessageText(), lang);
            messageResponseText = getResponseMessage(responsePhraseType.getMessageCode());
            if (PhraseTypeEnum.QUESTION.getMessageCode().equals(responsePhraseType.getMessageCode())) {
                isReplayToMessage = true;
            }

        } else if (isHello(message.getMessageText(), lang)) {
            messageResponseText = String.format(
                    getResponseMessage(PhraseTypeEnum.HELLO.getMessageCode()),
                    message.getAuthor().getUsername()
            );
        } else {
            return new EmptyReaction(message);
        }

        return new SendMessageRequest(
                message,
                messageResponseText,
                isReplayToMessage
        );
    }

    private PhraseTypeEnum getResponsePhraseType(String messageText, LangUsedEnum lang) {
        if (messageChecker.checkIsPhraseType(PhraseTypeEnum.AUTHOR, messageText, lang)) {
            return PhraseTypeEnum.AUTHOR;
        } else if (messageChecker.checkIsPhraseType(PhraseTypeEnum.NAME, messageText, lang)) {
            return PhraseTypeEnum.NAME;
        } else if (messageChecker.checkIsPhraseType(PhraseTypeEnum.TASK_LIST, messageText, lang)) {
            return PhraseTypeEnum.TASK_LIST;
        }
        return PhraseTypeEnum.QUESTION;
    }

    private Boolean isHello(String messageText, LangUsedEnum lang) {
        return messageChecker.checkIsPhraseType(PhraseTypeEnum.HELLO, messageText, lang);
    }

    private String getResponseMessage(String messageCode) {
        return messagesSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
    }
}
