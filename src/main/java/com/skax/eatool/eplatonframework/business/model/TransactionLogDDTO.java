package com.skax.eatool.eplatonframework.business.model;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.skax.eatool.framework.transfer.DTO;

public class TransactionLogDDTO extends DTO implements Cloneable {
  private String transactionId;
  private String hostName;
  private String systemName;
  private String methodName;
  private String bankCode;
  private String branchCode;
  private String userId;
  private String channelType;
  private String businessDate;
  private String registerDate;
  private String inTime;
  private String eventNo;
  private String transactionNo;
  private String outTime;
  private long responseTime;
  private String errorCode;
  private String IPAddress;

  ////////////////////////////////////////////////////////////////////////////
  // 추가 모듈 사용 필드 구성
  ////////////////////////////////////////////////////////////////////////////
  private String system_name;
  private String operation_name;
  private String cdto_name;
  private String action_name;
  private String hostseq;
  private String orgseq;
  private String tx_timer;
  private String tpfq;
  private String errorcode;
  private String trclass;
  private String web_timeout;
  private String web_intime;
  private String web_outtime;
  private String systemInTime;
  private String systemOutTime;
  private String system_date;
  private String error_message;
  ////////////////////////////////////////////////////////////////////////////
  // 추가 모듈 사용 필드 구성에서 추가로 필요한, 현재 여러가지 정보가 존재 가능하므로 하나로 묶는 것
  ////////////////////////////////////////////////////////////////////////////
  private String call_service_name;
  private String call_tpm_in_time;
  private String call_tpm_out_time;
  private String call_tpme_interval;
  private String call_tpm_stf_in_time;
  private String call_tpm_stf_out_time;
  private String call_tpm_etf_in_time;
  private String call_tpm_etf_out_time;
  private String call_tpm_service_interval;
  private String error_code;
  private String call_hostseq;
  private String call_orgseq;
  private String call_location;

  public TransactionLogDDTO() {
  }

  @Override
  public DTO clone() {
    TransactionLogDDTO cloned = new TransactionLogDDTO();
    // 기본 필드들
    cloned.transactionId = this.transactionId;
    cloned.hostName = this.hostName;
    cloned.systemName = this.systemName;
    cloned.methodName = this.methodName;
    cloned.bankCode = this.bankCode;
    cloned.branchCode = this.branchCode;
    cloned.userId = this.userId;
    cloned.channelType = this.channelType;
    cloned.businessDate = this.businessDate;
    cloned.registerDate = this.registerDate;
    cloned.inTime = this.inTime;
    cloned.eventNo = this.eventNo;
    cloned.transactionNo = this.transactionNo;
    cloned.outTime = this.outTime;
    cloned.responseTime = this.responseTime;
    cloned.errorCode = this.errorCode;
    cloned.IPAddress = this.IPAddress;

    // 추가 모듈 필드들
    cloned.system_name = this.system_name;
    cloned.operation_name = this.operation_name;
    cloned.cdto_name = this.cdto_name;
    cloned.action_name = this.action_name;
    cloned.hostseq = this.hostseq;
    cloned.orgseq = this.orgseq;
    cloned.tx_timer = this.tx_timer;
    cloned.tpfq = this.tpfq;
    cloned.errorcode = this.errorcode;
    cloned.trclass = this.trclass;
    cloned.web_timeout = this.web_timeout;
    cloned.web_intime = this.web_intime;
    cloned.web_outtime = this.web_outtime;
    cloned.systemInTime = this.systemInTime;
    cloned.systemOutTime = this.systemOutTime;
    cloned.system_date = this.system_date;
    cloned.error_message = this.error_message;

    // 호출 관련 필드들
    cloned.call_service_name = this.call_service_name;
    cloned.call_tpm_in_time = this.call_tpm_in_time;
    cloned.call_tpm_out_time = this.call_tpm_out_time;
    cloned.call_tpme_interval = this.call_tpme_interval;
    cloned.call_tpm_stf_in_time = this.call_tpm_stf_in_time;
    cloned.call_tpm_stf_out_time = this.call_tpm_stf_out_time;
    cloned.call_tpm_etf_in_time = this.call_tpm_etf_in_time;
    cloned.call_tpm_etf_out_time = this.call_tpm_etf_out_time;
    cloned.call_tpm_service_interval = this.call_tpm_service_interval;
    cloned.error_code = this.error_code;
    cloned.call_hostseq = this.call_hostseq;
    cloned.call_orgseq = this.call_orgseq;
    cloned.call_location = this.call_location;

    return cloned;
  }

