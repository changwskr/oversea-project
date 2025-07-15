package com.skcc.oversea.cashCard.business.facade;

import java.util.List;
import com.skcc.oversea.framework.transfer.BatchJobProcessorResultDTO;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.framework.exception.CosesAppException;

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

}
