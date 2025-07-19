package com.skax.eatool.foundation.tpmservice;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.io.*;
import java.util.*;
import java.rmi.*;
// Removed javax imports - converted to Spring Framework
// import javax.naming.*;
// import javax.ejb.*;
// import javax.transaction.*;
import java.math.BigDecimal;
// import javax.rmi.PortableRemoteObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import javax.sql.DataSource;

import com.skax.eatool.foundation.log.*;
import com.skax.eatool.foundation.base.*;
import com.skax.eatool.foundation.config.*;
import com.skax.eatool.foundation.jndi.*;
import com.skax.eatool.foundation.utility.*;

import com.skax.eatool.framework.transfer.*;
import com.skax.eatool.foundation.config.Config;
import com.skax.eatool.framework.constants.*;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.eplatonframework.business.delegate.action.*;
import com.skax.eatool.foundation.utility.*;
import com.skax.eatool.foundation.logej.LOGEJ;
import com.skax.eatool.framework.transaction.tcf.STF;

/*******************************************************************************
 * bizactionmap?�일??추�? ?�그
 * operation-class
 *******************************************************************************
 * <cashCard-listCashCard>
 * <bizaction-class>com.chb.coses.cashCard.business.delegate.action.CashCardBizAction</bizaction-class>
 * <transactionable>Y</transactionable>
 * <bizaction-method>listCashCard</bizaction-method>
 * <bizaction-parametertype>com.chb.coses.cashCard.transfer.CashCardConditionCDTO</bizaction-parametertype>
 * <operation-class>com.kdb.oversea.cashCard.business.facade.CashCardManageMent</operation-class>
 * </cashCard-listCashCard>
 * 
 ********************************************************************************/

@Service
public class TPMsvcutil extends AbstractTPMSVCAPI {
  private static final Logger logger = LoggerFactory.getLogger(TPMsvcutil.class);
  private static TPMsvcutil instance;

  @Autowired
  private org.springframework.context.ApplicationContext applicationContext;

  public Object ctx; // Spring ApplicationContext instead of JNDI Context
  public String url;
  public EPlatonCommonDTO cosescommon;
  public EPlatonEvent event;
  public IEvent result;

  public static synchronized TPMsvcutil getInstance(String ip, String port) {
    logger.info("==================[TPMsvcutil.getInstance START] - IP: {}, Port: {}", ip, port);
    try {
      if (instance == null) {
        try {
          instance = new TPMsvcutil(ip, port);
        } catch (Exception igex) {
          System.out.println(igex);
          return null;
        }
      }
      logger.info("==================[TPMsvcutil.getInstance END] - IP: {}, Port: {}", ip, port);
      return instance;
    } catch (Exception e) {
      logger.error("==================[TPMsvcutil.getInstance ERROR] - IP: {}, Port: {}, ?�러: {}", ip, port,
          e.getMessage(), e);
      throw e;
    }
  }

  public TPMsvcutil(String ip, String port) throws Exception {
    logger.info("==================[TPMsvcutil.TPMsvcutil START] - IP: {}, Port: {}", ip, port);
    try {
      url = "http://" + ip + ":" + port; // Changed from t3:// to http:// for Spring
      System.out.println("Spring Service URL : " + url);
      ctx = applicationContext; // Use Spring ApplicationContext
      event = new EPlatonEvent();
      logger.info("==================[TPMsvcutil.TPMsvcutil END] - IP: {}, Port: {}", ip, port);
    } catch (Exception ex) {
      logger.error("==================[TPMsvcutil.TPMsvcutil ERROR] - IP: {}, Port: {}, ?�러: {}", ip, port,
          ex.getMessage(), ex);
      ex.printStackTrace();
      throw ex;
    }
  }

  public static synchronized TPMsvcutil getInstance() {
    logger.info("==================[TPMsvcutil.getInstance START]");
    try {
      if (instance == null) {
        try {
          instance = new TPMsvcutil();
        } catch (Exception igex) {
          System.out.println(igex);
          return null;
        }
      }
      logger.info("==================[TPMsvcutil.getInstance END]");
      return instance;
    } catch (Exception e) {
      logger.error("==================[TPMsvcutil.getInstance ERROR] - {}", e.getMessage(), e);
      throw e;
    }
  }

