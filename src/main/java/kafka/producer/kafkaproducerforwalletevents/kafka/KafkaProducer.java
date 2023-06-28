package kafka.producer.kafkaproducerforwalletevents.kafka;

import kafka.producer.kafkaproducerforwalletevents.initializers.InitializeTypeWltSafeTxn;
import kafka.producer.kafkaproducerforwalletevents.initializers.InitializeWalletTxnEvent;
import kafka.producer.kafkaproducerforwalletevents.initializers.SimpleMapInitializer;
import kafka.producer.kafkaproducerforwalletevents.model.TypeWltSafeTxn;
import kafka.producer.kafkaproducerforwalletevents.model.WalletTxnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

@Component
@Slf4j
public class KafkaProducer {

    int i = 0;

    @Autowired
    private KafkaTemplate<String, TypeWltSafeTxn> safeKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, WalletTxnEvent> walletKafkaTemplate;

    private final LinkedBlockingQueue<TypeWltSafeTxn> queue = new LinkedBlockingQueue<>();

    SimpleMapInitializer smp = new SimpleMapInitializer();
    ArrayList<String> playerIds = smp.getPlayersList();

    Timestamp createdTimestamp = smp.getCreatedTs();

    @Scheduled(fixedRate = 1000)
    public void sendSafeEventsToKafka() {
        ++i;

        String userName = playerIds.get((int)(Math.random()*1000+1));
        long sessionId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        // String strSessionId = Long.toString(sessionId);
        long id = (int)(Math.random()*1350000+2111);

        InitializeTypeWltSafeTxn safeInitializer = new InitializeTypeWltSafeTxn();
        var safeEvent = safeInitializer.initializeSafeTxnObject(userName, sessionId, createdTimestamp);

        safeKafkaTemplate.send("safeEvents", safeEvent);
        log.info("Sent safeEvent : {}", safeEvent.toString());

        // Add the safeEvent to the queue for later processing
        queue.offer(safeEvent);
    }

    @Scheduled(initialDelay = 40000, fixedRate = 1250)
    public void sendWalletEventsToKafka() {
        // Retrieve the safeEvent from the queue
        TypeWltSafeTxn safeEvent = queue.poll();

        if (safeEvent != null) {
            String userName = safeEvent.getAccountName();
            long SessionId = safeEvent.getSessionId();
            long id = (int)(Math.random()*1350000+2111);

            InitializeWalletTxnEvent walletInitializer = new InitializeWalletTxnEvent();
            ++i;

            var walletEvent = walletInitializer.initializeWalletObject(id, userName, SessionId, createdTimestamp);

            walletKafkaTemplate.send("walletEvents", walletEvent);
            log.info("Sent WalletEvent : {}", walletEvent.toString());
        }
    }
}
