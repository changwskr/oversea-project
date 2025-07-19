package com.skax.eatool.eplatonframework.business.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skax.eatool.framework.business.dao.IDAO;
import com.skax.eatool.framework.business.dao.AbstractDAOFactory;

/**
 * EPlaton Delegate DAO Factory for SKCC Oversea
 * 
 * Spring Boot factory for managing EPlaton delegate DAO instances
 *
 * @author  <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/10/08
 */
@Component
public class EPlatonDelegateDAOFactory extends AbstractDAOFactory
{
    // DBMS Type constants
    public static final int DBMS_TYPE_ORACLE = 1;
    public static final int DBMS_TYPE_MYSQL = 2;
    public static final int DBMS_TYPE_POSTGRES = 3;

    @Autowired
    private EPlatonDelegateDAO eplatonDelegateDAO;

    /**
     * Implementation of abstract method from AbstractDAOFactory
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends IDAO<?, ?>> T getDAO(Class<T> daoClass) {
        // Since EPlatonDelegateDAO doesn't implement IDAO, 
        // we try to get bean from Spring context for IDAO implementations
        try {
            return getBean(daoClass);
        } catch (Exception e) {
            throw new IllegalArgumentException("DAO class not supported: " + daoClass.getName(), e);
        }
    }

    /**
     * Get EPlaton Delegate DAO (returns specific interface type)
     */
    public IEPlatonDelegateDAO getDAO()
    {
        return getDelegateDAO(getDBMSType());
    }

    /**
     * Get EPlaton Delegate DAO by DBMS type
     */
    public IEPlatonDelegateDAO getDelegateDAO(int type)
    {
        if (type == DBMS_TYPE_ORACLE) {
            return eplatonDelegateDAO;
        }
        String err = "'" + type + "' DBMS type is not supported";
        throw new IllegalArgumentException(err);
    }

    /**
     * Get DBMS type (default to Oracle)
     */
    public int getDBMSType() {
        // Default to Oracle for SKCC Oversea project
        // This can be made configurable via application properties if needed
        return DBMS_TYPE_ORACLE;
    }

    /**
     * Convenience method to get EPlaton Delegate DAO
     */
    public IEPlatonDelegateDAO getEPlatonDelegateDAO() {
        return eplatonDelegateDAO;
    }

    /**
     * Get EPlatonDelegateDAO implementation directly (for cases where concrete type is needed)
     */
    public EPlatonDelegateDAO getEPlatonDelegateDAOImpl() {
        return eplatonDelegateDAO;
    }
}


