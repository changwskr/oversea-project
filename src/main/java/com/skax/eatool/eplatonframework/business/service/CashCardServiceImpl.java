package com.skax.eatool.eplatonframework.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skax.eatool.eplatonframework.business.entity.CashCard;
import com.skax.eatool.eplatonframework.business.repository.CashCardRepository;

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

    // =========================== Controller Expected Methods
    // ===========================

    @Override
    public List<CashCard> findAll() {
        logger.info("Finding all cash cards");
        return cashCardRepository.findAll();
    }

    @Override
    public CashCard findById(String cardId) {
        logger.info("Finding cash card by ID: {}", cardId);
        // Find by card number since cardId refers to card number
        return cashCardRepository.findByCardNo(cardId).orElse(null);
    }

    @Override
    public CashCard save(CashCard cashCard) {
        logger.info("Saving cash card: {}", cashCard.getCardNo());
        return cashCardRepository.save(cashCard);
    }

    @Override
    public void deleteById(String cardId) {
        logger.info("Deleting cash card by ID: {}", cardId);
        // Find by card number and delete
        cashCardRepository.findByCardNo(cardId).ifPresent(cashCard -> {
            cashCardRepository.delete(cashCard);
            logger.info("Cash card deleted: {}", cardId);
        });
    }

    @Override
    public boolean existsById(String cardId) {
        logger.info("Checking if cash card exists by ID: {}", cardId);
        // Check by card number since cardId refers to card number
        return cashCardRepository.findByCardNo(cardId).isPresent();
    }

    @Override
    public List<CashCard> getAllCashCards() {
        logger.info("Getting all cash cards");
        return cashCardRepository.findAll();
    }

    @Override
    public CashCard getCashCardById(Long id) {
        logger.info("Getting cash card by ID: {}", id);
        return cashCardRepository.findById(id).orElse(null);
    }

    @Override
    public CashCard getCashCardByCardNo(String cardNo) {
        logger.info("Getting cash card by card number: {}", cardNo);
        return cashCardRepository.findByCardNo(cardNo).orElse(null);
    }

    @Override
    public List<CashCard> getCashCardsByCustomerId(String customerId) {
        logger.info("Getting cash cards by customer ID: {}", customerId);
        return cashCardRepository.findByCustomerId(customerId);
    }

    @Override
    public CashCard updateCashCard(CashCard cashCard) {
        logger.info("Updating cash card: {}", cashCard.getCardNo());
        if (cashCard.getPrimaryKey() != null && cashCardRepository.existsById(cashCard.getPrimaryKey())) {
            return cashCardRepository.save(cashCard);
        }
        return null;
    }

    @Override
    public boolean deleteCashCard(Long id) {
        logger.info("Deleting cash card by ID: {}", id);
        if (cashCardRepository.existsById(id)) {
            cashCardRepository.deleteById(id);
            logger.info("Cash card deleted with ID: {}", id);
            return true;
        }
        return false;
    }

    @Override
    public List<CashCard> getCashCardsByStatus(String status) {
        logger.info("Getting cash cards by status: {}", status);
        return cashCardRepository.findByCardStatus(status);
    }

    @Override
    public List<CashCard> getExpiredCashCards() {
        logger.info("Getting expired cash cards");
        return cashCardRepository.findExpiredCards(LocalDate.now());
    }

    @Override
    public CashCard updateCardBalance(Long id, BigDecimal newBalance) {
        logger.info("Updating card balance for ID: {} to {}", id, newBalance);
        return cashCardRepository.findById(id).map(cashCard -> {
            cashCard.setCurrentBalance(newBalance);
            return cashCardRepository.save(cashCard);
        }).orElse(null);
    }

    @Override
    public CashCard updateCardStatus(Long id, String status) {
        logger.info("Updating card status for ID: {} to {}", id, status);
        return cashCardRepository.findById(id).map(cashCard -> {
            cashCard.setCardStatus(status);
            return cashCardRepository.save(cashCard);
        }).orElse(null);
    }
}
