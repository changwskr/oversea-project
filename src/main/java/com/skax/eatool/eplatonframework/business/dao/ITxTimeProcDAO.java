package com.skax.eatool.eplatonframework.business.dao;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.math.BigDecimal;
import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;

/**
 * EPlaton Transaction Time Processing DAO(Data Access Object) business interface?´ë‹¤.
 *
 * @author  <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/10/08
 */
public interface ITxTimeProcDAO
{
    public boolean TRANSACTION_INFO(EPlatonEvent event) throws DAOException;
    
    public boolean isLogabled();
}



