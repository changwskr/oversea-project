package com.skcc.oversea.foundation.tpmservice;

/**
 * <p>Title: Spring-based TPC Send/Receive Service</p>
 * <p>Description: Converted from EJB to Spring Framework</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 2.0
 */

import java.io.*;
import java.util.*;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skcc.oversea.eplatonframework.business.EPlatonBizDelegateSBBean;
import com.skcc.oversea.eplatonframework.transfer.EPlatonCommonDTO;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.eplatonframework.transfer.TPSVCINFODTO;

import com.skcc.oversea.framework.exception.*;
import com.skcc.oversea.framework.transfer.*;
import com.skcc.oversea.foundation.utility.CommonUtil;

@Service
public class TPCsendrecv {
  private static final Logger logger = LoggerFactory.getLogger(TPCsendrecv.class);
  private static TPCsendrecv instance;
  
  @Autowired
  private EPlatonBizDelegateSBBean bizDelegateService;
  
  public int isUse = 0;

  public static synchronized TPCsendrecv getInstance(String ip, String port) {
    if (instance == null) {
      try {
        instance = new TPCsendrecv();
        logger.info("TPCsendrecv instance created for {}:{}", ip, port);
      } catch (Exception igex) {
        logger.error("Failed to create TPCsendrecv instance", igex);
        return null;
      }
    }
    return instance;
  }

  public TPCsendrecv() {
    // Spring-based constructor
    logger.info("TPCsendrecv initialized with Spring framework");
  }

  /**
   * Legacy constructor for backward compatibility
   * @param ip IP address (not used in Spring version)
   * @param port Port number (not used in Spring version)
   */
  public TPCsendrecv(String ip, String port) {
    // Spring-based constructor with legacy parameters
    logger.info("TPCsendrecv initialized with Spring framework (legacy: {}:{})", ip, port);
    // Note: IP and port are not used in Spring version as services are injected
    
    // For non-Spring context usage, try to get instance from Spring context
    try {
      // This will work if running in Spring context
      if (bizDelegateService == null) {
        logger.warn("bizDelegateService is null - running outside Spring context");
        // Try to get instance from static method
        instance = this;
      }
    } catch (Exception e) {
      logger.warn("Failed to access Spring services: {}", e.getMessage());
      // Set this as the static instance for non-Spring usage
      instance = this;
    }
  }

  /**
   * Get business delegate service (Spring-based)
   * @return EPlatonBizDelegateSBBean instance
   */
  public EPlatonBizDelegateSBBean getBizDelegateService() {
    return bizDelegateService;
  }



  /**
   * Spring-based service call
   * Converted from EJB to Spring Service call
   *
   * @param request_name Service request name
   * @param call_timeout Timeout value
   * @param pevent EPlatonEvent object
   * @return Response event
   */
  @Transactional
  public synchronized Object callService(String request_name,
                                     String call_timeout,
                                     EPlatonEvent pevent)
  {
    IEvent response_event = pevent;

    try {
      EPlatonEvent cevent = pevent;
      TPSVCINFODTO tpsvcinfoDTO = pevent.getTPSVCINFODTO();
      EPlatonCommonDTO commonDTO = pevent.getCommon();

      Object tobj = pevent.getRequest();
      if(!(tobj instanceof IDTO) ) {
        tpsvcinfoDTO.setErrorcode("EBDL001");
        tpsvcinfoDTO.setError_message("IDTO 객체가 아니다");
        return cevent;
      }

      tpsvcinfoDTO.setSystem_name(TPMSVCAPI.getInstance().TPgetcallsystemname(request_name));
      commonDTO.setReqName(request_name);
      tpsvcinfoDTO.setReqName(request_name);
      tpsvcinfoDTO.setTpfq("200");
      tpsvcinfoDTO.setErrorcode("IZZ000");
      tpsvcinfoDTO.setTx_timer(call_timeout);

      logger.info("호출시스템명: {}", tpsvcinfoDTO.getSystem_name());
      logger.info("호출request-name: {}", tpsvcinfoDTO.getReqName());

      commonDTO.setTimeZone("GMT+09:00");
      commonDTO.setFxRateCount(1);
      pevent.setAction(tpsvcinfoDTO.getReqName());

      // Spring-based service call
      if (bizDelegateService == null) {
        logger.error("bizDelegateService is null - cannot execute service call");
        tpsvcinfoDTO.setErrorcode("EBLD0002");
        tpsvcinfoDTO.setError_message("Business delegate service not available");
        return cevent;
      }
      response_event = bizDelegateService.execute(cevent);

      logger.debug("Response event: {}", response_event);

      String errorCode = ((EPlatonEvent)response_event).getTPSVCINFODTO().getErrorcode();
      String errorMessage = ((EPlatonEvent)response_event).getTPSVCINFODTO().getError_message();

      logger.info("TPCsendrecv errorcode: {}", errorCode);
      logger.info("TPCsendrecv errormesg: {}", errorMessage);

      if( response_event.getResponse() == null ){
        logger.warn("호출request-name: {}", tpsvcinfoDTO.getReqName());
        logger.warn("TPCsendrecv fail - 리턴된 CDTO 객체가 NULL입니다.");
        return response_event;
      }
      else{
        logger.info("TPCsendrecv success");
        return response_event;
      }
    }
    catch(Exception e)
    {
      logger.error("TPCsendrecv error", e);

      ((EPlatonEvent)response_event).getTPSVCINFODTO().setErrorcode("EBLD0001");
      ((EPlatonEvent)response_event).getTPSVCINFODTO().setError_message("Call TPCsendrecv() error");
      return response_event;
    }
  }

