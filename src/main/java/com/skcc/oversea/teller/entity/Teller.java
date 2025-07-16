package com.skcc.oversea.teller.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "MainTeller")
@Table(name = "TELLER")
public class Teller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TELLER_ID", length = 20, unique = true)
    private String tellerId;

    @Column(name = "TELLER_NAME", length = 100)
    private String tellerName;

    @Column(name = "BRANCH_CODE", length = 10)
    private String branchCode;

    @Column(name = "BANK_CODE", length = 10)
    private String bankCode;

    @Column(name = "STATUS", length = 10)
    private String status;

    @Column(name = "REGISTER_DATE", length = 8)
    private String registerDate;

    @Column(name = "REGISTER_TIME", length = 6)
    private String registerTime;

    @Column(name = "REGISTER_BY", length = 20)
    private String registerBy;

    @Column(name = "LAST_UPDATE_DATE", length = 8)
    private String lastUpdateDate;

    @Column(name = "LAST_UPDATE_TIME", length = 6)
    private String lastUpdateTime;

    @Column(name = "LAST_UPDATE_USER_ID", length = 20)
    private String lastUpdateUserId;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    // Constructors
    public Teller() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public Teller(String tellerId, String tellerName, String branchCode) {
        this();
        this.tellerId = tellerId;
        this.tellerName = tellerName;
        this.branchCode = branchCode;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTellerId() {
        return tellerId;
    }

    public void setTellerId(String tellerId) {
        this.tellerId = tellerId;
    }

    public String getTellerName() {
        return tellerName;
    }

    public void setTellerName(String tellerName) {
        this.tellerName = tellerName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}