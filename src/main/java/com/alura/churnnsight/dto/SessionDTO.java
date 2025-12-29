package com.alura.churnnsight.dto;


import jakarta.validation.constraints.*;

public record SessionDTO(
        @NotBlank String SessionId,
        @NotBlank String CustomerId,
        @NotBlank String SessionDate,
        @PositiveOrZero Double DurationMin,
        @NotNull Integer UsedTransfer,
        @NotNull Integer UsedPayment,
        @NotNull Integer UsedInvest,
        @NotNull Integer UsedPush,
        @NotNull Integer FailedLogin
) {}