package com.alura.churnnsight.dto.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DataIntegrationResponse(
        @JsonProperty("PredictedProba") Double PredictedProba,
        @JsonProperty("PredictedLabel") Integer PredictedLabel,
        @JsonProperty("CustomerSegment") String CustomerSegment,
        @JsonProperty("InterventionPriority") String InterventionPriority
) {}