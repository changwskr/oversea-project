package com.skcc.oversea.cashCard.business.cashCard.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

/**
 * CashCardPK - Primary Key for CashCard entity
 * 
 * Composite primary key class for CashCard entity
 * in the Spring Boot migration.
 */
@Embeddable
public class CashCardPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int sequenceNo;
    private String cardNumber;

    public CashCardPK() {
    }

    public CashCardPK(int sequenceNo, String cardNumber) {
        this.sequenceNo = sequenceNo;
        this.cardNumber = cardNumber;
    }

    /**
     * Alternative constructor for eplatonframework compatibility
     * Uses cardNo as cardNumber and accountNo is ignored for now
     */
    public CashCardPK(String cardNo, String accountNo) {
        this.sequenceNo = 0; // Default value, should be set appropriately
        this.cardNumber = cardNo;
        // accountNo is ignored in this implementation
        // If needed, additional fields can be added to support accountNo
    }

    public int getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        CashCardPK that = (CashCardPK) obj;

        if (sequenceNo != that.sequenceNo)
            return false;
        return cardNumber != null ? cardNumber.equals(that.cardNumber) : that.cardNumber == null;
    }

    @Override
    public int hashCode() {
        int result = sequenceNo;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        return result;
    }
}