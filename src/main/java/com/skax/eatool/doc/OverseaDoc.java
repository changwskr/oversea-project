package com.skax.eatool.doc;

/**
 * 시스템의 구조 설명
 * 1) Business Delegate - action - Facade - tcf - Business native operation -
 * rule - thing - entity
 * 2) 시스템의 구조 설명
 * business delegate 는 트랜잭션의 상태를 관리합니다.
 * 컨테이너가 트랜잭션 상태를container-not support 합니다.
 * action 은 비즈니스 logcal / remote facade 트랜잭션을 제어하는 역할입니다
 * Facade 는 트랜잭션을 관리합니다
 * 컨테이너가 비즈니스 container-required를 관리합니다
 * 이때 facade의 트랜잭션이 usertransaction이므로 컨테이너가 관리하는 경우
 * bean-usertx이므로 tcf 제어가 필요합니다
 * tcf 는 java native 트랜잭션입니다
 * 컨테이너가 bean/container가 아닌 경우에만 사용합니다.
 * ==========================================================================================================
 * 1) bizaction map 설정이 완료된 후의 실행
 * - 시스템이 StartUP Class에서 사용할 때 bizactionmap을 생성 완료 후
 * 비즈니스 로직을 실행합니다
 * - 필수 설정
 * 
 * 2) xml 설정 추가
 * <cashCard-listCashCard>
 * <bizaction-class>com.chb.coses.cashCard.business.delegate.action.CashCardBizAction</bizaction-class>
 * <transactionable>Y</transactionable>
 * <bizaction-method>listCashCard</bizaction-method>
 * <bizaction-parametertype>com.chb.coses.cashCard.transfer.CashCardConditionCDTO</bizaction-parametertype>
 * 추가<operation-class>com.kdb.oversea.cashCard.business.facade.CashCardManageMent</operation-class>
 * </cashCard-listCashCard>
 * 
 * 3) client와 server로부터의 시스템 호출 과정
 * - native client
 * a)EPlatonEvent를 생성
 * b)EPlatonCommonDTO를 설정 완료 후 설정
 * c)TPSVCINFODTO를 제어하여 설정
 * tpsvcinfoDTO.setReqName("cashCard-listCashCard");
 * d)TPCsendrecv를 호출
 * TPCsendrecv.getInstance("21.101.3.47","7001").callEJB("cashCard-listCashCard","30",event);
 * e)EPlatonBizDelegateSB를 호출 execute()메서드를 호출
 * f)1.TPSrecv 메서드
 * 모든 호출은 서버에서 받은 request_name을 호출하여 시스템의
 * 설정 정보를 확인합니다.(from bizaction.xml)
 * a.호출할 시스템명
 * tpsvcinfoDTO.setSystem_name(TPMSVCAPI.getInstance().TPgetcallsystemname(tpsvcinfo.getReqName()
 * ) );
 * b.호출할 시스템에서 실행할 action 명
 * tpsvcinfoDTO.setAction_name(TPMSVCAPI.getInstance().TPgetactionclassname(event)
 * );
 * c.호출할 시스템의 operation 명
 * tpsvcinfoDTO.setOperation_name(TPMSVCAPI.getInstance().TPgetoperationclassname(event)
 * );
 * d.호출할 시스템의 operation의 메서드명
 * tpsvcinfoDTO.setOperation_method(TPMSVCAPI.getInstance().TPgetinvokemethodname(event)
 * );
 * e.호출할 시스템의 메서드에 전달할 CDTO 명
 * tpsvcinfoDTO.setCdto_name(TPMSVCAPI.getInstance().TPgetparametertypename(event)
 * );
 * g)2.bizAction 메서드
 * a.CashCardBizAction(),TellerBizAction(), .... etc
 * b.EPlatonBizAction()
 * 시스템의 Facade의 트랜잭션을 호출, 서버에서 받은 이벤트 객체를 Facade의
 * 트랜잭션에 전달합니다
 * h)3.Facade Session Bean
 * a.TCF 컨테이너를 호출
 * 시스템이 컨테이너가 아닌 경우에만 TCF를 호출합니다.
 * TCF는 STF,BTF,ETF 3가지로 구성되어 있고, 비즈니스의 호출에 따라BTF에
 * 호출합니다.
 * b.BTF에서 tpsvcinfoDTO를 operation class로 전달
 * 결과를 비즈니스 시스템으로 반환
 * c.ETF결과를 다시 Facade Session Bean으로 반환
 * i)4.BizAction 메서드로 반환
 * j)5.EPlatonBizDelegateSB의 execute() 메서드로 반환
 * 최종적으로 서버로부터 반환됩니다.
 * 
 * - server ejb
 * a)EPlatonEvent를 생성
 * b)EPlatonCommonDTO를 설정 완료 후 설정
 * c)TPSVCINFODTO를 제어하여 설정
 * tpsvcinfoDTO.setReqName("cashCard-listCashCard");
 * d)TPSsendrecv를 호출
 * TPSsendrecv.getInstance().CallEJB("common","cashCard-listCashCard",event);
 * "호출할 시스템,"호출할 메서드명,"전달할 이벤트"
 * e)Business Action 호출
 * f)1.TPSrecv 메서드
 * 모든 호출은 서버에서 받은 request_name을 호출하여 시스템의
 * 설정 정보를 확인합니다.(from bizaction.xml)
 * a.호출할 시스템명
 * tpsvcinfoDTO.setSystem_name(TPMSVCAPI.getInstance().TPgetcallsystemname(tpsvcinfo.getReqName()
 * ) );
 * b.호출할 시스템에서 실행할 action 명
 * tpsvcinfoDTO.setAction_name(TPMSVCAPI.getInstance().TPgetactionclassname(event)
 * );
 * c.호출할 시스템의 operation 명
 * tpsvcinfoDTO.setOperation_name(TPMSVCAPI.getInstance().TPgetoperationclassname(event)
 * );
 * d.호출할 시스템의 operation의 메서드명
 * tpsvcinfoDTO.setOperation_method(TPMSVCAPI.getInstance().TPgetinvokemethodname(event)
 * );
 * e.호출할 시스템의 메서드에 전달할 CDTO 명
 * tpsvcinfoDTO.setCdto_name(TPMSVCAPI.getInstance().TPgetparametertypename(event)
 * );
 * g)2.bizAction 메서드
 * a.CashCardBizAction(),TellerBizAction(), .... etc
 * b.EPlatonBizAction()
 * 시스템의 Facade의 트랜잭션을 호출, 서버에서 받은 이벤트 객체를 Facade의
 * 트랜잭션에 전달합니다
 * h)3.Facade Session Bean
 * a.TCF 컨테이너를 호출
 * 시스템이 컨테이너가 아닌 경우에만 TCF를 호출합니다.
 * TCF는 STF,BTF,ETF 3가지로 구성되어 있고, 비즈니스의 호출에 따라BTF에
 * 호출합니다.
 * b.BTF에서 tpsvcinfoDTO를 operation class로 전달
 * 결과를 비즈니스 시스템으로 반환
 * c.ETF결과를 다시 Facade Session Bean으로 반환
 * i)4.BizAction 메서드로 반환
 * 최종적으로 서버로부터 반환됩니다.
 * 
 * 4) logging 설정과 관련된 설명
 * a. xml 설정과 관련- 설정파일은 epllogej.xml
 * <epllogej-common>
 * <print-mode>1</print-mode>
 * <error-mode>5</error-mode>
 * </epllogej-common>
 * <epllogej-lcommon>
 * <print-mode>1</print-mode>
 * <error-mode>5</error-mode>
 * </epllogej-lcommon>
 * <epllogej-teller>
 * <print-mode>1</print-mode>
 * <error-mode>5</error-mode>
 * </epllogej-teller>
 * <epllogej-cashCard>
 * <print-mode>1</print-mode>
 * <error-mode>5</error-mode>
 * </epllogej-cashCard>
 * <epllogej-deposit>
 * <print-mode>1</print-mode>
 * <error-mode>5</error-mode>
 * </epllogej-deposit>
 * b. 설정값은
 * epllogej.xml에 추가할 시스템에 해당하는 mode를 추가합니다.
 * /weblogic/bea/wlserver6.1/config/coses/applicationConfig/bizaction-map.xml
 * 파일을 touch하여 epllogej.xml이 jdom 파서로 읽어들 수 있습니다.
 * c. 사용법
 * LOGEJ(1,event,"message");
 * LOGEJ(1,event,exception);
 * 
 * 4) 컨테이너와 관련된 설정 방법
 * a. 설정파일- epllogej.xml
 * 
 * <block-txcode>
 * <count>3</count>
 * <mode>off</mode>
 * <s1>1000-1000</s1>
 * <s2>1003-1010</s2>
 * <s3>1010-2222</s3>
 * </block-txcode>
 * 
 * 
 * <block-system>
 * <count>3</count>
 * <mode>off</mode>
 * <bank>03</bank>
 * <s1>common</s1>
 * <s2>deposit</s2>
 * <s3>cashCard</s3>
 * </block-system>
 * 
 * 
 * <block-bankcode>
 * <count>3</count>
 * <mode>off</mode>
 * <s1>02-02</s1>
 * <s2>03-03</s2>
 * <s3>04-04</s3>
 * </block-txcode>
 * 
 * 
 * <block-tpfq>
 * <count>8</count>
 * <mode>off</mode>
 * <s1>100-100</s1>
 * <s2>200-200</s2>
 * <s3>300-300</s3>
 * <s4>400-400</s4>
 * <s5>500-500</s5>
 * <s6>600-600</s6>
 * <s7>700-700</s7>
 * <s8>800-800</s8>
 * </block-tpfq>
 *
 *
 * 
 * 
 * ////////////////////////////////////////////////////////////////////////////////
 * 
 * drop table TRANSACTION_INPUT;
 * 
 * CREATE TABLE TRANSACTION_INPUT (
 * terminalID VARCHAR2(100),
 * terminalType VARCHAR2(100),
 * xmlSeq VARCHAR2(100),
 * bankCode VARCHAR2(100),
 * branchCode VARCHAR2(100),
 * glPostBranchCode VARCHAR2(100),
 * channelType VARCHAR2(100),
 * userID VARCHAR2(100),
 * eventNo VARCHAR2(100),
 * nation VARCHAR2(100),
 * regionCode VARCHAR2(100),
 * timeZone VARCHAR2(100),
 * fxRateCount VARCHAR2(100),
 * reqName VARCHAR2(100),
 * systemDate VARCHAR2(100),
 * businessDate VARCHAR2(100),
 * transactionNo VARCHAR2(100),
 * baseCurrency VARCHAR2(100),
 * multiPL VARCHAR2(100),
 * userLevel VARCHAR2(100),
 * IPAddress VARCHAR2(100),
 * bp_sequence VARCHAR2(100),
 * req_name VARCHAR2(100),
 * system_name VARCHAR2(100),
 * operation_name VARCHAR2(100),
 * operation_method VARCHAR2(100),
 * cdto_name VARCHAR2(100),
 * action_name VARCHAR2(100),
 * hostseq VARCHAR2(100),
 * orgseq VARCHAR2(100),
 * tx_timer VARCHAR2(100),
 * tpfq VARCHAR2(100),
 * errorcode VARCHAR2(100),
 * trclass VARCHAR2(100),
 * web_timeout VARCHAR2(100),
 * web_intime VARCHAR2(100),
 * web_outtime VARCHAR2(100),
 * systemInTime VARCHAR2(100),
 * systemOutTime VARCHAR2(100),
 * system_date VARCHAR2(100),
 * error_message VARCHAR2(100),
 * logic_level VARCHAR2(100),
 * STF_intime VARCHAR2(100),
 * STF_outtime VARCHAR2(100),
 * BTF_intime VARCHAR2(100),
 * BTF_outtime VARCHAR2(100),
 * ETF_intime VARCHAR2(100),
 * ETF_outtime VARCHAR2(100),
 * INPUT_DTO VARCHAR2(4000),
 * OUTPUT_DTO VARCHAR2(4000),
 * CONSTRAINT TRANSACTION_INPUT PRIMARY KEY ( hostseq )
 * USING INDEX TABLESPACE TSP_CORE_IND )
 * TABLESPACE TSP_CORE
 * ;
 * 
 * CREATE SYNONYM TRANSACTION_INPUT FOR CORE.TRANSACTION_INPUT;
 * grant select,insert,update,delete on TRANSACTION_INPUT to eplaton;
 * 
 * 
 * ///////////////////////////////////////////////////////////////////////////////
 * drop table TRANSACTION_OUTPUT;
 * 
 * CREATE TABLE TRANSACTION_OUTPUT (
 * terminalID VARCHAR2(100),
 * terminalType VARCHAR2(100),
 * xmlSeq VARCHAR2(100),
 * bankCode VARCHAR2(100),
 * branchCode VARCHAR2(100),
 * glPostBranchCode VARCHAR2(100),
 * channelType VARCHAR2(100),
 * userID VARCHAR2(100),
 * eventNo VARCHAR2(100),
 * nation VARCHAR2(100),
 * regionCode VARCHAR2(100),
 * timeZone VARCHAR2(100),
 * fxRateCount VARCHAR2(100),
 * reqName VARCHAR2(100),
 * systemDate VARCHAR2(100),
 * businessDate VARCHAR2(100),
 * transactionNo VARCHAR2(100),
 * baseCurrency VARCHAR2(100),
 * multiPL VARCHAR2(100),
 * userLevel VARCHAR2(100),
 * IPAddress VARCHAR2(100),
 * bp_sequence VARCHAR2(100),
 * req_name VARCHAR2(100),
 * system_name VARCHAR2(100),
 * operation_name VARCHAR2(100),
 * operation_method VARCHAR2(100),
 * cdto_name VARCHAR2(100),
 * action_name VARCHAR2(100),
 * hostseq VARCHAR2(100),
 * orgseq VARCHAR2(100),
 * tx_timer VARCHAR2(100),
 * tpfq VARCHAR2(100),
 * errorcode VARCHAR2(100),
 * trclass VARCHAR2(100),
 * web_timeout VARCHAR2(100),
 * web_intime VARCHAR2(100),
 * web_outtime VARCHAR2(100),
 * systemInTime VARCHAR2(100),
 * systemOutTime VARCHAR2(100),
 * system_date VARCHAR2(100),
 * error_message VARCHAR2(100),
 * logic_level VARCHAR2(100),
 * STF_intime VARCHAR2(100),
 * STF_outtime VARCHAR2(100),
 * BTF_intime VARCHAR2(100),
 * BTF_outtime VARCHAR2(100),
 * ETF_intime VARCHAR2(100),
 * ETF_outtime VARCHAR2(100),
 * INPUT_DTO VARCHAR2(4000),
 * OUTPUT_DTO VARCHAR2(4000),
 * CONSTRAINT TRANSACTION_OUTPUT PRIMARY KEY ( hostseq )
 * USING INDEX TABLESPACE TSP_CORE_IND )
 * TABLESPACE TSP_CORE
 * ;
 * 
 * CREATE SYNONYM TRANSACTION_OUTPUT FOR CORE.TRANSACTION_OUTPUT;
 * grant select,insert,update,delete on TRANSACTION_OUTPUT to eplaton;
 * 
 * ////////////////////////////////////////////////////////////////////////////////
 * 
 * //TRANSACTION_INFO ???��???뺣낫
 * // channel_type == *01:General UI, 02:Batch, 03:ATM, 10:Internet Banking
 * 
 * drop table transaction_info;
 * CREATE TABLE TRANSACTION_INFO(
 * transaction_id VARCHAR2(100) ,
 * host_name VARCHAR2(100) ,
 * system_name VARCHAR2(100) ,
 * method_name VARCHAR2(100) ,
 * bank_code VARCHAR2(100) ,
 * branch_code VARCHAR2(100) ,
 * user_id VARCHAR2(100) ,
 * channel_type VARCHAR2(100) ,
 * business_date VARCHAR2(100) ,
 * register_date VARCHAR2(100) ,
 * in_time VARCHAR2(100) ,
 * event_no VARCHAR2(100) ,
 * transaction_no VARCHAR2(100) ,
 * org_seq VARCHAR2(100) ,
 * out_time VARCHAR2(100) ,
 * response_time VARCHAR2(100) ,
 * error_code VARCHAR2(100) ,
 * ip_address VARCHAR2(100) ,
 * tpfq VARCHAR2(100) ,
 * CONSTRAINT PK_TRANSACTION_INFO PRIMARY KEY (transaction_id)
 * USING INDEX TABLESPACE TSP_CORE_IND
 * )
 * TABLESPACE TSP_CORE
 * /
 * 
 * grant select,insert,update,delete on TRANSACTION_INFO to eplaton;
 * conn eplaton/useplaton
 * /
 * CREATE SYNONYM TRANSACTION_INFO FOR CORE.TRANSACTION_INFO;
 * 
 * ==========================================================================================================
 * *
 */

public class OverseaDoc {
}
