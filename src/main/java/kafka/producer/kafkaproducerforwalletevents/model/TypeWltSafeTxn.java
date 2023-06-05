package kafka.producer.kafkaproducerforwalletevents.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeWltSafeTxn {

    private String accountName;
    private String requestorRefId;
    private long wltRefId;
    private String sessionId;
    private long startBalance;
    private long amountUsed;
    private long endBalance;
    private String type;
    private String currency;
    private String productId;
    private String brandId;
    private String gameType;
    private Date createdDate;
    private String operation;
    private long txnType;
    private long fsSeqId;
    private long fsId;
}