  public TPMsvcutil() throws Exception {
    logger.info("==================[TPMsvcutil.TPMsvcutil START]");
    try {
      url = getProperty("service.call.url", "http://localhost:8080"); // Use Spring property
      System.out.println("--2 Spring Service URL : " + url);
      ctx = applicationContext; // Use Spring ApplicationContext
      event = new EPlatonEvent();
      logger.info("==================[TPMsvcutil.TPMsvcutil END]");
    } catch (Exception ex) {
      logger.error("==================[TPMsvcutil.TPMsvcutil ERROR] - {}", ex.getMessage(), ex);
      ex.printStackTrace();
      throw ex;
    }
  }

  /**
   * Spring-based context management - replaces JNDI Context
   */
  public org.springframework.context.ApplicationContext getSpringContext() {
    return applicationContext;
  }

  /**
   * Spring-based service lookup
   */
  public Object getSpringService(String serviceName) {
    try {
      return applicationContext.getBean(serviceName);
    } catch (Exception e) {
      logger.error("Failed to get Spring service: {}", serviceName, e);
      return null;
    }
  }

  /*****************************************************************************
   * ?�른 ?�스?�을 ?�출?�기 ?�한 조건
   *
   * [1] EPlatonCommonDTO??requestName ?�드�??�스?�명+?�출메소?�명 ?�로 ?�팅?�다
   * 
   * ?�예) com.kdb.oversea.cashCard.business.facade.CashCardManagementSBBean??
   * listCashCard 메소?�호출할 경우
   *
   * (1)cashCard.xml ?�일
   * <cashCard-listCashCard>
   * <bizaction-class>com.chb.coses.cashCard.business.delegate.action.CashCardBizAction</bizaction-class>
   * <transactionable>Y</transactionable>
   * <bizaction-method>listCashCard</bizaction-method>
   * <bizaction-parametertype>com.chb.coses.cashCard.transfer.CashCardConditionCDTO</bizaction-parametertype>
   * </cashCard-listCashCard>
   * 
   * (2) com.kdb.oversea.eplatonframework.transfer.EPlatonCommonDTO.requestName
   * ?�팅
   * com.kdb.oversea.eplatonframework.transfer.EPlatonCommonDTO.requestName =
   * cashCard-listCashCard
   *
   * [2] action ?�래?�명???�당 ?�우??xml ?�일로�???bizAction ?�래?�의 명을 가지�??�다
   * - ??값�? Config.xml?�일??"bizaction-map-filename"???�그값을 가지�??�다
   *
   * Spring Framework?�서???�음�?같이 처리?�니??
   *
   * String configFileName = getProperty("bizaction-map-filename",
   * "classpath:config/bizaction-map.xml");
   * = Spring Environment?�서 ?�정값을 가?�옵?�다
   * actionClassName = getConfigValue(requestName, "bizaction-class");
   * = Spring Config Bean???�해 ?�정값을 가?�옵?�다
   *
   * - cashCard.xml
   * <cashCard-listCashCard>
   * <bizaction-class>com.chb.coses.cashCard.business.delegate.action.CashCardBizAction</bizaction-class>
   * <transactionable>Y</transactionable>
   * <bizaction-method>listCashCard</bizaction-method>
   * <bizaction-parametertype>com.chb.coses.cashCard.transfer.CashCardConditionCDTO</bizaction-parametertype>
   * <operation-class>com.kdb.oversea.cashCard.business.facade.CashCardManageMent</operation-class>
   * </cashCard-listCashCard>
   *
   * [3] cashCard.xml???�음??추�??�다.
   * <operation-class>com.kdb.oversea.cashCard.business.facade.CashCardManageMent</operation-class>
   *****************************************************************************
   *
   * @return
   */

