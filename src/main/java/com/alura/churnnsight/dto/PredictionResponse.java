package com.alura.churnnsight.dto;


public record PredictionResponse(
        String CustomerId,
        Double PredictedProba,
        Integer PredictedLabel,
        String CustomerSegment,
        String InterventionPriority
) {}
