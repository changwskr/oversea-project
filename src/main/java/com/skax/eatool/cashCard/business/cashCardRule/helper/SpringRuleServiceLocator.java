package com.skax.eatool.cashCard.business.cashCardRule.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skax.eatool.cashCard.business.cashCard.ICashCardSB;
import com.skax.eatool.common.business.facade.ICommonManagementSB;
import com.skax.eatool.reference.business.facade.IReferenceManagementSB;

/**
 * Spring Rule Service Locator for Cash Card Business Rules
 * 
 * Provides centralized access to business rule services
 * using Spring dependency injection.
 */
@Component
public class SpringRuleServiceLocator {

    private static final Logger logger = LoggerFactory.getLogger(SpringRuleServiceLocator.class);

    @Autowired
    private ICashCardSB cashCardService;

    @Autowired(required = false)
    private ICommonManagementSB commonManagementService;

    @Autowired
    private IReferenceManagementSB referenceManagementService;

    public ICashCardSB getCashCardService() {
        logger.info("==================[SpringRuleServiceLocator.getCashCardService START]");
        try {
            logger.info("==================[SpringRuleServiceLocator.getCashCardService END]");
            return cashCardService;
        } catch (Exception e) {
            logger.error("==================[SpringRuleServiceLocator.getCashCardService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    public ICommonManagementSB getCommonManagementService() {
        logger.info("==================[SpringRuleServiceLocator.getCommonManagementService START]");
        try {
            if (commonManagementService == null) {
                logger.warn("CommonManagementService is not available");
                return null;
            }
            logger.info("==================[SpringRuleServiceLocator.getCommonManagementService END]");
            return commonManagementService;
        } catch (Exception e) {
            logger.error("==================[SpringRuleServiceLocator.getCommonManagementService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    public IReferenceManagementSB getReferenceManagementService() {
        logger.info("==================[SpringRuleServiceLocator.getReferenceManagementService START]");
        try {
            logger.info("==================[SpringRuleServiceLocator.getReferenceManagementService END]");
            return referenceManagementService;
        } catch (Exception e) {
            logger.error("==================[SpringRuleServiceLocator.getReferenceManagementService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }
}

