package com.skax.eatool.eplatonframework.business.dao;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * EPlaton Transaction Time Processing DAO Factory???˜í??¸ë‹¤.
 *
 * @author  <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/10/08
 */
@Component
public class TxTimeProcDAOFactory
{
    @Autowired
    @Qualifier("eplatonTxTimeProcDAO")
    private TxTimeProcDAO txTimeProcDAO;

    public ITxTimeProcDAO getTxTimeProcDAO()
    {
        return txTimeProcDAO;
    }

    public void setTxTimeProcDAO(TxTimeProcDAO txTimeProcDAO)
    {
        this.txTimeProcDAO = txTimeProcDAO;
    }
}



