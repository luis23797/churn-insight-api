package com.alura.churnnsight.dto.integration;

import java.util.List;

public record DataIntegrationRequest(
        ClienteIn cliente,
        List<TransaccionIn> transacciones,
        List<SesionIn> sesiones
) {
}
