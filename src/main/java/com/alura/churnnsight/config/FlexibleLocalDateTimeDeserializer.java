package com.alura.churnnsight.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class FlexibleLocalDateTimeDeserializer
        extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser p,
                                     DeserializationContext ctxt)
            throws IOException {

        String value = p.getText();

        // IMPORTANTE: null o vacío
        if (value == null || value.isBlank()) {
            return null;
        }

        value = value.trim();

        // Caso: YYYY-MM-DD
        if (value.length() == 10) {
            return LocalDate.parse(value).atStartOfDay();
        }

        // Caso: ISO con zona (Z o +00:00)
        if (value.endsWith("Z") || value.contains("+")) {
            return OffsetDateTime.parse(value).toLocalDateTime();
        }

        // Caso: ISO normal
        return LocalDateTime.parse(value);
    }
}