  /**
   * Legacy method for backward compatibility
   * @deprecated Use callService instead
   */
  @Deprecated
  public synchronized Object callEJB(String request_name, String call_timeout, EPlatonEvent pevent) {
    return callService(request_name, call_timeout, pevent);
  }


  /**
   * Spring-based service call with custom request DTO
   * Converted from EJB to Spring Service call
   *
   * @param request_name Service request name
   * @param call_timeout Timeout value
   * @param request_cdto Request CDTO object
   * @param pevent EPlatonEvent object
   * @return Response event
   */
  @Transactional
  public synchronized Object callService(String request_name,
                                     String call_timeout,
                                        IDTO request_cdto,
                                     EPlatonEvent pevent)
  {
    IEvent response_event = pevent;

    try {
      EPlatonEvent cevent = pevent;
      TPSVCINFODTO tpsvcinfoDTO = pevent.getTPSVCINFODTO();
      EPlatonCommonDTO commonDTO = pevent.getCommon();

      Object tobj = request_cdto;
      if(!(tobj instanceof IDTO) ) {
        tpsvcinfoDTO.setErrorcode("EBDL001");
        tpsvcinfoDTO.setError_message("IDTO 객체가 아니다");
        return cevent;
      }
      else{
        cevent.setRequest(request_cdto);
      }

      tpsvcinfoDTO.setSystem_name(TPMSVCAPI.getInstance().TPgetcallsystemname(request_name));
      commonDTO.setReqName(request_name);
      tpsvcinfoDTO.setReqName(request_name);
      tpsvcinfoDTO.setTpfq("200");
      tpsvcinfoDTO.setErrorcode("IZZ000");
      tpsvcinfoDTO.setTx_timer(call_timeout);

      logger.info("호출시스템명: {}", tpsvcinfoDTO.getSystem_name());
      logger.info("호출request-name: {}", tpsvcinfoDTO.getReqName());

      commonDTO.setTimeZone("GMT+09:00");
      commonDTO.setFxRateCount(1);
      pevent.setAction(tpsvcinfoDTO.getReqName());

      // Spring-based service call
      if (bizDelegateService == null) {
        logger.error("bizDelegateService is null - cannot execute service call");
        tpsvcinfoDTO.setErrorcode("EBLD0002");
        tpsvcinfoDTO.setError_message("Business delegate service not available");
        return cevent;
      }
      response_event = bizDelegateService.execute(cevent);

      String errorCode = ((EPlatonEvent)response_event).getTPSVCINFODTO().getErrorcode();
      if( errorCode.substring(0,6).equals("IZZ000") ) {
        logger.info("TPCsendrecv Success");
      } else {
        logger.warn("TPCsendrecv Fail");
      }

      logger.debug("Response event: {}", response_event);

      if( response_event.getResponse() == null ){
        logger.warn("TPCsendrecv fail - 리턴된 CDTO 객체가 NULL입니다.");
        return response_event;
      }
      else{
        return response_event;
      }
    }
    catch(Exception e)
    {
      logger.error("TPCsendrecv error", e);
      ((EPlatonEvent)response_event).getTPSVCINFODTO().setErrorcode("EBLD0001");
      ((EPlatonEvent)response_event).getTPSVCINFODTO().setError_message("Call TPCsendrecv() error");
      return response_event;
    }
  }

  /**
   * Legacy method for backward compatibility
   * @deprecated Use callService instead
   */
  @Deprecated
  public synchronized Object callEJB(String request_name, String call_timeout, IDTO request_cdto, EPlatonEvent pevent) {
    return callService(request_name, call_timeout, request_cdto, pevent);
  }

}



