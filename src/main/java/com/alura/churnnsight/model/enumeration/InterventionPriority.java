package com.alura.churnnsight.model.enumeration;

import java.text.Normalizer;

public enum InterventionPriority {
    HIGH,
    MEDIUM,
    LOW;

    public static InterventionPriority fromString(String value) {
        if (value == null || value.isBlank()) return LOW;


        String norm = Normalizer.normalize(value, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .trim()
                .toUpperCase();


        if (norm.startsWith("ALTA")) return HIGH;
        if (norm.startsWith("MEDIA")) return MEDIUM;
        if (norm.startsWith("BAJA")) return LOW;

        // Acepta inglés (por si el modelo cambia)
        if (norm.equals("HIGH")) return HIGH;
        if (norm.equals("MEDIUM")) return MEDIUM;
        if (norm.equals("LOW")) return LOW;


        return LOW;

    }


}
