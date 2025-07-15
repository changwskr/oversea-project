package com.skcc.oversea.deposit.repository;

import com.skcc.oversea.deposit.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    
    Optional<Deposit> findByAccountNumber(String accountNumber);
    
    List<Deposit> findByCifNo(String cifNo);
    
    List<Deposit> findByBankCodeAndBranchCode(String bankCode, String branchCode);
    
    List<Deposit> findByStatus(String status);
    
    boolean existsByAccountNumber(String accountNumber);
} 