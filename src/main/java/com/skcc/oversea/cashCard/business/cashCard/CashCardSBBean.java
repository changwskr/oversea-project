package com.skcc.oversea.cashCard.business.cashCard;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.skcc.oversea.foundation.log.Log;
import com.skcc.oversea.framework.transfer.CosesCommonDTO;
import com.skcc.oversea.framework.exception.CosesAppException;
import com.skcc.oversea.framework.exception.CosesExceptionDetail;

import com.skcc.oversea.cashCard.business.cashCard.model.*;
import com.skcc.oversea.cashCard.business.cashCard.helper.*;
import com.skcc.oversea.cashCard.business.cashCard.entity.*;
import com.skcc.oversea.cashCard.business.cashCardRule.helper.IOBoundCardRegister;
import com.skcc.oversea.cashCard.repository.CashCardRepository;
import com.skcc.oversea.cashCard.repository.HotCardRepository;
import com.skcc.oversea.cashCard.business.cashCard.entity.HotCard;
import com.skcc.oversea.cashCard.business.cashCard.entity.HotCardPK;

/**
 * Cash Card Service for SKCC Oversea
 * Spring Boot service replacing EJB session bean
 */
@Service
@Transactional
public class CashCardSBBean implements ICashCardSB {

    private static final Logger logger = LoggerFactory.getLogger(CashCardSBBean.class);

    @Autowired
    private CashCardRepository cashCardRepository;

    @Autowired
    private HotCardRepository hotCardRepository;

    @Autowired
    private IOBoundCardRegister ioBoundCardRegister;

    // =========================== Private Method Area
    // =================================//

    private CashCard findByCashCard(CashCardDDTO cashCardDDTO) throws CosesAppException {
        logger.debug("Finding cash card - SequenceNo: {}, CardNumber: {}, BankCode: {}, PrimaryAccountNo: {}",
                cashCardDDTO.getSequenceNo(), cashCardDDTO.getCardNumber(),
                cashCardDDTO.getBankCode(), cashCardDDTO.getPrimaryAccountNo());

        try {
            return cashCardRepository.findById(
                    new CashCardPK(cashCardDDTO.getSequenceNo(), cashCardDDTO.getCardNumber()))
                    .orElseThrow(() -> new CosesAppException("ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST",
                            "Account number does not exist"));
        } catch (Exception e) {
            CosesExceptionDetail detail = new CosesExceptionDetail("ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST",
                    "Account number does not exist");
            detail.addMessage("PrimaryAccountNo", cashCardDDTO.getPrimaryAccountNo());
            detail.addArgument("CashCard System");
            detail.addArgument("findByCashCard()");
            throw new CosesAppException("ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST", detail.toString());
        }
    }

    private HotCard findByHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.debug("Finding hot card - SequenceNo: {}, CardNumber: {}",
                hotCardDDTO.getSequenceNo(), hotCardDDTO.getCardNumber());

