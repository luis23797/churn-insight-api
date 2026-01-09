package com.alura.churnnsight.model.enumeration;

import java.util.Arrays;

public enum InterventionPriority {
    HIGH,
    MEDIUM,
    LOW;

    public static InterventionPriority fromString(String value) {
        return Arrays.stream(values())
                .filter(p -> p.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid priority: " + value)
                );
    }
    public static InterventionPriority fromDataLabel(String value) {
        if (value == null) return MEDIUM;

        String v = value.trim().toLowerCase();

        // basta con detectar la palabra clave al inicio
        if (v.startsWith("baja")) return LOW;
        if (v.startsWith("media")) return MEDIUM;

        // "alta" o "crítico" => HIGH
        if (v.startsWith("alta") || v.startsWith("crítico") || v.startsWith("critico")) return HIGH;

        // fallback
        return MEDIUM;
    }
}
