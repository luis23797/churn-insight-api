package com.alura.churnnsight.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record SesionIn(
        @JsonProperty("SessionId") String sessionId,
        @JsonProperty("CustomerId") String customerId,
        @JsonProperty("SessionDate") LocalDateTime sessionDate,
        @JsonProperty("DurationMin") Float durationMin,
        @JsonProperty("UsedTransfer") Integer usedTransfer,
        @JsonProperty("UsedPayment") Integer usedPayment,
        @JsonProperty("UsedInvest") Integer usedInvest,
        @JsonProperty("OpenedPush") Integer openedPush,
        @JsonProperty("FailedLogin") Integer failedLogin
) {
}
