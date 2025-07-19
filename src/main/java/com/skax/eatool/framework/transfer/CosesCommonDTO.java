package com.skax.eatool.framework.transfer;

import java.io.Serializable;

/**
 * CosesCommonDTO - Common DTO for SKCC Oversea
 * 
 * Base DTO class for common data transfer operations
 * in the Spring Boot migration.
 */
public class CosesCommonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String requestId;
    private String userId;
    private String sessionId;
    private String timestamp;
    private Object data;
    private String bankCode;
    private String branchCode;
    private String userID;
    private String systemDate;
    private String systemInTime;
    private String businessDate;
    private String transactionNo;

    public CosesCommonDTO() {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public CosesCommonDTO(String requestId, String userId) {
        this();
        this.requestId = requestId;
        this.userId = userId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(String systemDate) {
        this.systemDate = systemDate;
    }

    public String getSystemInTime() {
        return systemInTime;
    }

    public void setSystemInTime(String systemInTime) {
        this.systemInTime = systemInTime;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
