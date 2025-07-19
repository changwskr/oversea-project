package com.skax.eatool.framework.transaction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.skax.eatool.foundation.logej.LOGEJ;
import com.skax.eatool.foundation.utility.CommonUtil;
import com.skax.eatool.foundation.utility.FPrintf;
import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;
import com.skax.eatool.framework.transaction.model.TransactionLogDDTO;

/**
 * =============================================================================
 * 프로그램 명:
 * =============================================================================
 *
 *
 * =============================================================================
 * 변경내역 정보:
 * =============================================================================
 * 2004년 03월 16일 1차버전 release
 *
 *
 * =============================================================================
 * 
 * @author : 장우성(WooSungJang)
 * @company: IMS SYSTEM
 * @email : changwskr@yahoo.co.kr
 * @version 1.0
 *          =============================================================================
 */

public class TransactionLogEntity extends AbstractEntity {

  public TransactionLogEntity() {
  }

  public static void insertTransactionInLog(
      Connection con,
      EPlatonEvent event)
      throws SQLException {
    String transactionId = event.getTPSVCINFODTO().getBp_sequence();
    String methodName = event.getTPSVCINFODTO().getOperation_method();
    String systemName = event.getTPSVCINFODTO().getSystem_name();
    String hostName = CommonUtil.GetHostName();
    String bankCode = event.getCommon().getBankCode();
    String branchCode = event.getCommon().getBranchCode();
    String userId = event.getCommon().getUserID();
    String channelType = event.getCommon().getChannelType();
    String businessDate = event.getCommon().getBusinessDate();
    String eventNo = event.getCommon().getEventNo();
    String IPAddress = event.getCommon().getIPAddress();
    String org_seq = event.getTPSVCINFODTO().getOrgseq();
    String transaction_no = event.getTPSVCINFODTO().getHostseq();
    String tpfq = event.getTPSVCINFODTO().getTpfq();

    /**
     * 거래 식별자에 대한 설명: 거래를 구분하기 위한 고유한 식별자로 사용됩니다.
     * 형식: bp_sequence + "-" + orgseq + "-" + hostseq + "-" + tpfq
     * 예시: 10101010-00000001-00000001-200,10101010-00000001-00000002-100
     */
    transactionId = transactionId + "-" + org_seq + "-" + transaction_no + "-" + tpfq;

    LOGEJ.getInstance().printf(1, event, "  =====================================");
    LOGEJ.getInstance().printf(1, event, "TransactionID : " + transactionId);
    LOGEJ.getInstance().printf(1, event, "MethodName : " + methodName);
    LOGEJ.getInstance().printf(1, event, "SystemName : " + systemName);
    LOGEJ.getInstance().printf(1, event, "HostName : " + hostName);
    LOGEJ.getInstance().printf(1, event, "UserID : " + userId);
    LOGEJ.getInstance().printf(1, event, "ChannelType : " + channelType);
    LOGEJ.getInstance().printf(1, event, "BusinessDate : " + businessDate);
    LOGEJ.getInstance().printf(1, event, "EventNo : " + eventNo);
    LOGEJ.getInstance().printf(1, event, "IP Address : " + IPAddress);
    LOGEJ.getInstance().printf(1, event, "transaction_no : " + transaction_no);
    LOGEJ.getInstance().printf(1, event, "=====================================");

    PreparedStatement pstmt = null;
    StringBuffer query = new StringBuffer();
    query.append("INSERT INTO transaction_info (                                   ");
    query.append("                             transaction_id  ,                  ");
    query.append("                             host_name       ,                  ");
    query.append("                             system_name     ,                  ");
    query.append("                             method_name     ,                  ");
    query.append("                             bank_code       ,                  ");
    query.append("                             branch_code     ,                  ");
    query.append("                             user_id         ,                  ");
    query.append("                             channel_type    ,                  ");
    query.append("                             business_date   ,                  ");
    query.append("                             register_date   ,                  ");
    query.append("                             in_time         ,                  ");
    query.append("                             out_time        ,                  ");
    query.append("                             event_no        ,                  ");
    query.append("                             transaction_no  ,                  ");
    query.append("                             org_seq         ,                  ");
    query.append("                             tpfq            ,                  ");
    query.append("                             ip_address                         ");
    query.append("                            )                                   ");
    query.append("VALUES (                                                        ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        to_char(sysdate, 'yyyyMMdd') ,                          ");
    query.append("        to_char(sysdate,'HH24MIss')  ,                          ");
    query.append("        'XXXXXX'                     ,                          ");
    query.append("        ?                            ,                          ");
    query.append("        ?                            ,                           ");
    query.append("        ?                            ,                           ");
    query.append("        ?                            ,                           ");
    query.append("        ?                                                      ");
    query.append("      )                                                         ");
    try {
      pstmt = con.prepareStatement(query.toString());
      int index = 0;
      pstmt.setString(++index, transactionId);
      pstmt.setString(++index, hostName);
      pstmt.setString(++index, systemName);
      pstmt.setString(++index, methodName);
      pstmt.setString(++index, bankCode);
      pstmt.setString(++index, branchCode);
      pstmt.setString(++index, userId);
      pstmt.setString(++index, channelType);
      pstmt.setString(++index, businessDate);
      pstmt.setString(++index, eventNo);
      pstmt.setString(++index, transaction_no);
      pstmt.setString(++index, org_seq);
      pstmt.setString(++index, tpfq);
      pstmt.setString(++index, IPAddress);

      LOGEJ.getInstance().printf(1, event, "insertTransactionInLog Query : " + query.toString());

      pstmt.executeUpdate();
      con.commit();
    } finally {
      releaseResource(pstmt, null);
    }
  }

