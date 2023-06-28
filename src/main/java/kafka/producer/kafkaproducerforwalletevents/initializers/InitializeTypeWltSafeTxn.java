package kafka.producer.kafkaproducerforwalletevents.initializers;

import kafka.producer.kafkaproducerforwalletevents.model.TypeWltSafeTxn;

import java.sql.Timestamp;
import java.util.Random;

public class InitializeTypeWltSafeTxn {

    public TypeWltSafeTxn initializeSafeTxnObject(String username, long sessionId, Timestamp createdDate){
        Random random = new Random();

        TypeWltSafeTxn typeWltSafeTxn = new TypeWltSafeTxn();

        typeWltSafeTxn.setAccountName(username);
        typeWltSafeTxn.setWltRefId((int)(Math.random()*900000001+187675));
        typeWltSafeTxn.setSessionId(sessionId);
        typeWltSafeTxn.setCurrency("EUR");
        typeWltSafeTxn.setGameType("Casino");
        typeWltSafeTxn.setStartBalance((int)(Math.random()*5765454+10012));
        typeWltSafeTxn.setCreatedDate(createdDate);

        return typeWltSafeTxn;
    }
}
