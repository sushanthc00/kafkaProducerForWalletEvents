package kafka.producer.kafkaproducerforwalletevents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerForWalletEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerForWalletEventsApplication.class, args);
	}

}
