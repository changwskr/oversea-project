package com.skax.eatool.eplatonframework.business.facade.cashCard;

import com.skax.eatool.eplatonframework.business.entity.CashCard;
import com.skax.eatool.eplatonframework.business.service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CashCardFacadeService {

    private static final Logger logger = LoggerFactory.getLogger(CashCardFacadeService.class);

    @Autowired
    private CashCardService cashCardService;

    public List<CashCard> getAllCashCards() {
        logger.info("==================[CashCardFacadeService.getAllCashCards START]");
        try {
            List<CashCard> result = cashCardService.findAll();
            logger.info("==================[CashCardFacadeService.getAllCashCards END]");
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardFacadeService.getAllCashCards ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    public CashCard getCashCardById(String cardId) {
        logger.info("==================[CashCardFacadeService.getCashCardById START] - 카드ID: {}", cardId);
        try {
            CashCard result = cashCardService.findById(cardId);
            logger.info("==================[CashCardFacadeService.getCashCardById END] - 카드ID: {}", cardId);
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardFacadeService.getCashCardById ERROR] - 카드ID: {}, 오류: {}", cardId,
                    e.getMessage(), e);
            throw e;
        }
    }

    public CashCard createCashCard(CashCard cashCard) {
        logger.info("==================[CashCardFacadeService.createCashCard START] - 카드번호: {}", cashCard.getCardNo());
        try {
            CashCard result = cashCardService.save(cashCard);
            logger.info("==================[CashCardFacadeService.createCashCard END] - 카드번호: {}",
                    cashCard.getCardNo());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardFacadeService.createCashCard ERROR] - 카드번호: {}, 오류: {}",
                    cashCard.getCardNo(), e.getMessage(), e);
            throw e;
        }
    }

    public void deleteCashCard(String cardId) {
        logger.info("==================[CashCardFacadeService.deleteCashCard START] - 카드ID: {}", cardId);
        try {
            cashCardService.deleteById(cardId);
            logger.info("==================[CashCardFacadeService.deleteCashCard END] - 카드ID: {}", cardId);
        } catch (Exception e) {
            logger.error("==================[CashCardFacadeService.deleteCashCard ERROR] - 카드ID: {}, 오류: {}", cardId,
                    e.getMessage(), e);
            throw e;
        }
    }

    public boolean existsCashCard(String cardId) {
        logger.info("==================[CashCardFacadeService.existsCashCard START] - 카드ID: {}", cardId);
        try {
            boolean result = cashCardService.existsById(cardId);
            logger.info("==================[CashCardFacadeService.existsCashCard END] - 카드ID: {}", cardId);
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardFacadeService.existsCashCard ERROR] - 카드ID: {}, 오류: {}", cardId,
                    e.getMessage(), e);
            throw e;
        }
    }
}
