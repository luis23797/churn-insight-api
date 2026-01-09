package com.alura.churnnsight.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.alura.churnnsight.config.FlexibleLocalDateTimeDeserializer;
import java.time.LocalDateTime;

public record TransaccionIn(
        @JsonProperty("TransactionId") String transactionId,
        @JsonProperty("CustomerId") String customerId,
        @JsonDeserialize(using = FlexibleLocalDateTimeDeserializer.class)
        @JsonProperty("TransactionDate") LocalDateTime transactionDate,
        @JsonProperty("Amount") Float amount,
        @JsonProperty("TransactionType") String transactionType
) {
}
