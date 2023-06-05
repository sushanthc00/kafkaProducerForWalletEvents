package kafka.producer.kafkaproducerforwalletevents.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WalletTxnEvent {

    private long id;
    private String requestortxnId;
    private boolean isCancelled;
    private String accountName;
    private String operation;
    private long sessionId;
    private String description;
    private long txntype;
    private long adjustmentTxntype;
    private String status;
    private String fxMarkupType;
    private long snapshotid;
    private String ip;
    private String event;
    private String feId;
    private String brandId;
    private String productId;
    private String subProductId;
    private String channelId;
    private String closeSession;
    private boolean isGameTxn;
    private String agentName;
    private String subWltPriority;
    private String sourceSubwallet;
    private String destinationWlt;
    private String destinationWltCurrency;
    private String bonusType;
    private long gameDataRefId;
    private Timestamp createdTs;
    private Timestamp lastUpdatedTs;
    private String state;
    private String wltBatchTxnId;
    private long subtxntype;
    private boolean isInternalCancel;
    private String invokerProductId;
    private String loginSessionId;
}
