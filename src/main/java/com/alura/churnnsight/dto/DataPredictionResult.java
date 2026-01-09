package com.alura.churnnsight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataPredictionResult(
        @JsonProperty("CustomerId") String CustomerId,
        @JsonProperty("PredictedProba") Double PredictedProba, //
        @JsonProperty("PredictedLabel") Integer PredictedLabel, //
        @JsonProperty("CustomerSegment") String CustomerSegment,
        @JsonProperty("InterventionPriority") String InterventionPriority,
        @JsonProperty("AiInsight") String AiInsight
) {
        public DataPredictionResult(String CustomerId, Double PredictedProba, Integer PredictedLabel,
                                    String CustomerSegment, String InterventionPriority) {
                this(CustomerId, PredictedProba, PredictedLabel, CustomerSegment, InterventionPriority, null);
        }
}