package com.skcc.oversea.cashCard.business.facade.dao;

import com.skcc.oversea.cashCard.business.cashCard.entity.CashCard;
import com.skcc.oversea.framework.business.dao.AbstractDAO;
import com.skcc.oversea.framework.exception.CosesAppException;
import com.skcc.oversea.framework.transfer.CosesCommonDTO;
import com.skcc.oversea.cashCard.transfer.*;
import org.springframework.stereotype.Repository;

@Repository
public class CashCardDAO extends AbstractDAO<CashCard, Long> implements ICashCardDAO {
    // Add custom DAO methods as needed

    @Override
    public int getLastSequenceNoForRegisterCashCard(CashCardCDTO cashCardCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CashCardCDTO queryForCashCard(CashCardCDTO cashCardCDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CashCardPageCDTO listForCashCardNumber(CashCardConditionCDTO conditionCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CashCardPageCDTO listForCashCard(CashCardConditionCDTO conditionCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public HotCardListCDTO queryForHotCardList(HotCardCDTO hotCardCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int getLastSequenceNoForRegisterHotCard(HotCardCDTO hotCardCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public HotCardPageCDTO listForHotCard(HotCardQueryConditionCDTO conditionCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public HotCardListCDTO selectHotCardForRegister(HotCardCDTO hotCardCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public CashCardPageCDTO listInvalidAttemptCard(CashCardConditionCDTO conditionCDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void testBatch(CashCardCDTO cashCardCDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void updateAccumBalance(CashCardCDTO cashCardCDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}