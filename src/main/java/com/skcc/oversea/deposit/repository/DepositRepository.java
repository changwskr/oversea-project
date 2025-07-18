package com.skcc.oversea.deposit.repository;

import com.skcc.oversea.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository("mainDepositRepository")
public interface DepositRepository extends JpaRepository<Deposit, Long> {

        Optional<Deposit> findByAccountNumber(String accountNumber);

        // 디버깅용: 계좌번호로 조회 (삭제 여부 무관)
        @Query("SELECT d FROM MainDeposit d WHERE d.accountNumber = :accountNumber")
        List<Deposit> findByAccountNumberDebug(@Param("accountNumber") String accountNumber);

        // 디버깅용: 모든 예금 조회 (삭제 여부 무관)
        @Query("SELECT d FROM MainDeposit d")
        List<Deposit> findAllDebug();

        List<Deposit> findByCifNo(String cifNo);

        List<Deposit> findByBankCodeAndBranchCode(String bankCode, String branchCode);

        List<Deposit> findByStatus(String status);

        boolean existsByAccountNumber(String accountNumber);

        // 추가 검색 메서드들
        List<Deposit> findByCifNameContainingIgnoreCase(String cifName);

        List<Deposit> findByDepositTypeContainingIgnoreCase(String depositType);

        List<Deposit> findByAccountNumberContainingIgnoreCase(String accountNumber);

        List<Deposit> findByCifNoContainingIgnoreCase(String cifNo);

        // 복합 검색
        @Query("SELECT d FROM MainDeposit d WHERE " +
                        "(:accountNumber IS NULL OR :accountNumber = '' OR d.accountNumber LIKE %:accountNumber%) AND "
                        +
                        "(:cifNo IS NULL OR :cifNo = '' OR d.cifNo LIKE %:cifNo%) AND " +
                        "(:cifName IS NULL OR :cifName = '' OR d.cifName LIKE %:cifName%) AND " +
                        "(:depositType IS NULL OR :depositType = '' OR d.depositType LIKE %:depositType%) AND " +
                        "(:status IS NULL OR :status = '' OR d.status = :status) AND " +
                        "d.isDeleted = false")
        List<Deposit> findBySearchCriteria(
                        @Param("accountNumber") String accountNumber,
                        @Param("cifNo") String cifNo,
                        @Param("cifName") String cifName,
                        @Param("depositType") String depositType,
                        @Param("status") String status);

        // 통계 쿼리
        @Query("SELECT COUNT(d) FROM MainDeposit d WHERE d.isDeleted = false")
        Long countActiveDeposits();

        @Query("SELECT COUNT(d) FROM MainDeposit d WHERE d.status = 'A' AND d.isDeleted = false")
        Long countActiveStatusDeposits();

        @Query("SELECT SUM(d.amount) FROM MainDeposit d WHERE d.isDeleted = false")
        BigDecimal getTotalAmount();

        @Query("SELECT SUM(d.balance) FROM MainDeposit d WHERE d.isDeleted = false")
        BigDecimal getTotalBalance();

        // 최근 등록된 예금
        @Query("SELECT d FROM MainDeposit d WHERE d.isDeleted = false ORDER BY d.createdDate DESC")
        List<Deposit> findRecentDeposits();
}