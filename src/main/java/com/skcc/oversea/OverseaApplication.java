package com.skcc.oversea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SKCC Oversea Application
 * 
 * Main Spring Boot application class for the SKCC Oversea banking system.
 * This application has been migrated from legacy J2EE/EJB architecture to Spring Boot.
 * 
 * Features:
 * - Cash Card Management
 * - Deposit Services
 * - Common Services
 * - Teller Management
 * - User Management
 * - Transaction Logging
 * 
 * @author SKCC Development Team
 * @version 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {
    "com.skcc.oversea",
    "com.skcc.oversea.controller",
    "com.skcc.oversea.service",
    "com.skcc.oversea.config",
    "com.skcc.oversea.foundation",
    "com.skcc.oversea.eplatonframework"
})
@EntityScan(basePackages = {
    "com.skcc.oversea.cashCard.business.cashCard.entity",
    "com.skcc.oversea.deposit.entity",
    "com.skcc.oversea.common.entity",
    "com.skcc.oversea.teller.entity",
    "com.skcc.oversea.user.entity",
    "com.skcc.oversea.eplatonframework.business.entity"
})
@EnableJpaRepositories(basePackages = {
    "com.skcc.oversea.cashCard.repository",
    "com.skcc.oversea.deposit.repository",
    "com.skcc.oversea.common.repository",
    "com.skcc.oversea.teller.repository",
    "com.skcc.oversea.user.repository",
    "com.skcc.oversea.eplatonframework.business.repository"
})
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class OverseaApplication {

    private static final Logger logger = LoggerFactory.getLogger(OverseaApplication.class);

    public static void main(String[] args) {
        logger.info("==================[OverseaApplication.main START]");
        try {
            logger.info("Starting SKCC Oversea Banking System...");
            logger.info("System Version: 1.0.0");
            logger.info("Spring Boot Version: 3.x");
            logger.info("Java Version: {}", System.getProperty("java.version"));
            
            SpringApplication.run(OverseaApplication.class, args);
            
            logger.info("SKCC Oversea Banking System started successfully!");
            logger.info("==================[OverseaApplication.main END]");
        } catch (Exception e) {
            logger.error("==================[OverseaApplication.main ERROR] - {}", e.getMessage(), e);
            System.exit(1);
        }
    }
} 