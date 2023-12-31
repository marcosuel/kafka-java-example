package kafkajavalistener.controller;

import kafkajavalistener.model.PaymentEvent;
import kafkajavalistener.model.TransactionEvent;
import kafkajavalistener.streams.producer.PaymentProducer;
import kafkajavalistener.streams.producer.TransactionProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {
    private final TransactionProducer transactionProducer;
    private final PaymentProducer paymentProducer;

    @PostMapping("/transaction")
    ResponseEntity<String> produceTransaction(@RequestParam(required = false) String id, @RequestParam(required = false) Integer amount) {
        var event = new TransactionEvent(
                id != null ? id : UUID.randomUUID().toString(),
                new PaymentEvent(amount != null ? amount : 100, "BRL"),
                LocalDateTime.now().toString()
        );

        transactionProducer.produceEvent(event);
        return ResponseEntity.ok(event.getTransId());
    }

    @PostMapping("/payment")
    ResponseEntity<Integer> producePayment(@RequestParam(required = false) String currency, @RequestParam(required = false) Integer amount) {
        var event = new PaymentEvent(
                Objects.nonNull(amount) ? amount : 100, Objects.nonNull(currency) ? currency : "BRL"
        );

        paymentProducer.produceEvent(event);
        return ResponseEntity.ok(event.getAmount());
    }

}
