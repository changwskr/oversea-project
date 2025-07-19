package com.skax.eatool.foundation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Configuration class for SKCC Oversea Foundation
 * 
 * Provides configuration management for the SKCC Oversea system
 * using Spring Boot configuration properties and external files.
 */
@Configuration
public class SkccConfig {

    private static final Logger logger = LoggerFactory.getLogger(SkccConfig.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("${skcc.oversea.config.file:classpath:config/skcc-oversea.properties}")
    private String configFile;

    @Value("${skcc.oversea.config.xml.file:classpath:config/oversea-config.xml}")
    private String xmlConfigFile;

    @Value("${skcc.oversea.machine.mode:DEV}")
    private String machineMode;

    @Value("${skcc.oversea.environment:development}")
    private String environment;

    private final Map<String, Object> configCache = new HashMap<>();
    private Properties properties;

    /**
     * Initialize configuration
     */
    @PostConstruct
    public void init() {
        logger.info("==================[SkccConfig.init START]");
        try {
            // Override config file paths to use correct files
            this.configFile = "classpath:config/skcc-oversea.properties";
            this.xmlConfigFile = "classpath:config/oversea-config.xml";

            loadProperties();
            logger.info("SKCC Oversea Configuration initialized - Mode: {}, Environment: {}, Config: {}, XML: {}",
                    machineMode, environment, configFile, xmlConfigFile);
            logger.info("==================[SkccConfig.init END]");
        } catch (Exception e) {
            logger.error("==================[SkccConfig.init ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Load properties from configuration file
     */
    private void loadProperties() {
        logger.info("==================[SkccConfig.loadProperties START]");
        try {
            Resource resource = resourceLoader.getResource(configFile);
            if (resource.exists()) {
                properties = new Properties();
                try (InputStream inputStream = resource.getInputStream()) {
                    properties.load(inputStream);
                }
                logger.info("Configuration loaded from: {}", configFile);
            } else {
                logger.warn("Configuration file not found: {}", configFile);
                properties = new Properties();
            }
            logger.info("==================[SkccConfig.loadProperties END]");
        } catch (IOException e) {
            logger.error("==================[SkccConfig.loadProperties ERROR] - {}", e.getMessage(), e);
            properties = new Properties();
        }
    }

    /**
     * Get configuration value by key
     */
    public String getConfigValue(String key) {
        logger.info("==================[SkccConfig.getConfigValue START] - 키: {}", key);
        try {
            String result = getConfigValue(key, null);
            logger.info("==================[SkccConfig.getConfigValue END] - 키: {}", key);
            return result;
        } catch (Exception e) {
            logger.error("==================[SkccConfig.getConfigValue ERROR] - 키: {}, 오류: {}", key, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get configuration value by key with default value
     */
    public String getConfigValue(String key, String defaultValue) {
        logger.info("==================[SkccConfig.getConfigValue START] - 키: {}, 기본값: {}", key, defaultValue);
        try {
            // First check cache
            if (configCache.containsKey(key)) {
                String result = (String) configCache.get(key);
                logger.info("==================[SkccConfig.getConfigValue END] - 키: {} (캐시에서 조회)", key);
                return result;
            }

            // Then check properties file
            String value = properties.getProperty(key, defaultValue);

            // Cache the result
            configCache.put(key, value);

            logger.info("==================[SkccConfig.getConfigValue END] - 키: {}", key);
            return value;
        } catch (Exception e) {
            logger.error("==================[SkccConfig.getConfigValue ERROR] - 키: {}, 오류: {}", key, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Get machine mode
     */
    public String getMachineMode() {
        return machineMode;
    }

    /**
     * Get environment
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * Check if running in development mode
     */
    public boolean isDevelopment() {
        return "DEV".equalsIgnoreCase(machineMode) || "development".equalsIgnoreCase(environment);
    }

    /**
     * Check if running in production mode
     */
    public boolean isProduction() {
        return "PROD".equalsIgnoreCase(machineMode) || "production".equalsIgnoreCase(environment);
    }

    /**
     * Get all properties
     */
    public Properties getProperties() {
        return new Properties(properties);
    }
}
