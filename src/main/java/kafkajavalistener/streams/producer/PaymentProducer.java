package kafkajavalistener.streams.producer;

import kafkajavalistener.model.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class PaymentProducer implements IProducer<PaymentEvent> {
    @Value("${kafka.topics.payment}")
    private String topic;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Override
    public void produceEvent(PaymentEvent event) {
        log.info("method=produceTransactionEvent; Amount={};", event.getAmount());
        kafkaTemplate.send(topic, event);
    }
}
