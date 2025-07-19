package com.skax.eatool.common.business.facade;

import java.rmi.RemoteException;
import com.skax.eatool.framework.exception.CosesAppException;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.foundation.logej.*;

public interface ICommonManagementSB {
     /***************************************************************************
      * 공통 관리 인터페이스
      ***************************************************************************/
     public EPlatonEvent callmethod01(EPlatonEvent eplatonevent)
               throws CosesAppException, RemoteException;

     public EPlatonEvent callmethod02(EPlatonEvent eplatonevent)
               throws CosesAppException, RemoteException;
     /***************************************************************************/

}
