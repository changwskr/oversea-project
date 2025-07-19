package com.skax.eatool.cashCard.transfer;

import java.math.BigDecimal;

import com.skax.eatool.framework.transfer.DTO;
import com.skax.eatool.framework.constants.Constants;

import com.skax.eatool.cashCard.business.constants.CashCardConstants;

public class ValidateTransactionCDTO extends DTO {
    private String bankCode = Constants.BLANK;
    private String branchCode = Constants.BLANK;
    private String primaryAccountNo = Constants.BLANK;
    private String cardNumber = Constants.BLANK;
    private String passwordNo = Constants.BLANK;
    private BigDecimal transactionAmount = Constants.ZERO;
    private String cancelYN = CashCardConstants.BOOLEAN_FALSE;

    public ValidateTransactionCDTO() {
    }

    // Getter Method
    public String getBankCode() {
        return bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPrimaryAccountNo() {
        return primaryAccountNo;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public String getPasswordNo() {
        return passwordNo;
    }

    public String getCancelYN() {
        return cancelYN;
    }

    // Setter Method
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPrimaryAccountNo(String primaryAccountNo) {
        this.primaryAccountNo = primaryAccountNo;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setPasswordNo(String passwordNo) {
        this.passwordNo = passwordNo;
    }

    public void setCancelYN(String cancelYN) {
        this.cancelYN = cancelYN;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
        result = prime * result + ((branchCode == null) ? 0 : branchCode.hashCode());
        result = prime * result + ((primaryAccountNo == null) ? 0 : primaryAccountNo.hashCode());
        result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
        result = prime * result + ((passwordNo == null) ? 0 : passwordNo.hashCode());
        result = prime * result + ((transactionAmount == null) ? 0 : transactionAmount.hashCode());
        result = prime * result + ((cancelYN == null) ? 0 : cancelYN.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ValidateTransactionCDTO other = (ValidateTransactionCDTO) obj;
        if (bankCode == null) {
            if (other.bankCode != null)
                return false;
        } else if (!bankCode.equals(other.bankCode))
            return false;
        if (branchCode == null) {
            if (other.branchCode != null)
                return false;
        } else if (!branchCode.equals(other.branchCode))
            return false;
        if (primaryAccountNo == null) {
            if (other.primaryAccountNo != null)
                return false;
        } else if (!primaryAccountNo.equals(other.primaryAccountNo))
            return false;
        if (cardNumber == null) {
            if (other.cardNumber != null)
                return false;
        } else if (!cardNumber.equals(other.cardNumber))
            return false;
        if (passwordNo == null) {
            if (other.passwordNo != null)
                return false;
        } else if (!passwordNo.equals(other.passwordNo))
            return false;
        if (transactionAmount == null) {
            if (other.transactionAmount != null)
                return false;
        } else if (!transactionAmount.equals(other.transactionAmount))
            return false;
        if (cancelYN == null) {
            if (other.cancelYN != null)
                return false;
        } else if (!cancelYN.equals(other.cancelYN))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ValidateTransactionCDTO{" +
                "bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", primaryAccountNo='" + primaryAccountNo + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", passwordNo='" + passwordNo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", cancelYN='" + cancelYN + '\'' +
                '}';
    }

    @Override
    public DTO clone() {
        ValidateTransactionCDTO cloned = new ValidateTransactionCDTO();
        cloned.bankCode = this.bankCode;
        cloned.branchCode = this.branchCode;
        cloned.primaryAccountNo = this.primaryAccountNo;
        cloned.cardNumber = this.cardNumber;
        cloned.passwordNo = this.passwordNo;
        cloned.transactionAmount = this.transactionAmount;
        cloned.cancelYN = this.cancelYN;
        return cloned;
    }
}
