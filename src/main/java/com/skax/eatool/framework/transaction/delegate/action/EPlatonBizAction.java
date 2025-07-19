package com.skax.eatool.framework.transaction.delegate.action;

import com.skax.eatool.framework.transaction.tpmutil.TPMUtil;
import com.skax.eatool.framework.exception.BizActionException;
import com.skax.eatool.foundation.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EPlatonBizAction {

  @Autowired
  private TPMUtil tpmUtil;

  public void processBusinessAction(String actionId) throws BizActionException {
    try {
      // TPM 초기화 및 설정
      tpmUtil.initializeTPM();

      // 성공 처리
      String status = Constants.TXN_STATUS_SUCCESS;

    } catch (Exception e) {
      // 실패 처리
      String status = Constants.TXN_STATUS_FAILED;
      throw new BizActionException("Business action failed", e);
    }
  }
}
