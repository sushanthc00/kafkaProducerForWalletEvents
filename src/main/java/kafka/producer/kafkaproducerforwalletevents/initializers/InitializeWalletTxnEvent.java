package kafka.producer.kafkaproducerforwalletevents.initializers;

import kafka.producer.kafkaproducerforwalletevents.model.WalletTxnEvent;

import java.sql.Timestamp;
import java.util.Random;

public class InitializeWalletTxnEvent {

    public WalletTxnEvent initializeWalletObject(long id, String username, long sesssionId, Timestamp createdTs){

        Random random = new Random();

        WalletTxnEvent walletTxnEvent = new WalletTxnEvent();

        walletTxnEvent.setId(id);
        walletTxnEvent.setAccountName(username);
        walletTxnEvent.setSessionId(sesssionId);
        walletTxnEvent.setCreatedTs(createdTs);

        return walletTxnEvent;
    }
}
