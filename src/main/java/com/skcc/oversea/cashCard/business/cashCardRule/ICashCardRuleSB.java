package com.skcc.oversea.cashCard.business.cashCardRule;

/**
 * Cash Card Rule Service Interface
 * 
 * Interface for cash card business rule operations
 */
public interface ICashCardRuleSB {
    
    /**
     * Get system parameter
     * 
     * @param parameterId parameter identifier
     * @return system parameter value
     */
    String getSystemParameter(String parameterId);
    
    /**
     * Modify cash card rule
     * 
     * @param ruleId rule identifier
     * @return modification result
     */
    String modifyCashCardRule(String ruleId);
    
    /**
     * Delete cash card rule
     * 
     * @param ruleId rule identifier
     * @return deletion result
     */
    String deleteCashCardRule(String ruleId);
}