  public String TPgetactionclassname(EPlatonEvent event) {

    String configFileName = null;
    String requestName = null;
    String actionClassName = null;

    try {
      /***************************************************************************
       * BizAction??구성?�다.
       * [1] public static final String BIZDELEGATE_TAG = "bizaction-map-filename";
       * - <bizaction-map-filename>
       * /weblogic/bea/wlserver6.1/config/coses_US/applicationConfig/bizaction-map.xml
       * </bizaction-map-filename>
       * [2] requestName = event.getAction() = "cashCard-listCashCard"
       * [3] actionClassName = XMLCache.getInstance().getXML(configFileName).
       * getRootElement().getChild(requestName).
       * getChildTextTrim(Constants.ACTIONCLASS_TAG)
       * = com.chb.coses.cashCard.business.delegate.action.CashCardBizAction
       *
       ***************************************************************************/
      configFileName = getProperty("bizaction-map-filename", "classpath:config/bizaction-map.xml");
      requestName = (event.getTPSVCINFODTO()).getReqName();
      event.setAction(requestName);
      System.out.println("ConfigFileName : " + configFileName);
      System.out.println("RequestName : " + requestName);

      // Spring?�서??XML ?�정 ?�??직접 반환 (?�시 구현)
      actionClassName = "com.skcc.oversea.eplatonframework.business.delegate.action.DefaultBizAction";

      // BizAction?�서 구해??값들?�다.
      System.out.println("ActionClassName : " + actionClassName);
    } catch (Exception ex) {
      ex.printStackTrace();
      LOGEJ.getInstance().eprintf(5, event, ex);
      return null;
    }
    return actionClassName;
  }

  protected void TPgetrequestinfo(EPlatonEvent event) {
    String configFileName = getProperty("bizaction-map-filename", "classpath:config/bizaction-map.xml");
    String requestName = event.getAction();
    String actionClassName = getConfigValue(requestName, "bizaction-class");
    String methodName = getConfigValue(requestName, "bizaction-method");
    String parameterTypeName = getConfigValue(requestName, "bizaction-parametertype");

    System.out.println("ConfigFileName : " + configFileName);
    System.out.println("RequestName : " + requestName);
    System.out.println("methodName : " + methodName);
    System.out.println("parameterTypeName : " + parameterTypeName);
  }

  public String TPgetinvokemethodname(EPlatonEvent event) {

    String configFileName = null;
    String requestName = null;
    String methodName = null;

    try {
      /***************************************************************************
       * BizAction??구성?�다.
       * [1] public static final String BIZDELEGATE_TAG = "bizaction-map-filename";
       * - <bizaction-map-filename>
       * /weblogic/bea/wlserver6.1/config/coses_US/applicationConfig/bizaction-map.xml
       * </bizaction-map-filename>
       * [2] requestName = event.getAction() = "cashCard-listCashCard"
       * [3] actionClassName = XMLCache.getInstance().getXML(configFileName).
       * getRootElement().getChild(requestName).
       * getChildTextTrim(Constants.ACTIONCLASS_TAG)
       * = com.chb.coses.cashCard.business.delegate.action.CashCardBizAction
       *
       ***************************************************************************/
      configFileName = getProperty("bizaction-map-filename", "classpath:config/bizaction-map.xml");
      requestName = event.getAction();
      methodName = getConfigValue(requestName, "bizaction-method"); // Spring-based config lookup

      // BizAction?�서 구해??값들?�다.
      System.out.println("ConfigFileName : " + configFileName);
      System.out.println("RequestName : " + requestName);
      System.out.println("methodName : " + methodName);
    } catch (Exception ex) {
      ex.printStackTrace();
      LOGEJ.getInstance().eprintf(5, event, ex);
      return null;
    }
    return methodName;
  }

  /**
   * request_name = "cashCard-listCashcard"
   * = "system" + "-" + "method"
   * 
   * @param request_name
   * @return
   */
  public String TPgetcallsystemname(String request_name) {
    System.out.println("[request_name]-" + request_name);
    if (request_name == null) {
      return null;
    } else {
      int lastSlash = request_name.lastIndexOf('-');
      String method_name = request_name.substring(lastSlash + 1);
      String operation_classname = request_name.substring(0, lastSlash);
      return operation_classname;
    }
  }

