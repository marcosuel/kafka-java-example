package kafkajavalistener.streams.producer;

import kafkajavalistener.model.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer implements IProducer<PaymentEvent> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${kafka.topics.payment}")
    private String topic;
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void produceEvent(PaymentEvent event) {
        logger.info("method=produceTransactionEvent; Amount={};", event.getAmount());
        kafkaTemplate.send(topic, event);
    }
}
