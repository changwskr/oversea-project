package com.skax.eatool.framework.transaction.dao;

import java.math.BigDecimal;
import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;

/**
 * Transaction Time Processing DAO(Data Access Object) business interface이다.
 *
 * @author <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/10/08
 */
public interface ITxTimeProcDAO {
    public boolean TRANSACTION_INFO(EPlatonEvent event) throws DAOException;

    public boolean isLogabled();
}
