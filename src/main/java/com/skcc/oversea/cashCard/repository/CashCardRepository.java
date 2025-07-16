package com.skcc.oversea.cashCard.repository;

import com.skcc.oversea.cashCard.business.cashCard.entity.CashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository("cashCardMainRepository")
public interface CashCardRepository extends JpaRepository<CashCard, Long> {

        /**
         * Find cash card by primary key
         */
        Optional<CashCard> findById(Long id);

        /**
         * Find cash card by card number and bank code
         */
        @Query("SELECT c FROM CashCardBusiness c WHERE c.cardNumber = :cardNumber AND c.bankCode = :bankCode")
        Optional<CashCard> findByCardNumber(@Param("bankCode") String bankCode, @Param("cardNumber") String cardNumber);

        /**
         * Find cash card by card number only
         */
        @Query("SELECT c FROM CashCardBusiness c WHERE c.cardNumber = :cardNumber")
        Optional<CashCard> findByCardNumberOnly(@Param("cardNumber") String cardNumber);

        /**
         * Find cash cards by customer name (case-insensitive partial match)
         */
        List<CashCard> findByCifNameContainingIgnoreCase(String customerName);

        // /**
        //  * Find cash card by creation criteria (without sequenceNo)
        //  */
        // @Query("SELECT c FROM CashCardBusiness c WHERE c.bankType = :bankType AND c.bankCode = :bankCode " +
        //                 "AND c.primaryAccountNo = :primaryAccountNo AND c.cardNumber = :cardNumber " +
        //                 "AND c.branchCode = :branchCode AND c.type = :type")
        // Optional<CashCard> findByCreateCriteria(@Param("bankType") String bankType,
        //                 @Param("bankCode") String bankCode,
        //                 @Param("primaryAccountNo") String primaryAccountNo,
        //                 @Param("cardNumber") String cardNumber,
        //                 @Param("branchCode") String branchCode,
        //                 @Param("type") String type);

        /**
         * Create method for cash card
         */
        default CashCard create(String bankType, String bankCode, String primaryAccountNo,
                        String cardNumber, String branchCode, String type) {
                System.out.println("==================[CashCardRepository.create START]");
                try {
                        CashCard cashCard = new CashCard(cardNumber, bankCode, primaryAccountNo);
                        cashCard.setCardNo(cardNumber); // CARD_NO 필드 설정
                        cashCard.setBankType(bankType);
                        cashCard.setBranchCode(branchCode);
                        cashCard.setType(type);
                        CashCard result = save(cashCard);
                        System.out.println("==================[CashCardRepository.create END]");
                        return result;
                } catch (Exception e) {
                        System.err.println("==================[CashCardRepository.create ERROR] - " + e.getMessage());
                        throw e;
                }
        }
}