  /**
   * request_name = "cashCard-listCashcard"
   * = "system" + "-" + "method"
   * 
   * @param request_name
   * @return
   */
  public String TPgetcallmethodname(String request_name) {
    if (request_name == null) {
      return null;
    } else {
      int lastSlash = request_name.lastIndexOf('-');
      String method_name = request_name.substring(lastSlash + 1);
      String operation_classname = request_name.substring(0, lastSlash);
      return method_name;
    }
  }

  public static final String OPERATION_TAG = "operation-class";

  public String TPgetoperationclassname(EPlatonEvent event) {

    String configFileName = null;
    String requestName = null;
    String operationclass = null;

    try {
      /***************************************************************************
       * BizAction??구성?�다.
       * [1] public static final String BIZDELEGATE_TAG = "bizaction-map-filename";
       * - <bizaction-map-filename>
       * /weblogic/bea/wlserver6.1/config/coses_US/applicationConfig/bizaction-map.xml
       * </bizaction-map-filename>
       * [2] requestName = event.getAction() = "cashCard-listCashCard"
       * [3] actionClassName = XMLCache.getInstance().getXML(configFileName).
       * getRootElement().getChild(requestName).
       * getChildTextTrim(Constants.ACTIONCLASS_TAG)
       * = com.chb.coses.cashCard.business.delegate.action.CashCardBizAction
       *
       ***************************************************************************/
      configFileName = getProperty("bizaction-map-filename", "classpath:config/bizaction-map.xml");
      requestName = event.getAction();
      operationclass = getConfigValue(requestName, "operation-class"); // Spring-based config lookup

      // BizAction?�서 구해??값들?�다.
      System.out.println("ConfigFileName : " + configFileName);
      System.out.println("RequestName : " + requestName);
      System.out.println("operationclass : " + operationclass);
    } catch (Exception ex) {
      ex.printStackTrace();
      LOGEJ.getInstance().eprintf(5, event, ex);
      return null;
    }
    return operationclass;
  }

  public String TPgetparametertypename(EPlatonEvent event) {

    String configFileName = null;
    String requestName = null;
    String parameterTypeName = null;

    try {
      /***************************************************************************
       * BizAction??구성?�다.
       * [1] public static final String BIZDELEGATE_TAG = "bizaction-map-filename";
       * - <bizaction-map-filename>
       * /weblogic/bea/wlserver6.1/config/coses_US/applicationConfig/bizaction-map.xml
       * </bizaction-map-filename>
       * [2] requestName = event.getAction() = "cashCard-listCashCard"
       * [3] actionClassName = XMLCache.getInstance().getXML(configFileName).
       * getRootElement().getChild(requestName).
       * getChildTextTrim(Constants.ACTIONCLASS_TAG)
       * = com.chb.coses.cashCard.business.delegate.action.CashCardBizAction
       *
       ***************************************************************************/
      configFileName = getProperty("bizaction-map-filename", "classpath:config/bizaction-map.xml");
      requestName = event.getAction();
      parameterTypeName = getConfigValue(requestName, "bizaction-parametertype"); // Spring-based config lookup

      // BizAction?�서 구해??값들?�다.
      System.out.println("ConfigFileName : " + configFileName);
      System.out.println("RequestName : " + requestName);
      System.out.println("parameterTypeName : " + parameterTypeName);
    } catch (Exception ex) {
      ex.printStackTrace();
      LOGEJ.getInstance().eprintf(5, event, ex);
      return null;
    }
    return parameterTypeName;
  }

  // UserTransaction methods replaced with Spring @Transactional
  public int TPinfo() {
    // Spring manages transactions automatically with @Transactional
    return 0; // Always return ready state for Spring transactions
  }

  /**
   * Spring-based transaction management - no UserTransaction needed
   * 
   * @deprecated Use Spring @Transactional annotations instead
   */
  @Deprecated
  public Object getTransactionManager() {
    // Spring handles transactions automatically with @Transactional
    return applicationContext.getBean("transactionManager");
  }

