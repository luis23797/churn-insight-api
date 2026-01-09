package com.alura.churnnsight.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class LlmClient {

    private final WebClient webClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${llm.api.key}")
    private String apiKey;

    public LlmClient(WebClient.Builder webClientBuilder,
                     @Value("${llm.api.url}") String apiUrl) {
        this.webClient = webClientBuilder
                .baseUrl(apiUrl)
                .build();
    }

    public String generateInsight(String prompt) {
        try {
            var requestBody = Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(
                                    Map.of("text", prompt)
                            ))
                    )
            );

            return webClient.post()
                    .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .exchangeToMono(resp -> logRawThenExtract(resp))
                    .block();

        } catch (Exception e) {
            System.out.println("LLM ERROR: " + e.getMessage());
            return "No se pudo generar el análisis en este momento.";
        }
    }

    private Mono<String> logRawThenExtract(ClientResponse response) {
        return response.bodyToMono(String.class)
                .defaultIfEmpty("")
                .flatMap(raw -> {
                    System.out.println("RAW GEMINI status=" + response.statusCode() + " body=" + raw);

                    if (response.statusCode().isError()) {
                        return Mono.just("No se pudo generar el análisis en este momento.");
                    }

                    try {
                        JsonNode root = mapper.readTree(raw);

                        // Si viene error en body
                        if (root.has("error")) {
                            System.out.println("GEMINI BODY ERROR: " + root.get("error").toString());
                            return Mono.just("No se pudo generar el análisis en este momento.");
                        }

                        // candidates[0].content.parts[0].text
                        JsonNode textNode = root.path("candidates")
                                .path(0)
                                .path("content")
                                .path("parts")
                                .path(0)
                                .path("text");

                        String text = textNode.isMissingNode() ? null : textNode.asText(null);
                        if (text == null || text.isBlank()) {
                            return Mono.just("No se pudo generar el análisis en este momento.");
                        }

                        return Mono.just(text);

                    } catch (Exception ex) {
                        System.out.println("GEMINI PARSE ERROR: " + ex.getMessage());
                        return Mono.just("No se pudo generar el análisis en este momento.");
                    }
                });
    }
}
