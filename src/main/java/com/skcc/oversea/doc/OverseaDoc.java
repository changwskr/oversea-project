package com.skcc.oversea.doc;


/**
 * ???쒖뒪?쒖쓽 援ы쁽 ?뱀쭠
 * 1) Business Delegate - action - Facade - tcf - Business native operation - rule - thing - entity
 * 2) 媛??쒖뒪??蹂?援ы쁽?? *    business delegate ?⑥? ?몄뀡鍮덉쓽 ?뺥깭濡?援ы쁽?쒕떎.
 *                           ?몃옖??뀡 愿由??뺥깭??container-not support ??낆쑝濡??쒕떎.
 *    action ?⑥? 湲곕낯? logcal / remote facade ?몄뀡鍮덉쓣 ?몄텧?????덈뒗 援ъ“濡?留뚮뱺??
 *    Facade ?⑥? ?몄뀡鍮덉쑝濡?留뚮뱾硫? *                ?몃옖??뀡 ??낆? 湲곕낯? container-required??낆쑝濡?留뚮뱺??
 *                留뚯빟 facade?⑥쓽 ?몄뀡鍮덉씠 usertransaction??낆쑝濡??몃옖??뀡??愿由ы븷 寃쎌슦
 *                                       bean-usertx??낆쑝濡??섎ŉ tcf ?몄텧?뺣낫瑜??섍꺼二쇱뼱???쒕떎
 *    tcf ?⑥? java native ??낆쑝濡??앹꽦?섎ŉ
 *            ?몃옖??뀡 愿由ш? bean/container???숈떆愿由??좎닔 ?덈룄濡??쒕떎.
 * ========================================================================================================== *
1) bizaction map ?뚯씪??留뚮뱶???곕젅??援ы쁽
   - ?밸줈吏곸쓽 StartUP Class???댁슜???밸줈吏??ш린?숈떆 bizactionmap?뚯씪 ?앹꽦 ?곕젅??     ?먮룞湲곕룞
   - ?ш뎄???꾩슂

2) xml ?뚯씪 援ъ꽦 諛?異붽?
   <cashCard-listCashCard>
        <bizaction-class>com.chb.coses.cashCard.business.delegate.action.CashCardBizAction</bizaction-class>
        <transactionable>Y</transactionable>
        <bizaction-method>listCashCard</bizaction-method>
        <bizaction-parametertype>com.chb.coses.cashCard.transfer.CashCardConditionCDTO</bizaction-parametertype>
        * 異붽?<operation-class>com.kdb.oversea.cashCard.business.facade.CashCardManageMent</operation-class>
  </cashCard-listCashCard>

3) client?먯꽌 server濡쒖쓽 ?쒖뒪?쒗샇異?諛⑸쾿

   - native client
     媛)EPlatonEvent媛앹껜?앹꽦
     ??EPlatonCommonDTO媛앹껜??湲곕낯?뺣낫 ???     ??TPSVCINFODTO媛앹껜???몄텧???쒕퉬?ㅻ챸 ???        tpsvcinfoDTO.setReqName("cashCard-listCashCard");
     ??TPCsendrecv媛앹껜???꾨떖
        TPCsendrecv.getInstance("21.101.3.47","7001").callEJB("cashCard-listCashCard","30",event);
     ??EPlatonBizDelegateSB媛?몄텧 execute()硫붿냼???몄텧
       ??1.TPSrecv 硫붿냼??          紐⑤뱺 ?몄텧? ?대씪?댁뼵?몄뿉?쒕뒗 request_name留뚯쓣 ?몄텧?섍퀬 ?쒕쾭?먯꽌 ?몄텧???쒖뒪?쒖쓽
          援ъ껜?곸씤 ?뺣낫瑜??뗮똿?섎뒗 寃껋쑝濡??쒕떎.(from bizaction.xml)
         a.?몄텧???쒖뒪??紐?           tpsvcinfoDTO.setSystem_name(TPMSVCAPI.getInstance().TPgetcallsystemname(tpsvcinfo.getReqName() ) );
         b.?몄텧???쒖뒪?쒖쑝濡??쇱슦?낇븷 action ?대옒?ㅻ챸
           tpsvcinfoDTO.setAction_name(TPMSVCAPI.getInstance().TPgetactionclassname(event)  );
         c.?몄텧???쒖뒪???대옒?ㅻ챸
           tpsvcinfoDTO.setOperation_name(TPMSVCAPI.getInstance().TPgetoperationclassname(event)  );
         d.?몄텧???쒖뒪???대옒?ㅼ쓽 硫붿냼?쒕챸
           tpsvcinfoDTO.setOperation_method(TPMSVCAPI.getInstance().TPgetinvokemethodname(event)  );
         e.?몄텧???쒖뒪???대옒?ㅼ쓽 硫붿냼?쒖뿉 ?꾨떖??CDTO ?대옒?ㅻ챸
           tpsvcinfoDTO.setCdto_name(TPMSVCAPI.getInstance().TPgetparametertypename(event)  );
      ??2.bizAction 硫붿냼??         a.CashCardBizAction(),TellerBizAction(), .... etc
         b.EPlatonBizAction()
           媛??쒖뒪??Facade?⑥쓽 ?몄뀡鍮덉쓣 ?몄텧, ?대씪?댁뼵?몄뿉???щ씪??媛앹껜瑜?Facade?⑥쓽
           ?몄뀡鍮덉뿉寃??꾨떖?쒕떎
      ??3.Facade Session Bean
         a.TCF ?몃옖??뀡 紐⑤뱢 ?몄텧
           媛?鍮덉뿉???몃옖??뀡 愿由щ? ?꾨떞??TCF媛 ?몄텧?쒕떎.
           TCF???ш쾶 STF,BTF,ETF 3媛쒖쓽 ?⑤씫?쇰줈 援ъ꽦?섎ŉ, ?낅Т?⑥쓽 ?몄텧???꾪빐??BTF?⑥뿉??           ?몄텧?쒕떎.
         b.BTF?먯꽌 tpsvcinfoDTO媛앹껜??operation class??李얠븘??           ?닿쾬? ?몄뒪?댁뒪???쒗궓??
         c.ETF?④퀎瑜?留덉튂怨?Facade Session Bean?쇰줈 由ы꽩?쒕떎.
      ??4.BizAction 硫붿냼?쒕줈 由ы꽩
      ??5.EPlatonBizDelegateSB??execute() 硫붿냼?쒖뿉寃뚮줈 由ы꽩
    留??대씪?댁뼵?몄뿉寃뚮줈 由ы꽩?쒕떎.

   - server ejb
     媛)EPlatonEvent媛앹껜?앹꽦
     ??EPlatonCommonDTO媛앹껜??湲곕낯?뺣낫 ???     ??TPSVCINFODTO媛앹껜???몄텧???쒕퉬?ㅻ챸 ???        tpsvcinfoDTO.setReqName("cashCard-listCashCard");
     ??TPSsendrecv媛앹껜???꾨떖
        TPSsendrecv.getInstance().CallEJB("common","cashCard-listCashCard",event);
                                          "?먮낯?쒖뒪??,"?몄텧?쒖뒪??硫붿냼??,"?꾨떖媛앹껜"
     ??Business Action ?몄텧
       ??1.TPSrecv 硫붿냼??          紐⑤뱺 ?몄텧? ?대씪?댁뼵?몄뿉?쒕뒗 request_name留뚯쓣 ?몄텧?섍퀬 ?쒕쾭?먯꽌 ?몄텧???쒖뒪?쒖쓽
          援ъ껜?곸씤 ?뺣낫瑜??뗮똿?섎뒗 寃껋쑝濡??쒕떎.(from bizaction.xml)
         a.?몄텧???쒖뒪??紐?           tpsvcinfoDTO.setSystem_name(TPMSVCAPI.getInstance().TPgetcallsystemname(tpsvcinfo.getReqName() ) );
         b.?몄텧???쒖뒪?쒖쑝濡??쇱슦?낇븷 action ?대옒?ㅻ챸
           tpsvcinfoDTO.setAction_name(TPMSVCAPI.getInstance().TPgetactionclassname(event)  );
         c.?몄텧???쒖뒪???대옒?ㅻ챸
           tpsvcinfoDTO.setOperation_name(TPMSVCAPI.getInstance().TPgetoperationclassname(event)  );
         d.?몄텧???쒖뒪???대옒?ㅼ쓽 硫붿냼?쒕챸
           tpsvcinfoDTO.setOperation_method(TPMSVCAPI.getInstance().TPgetinvokemethodname(event)  );
         e.?몄텧???쒖뒪???대옒?ㅼ쓽 硫붿냼?쒖뿉 ?꾨떖??CDTO ?대옒?ㅻ챸
           tpsvcinfoDTO.setCdto_name(TPMSVCAPI.getInstance().TPgetparametertypename(event)  );
      ??2.bizAction 硫붿냼??         a.CashCardBizAction(),TellerBizAction(), .... etc
         b.EPlatonBizAction()
           媛??쒖뒪??Facade?⑥쓽 ?몄뀡鍮덉쓣 ?몄텧, ?대씪?댁뼵?몄뿉???щ씪??媛앹껜瑜?Facade?⑥쓽
           ?몄뀡鍮덉뿉寃??꾨떖?쒕떎
      ??3.Facade Session Bean
         a.TCF ?몃옖??뀡 紐⑤뱢 ?몄텧
           媛?鍮덉뿉???몃옖??뀡 愿由щ? ?꾨떞??TCF媛 ?몄텧?쒕떎.
           TCF???ш쾶 STF,BTF,ETF 3媛쒖쓽 ?⑤씫?쇰줈 援ъ꽦?섎ŉ, ?낅Т?⑥쓽 ?몄텧???꾪빐??BTF?⑥뿉??           ?몄텧?쒕떎.
         b.BTF?먯꽌 tpsvcinfoDTO媛앹껜??operation class??李얠븘??           ?닿쾬? ?몄뒪?댁뒪???쒗궓??
         c.ETF?④퀎瑜?留덉튂怨?Facade Session Bean?쇰줈 由ы꽩?쒕떎.
      ??4.BizAction 硫붿냼?쒕줈 由ы꽩
    留??쒕쾭?대씪?댁뼵?몄뿉寃뚮줈 由ы꽩?쒕떎.

4) loggin 愿由щ? ?꾪븳 諛⑹븞
   媛. xml ?뚯씪愿由?- ?뚯씪紐?epllogej.xml
        <epllogej-common>
          <print-mode>1</print-mode>
          <error-mode>5</error-mode>
        </epllogej-common>
        <epllogej-lcommon>
          <print-mode>1</print-mode>
          <error-mode>5</error-mode>
        </epllogej-lcommon>
        <epllogej-teller>
          <print-mode>1</print-mode>
          <error-mode>5</error-mode>
        </epllogej-teller>
        <epllogej-cashCard>
          <print-mode>1</print-mode>
          <error-mode>5</error-mode>
        </epllogej-cashCard>
        <epllogej-deposit>
          <print-mode>1</print-mode>
          <error-mode>5</error-mode>
        </epllogej-deposit>
  ?? 愿由щ갑??      epllogej.xml?뚯씪???대떦 ?쒖뒪?쒖뿉 ???mode??異붽??쒕떎.
      /weblogic/bea/wlserver6.1/config/coses/applicationConfig/bizaction-map.xml
      ?뚯씪??touch?댁꽌 epllogej.xml?뺣낫媛 jdom??硫붾え由ъ뿉 諛섏쁺?섎룄濡??쒕떎.
  ?? ?ㅼ젣?ъ슜諛⑸쾿
      LOGEJ(1,event,"message");
      LOGEJ(1,event,exception);

4) ?몃옖??뀡???쒖뼱?섍린 ?꾪븳 援ъ“

  媛. ?뚯씪紐?- epllogej.xml

  <block-txcode>
        <count>3</count>
        <mode>off</mode>
        <s1>1000-1000</s1>
        <s2>1003-1010</s2>
        <s3>1010-2222</s3>
</block-txcode>


<block-system>
        <count>3</count>
        <mode>off</mode>
        <bank>03</bank>
        <s1>common</s1>
        <s2>deposit</s2>
        <s3>cashCard</s3>
</block-system>


<block-bankcode>
        <count>3</count>
        <mode>off</mode>
        <s1>02-02</s1>
        <s2>03-03</s2>
        <s3>04-04</s3>
</block-txcode>


<block-tpfq>
        <count>8</count>
        <mode>off</mode>
        <s1>100-100</s1>
        <s2>200-200</s2>
        <s3>300-300</s3>
        <s4>400-400</s4>
        <s5>500-500</s5>
        <s6>600-600</s6>
        <s7>700-700</s7>
        <s8>800-800</s8>
</block-tpfq>

*
*

////////////////////////////////////////////////////////////////////////////////

drop table TRANSACTION_INPUT;

CREATE TABLE TRANSACTION_INPUT (
         terminalID 			VARCHAR2(100),
         terminalType 		VARCHAR2(100),
         xmlSeq 					VARCHAR2(100),
         bankCode 				VARCHAR2(100),
         branchCode 			VARCHAR2(100),
         glPostBranchCode VARCHAR2(100),
         channelType 			VARCHAR2(100),
         userID 					VARCHAR2(100),
         eventNo 					VARCHAR2(100),
         nation 					VARCHAR2(100),
         regionCode 			VARCHAR2(100),
         timeZone 				VARCHAR2(100),
         fxRateCount 			VARCHAR2(100),
         reqName 					VARCHAR2(100),
         systemDate 			VARCHAR2(100),
         businessDate 		VARCHAR2(100),
         transactionNo 		VARCHAR2(100),
         baseCurrency 		VARCHAR2(100),
         multiPL 					VARCHAR2(100),
         userLevel 				VARCHAR2(100),
         IPAddress 				VARCHAR2(100),
         bp_sequence 				VARCHAR2(100),
         req_name 				VARCHAR2(100),
         system_name 			VARCHAR2(100),
         operation_name 	VARCHAR2(100),
         operation_method VARCHAR2(100),
         cdto_name 				VARCHAR2(100),
         action_name 			VARCHAR2(100),
         hostseq 					VARCHAR2(100),
         orgseq 					VARCHAR2(100),
         tx_timer 				VARCHAR2(100),
         tpfq 						VARCHAR2(100),
         errorcode 				VARCHAR2(100),
         trclass 					VARCHAR2(100),
         web_timeout 			VARCHAR2(100),
         web_intime 			VARCHAR2(100),
         web_outtime 			VARCHAR2(100),
         systemInTime 		VARCHAR2(100),
         systemOutTime 		VARCHAR2(100),
         system_date 			VARCHAR2(100),
         error_message 		VARCHAR2(100),
         logic_level 			VARCHAR2(100),
         STF_intime 			VARCHAR2(100),
         STF_outtime 			VARCHAR2(100),
         BTF_intime 			VARCHAR2(100),
         BTF_outtime 			VARCHAR2(100),
         ETF_intime 			VARCHAR2(100),
         ETF_outtime 			VARCHAR2(100),
         INPUT_DTO        VARCHAR2(4000),
         OUTPUT_DTO       VARCHAR2(4000),
         CONSTRAINT TRANSACTION_INPUT PRIMARY KEY ( hostseq )
         USING INDEX TABLESPACE TSP_CORE_IND )
         TABLESPACE TSP_CORE
;

CREATE SYNONYM TRANSACTION_INPUT FOR CORE.TRANSACTION_INPUT;
grant select,insert,update,delete on TRANSACTION_INPUT to eplaton;


///////////////////////////////////////////////////////////////////////////////
drop table TRANSACTION_OUTPUT;

CREATE TABLE TRANSACTION_OUTPUT (
         terminalID 			VARCHAR2(100),
         terminalType 		VARCHAR2(100),
         xmlSeq 					VARCHAR2(100),
         bankCode 				VARCHAR2(100),
         branchCode 			VARCHAR2(100),
         glPostBranchCode VARCHAR2(100),
         channelType 			VARCHAR2(100),
         userID 					VARCHAR2(100),
         eventNo 					VARCHAR2(100),
         nation 					VARCHAR2(100),
         regionCode 			VARCHAR2(100),
         timeZone 				VARCHAR2(100),
         fxRateCount 			VARCHAR2(100),
         reqName 					VARCHAR2(100),
         systemDate 			VARCHAR2(100),
         businessDate 		VARCHAR2(100),
         transactionNo 		VARCHAR2(100),
         baseCurrency 		VARCHAR2(100),
         multiPL 					VARCHAR2(100),
         userLevel 				VARCHAR2(100),
         IPAddress 				VARCHAR2(100),
         bp_sequence 				VARCHAR2(100),
         req_name 				VARCHAR2(100),
         system_name 			VARCHAR2(100),
          operation_name 	VARCHAR2(100),
         operation_method VARCHAR2(100),
         cdto_name 				VARCHAR2(100),
         action_name 			VARCHAR2(100),
         hostseq 					VARCHAR2(100),
         orgseq 					VARCHAR2(100),
         tx_timer 				VARCHAR2(100),
         tpfq 						VARCHAR2(100),
         errorcode 				VARCHAR2(100),
         trclass 					VARCHAR2(100),
         web_timeout 			VARCHAR2(100),
         web_intime 			VARCHAR2(100),
         web_outtime 			VARCHAR2(100),
         systemInTime 		VARCHAR2(100),
         systemOutTime 		VARCHAR2(100),
         system_date 			VARCHAR2(100),
         error_message 		VARCHAR2(100),
         logic_level 			VARCHAR2(100),
         STF_intime 			VARCHAR2(100),
         STF_outtime 			VARCHAR2(100),
         BTF_intime 			VARCHAR2(100),
         BTF_outtime 			VARCHAR2(100),
         ETF_intime 			VARCHAR2(100),
         ETF_outtime 			VARCHAR2(100),
         INPUT_DTO        VARCHAR2(4000),
         OUTPUT_DTO       VARCHAR2(4000),
         CONSTRAINT TRANSACTION_OUTPUT PRIMARY KEY ( hostseq )
         USING INDEX TABLESPACE TSP_CORE_IND )
         TABLESPACE TSP_CORE
         ;

CREATE SYNONYM TRANSACTION_OUTPUT FOR CORE.TRANSACTION_OUTPUT;
grant select,insert,update,delete on TRANSACTION_OUTPUT to eplaton;

////////////////////////////////////////////////////////////////////////////////

//TRANSACTION_INFO ?뚯씠釉??뺣낫
// channel_type == *01:General UI, 02:Batch, 03:ATM, 10:Internet Banking

drop table transaction_info;
CREATE TABLE TRANSACTION_INFO(
        transaction_id           VARCHAR2(100)  ,
        host_name                VARCHAR2(100)  ,
        system_name              VARCHAR2(100)  ,
        method_name              VARCHAR2(100)  ,
        bank_code                VARCHAR2(100)  ,
        branch_code              VARCHAR2(100)  ,
        user_id                  VARCHAR2(100)  ,
        channel_type             VARCHAR2(100)  ,
        business_date            VARCHAR2(100)  ,
        register_date            VARCHAR2(100)  ,
        in_time                  VARCHAR2(100)  ,
        event_no                 VARCHAR2(100)  ,
        transaction_no           VARCHAR2(100)  ,
        org_seq                  VARCHAR2(100)  ,
        out_time                 VARCHAR2(100)  ,
        response_time            VARCHAR2(100)  ,
        error_code               VARCHAR2(100)  ,
        ip_address               VARCHAR2(100)  ,
        tpfq                     VARCHAR2(100)  ,
        CONSTRAINT PK_TRANSACTION_INFO PRIMARY KEY (transaction_id)
        USING INDEX TABLESPACE TSP_CORE_IND
)
TABLESPACE TSP_CORE
/

grant select,insert,update,delete on TRANSACTION_INFO to eplaton;
conn eplaton/useplaton
/
CREATE SYNONYM TRANSACTION_INFO FOR CORE.TRANSACTION_INFO;

* ========================================================================================================== *
*/

public class OverseaDoc {
}
