package com.alura.churnnsight.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record PredictionRequest(
        @Valid @NotNull CustomerDTO clientes,
        @Valid @NotNull TransactionDTO transacciones,
        @Valid @NotNull SessionDTO sesiones
) {}
