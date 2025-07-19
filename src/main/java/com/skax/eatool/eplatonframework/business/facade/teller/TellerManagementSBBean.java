package com.skax.eatool.eplatonframework.business.facade.teller;


import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.framework.transaction.tcf.*;
import com.skax.eatool.foundation.logej.*;

/**
 * =============================================================================
 * ?„ë¡œê·¸ë¨ ?¤ëª…:
 * =============================================================================
 * ?¤ì œ ?…ë¬´ ?œìŠ¤?œì˜ facade?¨ì´ ?•ì˜?˜ëŠ” ë¶€ë¶„ì´??
 * ?„ì¬ ??ê°??…ë¬´?œìŠ¤?œì˜ facade?¨ì„ ì§ì ‘ê°€ì§€ê³?ê°ìœ¼ë¡œì„œ ëª¨ë“  ?œìŠ¤?œì„ ?˜ë‚˜ë¡?ë¡œì§??ê´€ë¦? * ?˜ê³  ?¸ëœ??…˜???¼ë¥ ?ìœ¼ë¡?ê´€ë¦¬í•˜ê¸??„í•œ ë¡œì§?´ë‹¤.
 * ?¤ì œ ?¸ì¶œ?˜ëŠ” ?¸ì…˜ë¹ˆì— ?€??ë©”ì†Œ?œëŠ” execute()?´ë‹¤.
 * ??ë©”ì†Œ?œë‚´?ì„œ ê°??…ë¬´?œìŠ¤?œê³¼???°ê³„ë¥??„í•œ ?˜ë‚˜??ë©”ì†Œ?œê? ?•ì˜ ?œë‹¤.
 *
 * =============================================================================
 * ë³€ê²½ë‚´???•ë³´:
 * =============================================================================
 *  2004??03??16??1ì°¨ë²„??release
 *
 *
 * =============================================================================
 *                                                        @author : ?¥ìš°??WooSungJang)
 *                                                        @company: IMS SYSTEM
 *                                                        @email  : changwskr@yahoo.co.kr
 *                                                        @version 1.0
 *  =============================================================================
 */

public class TellerManagementSBBean implements ITellerManagementSB
{

  public EPlatonEvent execute(EPlatonEvent event)
  {
    EPlatonEvent resp_event = null;
    try
    {
      resp_event = event;

      /*************************************************************************
       * 1??: ë§Œì•½ TCF?ì„œ ê´€ë¦¬ë˜??ê²ƒì? USERTX,SESSIONCTX ?±ì´ ?ˆë‹¤.
       *       ê·¸ëŸ°??ë§Œì•½ 2?ˆì²˜???¬ìš©?œì—???´ì •ë³´ë? 1?˜ë¡œ ê´€ë¦¬ë˜ë¯€ë¡?ì¡°ì‹¬?´ì•¼ ?œë‹¤.
       *       Iellegalexception??ë§ì´ ë°œìƒ?œë‹¤.
       *
       *************************************************************************/
      TCF tcf = new TCF();
      // execute(String transactionId, String transactionType, EPlatonEvent event)
      String transactionId="1111";
      String transactionType="usertx";

      resp_event = (EPlatonEvent) tcf.execute(transactionId, transactionType, event );

    }
    catch (Exception re)
    {

      re.printStackTrace();
      //////////////////////////////////////////////////////////////////////////
      // ?ëŸ¬ì½”ë“œ ê´€ë¦?      //////////////////////////////////////////////////////////////////////////
      {
        TPSVCINFODTO tpsvcinfo = ((EPlatonEvent)resp_event).getTPSVCINFODTO();

        switch( tpsvcinfo.getErrorcode().charAt(0) )
        {
          case 'I' :
            tpsvcinfo.setErrorcode("EFWK0041");
            tpsvcinfo.setError_message(this.getClass().getName()+ ".execute():" + re.toString());
            break;
          case 'E' :
            String errorcode = "EFWK0041"+"|"+tpsvcinfo.getErrorcode();
            tpsvcinfo.setErrorcode(errorcode);
            tpsvcinfo.setError_message(this.getClass().getName()+ ".execute():" + re.toString());
            break;
        }
      }
      //////////////////////////////////////////////////////////////////////////
      LOGEJ.getInstance().eprintf(5,(EPlatonEvent)event,re);
      LOGEJ.getInstance().printf(1,resp_event,this.getClass().getName()+ ".execute():" + re.toString());

    }

    return resp_event;

  }


}
