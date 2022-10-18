package consumer;

import model.Customers;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class KafkaDataConsumer {
    private final KafkaConsumer<String, Customers> consumer;
    private final String topic;

    public KafkaDataConsumer(String topic, String server) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // it'll read EVERYTHING from kafka topic
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // controls the number of records returned from poll - controls the batch processing
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");

        consumer = new KafkaConsumer<>(properties);
        this.topic = topic;
    }

    // ToDo startGetMessage

}
