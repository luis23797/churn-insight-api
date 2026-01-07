package com.alura.churnnsight.dto.creation;

import java.time.LocalDateTime;

public record DataCreateCustomerSession(
        String sessionId,
        String customerId,
        LocalDateTime sessionDate,
        Double durationMin,
        Integer usedTransfer,
        Integer usedPayment,
        Integer usedInvest,
        Integer openedPush,
        Integer failedLogin
) {}
