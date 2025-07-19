package com.skax.eatool;

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
 * SKAX AI TOOL Application
 *
 * Main Spring Boot application class for the SKAX AI TOOL system.
 * This application has been migrated from legacy J2EE/EJB architecture to
 * Spring Boot.
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
        "com.skax.eatool",
        "com.skax.eatool.controller",
        "com.skax.eatool.service",
        "com.skax.eatool.config",
        "com.skax.eatool.foundation",
        "com.skax.eatool.eplatonframework"
})
@EntityScan(basePackages = {
        "com.skax.eatool.user.entity",
        "com.skax.eatool.user.infrastructure.jpa",
        "com.skax.eatool.user.service.port.file.infrastructure.jpa",
        "com.skax.eatool.cashCard.business.cashCard.entity",
        "com.skax.eatool.deposit.entity",
        "com.skax.eatool.common.entity",
        "com.skax.eatool.teller.entity",
        "com.skax.eatool.eplatonframework.business.entity",
        "com.skax.eatool.techspec.infrastructure.jpa"
})
@EnableJpaRepositories(basePackages = {
        "com.skax.eatool.user.infrastructure.jpa",
        "com.skax.eatool.user.service.port.file.infrastructure.jpa",
        "com.skax.eatool.cashCard.repository",
        "com.skax.eatool.deposit.repository",
        "com.skax.eatool.common.repository",
        "com.skax.eatool.teller.repository",
        "com.skax.eatool.eplatonframework.business.repository",
        "com.skax.eatool.techspec.infrastructure.jpa"
})
@EnableTransactionManagement
@EnableAsync
@EnableScheduling
public class OverseaApplication {

    private static final Logger logger = LoggerFactory.getLogger(OverseaApplication.class);

    public static void main(String[] args) {
        logger.info("==================[OverseaApplication.main START]");
        try {
            logger.info("Starting SKAX AI TOOL System...");
            logger.info("System Version: 1.0.0");
            logger.info("Spring Boot Version: 3.x");
            logger.info("Java Version: {}", System.getProperty("java.version"));

            SpringApplication.run(OverseaApplication.class, args);

            logger.info("SKAX AI TOOL System started successfully!");
            logger.info("==================[OverseaApplication.main END]");
        } catch (Exception e) {
            logger.error("==================[OverseaApplication.main ERROR] - {}", e.getMessage(), e);
            System.exit(1);
        }
    }
}
