package com.skcc.oversea.framework.transaction.dao;

import java.math.BigDecimal;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;

/**
 * Transaction Control Framework DAO(Data Access Object) business interface이다.
 *
 * @version 1.0, 2002/10/08
 */
public interface ITransactionControlDAO
{
    public boolean DB_INSERTinlog(EPlatonEvent event) throws DAOException;
    
    public boolean DB_INSERToutlog(EPlatonEvent event) throws DAOException;
    
    public boolean DB_INSERT_tpminlog(EPlatonEvent event) throws DAOException;
    
    public boolean DB_INSERTtpmoutlog(EPlatonEvent event, long interval_seconds) throws DAOException;
    
    public String queryForBusinessDate(String bankCode);
    
    public String GetBizDate();
    
    public boolean isLogabled();
}



