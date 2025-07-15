package com.skcc.oversea.common.business.facade;

import com.skcc.oversea.framework.transaction.tpmutil.TPMUtil;
import com.skcc.oversea.deposit.transfer.DepositTransferDTO;
import com.skcc.oversea.foundation.tpmservice.TPSsendrecv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonManagementSBean {

  @Autowired
  private TPMUtil tpmUtil;

  @Autowired
  private TPSsendrecv tpsSendRecv;

  public void processCommonTransaction(String transactionId) {
    // 怨듯넻 ?몃옖??뀡 泥섎━ 濡쒖쭅
    tpmUtil.initializeTPM();
    tpsSendRecv.sendMessage("Transaction: " + transactionId);
  }

  public DepositTransferDTO getDepositInfo(String accountNumber) {
    // ?덇툑 ?뺣낫 議고쉶 濡쒖쭅
    return new DepositTransferDTO(accountNumber, "SAVINGS", null);
  }
}