  /**
   * Spring transaction management - @Transactional handles begin/commit/rollback
   * 
   * @deprecated Use Spring @Transactional annotations instead
   */
  @Deprecated
  public boolean TPbegin(int second) {
    // Spring @Transactional automatically begins transactions
    System.out.println("Spring transaction will be managed automatically");
    return true;
  }

  public boolean TPbegin(String second) throws Exception {
    return true;
  }

  public boolean TPbegin() throws Exception {
    return true;
  }

  /**
   * Spring transaction management - @Transactional handles commit automatically
   * 
   * @deprecated Use Spring @Transactional annotations instead
   */
  @Deprecated
  public boolean TPcommit() {
    // Spring @Transactional automatically commits transactions
    System.out.println("Spring transaction will be committed automatically");
    return true;
  }

  /**
   * Spring transaction management - @Transactional handles rollback automatically
   * 
   * @deprecated Use Spring @Transactional annotations instead
   */
  @Deprecated
  public boolean TProllback() {
    // Spring @Transactional automatically rolls back transactions on exceptions
    System.out.println("Spring transaction will be rolled back automatically on exceptions");
    return true;
  }

  /**
   * 행위논리
   * 1. 라이언트로부터 최초로 전달받는 요청
   * 2. 기본 검증을 수행
   * 3. 거래번호를 채번한다.
   *
   * @param event : 라이언트의 요청 메시지 객체
   * @return
   */
  public EPlatonEvent TPSrecv(EPlatonEvent event) {
    Connection con = null;
    String hostseq = null;
    EPlatonCommonDTO commonDTO = (EPlatonCommonDTO) event.getCommon();
    TPSVCINFODTO tpsvcinfo = (TPSVCINFODTO) event.getTPSVCINFODTO();

    try {
      /*************************************************************************
       * 모든 EJB 서버로 전달되는 TPM 정보를 관리한다
       *************************************************************************/
      HashMap hm = new HashMap();
      tpsvcinfo.setTpmsvcinfolist(hm);

      /*************************************************************************
       * 출력 서버로 정보를 전달한다.
       *************************************************************************/
      // 출력 서버 이름
      tpsvcinfo.setSystem_name(TPMsvcutil.getInstance().TPgetcallsystemname(tpsvcinfo.getReqName()));
      // 출력 서버로 호출할 action 클래스명
      tpsvcinfo.setAction_name(TPMsvcutil.getInstance().TPgetactionclassname(event));
      // 출력 서버 클래스명
      tpsvcinfo.setOperation_name(TPMsvcutil.getInstance().TPgetoperationclassname(event));
      // 출력 서버 메소드명
      tpsvcinfo.setOperation_method(TPMsvcutil.getInstance().TPgetinvokemethodname(event));
      // 출력 서버 메소드에 전달할 CDTO 클래스명
      tpsvcinfo.setCdto_name(TPMsvcutil.getInstance().TPgetparametertypename(event));

      /*
       * 라이언트로부터 비비호출시 거래코드가 팅되어 있는지 조사한다.
       * 거래코드가 없을 경우에만 비비호출을 수행한다.
       * 1차적 검증을 수행한다.
       */
      if (commonDTO.getEventNo() == null) {
        tpsvcinfo.setErrorcode("EDEL0001");
        tpsvcinfo.setError_message("EPlatonCommonDTO.eventno is not set");
        commonDTO.setEventNo("********");
        LOGEJ.getInstance().printf(3, event, "EPlatonCommonDTO.eventno is not set");
      }

      /*
       * 라이언트로부터 비비호출시 라이언트의 벨호가 팅되어 있는지 조사한다.
       * 라이언트의 벨호가 없을 경우에만 비비호출을 수행한다.
       * 1차적 검증을 수행한다.
       *
       * TPFQ 정보 *
       * 100 : Server System - Server System 동기 => 거래번호 규채번 => OrgSeq 그룹?
       * 200 : WAF - Server System 동기 => 거래번호 규채번 => OrgSeq 규채번 번호 규채번?
       * 300 : ATM - Server System 동기 => 거래번호 규채번 => OrgSeq 규채번 번호 규채번?
       * 400 : ATM - Server System 동기 => 거래번호 규채번 => OrgSeq 규채번 번호 규채번?
       */
      if (tpsvcinfo.getTpfq() != null) {
        if (!tpsvcinfo.getTpfq().equals("100")) {
          tpsvcinfo.setHostseq("********");
          tpsvcinfo.setOrgseq("********");
        } else {
          tpsvcinfo.setHostseq("********");
        }
      } else {
        tpsvcinfo.setErrorcode("EDEL0002");
        tpsvcinfo.setError_message("TPFQ IS NULL");
        LOGEJ.getInstance().printf(3, event, "TPFQ IS NULL");
      }

      /*
       * Spring DataSource로 부터 DB 연결을 구한다.
       */
      try {
        javax.sql.DataSource dataSource = getService(javax.sql.DataSource.class);
        con = dataSource.getConnection();
        hostseq = CommonUtil.gethostseq(con);
      } catch (SQLException ex) {
        ex.printStackTrace();
        hostseq = null;
      }

      /*
       * DB 세션에 거래번호가 상태가 아니면 채번되어 있는지 조사한다.
       */
      if (con != null && hostseq != null) {
        if (!tpsvcinfo.getTpfq().equals("100")) {
          tpsvcinfo.setHostseq(hostseq);
          tpsvcinfo.setOrgseq(hostseq);
        } else {
          tpsvcinfo.setHostseq(hostseq);
        }
      } else {
        tpsvcinfo.setErrorcode("EDL999");
        tpsvcinfo.setError_message("TPSrecv gethostseq() error");
        LOGEJ.getInstance().printf(3, event,
            "==============================================================================");
        LOGEJ.getInstance().printf(3, (EPlatonEvent) event,
            "TPSrecv()-" + com.skax.eatool.foundation.utility.Reflector.objectToString(event));
        LOGEJ.getInstance().printf(3, event, "EDL999오류가 발생");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
      tpsvcinfo.setErrorcode("EDL997");
      tpsvcinfo.setError_message("TPSrecv Connection get error");
      LOGEJ.getInstance().eprintf(5, event, ex);
      LOGEJ.getInstance().printf(3, event,
          "==============================================================================");
      LOGEJ.getInstance().printf(3, (EPlatonEvent) event,
          "TPSrecv()-" + com.skax.eatool.foundation.utility.Reflector.objectToString(event));
      LOGEJ.getInstance().printf(3, event, "EDL997 오류가 발생");
    }

    try {
      con.close();
    } catch (Exception connex) {
      connex.printStackTrace();
    }

    System.out.println("==============================================================================");
    System.out.println("TPSrecv()-" + com.skax.eatool.foundation.utility.Reflector.objectToString(event));

    LOGEJ.getInstance().printf(3, event,
        "==============================================================================");
    LOGEJ.getInstance().printf(3, (EPlatonEvent) event,
        "TPSrecv()-" + com.skax.eatool.foundation.utility.Reflector.objectToString(event));
    return event;
  }

  /**
   * 클라이언트로 응답을 송신하고 송신정보를 기록한다
   * 
   * @param event : 클라이언트의 서버로 메시지 송수신 객체
   * @return
   */

  public EPlatonEvent TPSsend(EPlatonEvent event) {
    EPlatonCommonDTO commonDTO = (EPlatonCommonDTO) event.getCommon();
    TPSVCINFODTO tpsvcinfo = (TPSVCINFODTO) event.getTPSVCINFODTO();
    LOGEJ.getInstance().printf(3, event, "errorcode:[" + tpsvcinfo.getErrorcode() + "]");
    LOGEJ.getInstance().printf(3, (EPlatonEvent) event,
        "TPSsend()-" + com.skax.eatool.foundation.utility.Reflector.objectToString(event));
    LOGEJ.getInstance().printf(3, event,
        "==============================================================================\n\n");

    System.out.println("errorcode:[" + tpsvcinfo.getErrorcode() + "]");
    System.out.println("TPSsend()-" + com.skax.eatool.foundation.utility.Reflector.objectToString(event));
    System.out.println("==============================================================================\n\n");

    return event;
  }

}
