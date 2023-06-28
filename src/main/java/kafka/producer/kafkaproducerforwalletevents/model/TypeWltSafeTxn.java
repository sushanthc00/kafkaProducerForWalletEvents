package kafka.producer.kafkaproducerforwalletevents.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeWltSafeTxn {

    private String accountName;
    private String requestorRefId;
    private long wltRefId;
    private long sessionId;
    private long startBalance;
    private long amountUsed;
    private long endBalance;
    private String type;
    private String currency;
    private String productId;
    private String brandId;
    private String gameType;
    private Timestamp createdDate;
    private String operation;
    private long txnType;
    private long fsSeqId;
    private long fsId;
}
