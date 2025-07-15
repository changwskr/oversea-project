package com.skcc.oversea.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger Configuration
 * 
 * Configures OpenAPI documentation for the SKCC Oversea application.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SKCC Oversea API")
                        .description("SKCC Oversea Banking System API Documentation\n\n" +
                                "This API provides banking services including:\n" +
                                "- Cash Card Management\n" +
                                "- Deposit Services\n" +
                                "- Common Services\n" +
                                "- Teller Management\n" +
                                "- User Management\n" +
                                "- Transaction Logging\n\n" +
                                "This system has been migrated from legacy J2EE/EJB architecture to Spring Boot.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("SKCC Development Team")
                                .email("dev@skcc.com")
                                .url("https://www.skcc.com"))
                        .license(new License()
                                .name("SKCC Internal License")
                                .url("https://www.skcc.com/license")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server"),
                        new Server()
                                .url("https://api.skcc.com")
                                .description("Production Server")
                ));
    }
} 