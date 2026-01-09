package com.alura.churnnsight.client;

import com.alura.churnnsight.config.FastApiProperties;
import com.alura.churnnsight.dto.DataMakePrediction;
import com.alura.churnnsight.dto.DataPredictionResult;
import com.alura.churnnsight.dto.integration.DataIntegrationRequest;
import com.alura.churnnsight.dto.integration.DataIntegrationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class FastApiClient {

    private final WebClient webClient;
    private final FastApiProperties props;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FastApiClient(WebClient fastApiClient, FastApiProperties props) {
        this.webClient = fastApiClient;
        this.props = props;
    }

    public Mono<DataPredictionResult> predict(DataMakePrediction features) {
        return webClient.post()
                .uri(props.getPredictPath())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(features)
                .exchangeToMono(resp -> logRawThenMap(resp, DataPredictionResult.class, "predict"))
                .timeout(Duration.ofSeconds(props.getTimeoutSeconds()));
    }

    public Mono<DataIntegrationResponse> predictIntegration(DataIntegrationRequest request) {
        return webClient.post()
                .uri(props.getPredictPath())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchangeToMono(resp -> logRawThenMap(resp, DataIntegrationResponse.class, "integration"))
                .timeout(Duration.ofSeconds(props.getTimeoutSeconds()));
    }

    private <T> Mono<T> logRawThenMap(ClientResponse response, Class<T> clazz, String tag) {
        return response.bodyToMono(String.class)
                .defaultIfEmpty("")
                .flatMap(raw -> {
                    System.out.println("RAW FASTAPI RESPONSE (" + tag + ") status=" + response.statusCode() + " body=" + raw);


                    if (response.statusCode().isError()) {
                        return Mono.<T>error(new IllegalStateException(
                                "FastAPI devolvió error HTTP " + response.statusCode() + " body=" + raw
                        ));
                    }


                    if (raw.contains("\"error\"")) {
                        return Mono.<T>error(new IllegalStateException(
                                "FastAPI devolvió error en body (200 OK): " + raw
                        ));
                    }


                    try {
                        T mapped = objectMapper.readValue(raw, clazz);
                        return Mono.just(mapped);
                    } catch (JsonProcessingException e) {
                        return Mono.<T>error(new IllegalStateException(
                                "No se pudo parsear JSON de FastAPI a " + clazz.getSimpleName() + ": " + raw, e
                        ));
                    }
                });
    }
}