  public static void insertTransactionOutLog(
      Connection con,
      long interval_seconds,
      EPlatonEvent event)
      throws SQLException {
    String transactionId = event.getTPSVCINFODTO().getBp_sequence();
    String transactionNo = event.getTPSVCINFODTO().getHostseq();
    String errorCode = event.getTPSVCINFODTO().getErrorcode();
    String org_seq = event.getTPSVCINFODTO().getOrgseq();
    String transaction_no = event.getTPSVCINFODTO().getHostseq();
    String tpfq = event.getTPSVCINFODTO().getTpfq();

    /**
     * 거래 식별자에 대한 설명: 거래를 구분하기 위한 고유한 식별자로 사용됩니다.
     * 형식: bp_sequence + "-" + orgseq + "-" + hostseq + "-" + tpfq
     * 예시: 10101010-00000001-00000001-200,10101010-00000001-00000002-100
     */
    transactionId = transactionId + "-" + org_seq + "-" + transaction_no + "-" + tpfq;

    LOGEJ.getInstance().printf(1, event, "=====================================");
    LOGEJ.getInstance().printf(1, event, "TransactionID : " + transactionId);
    LOGEJ.getInstance().printf(1, event, "TransactionNo : " + transactionNo);
    LOGEJ.getInstance().printf(1, event, "ResponseTime : " + interval_seconds);
    LOGEJ.getInstance().printf(1, event, "ErrorCode : " + errorCode);
    LOGEJ.getInstance().printf(1, event, "=====================================");

    PreparedStatement pstmt = null;
    StringBuffer query = new StringBuffer();
    query.append("UPDATE transaction_info                                  ");
    query.append("SET                                                     ");
    query.append("    out_time        = to_char(sysdate,'HH24MIss')  ,   ");
    query.append("    response_time   = ?                             ,   ");
    query.append("    error_code      = ?                                 ");
    query.append("WHERE transaction_id = ?                                ");
    try {
      pstmt = con.prepareStatement(query.toString());
      int index = 0;

      pstmt.setString(++index, CommonUtil.Float2Str(interval_seconds / 1000.0f));
      pstmt.setString(++index, errorCode);
      pstmt.setString(++index, transactionId);

      LOGEJ.getInstance().printf(1, event, "insertTransactionOutLog Query : " + query.toString());

      pstmt.executeUpdate();

      con.commit();

    } finally {
      releaseResource(pstmt, null);
    }
  }

  public static void insertTransactionLog2File(EPlatonEvent event, long interval_seconds) {
    String transactionId = event.getTPSVCINFODTO().getBp_sequence();
    String transactionNo = event.getTPSVCINFODTO().getHostseq();
    String errorCode = event.getTPSVCINFODTO().getErrorcode();
    String org_seq = event.getTPSVCINFODTO().getOrgseq();
    String transaction_no = event.getTPSVCINFODTO().getHostseq();
    String tpfq = event.getTPSVCINFODTO().getTpfq();

    /**
     * 거래 식별자에 대한 설명: 거래를 구분하기 위한 고유한 식별자로 사용됩니다.
     * 형식: bp_sequence + "-" + orgseq + "-" + hostseq + "-" + tpfq
     * 예시: 10101010-00000001-00000001-200,10101010-00000001-00000002-100
     */
    transactionId = transactionId + "-" + org_seq + "-" + transaction_no + "-" + tpfq;

    LOGEJ.getInstance().printf(1, event, "=====================================");
    LOGEJ.getInstance().printf(1, event, "TransactionID : " + transactionId);
    LOGEJ.getInstance().printf(1, event, "TransactionNo : " + transactionNo);
    LOGEJ.getInstance().printf(1, event, "ResponseTime : " + interval_seconds);
    LOGEJ.getInstance().printf(1, event, "ErrorCode : " + errorCode);
    LOGEJ.getInstance().printf(1, event, "=====================================");

    String logLine = getTransactionLogLineInfo(event, interval_seconds);
    // FPrintf.getInstance().printf("transaction.log", logLine);
    System.out.println("Transaction Log: " + logLine);
  }

  public static String getTransactionLogLineInfo(EPlatonEvent event, long interval_seconds) {
    String transactionId = event.getTPSVCINFODTO().getBp_sequence();
    String transactionNo = event.getTPSVCINFODTO().getHostseq();
    String errorCode = event.getTPSVCINFODTO().getErrorcode();
    String org_seq = event.getTPSVCINFODTO().getOrgseq();
    String transaction_no = event.getTPSVCINFODTO().getHostseq();
    String tpfq = event.getTPSVCINFODTO().getTpfq();

    /**
     * 거래 식별자에 대한 설명: 거래를 구분하기 위한 고유한 식별자로 사용됩니다.
     * 형식: bp_sequence + "-" + orgseq + "-" + hostseq + "-" + tpfq
     * 예시: 10101010-00000001-00000001-200,10101010-00000001-00000002-100
     */
    transactionId = transactionId + "-" + org_seq + "-" + transaction_no + "-" + tpfq;

    StringBuffer logLine = new StringBuffer();
    logLine.append(transactionId).append("|");
    logLine.append(transactionNo).append("|");
    logLine.append(interval_seconds).append("|");
    logLine.append(errorCode).append("|");
    logLine.append(java.time.LocalDateTime.now().toString());

    return logLine.toString();
  }
}
