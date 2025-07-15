package com.skcc.oversea.cashCard.business.facade.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skcc.oversea.cashCard.business.cashCardRule.*;
import com.skcc.oversea.deposit.business.facade.*;
import com.skcc.oversea.reference.business.facade.*;

/**
 * Spring-based service locator for Cash Card module
 * Replaced EJB lookup pattern with Spring dependency injection
 */
@Component
public class SpringServiceLocator {
    
    private static final Logger logger = LoggerFactory.getLogger(SpringServiceLocator.class);
    
    @Autowired(required = false)
    private ICashCardRuleSB cashCardRuleService;
    
    @Autowired(required = false)
    private IAccountManagement accountManagementService;
    
    @Autowired(required = false)
    private IReferenceManagementSB referenceManagementService;
    
    public ICashCardRuleSB getCashCardRuleService() {
        if (cashCardRuleService == null) {
            logger.warn("CashCardRuleService is not available");
            throw new RuntimeException("CashCardRuleService is not configured");
        }
        return cashCardRuleService;
    }
    
    public IAccountManagement getAccountManagementService() {
        if (accountManagementService == null) {
            logger.warn("AccountManagementService is not available");
            throw new RuntimeException("AccountManagementService is not configured");
        }
        return accountManagementService;
    }
    
    public IReferenceManagementSB getReferenceManagementService() {
        if (referenceManagementService == null) {
            logger.warn("ReferenceManagementService is not available");
            throw new RuntimeException("ReferenceManagementService is not configured");
        }
        return referenceManagementService;
    }
}

