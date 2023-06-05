package kafka.producer.kafkaproducerforwalletevents.initializers;

import kafka.producer.kafkaproducerforwalletevents.model.WalletTxnEvent;

import java.util.Random;

public class InitializeWalletTxnEvent {

    WalletTxnEvent initializeWalletObject(long id, String username, long sesssionId){

        Random random = new Random();

        WalletTxnEvent walletTxnEvent = new WalletTxnEvent();

        walletTxnEvent.setId(id);
        walletTxnEvent.setAccountName(username);
        walletTxnEvent.setSessionId(sesssionId);

        return walletTxnEvent;
    }
}
