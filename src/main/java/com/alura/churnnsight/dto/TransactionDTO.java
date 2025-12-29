package com.alura.churnnsight.dto;

import jakarta.validation.constraints.*;

public record TransactionDTO(
        @NotBlank String TransactionId,
        @NotBlank String CustomerId,
        @NotBlank String TransactionDate,
        @Positive Double Amount,
        @NotBlank String TransactionType
) {}
