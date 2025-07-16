package com.skcc.oversea.cashCard.business.cashCardRule;

import com.skcc.oversea.cashCard.business.cashCard.model.CashCardDDTO;
import com.skcc.oversea.cashCard.business.cashCard.model.HotCardDDTO;
import com.skcc.oversea.framework.transfer.CosesCommonDTO;
import com.skcc.oversea.framework.exception.CosesAppException;
import java.util.List;

/**
 * Cash Card Rule Service Interface
 * 
 * Interface for cash card business rule operations
 */
public interface ICashCardRuleSB {
    
    /**
     * Get system parameter
     * 
     * @param parameterId parameter identifier
     * @return system parameter value
     */
    String getSystemParameter(String parameterId);
    
    /**
     * Modify cash card rule
     * 
     * @param ruleId rule identifier
     * @return modification result
     */
    String modifyCashCardRule(String ruleId);
    
    /**
     * Delete cash card rule
     * 
     * @param ruleId rule identifier
     * @return deletion result
     */
    String deleteCashCardRule(String ruleId);

    /**
     * 카드 발급 규칙 검증 및 처리
     * 
     * @param cashCardDDTO 카드 정보 DTO
     * @param commonDTO 공통 DTO
     * @return 처리된 카드 정보 DTO
     * @throws CosesAppException 비즈니스 예외
     */
    CashCardDDTO issueCashCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

    /**
     * 카드 정보 조회 규칙 검증 및 처리
     * 
     * @param cardNumber 카드번호
     * @param commonDTO 공통 DTO
     * @return 카드 정보 DTO
     * @throws CosesAppException 비즈니스 예외
     */
    CashCardDDTO getCashCardInfo(String cardNumber, CosesCommonDTO commonDTO) throws CosesAppException;
    
    List<CashCardDDTO> getCashCardsByCustomerName(String customerName, CosesCommonDTO commonDTO) throws CosesAppException;

    /**
     * 카드 정보 수정 규칙 검증 및 처리
     * 
     * @param cashCardDDTO 카드 정보 DTO
     * @param commonDTO 공통 DTO
     * @return 수정된 카드 정보 DTO
     * @throws CosesAppException 비즈니스 예외
     */
    CashCardDDTO updateCashCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

    /**
     * 핫카드 등록 규칙 검증 및 처리
     * 
     * @param hotCardDDTO 핫카드 정보 DTO
     * @param commonDTO 공통 DTO
     * @return 처리된 핫카드 정보 DTO
     * @throws CosesAppException 비즈니스 예외
     */
    HotCardDDTO registerHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

    /**
     * 핫카드 해제 규칙 검증 및 처리
     * 
     * @param hotCardDDTO 핫카드 정보 DTO
     * @param commonDTO 공통 DTO
     * @return 처리된 핫카드 정보 DTO
     * @throws CosesAppException 비즈니스 예외
     */
    HotCardDDTO releaseHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;
}
