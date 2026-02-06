package com.plannora.booking_service.logger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class LoggerConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
