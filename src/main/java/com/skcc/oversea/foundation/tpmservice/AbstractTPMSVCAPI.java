package com.skcc.oversea.foundation.tpmservice;

/**
 * <p>Title: Spring-based Abstract TPM Service API</p>
 * <p>Description: Converted from EJB to Spring Framework</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 2.0
 */
import java.io.*;
import java.util.*;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skcc.oversea.framework.transfer.*;
import com.skcc.oversea.eplatonframework.transfer.*;
import com.skcc.oversea.foundation.config.Config;

public abstract class AbstractTPMSVCAPI
{
  protected static final Logger logger = LoggerFactory.getLogger(AbstractTPMSVCAPI.class);
  
  @Autowired
  protected ApplicationContext applicationContext;
  
  @Autowired
  protected Config config;
  
  @Value("${tpmservice.url:#{null}}")
  protected String serviceUrl;
  
  @Value("${tpmservice.timeout:30}")
  protected int serviceTimeout;
  
  protected EPlatonCommonDTO cosescommon;
  protected EPlatonEvent event;
  protected IEvent result;

  public AbstractTPMSVCAPI()
  {
    // Spring-based initialization
  }

  /**
   * Spring-based service lookup
   * @param serviceName Service name to lookup
   * @return Service instance
   */
  protected Object getService(String serviceName) {
    try {
      return applicationContext.getBean(serviceName);
    } catch (Exception e) {
      logger.error("Failed to get service: {}", serviceName, e);
      return null;
    }
  }

  /**
   * Spring-based service lookup by type
   * @param serviceClass Service class type
   * @return Service instance
   */
  protected <T> T getService(Class<T> serviceClass) {
    try {
      return applicationContext.getBean(serviceClass);
    } catch (Exception e) {
      logger.error("Failed to get service of type: {}", serviceClass.getName(), e);
      return null;
      }
    }

  /**
   * Get configuration property
   * @param key Property key
   * @param defaultValue Default value if property not found
   * @return Property value
   */
  protected String getProperty(String key, String defaultValue) {
      try {
      // Spring Environment를 통해 프로퍼티 값 가져오기
      return applicationContext.getEnvironment().getProperty(key, defaultValue);
    } catch (Exception e) {
      logger.warn("Failed to get property: {}, using default: {}", key, defaultValue);
      return defaultValue;
    }
  }
  
  /**
   * Get XML configuration value
   * @param serviceName Service name
   * @param elementName Element name
   * @return Configuration value
   */
  protected String getConfigValue(String serviceName, String elementName) {
    try {
      return config.getValue(serviceName, elementName);
    } catch (Exception e) {
      logger.warn("Failed to get config value: {}.{}", serviceName, elementName);
      return null;
    }
  }

}

