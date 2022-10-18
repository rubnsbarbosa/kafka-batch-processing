package consumer;

public class ConsumerApp {
    private static final String TOPIC = "sky-topic";
    private static final String KAFKA_BOOTSTRAP_SERVERS = "localhost:9092";

    public static void main(String[] args) {
        KafkaDataConsumer consumer = new KafkaDataConsumer(TOPIC, KAFKA_BOOTSTRAP_SERVERS);
    }

}
