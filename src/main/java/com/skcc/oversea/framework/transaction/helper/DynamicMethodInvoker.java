package com.skcc.oversea.framework.transaction.helper;


import java.lang.reflect.*;
import java.util.*;
import java.rmi.*;

import com.skcc.oversea.framework.transfer.*;

import com.skcc.oversea.eplatonframework.transfer.*;
import com.skcc.oversea.foundation.logej.LOGEJ;


/**
 * =============================================================================
 * ?꾨줈洹몃옩 ?ㅻ챸:
 * =============================================================================
 * ???대옒?ㅻ뒗 System component瑜?李얠븘 ?뚮쭪? operation???몄텧?섎뒗 ??븷???섎뒗
 * 紐⑤뱺 BizAction class??理쒖긽??class?대떎.
 *
 * =============================================================================
 * 蹂寃쎈궡???뺣낫:
 * =============================================================================
 *  2004??03??16??1李⑤쾭??release
 *
 *
 * =============================================================================
 *                                                        @author : ?μ슦??WooSungJang)
 *                                                        @company: IMS SYSTEM
 *                                                        @email  : changwskr@yahoo.co.kr
 *                                                        @version 1.0
 *  =============================================================================
 */

public class DynamicMethodInvoker
{
  private static DynamicMethodInvoker instance;

  public DynamicMethodInvoker() {
  }

  public static synchronized DynamicMethodInvoker getInstance()
  {
    if (instance == null) {
      try{
        instance = new DynamicMethodInvoker();
        }catch(Exception igex){}
    }
    return instance;
  }


  protected IEvent doAct(String path_plus_classname, String methodName, Object event)
  {
    IEvent resevent = null;
    LOGEJ.getInstance().printf(1,(EPlatonEvent)event,"=================================================DynamicMethodInvoker.doAct() start");

    try
    {
      if( !(event instanceof com.skcc.oversea.framework.transfer.IEvent ) ) {
        resevent = (IEvent)event;
        return resevent;
      }
      Object ptarget = Class.forName(path_plus_classname).newInstance();
      Method meth = (ptarget.getClass()).getMethod(methodName,new Class[]{EPlatonEvent.class});
      resevent = (EPlatonEvent)meth.invoke(ptarget,new Object[]{(EPlatonEvent)event});
    }
    catch(Exception _e)
    {
      _e.printStackTrace();
      resevent = (IEvent)event;
      LOGEJ.getInstance().printf(1,(EPlatonEvent)event,"DynamicMethodInvoker error:[EFWK0036](MethodName=" + methodName + ") ?몄텧 ?먮윭");
      LOGEJ.getInstance().eprintf(5,(EPlatonEvent)event,_e);
    }
    finally
    {
      LOGEJ.getInstance().printf(1,(EPlatonEvent)event,"DynamicMethodInvoker.doAct() success()");
      LOGEJ.getInstance().printf(1,(EPlatonEvent)event,"=================================================DynamicMethodInvoker.doAct() end");
    }

    return resevent;
  }

}




