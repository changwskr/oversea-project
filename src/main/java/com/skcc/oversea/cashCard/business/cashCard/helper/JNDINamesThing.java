package com.skcc.oversea.cashCard.business.cashCard.helper;

import com.skcc.oversea.cashCard.business.cashCard.entity.CashCardEB;
import com.skcc.oversea.cashCard.business.cashCard.entity.HotCardEB;

/**
 * Spring Boot compatible JNDI Names Helper
 * 
 * Converted from EJB JNDI lookup to Spring Boot entity references
 * for backward compatibility with existing code.
 */
public class JNDINamesThing
{
    // Spring Boot entity classes instead of EJB Home interfaces
    public static final Class CASH_CARDEB_HOME = CashCardEB.class;
    public static final Class HOT_CARDEB_HOME = HotCardEB.class;
    
    /**
     * Get CashCardEB entity class for Spring Boot usage
     */
    public static Class getCashCardEBClass() {
        return CASH_CARDEB_HOME;
    }
    
    /**
     * Get HotCardEB entity class for Spring Boot usage
     */
    public static Class getHotCardEBClass() {
        return HOT_CARDEB_HOME;
    }
}

