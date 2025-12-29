package com.alura.churnnsight.controller;

import com.alura.churnnsight.dto.PredictionRequest;
import com.alura.churnnsight.dto.PredictionResponse;
import com.alura.churnnsight.service.PredictionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping
    public ResponseEntity<PredictionResponse> predictChurn(@RequestBody @Valid PredictionRequest request) {
        // Llamamos al servicio que se comunica con Python
        PredictionResponse response = predictionService.obtenerPrediccion(request);
        return ResponseEntity.ok(response);
    }
}