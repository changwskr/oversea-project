package com.skax.eatool.eplatonframework.business.helper;

import com.skax.eatool.eplatonframework.business.service.CommonService;
import com.skax.eatool.eplatonframework.business.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SpringServiceUtils {

    private static final Logger logger = LoggerFactory.getLogger(SpringServiceUtils.class);

    @Autowired
    private CommonService commonService;

    @Autowired
    private ReferenceService referenceService;

    public CommonService getCommonService() {
        logger.info("==================[SpringServiceUtils.getCommonService START]");
        try {
            logger.info("==================[SpringServiceUtils.getCommonService END]");
            return commonService;
        } catch (Exception e) {
            logger.error("==================[SpringServiceUtils.getCommonService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    public void setCommonService(CommonService commonService) {
        logger.info("==================[SpringServiceUtils.setCommonService START]");
        try {
            this.commonService = commonService;
            logger.info("==================[SpringServiceUtils.setCommonService END]");
        } catch (Exception e) {
            logger.error("==================[SpringServiceUtils.setCommonService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    public ReferenceService getReferenceService() {
        logger.info("==================[SpringServiceUtils.getReferenceService START]");
        try {
            logger.info("==================[SpringServiceUtils.getReferenceService END]");
            return referenceService;
        } catch (Exception e) {
            logger.error("==================[SpringServiceUtils.getReferenceService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    public void setReferenceService(ReferenceService referenceService) {
        logger.info("==================[SpringServiceUtils.setReferenceService START]");
        try {
            this.referenceService = referenceService;
            logger.info("==================[SpringServiceUtils.setReferenceService END]");
        } catch (Exception e) {
            logger.error("==================[SpringServiceUtils.setReferenceService ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }
}
