package com.skcc.oversea.foundation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import jakarta.annotation.PostConstruct;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for SKCC Oversea Foundation
 * 
 * Provides configuration management for the SKCC Oversea system
 * using Spring Boot configuration properties and external XML files.
 */
@Configuration
public class Config {

  private static final Logger logger = LoggerFactory.getLogger(Config.class);

  // Static instance for legacy compatibility
  private static Config instance;

  @Autowired
  private ResourceLoader resourceLoader;

  @Value("${skcc.oversea.config.xml.file:classpath:config/skcc-oversea.xml}")
  private String configXmlFile;

  private final Map<String, Document> xmlCache = new HashMap<>();
  private Document configDocument;

  /**
   * Legacy compatibility method for static access
   */
  public static Config getInstance() {
    if (instance == null) {
      logger.warn("Config instance not initialized via Spring, creating default instance");
      instance = new Config();
      instance.loadDefaultConfig();
    }
    return instance;
  }

  /**
   * Set static instance (called by Spring)
   */
  private void setStaticInstance() {
    Config.instance = this;
  }

  /**
   * Initialize configuration
   */
  @PostConstruct
  public void init() {
    loadXmlConfig();
    setStaticInstance(); // Set static instance for legacy compatibility
    logger.info("SKCC Oversea Configuration initialized");
  }

  /**
   * Load default configuration when not using Spring
   */
  private void loadDefaultConfig() {
    try {
      // Try to load from classpath
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config/skcc-oversea.xml");
      if (inputStream != null) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        configDocument = builder.parse(inputStream);
        configDocument.getDocumentElement().normalize();
        inputStream.close();
        logger.info("Default XML Configuration loaded");
      } else {
        logger.warn("Default XML Configuration file not found");
        configDocument = createDefaultDocument();
      }
    } catch (Exception e) {
      logger.error("Failed to load default XML configuration: {}", e.getMessage());
      configDocument = createDefaultDocument();
    }
  }

  /**
   * Create a default document with basic structure
   */
  private Document createDefaultDocument() {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.newDocument();

      Element root = doc.createElement("config");
      doc.appendChild(root);

      // Add transaction-log-service element
      Element transactionLog = doc.createElement("transaction-log-service");
      Element fileLog = doc.createElement("file-log");
      fileLog.setTextContent("/tmp/transaction.log");
      transactionLog.appendChild(fileLog);
      root.appendChild(transactionLog);

      return doc;
    } catch (Exception e) {
      logger.error("Failed to create default document", e);
      return null;
    }
  }

  /**
   * Load XML configuration file
   */
  private void loadXmlConfig() {
    try {
      Resource resource = resourceLoader.getResource(configXmlFile);
      if (resource.exists()) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        try (InputStream inputStream = resource.getInputStream()) {
          configDocument = builder.parse(inputStream);
          configDocument.getDocumentElement().normalize();
        }
        logger.info("XML Configuration loaded from: {}", configXmlFile);
      } else {
        logger.warn("XML Configuration file not found: {}", configXmlFile);
        configDocument = createDefaultDocument();
      }
    } catch (Exception e) {
      logger.error("Failed to load XML configuration: {}", e.getMessage());
      configDocument = createDefaultDocument();
    }
  }

  /**
   * Get configuration value by service name and element name
   */
  public String getValue(String serviceName, String elementName) {
    if (configDocument == null) {
      logger.warn("Configuration document not loaded");
      return null;
    }

    try {
      Element rootElement = configDocument.getDocumentElement();
      NodeList serviceNodes = rootElement.getElementsByTagName(serviceName);

      if (serviceNodes.getLength() > 0) {
        Element serviceElement = (Element) serviceNodes.item(0);
        NodeList elementNodes = serviceElement.getElementsByTagName(elementName);

        if (elementNodes.getLength() > 0) {
          return elementNodes.item(0).getTextContent().trim();
        }
      }

      logger.debug("Configuration value not found: {}.{}", serviceName, elementName);
      return null;
    } catch (Exception e) {
      logger.error("Error getting configuration value: {}.{}", serviceName, elementName, e);
      return null;
    }
  }

  /**
   * Get configuration element by service name (returns wrapper for legacy
   * compatibility)
   */
  public ConfigElement getElement(String serviceName) {
    if (configDocument == null) {
      logger.warn("Configuration document not loaded");
      return new ConfigElement(null);
    }

    try {
      Element rootElement = configDocument.getDocumentElement();
      NodeList serviceNodes = rootElement.getElementsByTagName(serviceName);

      if (serviceNodes.getLength() > 0) {
        return new ConfigElement((Element) serviceNodes.item(0));
      }

      logger.debug("Configuration element not found: {}", serviceName);
      return new ConfigElement(null);
    } catch (Exception e) {
      logger.error("Error getting configuration element: {}", serviceName, e);
      return new ConfigElement(null);
    }
  }

  /**
   * Get configuration document
   */
  public Document getConfigDocument() {
    return configDocument;
  }

  /**
   * Check if configuration is loaded
   */
  public boolean isConfigLoaded() {
    return configDocument != null;
  }

  /**
   * Wrapper class for configuration elements to provide legacy getChild() method
   */
  public static class ConfigElement {
    private final Element element;

    public ConfigElement(Element element) {
      this.element = element;
    }

    public ConfigElement getChild(String childName) {
      if (element == null) {
        return new ConfigElement(null);
      }

      NodeList children = element.getElementsByTagName(childName);
      if (children.getLength() > 0) {
        return new ConfigElement((Element) children.item(0));
      }
      return new ConfigElement(null);
    }

    public String getText() {
      if (element == null) {
        return "disable"; // Default value for compatibility
      }
      return element.getTextContent().trim();
    }

    public Element getElement() {
      return element;
    }
  }
}
