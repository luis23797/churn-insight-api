package com.alura.churnnsight.dto.consult;

import com.alura.churnnsight.model.Prediction;
import com.alura.churnnsight.model.enumeration.InterventionPriority;

import java.time.LocalDateTime;

public record DataPredictionDetail(
        Float predictedProba,
        Integer predictedLabel,
        String customerSegment,
        InterventionPriority interventionPriority,
        LocalDateTime predictedAt
) {
    public DataPredictionDetail(Prediction prediction){
        this(
                (float) prediction.getPredictedProba(),
                prediction.getPredictedLabel(),
                prediction.getCustomerSegment(),
                prediction.getInterventionPriority(),
                prediction.getPredictedAt()
                );
    }
}
