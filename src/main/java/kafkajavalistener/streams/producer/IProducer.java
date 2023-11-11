package kafkajavalistener.streams.producer;

public interface IProducer<T> {
    void produceEvent(T event);
}
