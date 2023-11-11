package kafkajavalistener.controller;

import kafkajavalistener.model.PaymentEvent;
import kafkajavalistener.model.TransactionEvent;
import kafkajavalistener.streams.producer.PaymentProducer;
import kafkajavalistener.streams.producer.TransactionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private TransactionProducer transactionProducer;

    @Autowired
    private PaymentProducer paymentProducer;

    @PostMapping("/transaction")
    ResponseEntity<String> produceTransaction(@RequestParam(required = false) String id, @RequestParam(required = false) Integer amount) {
        var event = new TransactionEvent(
                id != null ? id : UUID.randomUUID().toString(),
                new PaymentEvent(amount != null ? amount : 100, "BRL"),
                LocalDateTime.now().toString()
        );

        transactionProducer.produceTransactionEvent(event);
        return ResponseEntity.ok(event.getTransId());
    }

    @PostMapping("/payment")
    ResponseEntity<Integer> producePayment(@RequestParam(required = false) String currency, @RequestParam(required = false) Integer amount) {
        var event = new PaymentEvent(
                Objects.nonNull(amount) ? amount : 100, Objects.nonNull(currency) ? currency : "BRL"
        );

        paymentProducer.producePaymentEvent(event);
        return ResponseEntity.ok(event.getAmount());
    }

}
