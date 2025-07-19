package com.skax.eatool.cashCard.transfer;

import java.math.BigDecimal;
import com.skax.eatool.framework.transfer.DTO;
import com.skax.eatool.framework.constants.Constants;

/**
 * Account Query CDTO for SKCC Oversea
 * 
 * Data Transfer Object for account query operations
 */
public class AccountQueryCDTO extends DTO {
    private String accountNumber = Constants.BLANK;
    private String bankCode = Constants.BLANK;
    private String branchCode = Constants.BLANK;
    private String accountType = Constants.BLANK;
    private String CIFNo = Constants.BLANK;
    private String CIFName = Constants.BLANK;
    private String currency = Constants.BLANK;
    private BigDecimal balance = Constants.ZERO;
    private String status = Constants.BLANK;
    private String openDate = Constants.BLANK;
    private String lastTransactionDate = Constants.BLANK;
    private BigDecimal lastTransactionAmount = Constants.ZERO;
    private String description = Constants.BLANK;

    public AccountQueryCDTO() {
    }

    public AccountQueryCDTO(String accountNumber, String bankCode) {
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
    }

    // Getter Methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCIFNo() {
        return CIFNo;
    }

    public String getCIFName() {
        return CIFName;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getLastTransactionDate() {
        return lastTransactionDate;
    }

    public BigDecimal getLastTransactionAmount() {
        return lastTransactionAmount;
    }

    public String getDescription() {
        return description;
    }

    // Setter Methods
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCIFNo(String CIFNo) {
        this.CIFNo = CIFNo;
    }

    public void setCIFName(String CIFName) {
        this.CIFName = CIFName;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setLastTransactionDate(String lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    public void setLastTransactionAmount(BigDecimal lastTransactionAmount) {
        this.lastTransactionAmount = lastTransactionAmount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AccountQueryCDTO{" +
                "accountNumber='" + accountNumber + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", branchCode='" + branchCode + '\'' +
                ", accountType='" + accountType + '\'' +
                ", CIFNo='" + CIFNo + '\'' +
                ", CIFName='" + CIFName + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                ", openDate='" + openDate + '\'' +
                ", lastTransactionDate='" + lastTransactionDate + '\'' +
                ", lastTransactionAmount=" + lastTransactionAmount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
        result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
        result = prime * result + ((branchCode == null) ? 0 : branchCode.hashCode());
        result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
        result = prime * result + ((CIFNo == null) ? 0 : CIFNo.hashCode());
        result = prime * result + ((CIFName == null) ? 0 : CIFName.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((openDate == null) ? 0 : openDate.hashCode());
        result = prime * result + ((lastTransactionDate == null) ? 0 : lastTransactionDate.hashCode());
        result = prime * result + ((lastTransactionAmount == null) ? 0 : lastTransactionAmount.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        AccountQueryCDTO other = (AccountQueryCDTO) obj;
        if (accountNumber == null) {
            if (other.accountNumber != null)
                return false;
        } else if (!accountNumber.equals(other.accountNumber))
            return false;
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
        if (accountType == null) {
            if (other.accountType != null)
                return false;
        } else if (!accountType.equals(other.accountType))
            return false;
        if (CIFNo == null) {
            if (other.CIFNo != null)
                return false;
        } else if (!CIFNo.equals(other.CIFNo))
            return false;
        if (CIFName == null) {
            if (other.CIFName != null)
                return false;
        } else if (!CIFName.equals(other.CIFName))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        if (balance == null) {
            if (other.balance != null)
                return false;
        } else if (!balance.equals(other.balance))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (openDate == null) {
            if (other.openDate != null)
                return false;
        } else if (!openDate.equals(other.openDate))
            return false;
        if (lastTransactionDate == null) {
            if (other.lastTransactionDate != null)
                return false;
        } else if (!lastTransactionDate.equals(other.lastTransactionDate))
            return false;
        if (lastTransactionAmount == null) {
            if (other.lastTransactionAmount != null)
                return false;
        } else if (!lastTransactionAmount.equals(other.lastTransactionAmount))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public DTO clone() {
        AccountQueryCDTO cloned = new AccountQueryCDTO();
        cloned.accountNumber = this.accountNumber;
        cloned.bankCode = this.bankCode;
        cloned.branchCode = this.branchCode;
        cloned.accountType = this.accountType;
        cloned.CIFNo = this.CIFNo;
        cloned.CIFName = this.CIFName;
        cloned.currency = this.currency;
        cloned.balance = this.balance;
        cloned.status = this.status;
        cloned.openDate = this.openDate;
        cloned.lastTransactionDate = this.lastTransactionDate;
        cloned.lastTransactionAmount = this.lastTransactionAmount;
        cloned.description = this.description;
        return cloned;
    }
} 
