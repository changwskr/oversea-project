package com.skcc.oversea.framework.transaction.constant;

import java.io.Serializable;
import java.math.BigDecimal;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;

/**
 * =============================================================================
 * ?꾨줈洹몃옩 ?ㅻ챸:
 * =============================================================================
 *   Transaction Control Framwwork?곸뿉???ъ슜?섎뒗 紐⑤뱺 ?곸닔媛믪뿉 ??댁꽌 ?ㅼ젙?섎뒗 ?대옒?? *
 * =============================================================================
 * 蹂寃쎈궡???뺣낫:
 * =============================================================================
 *  2004??03??16??1李⑤쾭??release
 *
 *
 *                                                        @author : WooSungJang
 *                                                        @email  : changwskr@yahoo.co.kr
 *                                                        @version 1.0
 *  =============================================================================
 */


public final class TCFConstants {

  public static String TX_BLOCKING_CONFIG_FILE_NAME = "/weblogic/bea/wlserver6.1/config/coses_US/applicationConfig/EPLtxblocking.xml";
  public static String TPMTRACE_LoggingFileName = "/home/coses/log/outlog/TPMTRACE";
  public static String INPUT_LOGFILENAME = "/home/coses/log/input/";
  public static String OUTPUT_LOGFILENAME = "/home/coses/log/output/";
  public static String BIZDELEGATE_CALL_METHOD_NAME="execute";
  public static String BIZDELEGATE_CALL_PARAMETER_TYPE_NAME=EPlatonEvent.class.getName();
  public static String TCF_TRANSACTION_INFO_FILE_NAME="/home/coses/log/outlog/TPMTRACE_TRANSACTIONINFO";
  public static String TCF_TRANSACTION_DEFAULT_TIMEOUT="180";
  public static String TCF_SUCCESS_ERRCODE="IZZ000";

  // Fields
  public static final String BLANK = "";
  public static final String BIZDELEGATE_TAG = "bizaction-map-filename";
  public static final String ACTIONCLASS_TAG = "bizaction-class";
  public static final String TRANSACTIONABLE = "transactionable";
  public static final String METHOD_TAG = "bizaction-method";
  public static final String TYPE_TAG = "bizaction-parametertype";
  public static final String CALL_BEAN_TYPE_TAG = "action-call-bean-type";
  public static final String BEAN_TRANSACTION_TYPE_TAG = "bean-transaction-type";
  public static final String GENERAL_UI_CHANNEL = "01";
  public static final String BATCH_CHANNEL = "02";
  public static final String ATM_CHANNEL = "03";
  public static final String INTERNET_BANK_CHANNEL = "10";


}