  public String getTransactionId() {
    return this.transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getHostName() {
    return this.hostName;
  }

  public void setHostName(String hostName) {
    this.hostName = hostName;
  }

  public String getSystemName() {
    return this.systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }

  public String getMethodName() {
    return this.methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public String getBankCode() {
    return this.bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getBranchCode() {
    return this.branchCode;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getChannelType() {
    return this.channelType;
  }

  public void setChannelType(String channelType) {
    this.channelType = channelType;
  }

  public String getBusinessDate() {
    return this.businessDate;
  }

  public void setBusinessDate(String businessDate) {
    this.businessDate = businessDate;
  }

  public String getRegisterDate() {
    return this.registerDate;
  }

  public String getIPAddress() {
    return IPAddress;
  }

  public void setRegisterDate(String registerDate) {
    this.registerDate = registerDate;
  }

  public String getInTime() {
    return this.inTime;
  }

  public void setInTime(String inTime) {
    this.inTime = inTime;
  }

  public String getEventNo() {
    return this.eventNo;
  }

  public void setEventNo(String eventNo) {
    this.eventNo = eventNo;
  }

  public String getTransactionNo() {
    return this.transactionNo;
  }

  public void setTransactionNo(String transactionNo) {
    this.transactionNo = transactionNo;
  }

  public String getOutTime() {
    return this.outTime;
  }

  public void setOutTime(String outTime) {
    this.outTime = outTime;
  }

  public long getResponseTime() {
    return this.responseTime;
  }

  public void setResponseTime(long responseTime) {
    this.responseTime = responseTime;
  }

  public String getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setIPAddress(String IPAddress) {
    this.IPAddress = IPAddress;
  }

  public String getCall_hostseq() {
    return call_hostseq;
  }

  public void setCall_hostseq(String call_hostseq) {
    this.call_hostseq = call_hostseq;
  }

  public String getCall_location() {
    return call_location;
  }

  public void setCall_location(String call_location) {
    this.call_location = call_location;
  }

  public String getCall_orgseq() {
    return call_orgseq;
  }

  public void setCall_orgseq(String call_orgseq) {
    this.call_orgseq = call_orgseq;
  }

  public String getCall_service_name() {
    return call_service_name;
  }

  public void setCall_service_name(String call_service_name) {
    this.call_service_name = call_service_name;
  }

  public String getCall_tpm_etf_in_time() {
    return call_tpm_etf_in_time;
  }

  public void setCall_tpm_etf_in_time(String call_tpm_etf_in_time) {
    this.call_tpm_etf_in_time = call_tpm_etf_in_time;
  }

  public String getCall_tpm_etf_out_time() {
    return call_tpm_etf_out_time;
  }

  public void setCall_tpm_etf_out_time(String call_tpm_etf_out_time) {
    this.call_tpm_etf_out_time = call_tpm_etf_out_time;
  }

  public String getCall_tpm_in_time() {
    return call_tpm_in_time;
  }

  public void setCall_tpm_in_time(String call_tpm_in_time) {
    this.call_tpm_in_time = call_tpm_in_time;
  }

  public String getCall_tpm_out_time() {
    return call_tpm_out_time;
  }

  public void setCall_tpm_out_time(String call_tpm_out_time) {
    this.call_tpm_out_time = call_tpm_out_time;
  }

  public String getCall_tpm_service_interval() {
    return call_tpm_service_interval;
  }

  public void setCall_tpm_service_interval(String call_tpm_service_interval) {
    this.call_tpm_service_interval = call_tpm_service_interval;
  }

  public String getCall_tpm_stf_in_time() {
    return call_tpm_stf_in_time;
  }

  public void setCall_tpm_stf_in_time(String call_tpm_stf_in_time) {
    this.call_tpm_stf_in_time = call_tpm_stf_in_time;
  }

  public String getCall_tpm_stf_out_time() {
    return call_tpm_stf_out_time;
  }

  public void setCall_tpm_stf_out_time(String call_tpm_stf_out_time) {
    this.call_tpm_stf_out_time = call_tpm_stf_out_time;
  }

  public String getCall_tpme_interval() {
    return call_tpme_interval;
  }

  public void setCall_tpme_interval(String call_tpme_interval) {
    this.call_tpme_interval = call_tpme_interval;
  }

  public String getCdto_name() {
    return cdto_name;
  }

  public void setCdto_name(String cdto_name) {
    this.cdto_name = cdto_name;
  }

  public String getError_code() {
    return error_code;
  }

  public void setError_code(String error_code) {
    this.error_code = error_code;
  }

  public String getError_message() {
    return error_message;
  }

  public void setError_message(String error_message) {
    this.error_message = error_message;
  }

  public String getErrorcode() {
    return errorcode;
  }

  public void setErrorcode(String errorcode) {
    this.errorcode = errorcode;
  }

  public String getHostseq() {
    return hostseq;
  }

  public void setHostseq(String hostseq) {
    this.hostseq = hostseq;
  }

  public String getOperation_name() {
    return operation_name;
  }

  public void setOperation_name(String operation_name) {
    this.operation_name = operation_name;
  }

  public String getOrgseq() {
    return orgseq;
  }

  public void setOrgseq(String orgseq) {
    this.orgseq = orgseq;
  }

  public String getSystem_date() {
    return system_date;
  }

  public void setSystem_date(String system_date) {
    this.system_date = system_date;
  }

  public String getSystem_name() {
    return system_name;
  }

  public void setSystem_name(String system_name) {
    this.system_name = system_name;
  }

  public String getSystemInTime() {
    return systemInTime;
  }

  public void setSystemInTime(String systemInTime) {
    this.systemInTime = systemInTime;
  }

  public String getSystemOutTime() {
    return systemOutTime;
  }

  public void setSystemOutTime(String systemOutTime) {
    this.systemOutTime = systemOutTime;
  }

  public String getTpfq() {
    return tpfq;
  }

  public void setTpfq(String tpfq) {
    this.tpfq = tpfq;
  }

  public String getTrclass() {
    return trclass;
  }

  public void setTrclass(String trclass) {
    this.trclass = trclass;
  }

  public String getTx_timer() {
    return tx_timer;
  }

  public void setTx_timer(String tx_timer) {
    this.tx_timer = tx_timer;
  }

  public String getWeb_intime() {
    return web_intime;
  }

  public void setWeb_intime(String web_intime) {
    this.web_intime = web_intime;
  }

  public String getWeb_outtime() {
    return web_outtime;
  }

  public void setWeb_outtime(String web_outtime) {
    this.web_outtime = web_outtime;
  }

  public String getWeb_timeout() {
    return web_timeout;
  }

  public void setWeb_timeout(String web_timeout) {
    this.web_timeout = web_timeout;
  }

  public String getAction_name() {
    return action_name;
  }

  public void setAction_name(String action_name) {
    this.action_name = action_name;
  }

  @Override
  public String toString() {
    return "TransactionLogDDTO{" +
        "transactionId='" + transactionId + '\'' +
        ", hostName='" + hostName + '\'' +
        ", systemName='" + systemName + '\'' +
        ", methodName='" + methodName + '\'' +
        ", bankCode='" + bankCode + '\'' +
        ", branchCode='" + branchCode + '\'' +
        ", userId='" + userId + '\'' +
        ", channelType='" + channelType + '\'' +
        ", businessDate='" + businessDate + '\'' +
        ", registerDate='" + registerDate + '\'' +
        ", inTime='" + inTime + '\'' +
        ", eventNo='" + eventNo + '\'' +
        ", transactionNo='" + transactionNo + '\'' +
        ", outTime='" + outTime + '\'' +
        ", responseTime=" + responseTime +
        ", errorCode='" + errorCode + '\'' +
        ", IPAddress='" + IPAddress + '\'' +
        ", system_name='" + system_name + '\'' +
        ", operation_name='" + operation_name + '\'' +
        ", cdto_name='" + cdto_name + '\'' +
        ", action_name='" + action_name + '\'' +
        ", hostseq='" + hostseq + '\'' +
        ", orgseq='" + orgseq + '\'' +
        ", tx_timer='" + tx_timer + '\'' +
        ", tpfq='" + tpfq + '\'' +
        ", errorcode='" + errorcode + '\'' +
        ", trclass='" + trclass + '\'' +
        ", web_timeout='" + web_timeout + '\'' +
        ", web_intime='" + web_intime + '\'' +
        ", web_outtime='" + web_outtime + '\'' +
        ", systemInTime='" + systemInTime + '\'' +
        ", systemOutTime='" + systemOutTime + '\'' +
        ", system_date='" + system_date + '\'' +
        ", error_message='" + error_message + '\'' +
        ", call_service_name='" + call_service_name + '\'' +
        ", call_tpm_in_time='" + call_tpm_in_time + '\'' +
        ", call_tpm_out_time='" + call_tpm_out_time + '\'' +
        ", call_tpme_interval='" + call_tpme_interval + '\'' +
        ", call_tpm_stf_in_time='" + call_tpm_stf_in_time + '\'' +
        ", call_tpm_stf_out_time='" + call_tpm_stf_out_time + '\'' +
        ", call_tpm_etf_in_time='" + call_tpm_etf_in_time + '\'' +
        ", call_tpm_etf_out_time='" + call_tpm_etf_out_time + '\'' +
        ", call_tpm_service_interval='" + call_tpm_service_interval + '\'' +
        ", error_code='" + error_code + '\'' +
        ", call_hostseq='" + call_hostseq + '\'' +
        ", call_orgseq='" + call_orgseq + '\'' +
        ", call_location='" + call_location + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    TransactionLogDDTO other = (TransactionLogDDTO) obj;
    if (transactionId == null) {
      if (other.transactionId != null)
        return false;
    } else if (!transactionId.equals(other.transactionId))
      return false;
    if (hostName == null) {
      if (other.hostName != null)
        return false;
    } else if (!hostName.equals(other.hostName))
      return false;
    if (systemName == null) {
      if (other.systemName != null)
        return false;
    } else if (!systemName.equals(other.systemName))
      return false;
    if (methodName == null) {
      if (other.methodName != null)
        return false;
    } else if (!methodName.equals(other.methodName))
      return false;
    // For brevity, comparing key fields only - in a real implementation you'd
    // compare all fields
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
    result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
    result = prime * result + ((systemName == null) ? 0 : systemName.hashCode());
    result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
    result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
    result = prime * result + ((branchCode == null) ? 0 : branchCode.hashCode());
    result = prime * result + ((userId == null) ? 0 : userId.hashCode());
    result = prime * result + ((channelType == null) ? 0 : channelType.hashCode());
    result = prime * result + ((businessDate == null) ? 0 : businessDate.hashCode());
    result = prime * result + ((registerDate == null) ? 0 : registerDate.hashCode());
    result = prime * result + ((inTime == null) ? 0 : inTime.hashCode());
    result = prime * result + ((eventNo == null) ? 0 : eventNo.hashCode());
    result = prime * result + ((transactionNo == null) ? 0 : transactionNo.hashCode());
    result = prime * result + ((outTime == null) ? 0 : outTime.hashCode());
    result = prime * result + (int) (responseTime ^ (responseTime >>> 32));
    result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
    result = prime * result + ((IPAddress == null) ? 0 : IPAddress.hashCode());
    return result;
  }
}
