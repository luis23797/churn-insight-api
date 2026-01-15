package com.alura.churnnsight.dto.integration;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataIntegrationRequest(
        @Valid
        @NotNull(message = "cliente es requerido")
        ClienteIn cliente,
        List<TransaccionIn> transacciones,
        List<SesionIn> sesiones
) {
}
