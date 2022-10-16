package producer;

import model.Customers;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaDataProducer {

    private final KafkaProducer<String, Customers> producer;
    private final String topic;

    public KafkaDataProducer(String topic, String server) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "20"); // it will wait a few milliseconds for the batches to fill up before sending them
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024)); // is the maximum number of bytes that will be included in a batch
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    // ToDo startSendMessage

}
