package com.skcc.oversea.cashCard.business.facade;

import java.util.List;
import com.skcc.oversea.framework.transfer.BatchJobProcessorResultDTO;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.framework.exception.CosesAppException;
import com.skcc.oversea.framework.transfer.CosesCommonDTO;
import com.skcc.oversea.cashCard.business.cashCard.model.CashCardDDTO;
import com.skcc.oversea.cashCard.business.cashCard.model.HotCardDDTO;

public interface ICashCardManagementSB {

        List<Object> getAllCashCards();

        Object getCashCardById(String cardId);

        Object createCashCard(Object cashCard);

        void deleteCashCard(String cardId);

        boolean existsCashCard(String cardId);

        BatchJobProcessorResultDTO processBatchJob(String jobId);

        BatchJobProcessorResultDTO getBatchJobStatus(String jobId);

        EPlatonEvent queryForRegisterCashCard(EPlatonEvent eplatonevent) throws CosesAppException;

        EPlatonEvent callmethod01(EPlatonEvent eplatonevent) throws CosesAppException;

        EPlatonEvent callmethod02(EPlatonEvent eplatonevent) throws CosesAppException;

        // ======================== Controller Service Methods ========================//

        /**
         * 카드 발급 신청 처리
         */
        CashCardDDTO issueCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

        /**
         * 카드 정보 조회
         */
        CashCardDDTO getCardInfo(String cardNumber, CosesCommonDTO commonDTO) throws CosesAppException;

        /**
         * 카드 정보 수정
         */
        CashCardDDTO updateCardInfo(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

        /**
         * 핫카드 등록
         */
        HotCardDDTO registerHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

        /**
         * 핫카드 해제
         */
        HotCardDDTO releaseHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException;

        /**
         * 카드 번호 생성
         */
        String generateCardNumber();

}
