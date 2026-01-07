package com.alura.churnnsight.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public record TransaccionIn(
        @JsonProperty("TransactionId") String transactionId,
        @JsonProperty("CustomerId") String customerId,
        @JsonProperty("TransactionDate") LocalDateTime transactionDate,
        @JsonProperty("Amount") Float amount,
        @JsonProperty("TransactionType") String transactionType
) {
}
