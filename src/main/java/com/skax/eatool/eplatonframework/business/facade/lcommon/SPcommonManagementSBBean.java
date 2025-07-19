package com.skax.eatool.eplatonframework.business.facade.lcommon;

import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.framework.transaction.tcf.*;
import com.skax.eatool.foundation.logej.*;

/**
 * =============================================================================
 * 프로그램 명:
 * =============================================================================
 * 제제 업무 스의 facade에서 의하는 부분이다.
 * 제재 기존 업무 스의 facade을 직접가지고 감으로서 모든 스을 하나의 로직으로 관리하고 트랜잭션의 효율적으로 관리하기 위한 로직이다.
 * 제재 출력하는 액션빈에 있는 메소드는 execute()이다.
 * 이 메소드내에서 실제 업무 스과의 관계를 하는 하나의 메소드를 정의 한다.
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

public class SPcommonManagementSBBean implements ISPcommonManagementSB {

  public EPlatonEvent execute(EPlatonEvent event) {
    EPlatonEvent resp_event = null;
    try {
      resp_event = event;

      /*************************************************************************
       * 1단계: 만약 TCF에서 관리되는 것이 USERTX,SESSIONCTX 이다.
       * 그런데 만약 2차처리에서 사용자에 대한 정보를 1차로 관리되므로 조심해야 한다.
       * Iellegalexception이 많이 발생한다.
       *
       *************************************************************************/
      TCF tcf = new TCF();
      // execute(String transactionId, String transactionType, EPlatonEvent event)
      String transactionId = "1111";
      String transactionType = "usertx";

      resp_event = (EPlatonEvent) tcf.execute(transactionId, transactionType, event);

    } catch (Exception re) {

      re.printStackTrace();
      //////////////////////////////////////////////////////////////////////////
      // 에러코드 관리
      //////////////////////////////////////////////////////////////////////////
      {
        TPSVCINFODTO tpsvcinfo = ((EPlatonEvent) resp_event).getTPSVCINFODTO();

        switch (tpsvcinfo.getErrorcode().charAt(0)) {
          case 'I':
            tpsvcinfo.setErrorcode("EFWK0041");
            tpsvcinfo.setError_message(this.getClass().getName() + ".execute():" + re.toString());
            break;
          case 'E':
            String errorcode = "EFWK0041" + "|" + tpsvcinfo.getErrorcode();
            tpsvcinfo.setErrorcode(errorcode);
            tpsvcinfo.setError_message(this.getClass().getName() + ".execute():" + re.toString());
            break;
        }
      }
      //////////////////////////////////////////////////////////////////////////
      LOGEJ.getInstance().eprintf(5, (EPlatonEvent) event, re);
      LOGEJ.getInstance().printf(1, resp_event, this.getClass().getName() + ".execute():" + re.toString());

    }

    return resp_event;

  }

}
