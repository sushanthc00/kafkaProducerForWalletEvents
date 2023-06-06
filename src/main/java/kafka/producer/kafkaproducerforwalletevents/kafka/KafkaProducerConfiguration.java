package kafka.producer.kafkaproducerforwalletevents.kafka;

import kafka.producer.kafkaproducerforwalletevents.model.TypeWltSafeTxn;
import kafka.producer.kafkaproducerforwalletevents.model.WalletTxnEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@Configuration
public class KafkaProducerConfiguration {

    String bootstrapServers = "10.179.185.64:9092,10.179.185.65:9092";
    @Bean
    public ProducerFactory<String, TypeWltSafeTxn> safeProducerFactory(){
        var properties = new HashMap<String, Object>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.RETRIES_CONFIG, 5);
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public ProducerFactory<String, WalletTxnEvent> walletProducerFactory(){
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.RETRIES_CONFIG, 5);
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 300);

        return new DefaultKafkaProducerFactory<>(props);
    }
    @Bean
    public KafkaTemplate<String, TypeWltSafeTxn> safeKafkaTemplate(){
        return new KafkaTemplate<>(safeProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, WalletTxnEvent> walletKafkaTemplate(){
        return new KafkaTemplate<>(walletProducerFactory());
    }

    @Bean
    public NewTopic safeEvents() {
        return TopicBuilder.name("safeEvents")
                           .partitions(3)
                           .replicas(3)
                           .build();
      }

    @Bean
    public NewTopic walletTxnEvents() {
        return TopicBuilder.name("walletEvents")
                .partitions(2)
                .replicas(2)
                .build();
    }
}
