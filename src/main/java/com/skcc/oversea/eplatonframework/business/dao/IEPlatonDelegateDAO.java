package com.skcc.oversea.eplatonframework.business.dao;

import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;

/**
 * EPlaton Delegate DAO Interface for SKCC Oversea
 * 
 * Defines contract for EPlaton delegate database operations
 * specialized for transaction logging and delegate operations.
 */
public interface IEPlatonDelegateDAO {
    
    /**
     * Insert transaction in log
     */
    boolean DB_INSERTinlog(EPlatonEvent event);
    
    /**
     * Insert transaction out log
     */
    boolean DB_INSERToutlog(EPlatonEvent event);
    
    /**
     * Query for business date
     */
    String queryForBusinessDate(String bankCode);
    
    /**
     * Get business date
     */
    String GetBizDate();
    
    /**
     * Check if logging is enabled
     */
    boolean isLogabled();
}