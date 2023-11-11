package kafkajavalistener.streams.producer;

import kafkajavalistener.model.TransactionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TransactionProducer {

    @Value("${kafka.topics.transaction}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void produceTransactionEvent(TransactionEvent event){
        logger.info("method=produceTransactionEvent; transactionId={};", event.getTransId());
        kafkaTemplate.send(topic, event);
    }

}
