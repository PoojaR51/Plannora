package com.plannora.booking_service.logger.client;

import com.plannora.booking_service.logger.DTO.LogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class LoggerClient {

    private final RestTemplate restTemplate;

    private static final String LOGGER_URL =
            "http://localhost:5141/api/logs";

    public void send(LogEvent event) {
        try {
            restTemplate.postForEntity(LOGGER_URL, event, Void.class);
        } catch (Exception ignored) {
            // logging must NEVER break user-service
        }
    }
}
