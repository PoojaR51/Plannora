package com.plannora.user_service.logger.aspect;

import com.plannora.user_service.logger.client.LoggerClient;
import com.plannora.user_service.logger.DTO.LogEvent;
import com.plannora.user_service.security.RequestContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class UserLoggerAspect {

    private final LoggerClient loggerClient;

    /**
     * Logs all APIs inside user-service controllers
     */
    @Around("execution(* com.plannora.user_service.controller..*(..))")
    public Object logUserApis(ProceedingJoinPoint joinPoint) throws Throwable {

        Object response = null;
        Exception exception = null;

        try {
            response = joinPoint.proceed();
            return response;
        } catch (Exception ex) {
            exception = ex;
            throw ex;
        } finally {

            // 🔐 Safe access to RequestContext
            RequestContext context = RequestContext.get();

            LogEvent event = LogEvent.builder()
                    .serviceName("user-service")
                    .level(exception == null ? "INFO" : "ERROR")
                    .action(joinPoint.getSignature().getName())
                    .message(exception == null
                            ? "User API executed successfully"
                            : "User API execution failed")

                    .userId(context != null ? context.getUserId() : null)
                    .role(context != null ? context.getRole() : null)
                    .email(context != null ? context.getEmail() : null)

                    .method("HTTP")
                    .path(joinPoint.getSignature().toShortString())

                    .requestBody(joinPoint.getArgs())
                    .responseBody(response)
                    .exception(exception != null ? exception.getMessage() : null)
                    .timestamp(LocalDateTime.now())
                    .build();

            loggerClient.send(event);
        }
    }
}
