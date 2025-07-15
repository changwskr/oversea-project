package com.skcc.oversea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SKCC Oversea Application
 * 
 * Main Spring Boot application class for the SKCC Oversea banking system.
 * This application provides cash card management, deposit services, and other
 * banking operations migrated from legacy J2EE/EJB architecture.
 * 
 * @author SKCC Development Team
 * @version 1.0
 */
@SpringBootApplication
@EntityScan(basePackages = "com.skcc.oversea")
@EnableJpaRepositories(basePackages = {
        "com.skcc.oversea.cashCard.repository",
        "com.skcc.oversea.deposit.repository",
        "com.skcc.oversea.common.repository",
        "com.skcc.oversea.teller.repository",
        "com.skcc.oversea.user.repository",
        "com.skcc.oversea.eplatonframework.business.repository"
})
@EnableJpaAuditing
@EnableTransactionManagement
public class OverseaApplication {

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("Starting SKCC Oversea Application...");
        System.out.println("==========================================");

        try {
            SpringApplication.run(OverseaApplication.class, args);

            System.out.println("==========================================");
            System.out.println("SKCC Oversea Application started successfully!");
            System.out.println("==========================================");

        } catch (Exception e) {
            System.err.println("==========================================");
            System.err.println("Failed to start SKCC Oversea Application!");
            System.err.println("Error: " + e.getMessage());
            System.err.println("==========================================");
            e.printStackTrace();
            System.exit(1);
        }
    }
}