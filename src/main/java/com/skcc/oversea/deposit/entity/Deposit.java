package com.skcc.oversea.deposit.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "MainDeposit")
@Table(name = "DEPOSIT")
@EntityListeners(AuditingEntityListener.class)
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPOSIT_ID")
    private Long depositId;

    @Column(name = "ACCOUNT_NUMBER", length = 20)
    private String accountNumber;

    @Column(name = "BANK_CODE", length = 3)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 3)
    private String branchCode;

    @Column(name = "CIF_NO", length = 10)
    private String cifNo;

    @Column(name = "CIF_NAME", length = 100)
    private String cifName;

    @Column(name = "DEPOSIT_TYPE", length = 10)
    private String depositType;

    @Column(name = "AMOUNT", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "BALANCE", precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "CURRENCY", length = 3)
    private String currency;

    @Column(name = "INTEREST_RATE", precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "MATURITY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime maturityDate;

    @Column(name = "OPEN_DATE", length = 8)
    private String openDate;

    @Column(name = "REGISTER_DATE", length = 8)
    private String registerDate;

    @Column(name = "REGISTER_TIME", length = 6)
    private String registerTime;

    @Column(name = "REGISTER_BY", length = 20)
    private String registerBy;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    // BaseEntity에서 필요한 필드들 추가
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "modified_by", length = 50)
    private String modifiedBy;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Constructors
    public Deposit() {
    }

    public Deposit(String accountNumber, String depositType, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.depositType = depositType;
        this.amount = amount;
        this.createdDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getDepositId() {
        return depositId;
    }

    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCifNo() {
        return cifNo;
    }

    public void setCifNo(String cifNo) {
        this.cifNo = cifNo;
    }

    public String getCifName() {
        return cifName;
    }

    public void setCifName(String cifName) {
        this.cifName = cifName;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDateTime getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDateTime maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public LocalDateTime getCreateDate() {
        return createdDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createdDate = createDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterBy() {
        return registerBy;
    }

    public void setRegisterBy(String registerBy) {
        this.registerBy = registerBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // BaseEntity 필드들의 getter/setter
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}