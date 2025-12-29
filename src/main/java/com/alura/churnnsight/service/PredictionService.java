package com.alura.churnnsight.service;

import com.alura.churnnsight.dto.PredictionRequest;
import com.alura.churnnsight.dto.PredictionResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictionService {

    private final RestTemplate restTemplate;

    private final String PYTHON_SERVICE_URL = "http://localhost:8000/predict";

    public PredictionService() {
        this.restTemplate = new RestTemplate();
    }

    public PredictionResponse obtenerPrediccion(PredictionRequest request) {
        try {

            return restTemplate.postForObject(PYTHON_SERVICE_URL, request, PredictionResponse.class);
        } catch (ResourceAccessException e) {

            throw new RuntimeException("Servicio de IA no disponible");
        }
    }
}