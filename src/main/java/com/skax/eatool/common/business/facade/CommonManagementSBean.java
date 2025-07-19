package com.skax.eatool.common.business.facade;

import com.skax.eatool.framework.transaction.tpmutil.TPMUtil;
import com.skax.eatool.deposit.transfer.DepositTransferDTO;
import com.skax.eatool.foundation.tpmservice.TPSsendrecv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonManagementSBean {

  @Autowired(required = false)
  private TPMUtil tpmUtil;

  @Autowired(required = false)
  private TPSsendrecv tpsSendRecv;

  public void processCommonTransaction(String transactionId) {
    // 공통 거래 처리 로직
    if (tpmUtil != null) {
      tpmUtil.initializeTPM();
    }
    if (tpsSendRecv != null) {
      tpsSendRecv.sendMessage("Transaction: " + transactionId);
    }
  }

  public DepositTransferDTO getDepositInfo(String accountNumber) {
    // 예금 정보 조회 로직
    return new DepositTransferDTO(accountNumber, "SAVINGS", null);
  }
}
