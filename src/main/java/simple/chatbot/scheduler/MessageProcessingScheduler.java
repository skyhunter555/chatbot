package simple.chatbot.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import simple.chatbot.usecase.MessageProcessingUsecase;

import java.util.logging.Logger;

/**
 * Run message processing usecase every 10-20 sec
 *
 * @date 01.12.2020
 * @author Skyhunter
 */
@Component
public class MessageProcessingScheduler {

    private final static Logger LOG = Logger.getLogger(MessageProcessingScheduler.class.getName());

    private final MessageProcessingUsecase processor;

    public MessageProcessingScheduler(MessageProcessingUsecase processor) {
        this.processor = processor;
    }

    @Scheduled(cron = "${chatbot.schedule.check-updates}")
    public void runMessageProcessing() {
        try {
            processor.execute();
        } catch (Exception ex) {
            String errorMessage = String.format("Unknown error processing: %s", ex.getMessage());
            LOG.warning(errorMessage);
        }
    }

}