        try {
            return hotCardRepository.findById(
                    new HotCardPK(hotCardDDTO.getSequenceNo(), hotCardDDTO.getCardNumber()))
                    .orElseThrow(() -> new CosesAppException("ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST",
                            "Account number does not exist"));
        } catch (Exception e) {
            CosesExceptionDetail detail = new CosesExceptionDetail("ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST",
                    "Account number does not exist");
            detail.addMessage("CardNumber", hotCardDDTO.getCardNumber());
            detail.addArgument("CashCard System");
            detail.addArgument("findByHotCard()");
            throw new CosesAppException("ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST", detail.toString());
        }
    }

    // =========================== Public Method Area
    // =================================//

    @Override
    @Transactional(readOnly = true)
    public CashCardDDTO getCashCardInfo(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardSBBean.getCashCardInfo START]");
        try {
            CashCard cashCard = findByCashCard(cashCardDDTO);
            CashCardDDTO result = DTOConverter.getCashCardDDTO(cashCard, cashCardDDTO);
            logger.info("==================[CashCardSBBean.getCashCardInfo END]");
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.getCashCardInfo ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public CashCardDDTO makeCashCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardSBBean.makeCashCard START]");
        try {
            CashCard cashCard = cashCardRepository.create(
                    cashCardDDTO.getBankType(), cashCardDDTO.getBankCode(),
                    cashCardDDTO.getPrimaryAccountNo(), cashCardDDTO.getSequenceNo(),
                    cashCardDDTO.getCardNumber(), cashCardDDTO.getBranchCode(),
                    cashCardDDTO.getType());

            DTOConverter.setCashCardDDTO(cashCardDDTO, cashCard);

            // Execute IOBound card registration
            ioBoundCardRegister.execute(
                    cashCardDDTO.getPrimaryAccountNo(), cashCardDDTO.getCIFName(),
                    cashCardDDTO.getBranchCode(), cashCardDDTO.getCardNumber());

            logger.info("==================[CashCardSBBean.makeCashCard END]");
            return cashCardDDTO;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.makeCashCard ERROR] - {}", e.getMessage(), e);
            CosesExceptionDetail detail = new CosesExceptionDetail("ERR_0182_ALREADY_EXISTING", "Already existing");
            detail.addMessage("PrimaryAccountNo", cashCardDDTO.getPrimaryAccountNo());
            detail.addArgument("CashCard System");
            detail.addArgument("makeCashCard()");
            throw new CosesAppException("ERR_0182_ALREADY_EXISTING", detail.toString());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CashCardDDTO findCashCardInfoByCardNo(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO)
            throws CosesAppException {
        logger.info("==================[CashCardSBBean.findCashCardInfoByCardNo START] - 카드번호: {}", cashCardDDTO.getCardNumber());
        try {
            CashCard cashCard = cashCardRepository.findByCardNumber(commonDTO.getBankCode(),
                    cashCardDDTO.getCardNumber())
                    .orElseThrow(() -> new CosesAppException("ERR_0100_ACCOUNT_DOES_NOT_EXIST",
                            "Account does not exist"));
            CashCardDDTO result = DTOConverter.getCashCardDDTO(cashCard, cashCardDDTO);
            logger.info("==================[CashCardSBBean.findCashCardInfoByCardNo END] - 카드번호: {}", cashCardDDTO.getCardNumber());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.findCashCardInfoByCardNo ERROR] - 카드번호: {}, 에러: {}", cashCardDDTO.getCardNumber(), e.getMessage(), e);
            CosesExceptionDetail detail = new CosesExceptionDetail("ERR_0100_ACCOUNT_DOES_NOT_EXIST",
                    "Account does not exist");
            detail.addMessage("CardNumber", cashCardDDTO.getCardNumber());
            detail.addArgument("CashCard System");
            detail.addArgument("findCashCardInfoByCardNo()");
            throw new CosesAppException("ERR_0100_ACCOUNT_DOES_NOT_EXIST", detail.toString());
        }
    }

    @Override
    @Transactional
    public CashCardDDTO setCashCard(CashCardDDTO cashCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardSBBean.setCashCard START] - 카드번호: {}, InvalidAttemptCount: {}", cashCardDDTO.getCardNumber(), cashCardDDTO.getInvalidAttemptCnt());
        try {
            logger.debug("Setting cash card - InvalidAttemptCount: {}", cashCardDDTO.getInvalidAttemptCnt());

            CashCard cashCard = findByCashCard(cashCardDDTO);
            logger.debug("InvalidAttemptCount with Entity: {}", cashCard.getInvalidAttemptCnt());

            DTOConverter.setCashCardDDTO(cashCardDDTO, cashCard);
            logger.info("==================[CashCardSBBean.setCashCard END] - 카드번호: {}", cashCardDDTO.getCardNumber());
            return cashCardDDTO;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.setCashCard ERROR] - 카드번호: {}, 에러: {}", cashCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public HotCardDDTO getHotCardInfo(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardSBBean.getHotCardInfo START] - 카드번호: {}", hotCardDDTO.getCardNumber());
        try {
            HotCard hotCard = findByHotCard(hotCardDDTO, commonDTO);
            HotCardDDTO result = DTOConverter.getHotCardCDTO(hotCard, hotCardDDTO);
            logger.info("==================[CashCardSBBean.getHotCardInfo END] - 카드번호: {}", hotCardDDTO.getCardNumber());
            return result;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.getHotCardInfo ERROR] - 카드번호: {}, 에러: {}", hotCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    @Override
    @Transactional
    public HotCardDDTO makeHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardSBBean.makeHotCard START] - 카드번호: {}", hotCardDDTO.getCardNumber());
        try {
            HotCard hotCard = new HotCard();
            hotCard.setCardNumber(hotCardDDTO.getCardNumber());
            hotCard.setSequenceNo(hotCardDDTO.getSequenceNo());
            hotCard = hotCardRepository.save(hotCard);
            DTOConverter.setHotCardDDTO(hotCardDDTO, hotCard);
            logger.info("==================[CashCardSBBean.makeHotCard END] - 카드번호: {}", hotCardDDTO.getCardNumber());
            return hotCardDDTO;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.makeHotCard ERROR] - 카드번호: {}, 에러: {}", hotCardDDTO.getCardNumber(), e.getMessage(), e);
            CosesExceptionDetail detail = new CosesExceptionDetail("ERR_0182_ALREADY_EXISTING", "Already existing");
            detail.addMessage("CardNumber", hotCardDDTO.getCardNumber());
            detail.addArgument("CashCard System");
            detail.addArgument("makeHotCard()");
            throw new CosesAppException("ERR_0182_ALREADY_EXISTING", detail.toString());
        }
    }

    @Override
    @Transactional
    public HotCardDDTO releaseHotCard(HotCardDDTO hotCardDDTO, CosesCommonDTO commonDTO) throws CosesAppException {
        logger.info("==================[CashCardSBBean.releaseHotCard START] - 카드번호: {}", hotCardDDTO.getCardNumber());
        try {
            HotCard hotCard = findByHotCard(hotCardDDTO, commonDTO);
            setReleaseHotCard(hotCardDDTO, hotCard);
            logger.info("==================[CashCardSBBean.releaseHotCard END] - 카드번호: {}", hotCardDDTO.getCardNumber());
            return hotCardDDTO;
        } catch (Exception e) {
            logger.error("==================[CashCardSBBean.releaseHotCard ERROR] - 카드번호: {}, 에러: {}", hotCardDDTO.getCardNumber(), e.getMessage(), e);
            throw e;
        }
    }

    private void setReleaseHotCard(HotCardDDTO hotCardDDTO, HotCard hotCard) {
        // Implementation for releasing hot card
        logger.debug("Releasing hot card: {}", hotCardDDTO.getCardNumber());
    }
}
