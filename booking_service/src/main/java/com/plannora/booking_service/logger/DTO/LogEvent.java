package com.plannora.booking_service.logger.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LogEvent {

    private String serviceName;   // user-service
    private String level;         // INFO / ERROR
    private String action;        // LOGIN, REGISTER, UPDATE_PROFILE
    private String message;

    private Long userId;
    private String role;
    private String email;

    private String method;
    private String path;

    private Object requestBody;
    private Object responseBody;

    private String exception;

    private LocalDateTime timestamp;
}
