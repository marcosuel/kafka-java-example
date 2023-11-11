package kafkajavalistener.streams.producer;

import kafkajavalistener.model.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentProducer {

    @Value("${kafka.topics.payment}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void producePaymentEvent(PaymentEvent event){
        logger.info("method=produceTransactionEvent; Amount={};", event.getAmount());
        kafkaTemplate.send(topic, event);
    }

}
