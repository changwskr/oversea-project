package com.skax.eatool.eplatonframework.business.facade.ecommon;

import java.text.*;
import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.eplatonframework.transfer.EPlatonCommonDTO;
import com.skax.eatool.framework.exception.CosesAppException;
import com.skax.eatool.foundation.utility.*;
import com.skax.eatool.foundation.logej.*;

/**
 * =============================================================================
 * ?λ‘κ·Έλ¨ ?€λͺ: Spring Boot κΈ°λ° ECommon Management Service
 * =============================================================================
 * Spring Boot ?κ²½?μ ECommon κ΄λ¦??λΉ?€λ? ?κ³΅?λ ?΄λ?€μ?λ€.
 * EJB?μ Spring Bootλ‘?λ§μ΄κ·Έλ ?΄μ???λΉ?€μ?λ€.
 * 
 * =============================================================================
 * λ³κ²½λ΄???λ³΄:
 * =============================================================================
 *  2004??03??16??1μ°¨λ²??release (EJB)
 *  2024??Spring Boot λ³???λ£
 *
 * =============================================================================
 *                                                        @author : ?₯μ°??WooSungJang)
 *                                                        @company: IMS SYSTEM
 *                                                        @email  : changwskr@yahoo.co.kr
 *                                                        @version 2.0 (Spring Boot)
 *  =============================================================================
 */

@Service
@Transactional
public class ECommonManagementSBBean {

    private static final Logger logger = LoggerFactory.getLogger(ECommonManagementSBBean.class);

    /**
     * ECommon κ΄λ¦??΄λ²€?Έλ? μ²λ¦¬?λ λ©μ??     * Spring Boot ?κ²½?μ ?Έλ????κ΄λ¦¬ν©?λ€.
     */
    @Transactional
    public EPlatonEvent execute(EPlatonEvent event) throws CosesAppException {
        EPlatonEvent resp_event = null;
        
        try {
            resp_event = event;
            
            logger.info("ECommon Management Service execute started for event: {}", event);
            
            EPlatonCommonDTO commonDTO = (EPlatonCommonDTO) event.getCommon();
            TPSVCINFODTO tpsvcinfo = event.getTPSVCINFODTO();
            
            logger.debug("Processing ecommon management request for transaction: {}", 
                        tpsvcinfo != null ? tpsvcinfo.getTransaction_id() : "unknown");
            
            // ?μ²­ ??μ ?°λ₯Έ μ²λ¦¬
            String requestType = tpsvcinfo != null ? tpsvcinfo.getReqName() : "";
            
            switch (requestType) {
                case "QUERY_ECOMMON":
                    return processQueryECommon(event);
                case "CREATE_ECOMMON":
                    return processCreateECommon(event);
                case "UPDATE_ECOMMON":
                    return processUpdateECommon(event);
                case "DELETE_ECOMMON":
                    return processDeleteECommon(event);
                default:
                    logger.warn("Unknown request type: {}", requestType);
                    return event;
            }
            
        } catch (Exception re) {
            logger.error("Error in ECommon Management Service execute", re);
            
            // ?λ¬ μ²λ¦¬
            if (resp_event != null) {
                TPSVCINFODTO tpsvcinfo = resp_event.getTPSVCINFODTO();
                if (tpsvcinfo != null) {
                    String errorCode = tpsvcinfo.getErrorcode();
                    if (errorCode != null && errorCode.length() > 0) {
                        switch (errorCode.charAt(0)) {
                            case 'I':
                                tpsvcinfo.setErrorcode("EFWK0041");
                                tpsvcinfo.setError_message(this.getClass().getName() + ".execute():" + re.toString());
                                break;
                            case 'E':
                                String newErrorCode = "EFWK0041" + "|" + tpsvcinfo.getErrorcode();
                                tpsvcinfo.setErrorcode(newErrorCode);
                                tpsvcinfo.setError_message(this.getClass().getName() + ".execute():" + re.toString());
                                break;
                            default:
                                tpsvcinfo.setErrorcode("EFWK0041");
                                tpsvcinfo.setError_message(this.getClass().getName() + ".execute():" + re.toString());
                                break;
                        }
                    } else {
                        tpsvcinfo.setErrorcode("EFWK0041");
                        tpsvcinfo.setError_message(this.getClass().getName() + ".execute():" + re.toString());
                    }
                }
            }
            
            // λ‘κΉ
            LOGEJ.getInstance().eprintf(5, event, re);
            if (resp_event != null) {
                LOGEJ.getInstance().printf(1, resp_event, this.getClass().getName() + ".execute():" + re.toString());
            }
            
            throw new CosesAppException("ECommon Management Service execution failed", re);
        }
    }

    /**
     * ECommon μ‘°ν μ²λ¦¬
     */
    @Transactional(readOnly = true)
    private EPlatonEvent processQueryECommon(EPlatonEvent event) throws CosesAppException {
        logger.debug("Processing query ecommon request");
        
        try {
            // ECommon μ‘°ν λ‘μ§ κ΅¬ν
            // TODO: ?€μ  λΉμ¦?μ€ λ‘μ§ κ΅¬ν
            
            logger.info("Query ecommon completed successfully");
            return event;
            
        } catch (Exception e) {
            logger.error("Error processing query ecommon", e);
            throw new CosesAppException("Failed to query ecommon", e);
        }
    }

    /**
     * ECommon ?μ± μ²λ¦¬
     */
    @Transactional
    private EPlatonEvent processCreateECommon(EPlatonEvent event) throws CosesAppException {
        logger.debug("Processing create ecommon request");
        
        try {
            // ECommon ?μ± λ‘μ§ κ΅¬ν
            // TODO: ?€μ  λΉμ¦?μ€ λ‘μ§ κ΅¬ν
            
            logger.info("Create ecommon completed successfully");
            return event;
            
        } catch (Exception e) {
            logger.error("Error processing create ecommon", e);
            throw new CosesAppException("Failed to create ecommon", e);
        }
    }

    /**
     * ECommon ?μ  μ²λ¦¬
     */
    @Transactional
    private EPlatonEvent processUpdateECommon(EPlatonEvent event) throws CosesAppException {
        logger.debug("Processing update ecommon request");
        
        try {
            // ECommon ?μ  λ‘μ§ κ΅¬ν
            // TODO: ?€μ  λΉμ¦?μ€ λ‘μ§ κ΅¬ν
            
            logger.info("Update ecommon completed successfully");
            return event;
            
        } catch (Exception e) {
            logger.error("Error processing update ecommon", e);
            throw new CosesAppException("Failed to update ecommon", e);
        }
    }

    /**
     * ECommon ??  μ²λ¦¬
     */
    @Transactional
    private EPlatonEvent processDeleteECommon(EPlatonEvent event) throws CosesAppException {
        logger.debug("Processing delete ecommon request");
        
        try {
            // ECommon ??  λ‘μ§ κ΅¬ν
            // TODO: ?€μ  λΉμ¦?μ€ λ‘μ§ κ΅¬ν
            
            logger.info("Delete ecommon completed successfully");
            return event;
            
        } catch (Exception e) {
            logger.error("Error processing delete ecommon", e);
            throw new CosesAppException("Failed to delete ecommon", e);
        }
    }
}
