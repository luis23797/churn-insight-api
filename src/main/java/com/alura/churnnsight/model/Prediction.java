package com.alura.churnnsight.model;

import com.alura.churnnsight.dto.DataPredictionResult;
import com.alura.churnnsight.model.enumeration.InterventionPriority;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity(name = "Prediction")
@Table(name = "prediction")
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "predicted_proba")
    private double predictedProba;
    @Column(name = "predicted_label")
    private int predictedLabel;

    @Enumerated(EnumType.STRING)
    @Column(name = "intervention_priority")
    private InterventionPriority interventionPriority;

    @Column(name = "customer_segment")
    private String customerSegment;

    @Column(name = "prediction_date", nullable = false)
    private LocalDate predictionDate;

    @Column(name = "predicted_at")
    @CreationTimestamp
    private LocalDateTime predictedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "ai_insight", columnDefinition = "TEXT")
    private String aiInsight;

    public Prediction(DataPredictionResult response, Customer customer) {
        this.predictedProba = (response.PredictedProba() != null) ? response.PredictedProba() : 0.0;
        this.predictedLabel = (response.PredictedLabel() != null) ? response.PredictedLabel() : 0;
        this.customerSegment = response.CustomerSegment();
        this.predictionDate = LocalDate.now();
        this.customer = customer;
        this.interventionPriority = (response.InterventionPriority() != null) ?
                InterventionPriority.fromString(response.InterventionPriority()) :
                InterventionPriority.LOW;
    }
}
