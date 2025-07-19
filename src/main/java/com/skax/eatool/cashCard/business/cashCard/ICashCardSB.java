package com.skax.eatool.cashCard.business.cashCard;

import com.skax.eatool.framework.transfer.CosesCommonDTO;
import com.skax.eatool.framework.exception.CosesAppException;

import com.skax.eatool.cashCard.business.cashCard.model.*;
import java.util.List;

public interface ICashCardSB
{
    public     CashCardDDTO getCashCardInfo(CashCardDDTO cashCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;
            
    List<CashCardDDTO> findCashCardsByCustomerName(String customerName, CosesCommonDTO commonDTO) throws CosesAppException;

    public CashCardDDTO findCashCardInfoByCardNo(CashCardDDTO cashCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;

    public CashCardDDTO makeCashCard(CashCardDDTO cashCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;

    public CashCardDDTO setCashCard(CashCardDDTO cashCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;

    public HotCardDDTO getHotCardInfo(HotCardDDTO hotCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;

    public HotCardDDTO makeHotCard(HotCardDDTO hotCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;

    public HotCardDDTO releaseHotCard(HotCardDDTO hotCardDDTO,
            CosesCommonDTO commonDTO) throws CosesAppException;
}

