package com.skax.eatool.teller.business.facade;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.rmi.RemoteException;
import com.skax.eatool.framework.exception.CosesAppException;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.foundation.logej.*;

public interface ITellerManagementSB {

  public EPlatonEvent callmethod01(EPlatonEvent eplatonevent)
       throws CosesAppException, RemoteException;
  public EPlatonEvent callmethod02(EPlatonEvent eplatonevent)
       throws CosesAppException, RemoteException;

}




