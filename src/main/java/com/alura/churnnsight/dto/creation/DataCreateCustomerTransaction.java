package com.alura.churnnsight.dto.creation;

import java.time.LocalDateTime;

public record DataCreateCustomerTransaction(
        String transactionId,
        String customerId,
        LocalDateTime transactionDate,
        Double amount,
        String transactionType
) {}
