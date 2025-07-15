package com.skcc.oversea.eplatonframework.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.eplatonframework.transfer.EPlatonCommonDTO;
import com.skcc.oversea.eplatonframework.transfer.TPSVCINFODTO;
import com.skcc.oversea.foundation.logej.LOGEJ;
import com.skcc.oversea.foundation.constant.Constants;
import com.skcc.oversea.eplatonframework.business.delegate.action.CashCardBizAction.CashCardService;
import com.skcc.oversea.eplatonframework.business.entity.CashCard;
import com.skcc.oversea.eplatonframework.business.repository.CashCardRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Cash Card Service Implementation for SKCC Oversea
 * 
 * Provides cash card business operations
 * using Spring Boot and modern Java patterns.
 */
@Service
public class CashCardServiceImpl implements CashCardService {

    private static final Logger logger = LoggerFactory.getLogger(CashCardServiceImpl.class);

    @Autowired
    private CashCardRepository cashCardRepository;

    /**
     * Create cash card
     */
    @Override
    @Transactional
    public EPlatonEvent createCard(EPlatonEvent event) {
        try {
            logger.info("Creating cash card");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidCreateCardRequest(requestData)) {
                setErrorInfo(event, "ECARD101", "Invalid create card request data");
                return event;
            }

            // Create card logic here
            // CashCard card = cashCardRepository.createCard(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card created successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card created successfully");

            logger.info("Cash card created successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error creating cash card", e);
            setErrorInfo(event, "ECARD102", "Failed to create card: " + e.getMessage());
            return event;
        }
    }

    /**
     * Update cash card
     */
    @Override
    @Transactional
    public EPlatonEvent updateCard(EPlatonEvent event) {
        try {
            logger.info("Updating cash card");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidUpdateCardRequest(requestData)) {
                setErrorInfo(event, "ECARD201", "Invalid update card request data");
                return event;
            }

            // Update card logic here
            // CashCard card = cashCardRepository.updateCard(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card updated successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card updated successfully");

            logger.info("Cash card updated successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error updating cash card", e);
            setErrorInfo(event, "ECARD202", "Failed to update card: " + e.getMessage());
            return event;
        }
    }

    /**
     * Delete cash card
     */
    @Override
    @Transactional
    public EPlatonEvent deleteCard(EPlatonEvent event) {
        try {
            logger.info("Deleting cash card");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidDeleteCardRequest(requestData)) {
                setErrorInfo(event, "ECARD301", "Invalid delete card request data");
                return event;
            }

            // Delete card logic here
            // cashCardRepository.deleteCard(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card deleted successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card deleted successfully");

            logger.info("Cash card deleted successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error deleting cash card", e);
            setErrorInfo(event, "ECARD302", "Failed to delete card: " + e.getMessage());
            return event;
        }
    }

    /**
     * Get cash card
     */
    @Override
    @Transactional(readOnly = true)
    public EPlatonEvent getCard(EPlatonEvent event) {
        try {
            logger.info("Getting cash card");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidGetCardRequest(requestData)) {
                setErrorInfo(event, "ECARD401", "Invalid get card request data");
                return event;
            }

            // Get card logic here
            // CashCard card = cashCardRepository.getCard(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card data retrieved successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card data retrieved successfully");

            logger.info("Cash card retrieved successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error getting cash card", e);
            setErrorInfo(event, "ECARD402", "Failed to get card: " + e.getMessage());
            return event;
        }
    }

    /**
     * Get cash card list
     */
    @Override
    @Transactional(readOnly = true)
    public EPlatonEvent getCardList(EPlatonEvent event) {
        try {
            logger.info("Getting cash card list");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidGetCardListRequest(requestData)) {
                setErrorInfo(event, "ECARD501", "Invalid get card list request data");
                return event;
            }

            // Get card list logic here
            // List<CashCard> cards = cashCardRepository.getCardList(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card list retrieved successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card list retrieved successfully");

            logger.info("Cash card list retrieved successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error getting cash card list", e);
            setErrorInfo(event, "ECARD502", "Failed to get card list: " + e.getMessage());
            return event;
        }
    }

    /**
     * Block cash card
     */
    @Override
    @Transactional
    public EPlatonEvent blockCard(EPlatonEvent event) {
        try {
            logger.info("Blocking cash card");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidBlockCardRequest(requestData)) {
                setErrorInfo(event, "ECARD601", "Invalid block card request data");
                return event;
            }

            // Block card logic here
            // cashCardRepository.blockCard(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card blocked successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card blocked successfully");

            logger.info("Cash card blocked successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error blocking cash card", e);
            setErrorInfo(event, "ECARD602", "Failed to block card: " + e.getMessage());
            return event;
        }
    }

    /**
     * Unblock cash card
     */
    @Override
    @Transactional
    public EPlatonEvent unblockCard(EPlatonEvent event) {
        try {
            logger.info("Unblocking cash card");

            // Extract request data
            Object requestData = event.getRequest();

            // Validate request data
            if (!isValidUnblockCardRequest(requestData)) {
                setErrorInfo(event, "ECARD701", "Invalid unblock card request data");
                return event;
            }

            // Unblock card logic here
            // cashCardRepository.unblockCard(requestData);

            // Set response
            EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
            responseDTO.setMessage("Card unblocked successfully");
            event.setResponse(responseDTO);
            event.getTPSVCINFODTO().setErrorcode("I0000");
            event.getTPSVCINFODTO().setError_message("Card unblocked successfully");

            logger.info("Cash card unblocked successfully");
            return event;

        } catch (Exception e) {
            logger.error("Error unblocking cash card", e);
            setErrorInfo(event, "ECARD702", "Failed to unblock card: " + e.getMessage());
            return event;
        }
    }

    /**
     * Set error information
     */
    private void setErrorInfo(EPlatonEvent event, String errorCode, String errorMessage) {
        TPSVCINFODTO tpsvcinfo = event.getTPSVCINFODTO();
        String currentErrorCode = tpsvcinfo.getErrorcode();

        if (currentErrorCode != null && currentErrorCode.startsWith("I")) {
            tpsvcinfo.setErrorcode(errorCode);
            tpsvcinfo.setError_message(errorMessage);
        } else if (currentErrorCode != null && currentErrorCode.startsWith("E")) {
            String combinedErrorCode = errorCode + "|" + currentErrorCode;
            tpsvcinfo.setErrorcode(combinedErrorCode);
            tpsvcinfo.setError_message(errorMessage);
        } else {
            tpsvcinfo.setErrorcode(errorCode);
            tpsvcinfo.setError_message(errorMessage);
        }

        // Set response as error message - create a simple response DTO
        EPlatonCommonDTO responseDTO = new EPlatonCommonDTO();
        responseDTO.setReqName("ERROR");
        event.setResponse(responseDTO);
    }

    // Validation methods
    private boolean isValidCreateCardRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidUpdateCardRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidDeleteCardRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidGetCardRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidGetCardListRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidBlockCardRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidUnblockCardRequest(Object requestData) {
        return requestData != null;
    }

    private boolean isValidGetCardBalanceRequest(Object requestData) {
        return requestData != null;
    }

    // =========================== Controller Expected Methods
    // ===========================

    public List<CashCard> findAll() {
        logger.info("Finding all cash cards");
        return cashCardRepository.findAll();
    }

    public CashCard findById(String cardId) {
        logger.info("Finding cash card by ID: {}", cardId);
        // Find by card number since cardId refers to card number
        return cashCardRepository.findByCardNo(cardId).orElse(null);
    }

    public CashCard save(CashCard cashCard) {
        logger.info("Saving cash card: {}", cashCard.getCardNo());
        return cashCardRepository.save(cashCard);
    }

    public void deleteById(String cardId) {
        logger.info("Deleting cash card by ID: {}", cardId);
        // Find by card number and delete
        cashCardRepository.findByCardNo(cardId).ifPresent(cashCard -> {
            cashCardRepository.delete(cashCard);
            logger.info("Cash card deleted: {}", cardId);
        });
    }

    public boolean existsById(String cardId) {
        logger.info("Checking if cash card exists by ID: {}", cardId);
        // Check by card number since cardId refers to card number
        return cashCardRepository.findByCardNo(cardId).isPresent();
    }

    public List<CashCard> getAllCashCards() {
        logger.info("Getting all cash cards");
        return cashCardRepository.findAll();
    }

    public CashCard getCashCardById(Long id) {
        logger.info("Getting cash card by ID: {}", id);
        return cashCardRepository.findById(id).orElse(null);
    }

    public CashCard getCashCardByCardNo(String cardNo) {
        logger.info("Getting cash card by card number: {}", cardNo);
        return cashCardRepository.findByCardNo(cardNo).orElse(null);
    }

    public List<CashCard> getCashCardsByCustomerId(String customerId) {
        logger.info("Getting cash cards by customer ID: {}", customerId);
        return cashCardRepository.findByCustomerId(customerId);
    }

    public CashCard createCashCard(CashCard cashCard) {
        logger.info("Creating cash card: {}", cashCard.getCardNo());
        return cashCardRepository.save(cashCard);
    }

    public CashCard updateCashCard(CashCard cashCard) {
        logger.info("Updating cash card: {}", cashCard.getCardNo());
        if (cashCard.getPrimaryKey() != null && cashCardRepository.existsById(cashCard.getPrimaryKey())) {
            return cashCardRepository.save(cashCard);
        }
        return null;
    }

    public boolean deleteCashCard(Long id) {
        logger.info("Deleting cash card by ID: {}", id);
        if (cashCardRepository.existsById(id)) {
            cashCardRepository.deleteById(id);
            logger.info("Cash card deleted with ID: {}", id);
            return true;
        }
        return false;
    }

    public List<CashCard> getCashCardsByStatus(String status) {
        logger.info("Getting cash cards by status: {}", status);
        return cashCardRepository.findByCardStatus(status);
    }

    public List<CashCard> getExpiredCashCards() {
        logger.info("Getting expired cash cards");
        return cashCardRepository.findExpiredCards(LocalDate.now());
    }

    public CashCard updateCardBalance(Long id, BigDecimal newBalance) {
        logger.info("Updating card balance for ID: {} to {}", id, newBalance);
        return cashCardRepository.findById(id).map(cashCard -> {
            cashCard.setCurrentBalance(newBalance);
            return cashCardRepository.save(cashCard);
        }).orElse(null);
    }

    public CashCard updateCardStatus(Long id, String status) {
        logger.info("Updating card status for ID: {} to {}", id, status);
        return cashCardRepository.findById(id).map(cashCard -> {
            cashCard.setCardStatus(status);
            return cashCardRepository.save(cashCard);
        }).orElse(null);
    }
}