package com.skax.eatool.framework.transaction.helper;

import java.lang.reflect.*;
import java.util.*;
import java.rmi.*;

import com.skax.eatool.framework.transfer.*;

import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.foundation.logej.LOGEJ;

/**
 * =============================================================================
 * 프로그램 명:
 * =============================================================================
 * 동적으로 System component를 찾아서 operation을 호출하는 메소드를
 * 모든 BizAction class에서 공통으로 사용하는 class이다
 *
 * =============================================================================
 * 변경내역 정보:
 * =============================================================================
 * 2004년 03월 16일 1차버전 release
 *
 *
 * =============================================================================
 * 
 * @author : 장우성(WooSungJang)
 * @company: IMS SYSTEM
 * @email : changwskr@yahoo.co.kr
 * @version 1.0
 *          =============================================================================
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
