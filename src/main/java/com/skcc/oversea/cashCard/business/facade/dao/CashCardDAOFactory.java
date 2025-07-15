package com.skcc.oversea.cashCard.business.facade.dao;

import com.skcc.oversea.framework.business.dao.AbstractDAOFactory;
import com.skcc.oversea.framework.business.dao.IDAO;
import org.springframework.stereotype.Component;

/**
 * Cash Card DAO Factory
 */
@Component
public class CashCardDAOFactory extends AbstractDAOFactory {

    @Override
    public <T extends IDAO<?, ?>> T getDAO(Class<T> daoClass) {
        // Implementation for getting DAO instances
        // This is a placeholder implementation
        return getBean(daoClass);
    }
}
