package com.skax.eatool.eplatonframework.business.facade.deposit;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.rmi.RemoteException;
import com.skax.eatool.framework.exception.CosesAppException;
import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;

public interface IDepositManagementSB
{
  public EPlatonEvent execute(EPlatonEvent event) throws CosesAppException,RemoteException;
}




