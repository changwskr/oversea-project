package com.skcc.oversea.framework.transaction.dao;

import com.skcc.oversea.framework.business.dao.IDAO;
import com.skcc.oversea.framework.business.dao.AbstractDAOFactory;
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
 *  2004년 03월 16일 1차버전 release
 *  Spring Boot 마이그레이션 - 싱글톤 패턴을 Spring 컨테이너 관리로 변경
 *
 *
 * =============================================================================
 *                                                        @author : 장우승(WooSungJang)
 *                                                        @company: IMS SYSTEM
 *                                                        @email  : changwskr@yahoo.co.kr
 *                                                        @version 1.0
 *  =============================================================================
 */

@Component
public class TransactionControlDAOFactory extends AbstractDAOFactory
{
    @Override
    @SuppressWarnings("unchecked")
    public <T extends IDAO<?, ?>> T getDAO(Class<T> daoClass) {
        if (daoClass.isAssignableFrom(TransactionControlDAO.class)) {
            return (T) getBean(TransactionControlDAO.class);
        }
        throw new IllegalArgumentException("Unsupported DAO class: " + daoClass.getName());
    }

    /**
     * TransactionControlDAO를 반환하는 편의 메서드
     * @return TransactionControlDAO 인스턴스
     */
    public TransactionControlDAO getTransactionControlDAO() {
        return getBean(TransactionControlDAO.class);
    }
} 