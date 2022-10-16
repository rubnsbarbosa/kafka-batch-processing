package producer;

public class ProducerApp {
    private static final String TOPIC = "sky-topic";
    private static final String KAFKA_BOOTSTRAP_SERVER = "localhost:9092";

    public static void main(String[] args) {
        KafkaDataProducer producer = new KafkaDataProducer(TOPIC, KAFKA_BOOTSTRAP_SERVER);
    }

}