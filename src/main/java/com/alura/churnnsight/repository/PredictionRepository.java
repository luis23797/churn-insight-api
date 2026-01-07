package com.alura.churnnsight.repository;

import com.alura.churnnsight.dto.consult.DataPredictionDetail;
import com.alura.churnnsight.model.Prediction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface PredictionRepository extends JpaRepository<Prediction,Long> {
    Page<Prediction> findByCustomerId(Long customerId, Pageable pageable);
    Optional<Prediction> findByCustomerIdAndPredictionDate(Long customerId, LocalDate predictionDate);
}
