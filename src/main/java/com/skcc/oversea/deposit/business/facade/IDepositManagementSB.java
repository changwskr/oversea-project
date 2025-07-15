package com.skcc.oversea.deposit.business.facade;

import java.rmi.RemoteException;
import com.skcc.oversea.framework.exception.CosesAppException;
import com.skcc.oversea.eplatonframework.transfer.*;
import com.skcc.oversea.foundation.logej.*;


public interface IDepositManagementSB {
  /***************************************************************************
   * ?뚯뒪???섑뵆
   ***************************************************************************/
    public EPlatonEvent callmethod01(EPlatonEvent eplatonevent)
         throws CosesAppException, RemoteException;
    public EPlatonEvent callmethod02(EPlatonEvent eplatonevent)
         throws CosesAppException, RemoteException;
  /***************************************************************************/

}




