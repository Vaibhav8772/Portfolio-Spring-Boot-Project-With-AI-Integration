package com.portfolio.vaibhav.repository;

import com.portfolio.vaibhav.entities.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class AiRepository {

    @Value("${openrouter.api.key}")
    private String openRouterApiKey;

    @Value("${openrouter.api.url}")
    private String openRouterUrl;

    public Prompt givePrompt(Prompt userPrompt) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.set("Authorization", "Bearer " + openRouterApiKey);
            headers.set("HTTP-Referer", "http://localhost:8080"); // Change to your domain if hosted

            // Build request body
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "mistralai/mistral-7b-instruct");

            List<Map<String, String>> messages = new ArrayList<>();
            messages.add(Map.of("role", "user", "content", userPrompt.getPrompt()));
            requestBody.put("messages", messages);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    openRouterUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String aiResponse = (String) message.get("content");

            Prompt responsePrompt = new Prompt();
            responsePrompt.setPrompt(userPrompt.getPrompt());
            responsePrompt.setResponse(aiResponse);
            return responsePrompt;

        } catch (Exception e) {
            Prompt errorPrompt = new Prompt();
            errorPrompt.setPrompt(userPrompt.getPrompt());
            errorPrompt.setResponse("OpenRouter API call failed: " + e.getMessage());
            return errorPrompt;
        }
    }
}
