package kafkajavalistener.streams.producer;

import kafkajavalistener.model.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProducer implements IProducer<TransactionEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${kafka.topics.transaction}")
    private String topic;
    private KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    public TransactionProducer(KafkaTemplate<String, TransactionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void produceEvent(TransactionEvent event) {
        logger.info("method=produceTransactionEvent; transactionId={};", event.getTransId());
        kafkaTemplate.send(topic, event);
    }
}
