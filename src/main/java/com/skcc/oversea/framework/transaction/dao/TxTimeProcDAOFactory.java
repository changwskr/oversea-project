package com.skcc.oversea.framework.transaction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * =============================================================================
 * 프로그램 설명:
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
 * @author : 장우승(WooSungJang)
 * @company: IMS SYSTEM
 * @email : changwskr@yahoo.co.kr
 * @version 1.0
 *          =============================================================================
 */
@Component("frameworkTxTimeProcDAOFactory")
public class TxTimeProcDAOFactory {
    @Autowired
    private TxTimeProcDAO txTimeProcDAO;

    public ITxTimeProcDAO getTxTimeProcDAO() {
        return txTimeProcDAO;
    }

    public void setTxTimeProcDAO(TxTimeProcDAO txTimeProcDAO) {
        this.txTimeProcDAO = txTimeProcDAO;
    }
}
