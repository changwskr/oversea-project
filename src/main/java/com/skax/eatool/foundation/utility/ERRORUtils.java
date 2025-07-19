package com.skax.eatool.foundation.utility;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.*;
import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;

public class ERRORUtils {
  private static ERRORUtils instance;
  private Map contextMap;

  /**
   * A private constructor
   *
   */
  private ERRORUtils() {
  }

  /**
   * <p>
   * 이미 생성된 Instance가 있는지 검사하여 생성된 것이 있으면 그 Instance를 반환하고, 생성된 것이 없으면 새로 생성하여
   * 반환합니다.
   * </p>
   * <p>
   * Instance가 생성되어 있지 않으면 새로 생성합니다.
   * </p>
   *
   * @return ERRORUtils 생성된 또는 새로 생성된 Instance
   */
  public static synchronized ERRORUtils getInstance() {
    if (instance == null) {
      instance = new ERRORUtils();
    }
    return instance;
  }

  public EPlatonEvent ERRORadd(EPlatonEvent event, String errorcode, String message) {

    switch (event.getTPSVCINFODTO().getErrorcode().charAt(0)) {
      case 'I':
        event.getTPSVCINFODTO().setErrorcode(errorcode);
        event.getTPSVCINFODTO().setError_message(message);
      case 'E':
        errorcode = errorcode + "|" + event.getTPSVCINFODTO().getErrorcode();
        event.getTPSVCINFODTO().setErrorcode(errorcode);
        event.getTPSVCINFODTO().setError_message(message);
    }

    return event;
  }

}
