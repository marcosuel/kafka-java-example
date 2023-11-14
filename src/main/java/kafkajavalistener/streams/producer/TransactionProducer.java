package kafkajavalistener.streams.producer;

import kafkajavalistener.model.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class TransactionProducer implements IProducer<TransactionEvent> {
    @Value("${kafka.topics.transaction}")
    private String topic;
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    @Override
    public void produceEvent(TransactionEvent event) {
        log.info("method=produceTransactionEvent; transactionId={};", event.getTransId());
        kafkaTemplate.send(topic, event);
    }
}

