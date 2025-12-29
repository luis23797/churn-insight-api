package com.alura.churnnsight.controller;

import com.alura.churnnsight.dto.PredictionRequest;
import com.alura.churnnsight.dto.PredictionResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
public class PredictionController {

    @PostMapping
    public ResponseEntity<PredictionResponse> predictChurn(@RequestBody @Valid PredictionRequest request) {


        PredictionResponse mockResponse = new PredictionResponse(
                request.clientes().CustomerId(),
                0.85,
                1,
                "Alto Riesgo",
                "Inmediata"
        );

        return ResponseEntity.ok(mockResponse);
    }
}