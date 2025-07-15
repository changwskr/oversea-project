package com.skcc.oversea.framework.transaction.dao;

import com.skcc.oversea.foundation.utility.StringUtils;
import com.skcc.oversea.foundation.utility.CommonUtil;
import com.skcc.oversea.foundation.logej.LOGEJ;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.eplatonframework.transfer.EPlatonCommonDTO;
import com.skcc.oversea.eplatonframework.transfer.TPSVCINFODTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Entity
@Table(name = "TRANSACTION_UP_DOWN")
public class TransactionUpDownEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "TRANSACTION_ID")
  private String transactionId;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "CREATE_DATE")
  private LocalDateTime createDate;

  // Constructors
  public TransactionUpDownEntity() {
  }

  public TransactionUpDownEntity(String transactionId, String status) {
    this.transactionId = transactionId;
    this.status = status;
    this.createDate = LocalDateTime.now();
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  /**
   * Insert transaction in log - called by TransactionControlDAO
   */
  public static void insertTransactionInLog(Connection con, EPlatonEvent event) throws SQLException {
    EPlatonCommonDTO commonDTO = event.getCommon();
    TPSVCINFODTO tpsvcinfoDTO = event.getTPSVCINFODTO();
    
    String transactionId = tpsvcinfoDTO.getBp_sequence();
    String hostName = CommonUtil.GetHostName();
    String bankCode = commonDTO.getBankCode();
    String branchCode = commonDTO.getBranchCode();
    String userId = commonDTO.getUserID();
    String channelType = commonDTO.getChannelType();
    String businessDate = commonDTO.getBusinessDate();
    String eventNo = commonDTO.getEventNo();
    String ipAddress = commonDTO.getIPAddress();
    
    String sql = "INSERT INTO TRANSACTION_UP_DOWN (TRANSACTION_ID, HOST_NAME, BANK_CODE, BRANCH_CODE, " +
                 "USER_ID, CHANNEL_TYPE, BUSINESS_DATE, EVENT_NO, IP_ADDRESS, STATUS, CREATE_DATE) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'IN', ?)";
    
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, transactionId);
      pstmt.setString(2, hostName);
      pstmt.setString(3, bankCode);
      pstmt.setString(4, branchCode);
      pstmt.setString(5, userId);
      pstmt.setString(6, channelType);
      pstmt.setString(7, businessDate);
      pstmt.setString(8, eventNo);
      pstmt.setString(9, ipAddress);
      pstmt.setTimestamp(10, java.sql.Timestamp.valueOf(LocalDateTime.now()));
      
      pstmt.executeUpdate();
      LOGEJ.getInstance().printf(1, event, "Inserted transaction in log: " + transactionId);
    }
  }
  
  /**
   * Insert transaction out log - called by TransactionControlDAO
   */
  public static void insertTransactionOutLog(Connection con, EPlatonEvent event) throws SQLException {
    EPlatonCommonDTO commonDTO = event.getCommon();
    TPSVCINFODTO tpsvcinfoDTO = event.getTPSVCINFODTO();
    
    String transactionId = tpsvcinfoDTO.getBp_sequence();
    String hostName = CommonUtil.GetHostName();
    String bankCode = commonDTO.getBankCode();
    String branchCode = commonDTO.getBranchCode();
    String userId = commonDTO.getUserID();
    String channelType = commonDTO.getChannelType();
    String businessDate = commonDTO.getBusinessDate();
    String eventNo = commonDTO.getEventNo();
    String ipAddress = commonDTO.getIPAddress();
    
    String sql = "INSERT INTO TRANSACTION_UP_DOWN (TRANSACTION_ID, HOST_NAME, BANK_CODE, BRANCH_CODE, " +
                 "USER_ID, CHANNEL_TYPE, BUSINESS_DATE, EVENT_NO, IP_ADDRESS, STATUS, CREATE_DATE) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'OUT', ?)";
    
    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
      pstmt.setString(1, transactionId);
      pstmt.setString(2, hostName);
      pstmt.setString(3, bankCode);
      pstmt.setString(4, branchCode);
      pstmt.setString(5, userId);
      pstmt.setString(6, channelType);
      pstmt.setString(7, businessDate);
      pstmt.setString(8, eventNo);
      pstmt.setString(9, ipAddress);
      pstmt.setTimestamp(10, java.sql.Timestamp.valueOf(LocalDateTime.now()));
      
      pstmt.executeUpdate();
      LOGEJ.getInstance().printf(1, event, "Inserted transaction out log: " + transactionId);
    }
  }
}
