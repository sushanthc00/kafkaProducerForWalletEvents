package kafka.producer.kafkaproducerforwalletevents.initializers;

import java.sql.Timestamp;
import java.util.ArrayList;


public class SimpleMapInitializer {

    public ArrayList<String> getPlayersList(){
        ArrayList<String> players = new ArrayList<>();
        for (int i =1; i <=1000; i++){
            players.add("bz_bwin" + i);
        }
        return players;
    }

    public Timestamp getCreatedTs(){
        return new Timestamp(System.currentTimeMillis());
    }
}
