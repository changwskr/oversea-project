package com.skax.eatool.foundation.methodinvoke;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.lang.reflect.*;
import java.util.*;
import java.rmi.*;

import com.skax.eatool.framework.transfer.*;

import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.foundation.logej.LOGEJ;

/**
 * @version 1.30
 *
 *          ????�???�뒗 System component??李얠�??????? operation???몄텧??�뒗 ??�????�뒗
 *          紐⑤�?BizAction class??理쒖�??class??�??
 */

public class DynamicMethodInvoker {
  private static DynamicMethodInvoker instance;

  public DynamicMethodInvoker() {
  }

  public static synchronized DynamicMethodInvoker getInstance() {
    if (instance == null) {
      try {
        instance = new DynamicMethodInvoker();
      } catch (Exception igex) {
      }
    }
    return instance;
  }

  protected IEvent doAct(String path_plus_classname, String methodName, Object event) {
    IEvent resevent = null;
    LOGEJ.getInstance().printf(1, (EPlatonEvent) event,
        "=================================================DynamicMethodInvoker.doAct() start");

    try {
      if (!(event instanceof com.skax.eatool.framework.transfer.IEvent)) {
        resevent = (IEvent) event;
        return resevent;
      }
      Object ptarget = Class.forName(path_plus_classname).newInstance();
      Method meth = (ptarget.getClass()).getMethod(methodName, new Class[] { EPlatonEvent.class });
      resevent = (EPlatonEvent) meth.invoke(ptarget, new Object[] { (EPlatonEvent) event });
    } catch (Exception _e) {
      _e.printStackTrace();
      resevent = (IEvent) event;
      LOGEJ.getInstance().printf(1, (EPlatonEvent) event,
          "DynamicMethodInvoker error:[EFWK0036](MethodName=" + methodName + ") 메소드 호출 실패");
      LOGEJ.getInstance().eprintf(5, (EPlatonEvent) event, _e);
    } finally {
      LOGEJ.getInstance().printf(1, (EPlatonEvent) event, "DynamicMethodInvoker.doAct() success()");
      LOGEJ.getInstance().printf(1, (EPlatonEvent) event,
          "=================================================DynamicMethodInvoker.doAct() end");
    }

    return resevent;
  }

}
