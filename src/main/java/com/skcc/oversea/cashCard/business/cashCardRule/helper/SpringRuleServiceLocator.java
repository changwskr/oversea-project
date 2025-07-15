package com.skcc.oversea.cashCard.business.cashCardRule.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skcc.oversea.common.business.facade.*;
import com.skcc.oversea.reference.business.facade.*;
import com.skcc.oversea.cashCard.business.cashCard.*;

/**
 * Spring-based service locator for Cash Card Rule module
 * Replaced EJB lookup pattern with Spring dependency injection
 */
@Component
public class SpringRuleServiceLocator {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringRuleServiceLocator.class);
    
    @Autowired(required = false)
    private ICashCardSB cashCardService;
    
    @Autowired(required = false)
    private ICommonManagementSB commonManagementService;
    
    @Autowired(required = false)
    private IReferenceManagementSB referenceManagementService;
    
    public ICashCardSB getCashCardService() {
        if (cashCardService == null) {
            logger.warn("CashCardService is not available");
            throw new RuntimeException("CashCardService is not configured");
        }
        return cashCardService;
    }
    
    public ICommonManagementSB getCommonManagementService() {
        if (commonManagementService == null) {
            logger.warn("CommonManagementService is not available");
            throw new RuntimeException("CommonManagementService is not configured");
        }
        return commonManagementService;
    }
    
    public IReferenceManagementSB getReferenceManagementService() {
        if (referenceManagementService == null) {
            logger.warn("ReferenceManagementService is not available");
            throw new RuntimeException("ReferenceManagementService is not configured");
        }
        return referenceManagementService;
    }
}

