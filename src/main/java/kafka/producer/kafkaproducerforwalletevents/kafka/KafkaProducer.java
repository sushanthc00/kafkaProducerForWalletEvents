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

import java.util.ArrayList;
import java.util.UUID;

@Component
@Slf4j
public class KafkaProducer {

    int i =0;

    @Autowired
    private KafkaTemplate<String, TypeWltSafeTxn> safeKafkaTemplate;

    @Autowired
    private KafkaTemplate<String , WalletTxnEvent> walletKafkaTemplate;

    @Scheduled(fixedRate = 3000)
    public void sendEventsToKafka(){
        InitializeTypeWltSafeTxn safeInitializer = new InitializeTypeWltSafeTxn();
        InitializeWalletTxnEvent walletInitializer = new InitializeWalletTxnEvent();
        SimpleMapInitializer smp = new SimpleMapInitializer();

        ArrayList<String> playerIds = smp.getPlayersList();
        String userName = playerIds.get((int)(Math.random()*1000+1));
        ++i;
        long sessionId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        String strSessionId = Long.toString(sessionId);
        long id = (int)(Math.random()*1350000+2111);

        var safeEvent = safeInitializer.initializeSafeTxnObject(userName,strSessionId);
        var walletEvent = walletInitializer.initializeWalletObject(id, userName, sessionId);

        safeKafkaTemplate.send("safeEvents", safeEvent);
        log.info("Sent SafeEvent : {}", safeEvent.toString());

        walletKafkaTemplate.send("walletEvents", walletEvent);
        log.info("Sent WalletEvent : {}", walletEvent.toString());
    }

}
