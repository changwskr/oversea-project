package com.skcc.oversea.cashCard.business.cashCardRule.helper;

import com.skcc.oversea.common.business.facade.CommonManagementSBean;
import com.skcc.oversea.reference.business.facade.IReferenceManagementSB;

public class JNDINamesRule
{
    // 레거시 EJB 제거 - Spring Boot 환경에서는 사용하지 않음
    // public static final Class CASH_CARD_HOME = CashCardSBHome.class;
    public static final Class COMMON_MANAGEMENT_SERVICE = CommonManagementSBean.class;
    public static final Class REFERENCE_MANAGEMENT_SERVICE = IReferenceManagementSB.class;
}
