
package com.skcc.oversea.cashCard.business.cashCardRule;

import com.skcc.oversea.foundation.security.SecurityManager;
import com.skcc.oversea.cashCard.business.cashCard.CashCardSBBean;
import com.skcc.oversea.cashCard.business.cashCard.model.CashCardDDTO;
import com.skcc.oversea.cashCard.business.cashCard.model.HotCardDDTO;
import com.skcc.oversea.framework.transfer.CosesCommonDTO;
import com.skcc.oversea.framework.exception.CosesAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class CashCardRuleSBBean implements ICashCardRuleSB {

    private static final Logger logger = LoggerFactory.getLogger(CashCardRuleSBBean.class);

    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private CashCardSBBean cashCardSBBean;

    @Override
    public String getSystemParameter(String parameterId) {
        logger.info("==================[CashCardRuleSBBean.getSystemParameter START] - parameterId: {}", parameterId);
        try {
            // 시스템 파라미터 조회 로직
            String result = "PARAM_VALUE";
            logger.info("==================[CashCardRuleSBBean.getSystemParameter END] - parameterId: {}", parameterId);
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.getSystemParameter ERROR] - parameterId: {}, 에러: {}", parameterId, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String modifyCashCardRule(String ruleId) {
        logger.info("==================[CashCardRuleSBBean.modifyCashCardRule START] - ruleId: {}", ruleId);
        try {
            // 캐시카드 규칙 수정 로직
            String result = "MODIFY_SUCCESS";
            logger.info("==================[CashCardRuleSBBean.modifyCashCardRule END] - ruleId: {}", ruleId);
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.modifyCashCardRule ERROR] - ruleId: {}, 에러: {}", ruleId, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public String deleteCashCardRule(String ruleId) {
        logger.info("==================[CashCardRuleSBBean.deleteCashCardRule START] - ruleId: {}", ruleId);
        try {
            // 캐시카드 규칙 삭제 로직
            String result = "DELETE_SUCCESS";
            logger.info("==================[CashCardRuleSBBean.deleteCashCardRule END] - ruleId: {}", ruleId);
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.deleteCashCardRule ERROR] - ruleId: {}, 에러: {}", ruleId, e.getMessage(), e);
            throw e;
        }
    }

    // ======================== Controller Service Methods (Rule Layer) ========================//

    /**
     * 카드 발급 규칙 검증 및 처리
     */
    @Override
    public CashCardDDTO issueCashCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardRuleSBBean.issueCashCard START] - 고객명: {}, 계좌번호: {}", 
                   cashCardDDTO.getCIFName(), cashCardDDTO.getPrimaryAccountNo());
        try {
            // 1. 비즈니스 규칙 검증
            String ruleResult = getSystemParameter("CARD_ISSUANCE_RULE");
            logger.info("Card issuance rule validation result: {}", ruleResult);
            
            // 2. 추가 규칙 검증 (예: 일일 한도 검증, 고객 상태 검증 등)
            validateCardIssuanceRules(cashCardDDTO);
            
            // 3. Thing 계층에서 실제 카드 발급 처리
            CashCardDDTO result = cashCardSBBean.makeCashCard(cashCardDDTO, commonDTO);
            
            logger.info("==================[CashCardRuleSBBean.issueCashCard END] - 고객명: {}, 계좌번호: {}", 
                       cashCardDDTO.getCIFName(), cashCardDDTO.getPrimaryAccountNo());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.issueCashCard ERROR] - 고객명: {}, 계좌번호: {}, 에러: {}", 
                        cashCardDDTO.getCIFName(), cashCardDDTO.getPrimaryAccountNo(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 정보 조회 규칙 검증 및 처리
     */
    @Override
    public CashCardDDTO getCashCardInfo(String cardNumber, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardRuleSBBean.getCashCardInfo START] - 카드번호: {}", cardNumber);
        try {
            // 1. 조회 권한 규칙 검증
            String ruleResult = getSystemParameter("CARD_SEARCH_RULE");
            logger.info("Card search rule validation result: {}", ruleResult);
            
            // 2. 추가 규칙 검증 (예: 카드 상태 검증, 조회 권한 검증 등)
            validateCardSearchRules(cardNumber);
            
            // 3. Thing 계층에서 실제 카드 정보 조회
            CashCardDDTO cashCardDDTO = new CashCardDDTO();
            cashCardDDTO.setCardNumber(cardNumber);
            CashCardDDTO result = cashCardSBBean.findCashCardInfoByCardNo(cashCardDDTO, commonDTO);
            
            logger.info("==================[CashCardRuleSBBean.getCashCardInfo END] - 카드번호: {}", cardNumber);
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.getCashCardInfo ERROR] - 카드번호: {}, 에러: {}", 
                        cardNumber, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 고객명으로 카드 정보 조회 규칙 검증 및 처리
     */
    @Override
    public List<CashCardDDTO> getCashCardsByCustomerName(String customerName, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardRuleSBBean.getCashCardsByCustomerName START] - 고객명: {}", customerName);
        try {
            // 1. 조회 권한 규칙 검증
            String ruleResult = getSystemParameter("CARD_SEARCH_BY_CUSTOMER_RULE");
            logger.info("Card search by customer rule validation result: {}", ruleResult);
            
            // 2. 추가 규칙 검증 (예: 고객명 형식 검증, 조회 권한 검증 등)
            validateCustomerSearchRules(customerName);
            
            // 3. Thing 계층에서 실제 카드 정보 조회
            List<CashCardDDTO> results = cashCardSBBean.findCashCardsByCustomerName(customerName, commonDTO);
            
            logger.info("==================[CashCardRuleSBBean.getCashCardsByCustomerName END] - 고객명: {}, 검색결과: {}건", customerName, results.size());
            return results;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.getCashCardsByCustomerName ERROR] - 고객명: {}, 에러: {}", 
                        customerName, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 정보 수정 규칙 검증 및 처리
     */
    @Override
    public CashCardDDTO updateCashCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardRuleSBBean.updateCashCard START] - 카드번호: {}", 
                   cashCardDDTO.getCardNumber());
        try {
            // 1. 수정 권한 규칙 검증
            String ruleResult = modifyCashCardRule("CARD_UPDATE_RULE");
            logger.info("Card update rule validation result: {}", ruleResult);
            
            // 2. 추가 규칙 검증 (예: 수정 가능한 상태인지, 권한이 있는지 등)
            validateCardUpdateRules(cashCardDDTO);
            
            // 3. Thing 계층에서 실제 카드 정보 수정
            CashCardDDTO result = cashCardSBBean.setCashCard(cashCardDDTO, commonDTO);
            
            logger.info("==================[CashCardRuleSBBean.updateCashCard END] - 카드번호: {}", 
                       cashCardDDTO.getCardNumber());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.updateCashCard ERROR] - 카드번호: {}, 에러: {}", 
                        cashCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 등록 규칙 검증 및 처리
     */
    @Override
    public HotCardDDTO registerHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardRuleSBBean.registerHotCard START] - 카드번호: {}", 
                   hotCardDDTO.getCardNumber());
        try {
            // 1. 핫카드 등록 규칙 검증
            String ruleResult = getSystemParameter("HOT_CARD_REGISTRATION_RULE");
            logger.info("Hot card registration rule validation result: {}", ruleResult);
            
            // 2. 추가 규칙 검증 (예: 이미 핫카드인지, 등록 가능한 상태인지 등)
            validateHotCardRegistrationRules(hotCardDDTO);
            
            // 3. Thing 계층에서 실제 핫카드 등록
            HotCardDDTO result = cashCardSBBean.makeHotCard(hotCardDDTO, commonDTO);
            
            logger.info("==================[CashCardRuleSBBean.registerHotCard END] - 카드번호: {}", 
                       hotCardDDTO.getCardNumber());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.registerHotCard ERROR] - 카드번호: {}, 에러: {}", 
                        hotCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 해제 규칙 검증 및 처리
     */
    @Override
    public HotCardDDTO releaseHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardRuleSBBean.releaseHotCard START] - 카드번호: {}", 
                   hotCardDDTO.getCardNumber());
        try {
            // 1. 핫카드 해제 규칙 검증
            String ruleResult = getSystemParameter("HOT_CARD_RELEASE_RULE");
            logger.info("Hot card release rule validation result: {}", ruleResult);
            
            // 2. 추가 규칙 검증 (예: 실제로 핫카드인지, 해제 가능한 상태인지 등)
            validateHotCardReleaseRules(hotCardDDTO);
            
            // 3. Thing 계층에서 실제 핫카드 해제
            HotCardDDTO result = cashCardSBBean.releaseHotCard(hotCardDDTO, commonDTO);
            
            logger.info("==================[CashCardRuleSBBean.releaseHotCard END] - 카드번호: {}", 
                       hotCardDDTO.getCardNumber());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.releaseHotCard ERROR] - 카드번호: {}, 에러: {}", 
                        hotCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    // ======================== Private Validation Methods ========================//

    /**
     * 카드 발급 규칙 검증
     */
    private void validateCardIssuanceRules(CashCardDDTO cashCardDDTO) throws CosesAppException {
        logger.debug("==================[CashCardRuleSBBean.validateCardIssuanceRules START] - 카드번호: {}", 
                    cashCardDDTO.getCardNumber());
        try {
            // 일일 한도 검증
            if (cashCardDDTO.getDailyLimitAmount() != null && 
                cashCardDDTO.getDailyLimitAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                throw new CosesAppException("일일 한도는 0보다 커야 합니다.");
            }
            
            // 계좌번호 형식 검증
            if (cashCardDDTO.getPrimaryAccountNo() == null || cashCardDDTO.getPrimaryAccountNo().trim().isEmpty()) {
                throw new CosesAppException("계좌번호는 필수입니다.");
            }
            
            logger.debug("==================[CashCardRuleSBBean.validateCardIssuanceRules END] - 카드번호: {}", 
                        cashCardDDTO.getCardNumber());
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.validateCardIssuanceRules ERROR] - 카드번호: {}, 에러: {}", 
                        cashCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 고객명 검색 규칙 검증
     */
    private void validateCustomerSearchRules(String customerName) throws CosesAppException {
        logger.debug("==================[CashCardRuleSBBean.validateCustomerSearchRules START] - 고객명: {}", customerName);
        try {
            // 고객명 형식 검증
            if (customerName == null || customerName.trim().isEmpty()) {
                throw new CosesAppException("고객명은 필수입니다.");
            }
            
            // 고객명 길이 검증 (최소 2자, 최대 50자)
            if (customerName.trim().length() < 2 || customerName.trim().length() > 50) {
                throw new CosesAppException("고객명은 2자 이상 50자 이하여야 합니다.");
            }
            
            logger.debug("==================[CashCardRuleSBBean.validateCustomerSearchRules END] - 고객명: {}", customerName);
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.validateCustomerSearchRules ERROR] - 고객명: {}, 에러: {}", 
                        customerName, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 조회 규칙 검증
     */
    private void validateCardSearchRules(String cardNumber) throws CosesAppException {
        logger.debug("==================[CashCardRuleSBBean.validateCardSearchRules START] - 카드번호: {}", cardNumber);
        try {
            // 카드번호 형식 검증
            if (cardNumber == null || cardNumber.trim().isEmpty()) {
                throw new CosesAppException("카드번호는 필수입니다.");
            }
            
            // 카드번호 길이 검증 (16자리)
            if (cardNumber.length() != 16) {
                throw new CosesAppException("카드번호는 16자리여야 합니다.");
            }
            
            logger.debug("==================[CashCardRuleSBBean.validateCardSearchRules END] - 카드번호: {}", cardNumber);
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.validateCardSearchRules ERROR] - 카드번호: {}, 에러: {}", 
                        cardNumber, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 카드 수정 규칙 검증
     */
    private void validateCardUpdateRules(CashCardDDTO cashCardDDTO) throws CosesAppException {
        logger.debug("==================[CashCardRuleSBBean.validateCardUpdateRules START] - 카드번호: {}", 
                    cashCardDDTO.getCardNumber());
        try {
            // 카드번호 필수 검증
            if (cashCardDDTO.getCardNumber() == null || cashCardDDTO.getCardNumber().trim().isEmpty()) {
                throw new CosesAppException("카드번호는 필수입니다.");
            }
            
            // 일일 한도 검증
            if (cashCardDDTO.getDailyLimitAmount() != null && 
                cashCardDDTO.getDailyLimitAmount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
                throw new CosesAppException("일일 한도는 0보다 커야 합니다.");
            }
            
            logger.debug("==================[CashCardRuleSBBean.validateCardUpdateRules END] - 카드번호: {}", 
                        cashCardDDTO.getCardNumber());
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.validateCardUpdateRules ERROR] - 카드번호: {}, 에러: {}", 
                        cashCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 등록 규칙 검증
     */
    private void validateHotCardRegistrationRules(HotCardDDTO hotCardDDTO) throws CosesAppException {
        logger.debug("==================[CashCardRuleSBBean.validateHotCardRegistrationRules START] - 카드번호: {}", 
                    hotCardDDTO.getCardNumber());
        try {
            // 카드번호 필수 검증
            if (hotCardDDTO.getCardNumber() == null || hotCardDDTO.getCardNumber().trim().isEmpty()) {
                throw new CosesAppException("카드번호는 필수입니다.");
            }
            
            // 사유 필수 검증
            if (hotCardDDTO.getReason() == null || hotCardDDTO.getReason().trim().isEmpty()) {
                throw new CosesAppException("핫카드 등록 사유는 필수입니다.");
            }
            
            logger.debug("==================[CashCardRuleSBBean.validateHotCardRegistrationRules END] - 카드번호: {}", 
                        hotCardDDTO.getCardNumber());
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.validateHotCardRegistrationRules ERROR] - 카드번호: {}, 에러: {}", 
                        hotCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 핫카드 해제 규칙 검증
     */
    private void validateHotCardReleaseRules(HotCardDDTO hotCardDDTO) throws CosesAppException {
        logger.debug("==================[CashCardRuleSBBean.validateHotCardReleaseRules START] - 카드번호: {}", 
                    hotCardDDTO.getCardNumber());
        try {
            // 카드번호 필수 검증
            if (hotCardDDTO.getCardNumber() == null || hotCardDDTO.getCardNumber().trim().isEmpty()) {
                throw new CosesAppException("카드번호는 필수입니다.");
            }
            
            // 해제 사유 필수 검증
            if (hotCardDDTO.getReleaseReason() == null || hotCardDDTO.getReleaseReason().trim().isEmpty()) {
                throw new CosesAppException("핫카드 해제 사유는 필수입니다.");
            }
            
            logger.debug("==================[CashCardRuleSBBean.validateHotCardReleaseRules END] - 카드번호: {}", 
                        hotCardDDTO.getCardNumber());
        } catch (Exception e) {
            logger.error("==================[CashCardRuleSBBean.validateHotCardReleaseRules ERROR] - 카드번호: {}, 에러: {}", 
                        hotCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }
}
