package producer;

import model.Address;
import model.Customers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaDataProducer {

    private String id;
    private String firstName;
    private String lastName;
    private final int min = 20;
    private final int max = 100;
    private int age;
    private String street;
    private int houseNumber;
    private String city;
    private String state;
    private final String PATH = "/Users/rubensbarbosa/Developer/kafka-batch-processing/KafkaConnector/src/main/resources/customers.csv";
    Random random = new Random();
    ArrayList<String> products = new ArrayList<>(
            Arrays.asList("Macbook Air", "Macbook Pro", "iPhone 14", "Apple Watch")
    );

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

    public void startSendMessage() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            int _key = 0;
            String row;
            while ((row = reader.readLine()) != null) {
                String[] customerData = row.split(",");

                id = customerData[0];
                firstName = customerData[1];
                lastName = customerData[2];
                age = (int)Math.floor(Math.random() * (max - min+1) + min);
                street = customerData[3];
                houseNumber = random.nextInt(max);
                city = customerData[4];
                state = customerData[5];

                Collections.shuffle(products);
                int n = random.nextInt(products.size()) + 1;
                ArrayList<String> customerProducts = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    customerProducts.add(products.get(i));
                }

                Customers customers = new Customers(id, firstName, lastName, age, new Address(street, houseNumber, city, state), customerProducts);
                ProducerRecord<String, Customers> record = new ProducerRecord<>(this.topic, Integer.toString(_key++), customers); // Send customer object as a message to Kafka
                producer.send(record);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            producer.flush();
            producer.close();
        }
    }

}
