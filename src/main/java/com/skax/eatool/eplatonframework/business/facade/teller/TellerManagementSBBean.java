package com.skax.eatool.eplatonframework.business.facade.teller;


import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.framework.transaction.tcf.*;
import com.skax.eatool.foundation.logej.*;

/**
 * =============================================================================
 * ?λ‘κ·Έλ¨ ?€λͺ:
 * =============================================================================
 * ?€μ  ?λ¬΄ ?μ€?μ facade?¨μ΄ ?μ?λ λΆλΆμ΄??
 * ?μ¬ ??κ°??λ¬΄?μ€?μ facade?¨μ μ§μ κ°μ§κ³?κ°μΌλ‘μ λͺ¨λ  ?μ€?μ ?λλ‘?λ‘μ§??κ΄λ¦? * ?κ³  ?Έλ?????Όλ₯ ?μΌλ‘?κ΄λ¦¬νκΈ??ν λ‘μ§?΄λ€.
 * ?€μ  ?ΈμΆ?λ ?ΈμλΉμ ???λ©μ?λ execute()?΄λ€.
 * ??λ©μ?λ΄?μ κ°??λ¬΄?μ€?κ³Ό???°κ³λ₯??ν ?λ??λ©μ?κ? ?μ ?λ€.
 *
 * =============================================================================
 * λ³κ²½λ΄???λ³΄:
 * =============================================================================
 *  2004??03??16??1μ°¨λ²??release
 *
 *
 * =============================================================================
 *                                                        @author : ?₯μ°??WooSungJang)
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
       * 1??: λ§μ½ TCF?μ κ΄λ¦¬λ??κ²μ? USERTX,SESSIONCTX ?±μ΄ ?λ€.
       *       κ·Έλ°??λ§μ½ 2?μ²???¬μ©?μ???΄μ λ³΄λ? 1?λ‘ κ΄λ¦¬λλ―λ‘?μ‘°μ¬?΄μΌ ?λ€.
       *       Iellegalexception??λ§μ΄ λ°μ?λ€.
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
      // ?λ¬μ½λ κ΄λ¦?      //////////////////////////////////////////////////////////////////////////
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
