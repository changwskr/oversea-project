package com.skcc.oversea.foundation.utility;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.*;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;

public class ERRORUtils
{
    private static ERRORUtils instance;
    private Map contextMap;

    /**
     * A private constructor
     *
     */
    private ERRORUtils()
    {
    }

    /**
     * <p>?먭린 ?먯떊??Instance媛 ?대? ?앹꽦?섏뼱 ?덈뒗吏 寃?ы븳 ?? ?앹꽦 ?섏뼱 ?덉? ?딆쑝硫?/p>
     * <p>Instance瑜??앹꽦???? 諛섑솚?쒕떎.</p>
     *
     * @return ERRORUtils ?앹꽦???먭린 ?먯떊??Instance
     */
    public static synchronized ERRORUtils getInstance()
    {
        if (instance == null)
        {
            instance = new ERRORUtils();
        }
        return instance;
    }

    public EPlatonEvent ERRORadd(EPlatonEvent event,String errorcode,String message)
    {

      switch( event.getTPSVCINFODTO().getErrorcode().charAt(0) )
      {
        case 'I' :
          event.getTPSVCINFODTO().setErrorcode(errorcode);
          event.getTPSVCINFODTO().setError_message(message);
        case 'E' :
          errorcode = errorcode+"|"+event.getTPSVCINFODTO().getErrorcode();
          event.getTPSVCINFODTO().setErrorcode(errorcode);
          event.getTPSVCINFODTO().setError_message(message);
      }

      return event;
    }

}

