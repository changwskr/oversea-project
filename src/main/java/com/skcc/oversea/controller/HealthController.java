package com.skcc.oversea.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Health Check Controller
 * 
 * Provides basic health check endpoints to verify the application status.
 */
@RestController
@RequestMapping("/api/health")
@Tag(name = "Health Check", description = "Health check and application information endpoints")
public class HealthController {

    private static final Logger logger = LoggerFactory.getLogger(HealthController.class);

    @GetMapping
    @Operation(
        summary = "Health Check",
        description = "Returns the current health status of the SKCC Oversea application"
    )
    public Map<String, Object> health() {
        logger.info("==================[HealthController.health START]");
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "UP");
            response.put("timestamp", LocalDateTime.now());
            response.put("application", "SKCC Oversea");
            response.put("version", "1.0.0");
            response.put("message", "SKCC Oversea Application is running successfully");
            logger.info("==================[HealthController.health END]");
            return response;
        } catch (Exception e) {
            logger.error("==================[HealthController.health ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/info")
    @Operation(
        summary = "Application Information",
        description = "Returns detailed information about the SKCC Oversea application"
    )
    public Map<String, Object> info() {
        logger.info("==================[HealthController.info START]");
        try {
            Map<String, Object> info = new HashMap<>();
            info.put("application", "SKCC Oversea Banking System");
            info.put("description", "Legacy J2EE/EJB banking system migrated to Spring Boot");
            info.put("version", "1.0.0");
            info.put("startTime", LocalDateTime.now());
            info.put("modules", new String[]{
                "Cash Card Management",
                "Deposit Services", 
                "Common Services",
                "Teller Management",
                "User Management",
                "Transaction Logging"
            });
            logger.info("==================[HealthController.info END]");
            return info;
        } catch (Exception e) {
            logger.error("==================[HealthController.info ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }
} 