package com.skcc.oversea.eplatonframework.business.controller;

import com.skcc.oversea.eplatonframework.business.dto.ServiceResponse;
import com.skcc.oversea.deposit.entity.Deposit;
import com.skcc.oversea.deposit.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Deposit REST API Controller
 */
@RestController("eplatonDepositController")
@RequestMapping("/api/deposit")
public class DepositController extends BaseController {

    @Autowired
    private DepositRepository depositRepository;

    /**
     * Get all deposits
     */
    @GetMapping
    public ResponseEntity<ServiceResponse<List<Deposit>>> getAllDeposits() {
        try {
            List<Deposit> deposits = depositRepository.findAll();
            return successList(deposits);
        } catch (Exception e) {
            logger.error("Error getting all deposits", e);
            return error("Failed to retrieve deposits");
        }
    }

    /**
     * Get deposit by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<Deposit>> getDepositById(@PathVariable Long id) {
        try {
            Deposit deposit = depositRepository.findById(id).orElse(null);
            if (deposit != null) {
                return success(deposit);
            } else {
                return error("Deposit not found");
            }
        } catch (Exception e) {
            logger.error("Error getting deposit by ID: {}", id, e);
            return error("Failed to retrieve deposit");
        }
    }

    /**
     * Get deposit by account number
     */
    @GetMapping("/account/{accountNo}")
    public ResponseEntity<ServiceResponse<Deposit>> getDepositByAccountNo(@PathVariable String accountNo) {
        try {
            Deposit deposit = depositRepository.findByAccountNumber(accountNo).orElse(null);
            if (deposit != null) {
                return success(deposit);
            } else {
                return error("Deposit not found");
            }
        } catch (Exception e) {
            logger.error("Error getting deposit by account number: {}", accountNo, e);
            return error("Failed to retrieve deposit");
        }
    }

    /**
     * Get deposits by customer ID
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ServiceResponse<List<Deposit>>> getDepositsByCustomerId(@PathVariable String customerId) {
        try {
            List<Deposit> deposits = depositRepository.findByCifNo(customerId);
            return successList(deposits);
        } catch (Exception e) {
            logger.error("Error getting deposits by customer ID: {}", customerId, e);
            return error("Failed to retrieve deposits");
        }
    }

    /**
     * Create new deposit
     */
    @PostMapping
    public ResponseEntity<ServiceResponse<Deposit>> createDeposit(@RequestBody Deposit deposit) {
        try {
            Deposit createdDeposit = depositRepository.save(deposit);
            return success(createdDeposit, "Deposit created successfully");
        } catch (Exception e) {
            logger.error("Error creating deposit", e);
            return error("Failed to create deposit");
        }
    }

    /**
     * Update deposit
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<Deposit>> updateDeposit(@PathVariable Long id, @RequestBody Deposit deposit) {
        try {
            if (depositRepository.existsById(id)) {
                deposit.setDepositId(id);
                Deposit updatedDeposit = depositRepository.save(deposit);
                return success(updatedDeposit, "Deposit updated successfully");
            } else {
                return error("Deposit not found");
            }
        } catch (Exception e) {
            logger.error("Error updating deposit: {}", id, e);
            return error("Failed to update deposit");
        }
    }

    /**
     * Delete deposit
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<Void>> deleteDeposit(@PathVariable Long id) {
        try {
            if (depositRepository.existsById(id)) {
                depositRepository.deleteById(id);
                return success(null, "Deposit deleted successfully");
            } else {
                return error("Deposit not found");
            }
        } catch (Exception e) {
            logger.error("Error deleting deposit: {}", id, e);
            return error("Failed to delete deposit");
        }
    }

    /**
     * Get deposits by status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<ServiceResponse<List<Deposit>>> getDepositsByStatus(@PathVariable String status) {
        try {
            List<Deposit> deposits = depositRepository.findByStatus(status);
            return successList(deposits);
        } catch (Exception e) {
            logger.error("Error getting deposits by status: {}", status, e);
            return error("Failed to retrieve deposits");
        }
    }

    /**
     * Get deposits by branch code
     */
    @GetMapping("/branch/{branchCode}")
    public ResponseEntity<ServiceResponse<List<Deposit>>> getDepositsByBranchCode(@PathVariable String branchCode) {
        try {
            List<Deposit> deposits = depositRepository.findByBankCodeAndBranchCode("03", branchCode);
            return successList(deposits);
        } catch (Exception e) {
            logger.error("Error getting deposits by branch code: {}", branchCode, e);
            return error("Failed to retrieve deposits");
        }
    }

    /**
     * Update account balance
     */
    @PutMapping("/{id}/balance")
    public ResponseEntity<ServiceResponse<Deposit>> updateAccountBalance(@PathVariable Long id,
            @RequestParam BigDecimal newBalance) {
        try {
            Deposit deposit = depositRepository.findById(id).orElse(null);
            if (deposit != null) {
                deposit.setBalance(newBalance);
                Deposit updatedDeposit = depositRepository.save(deposit);
                return success(updatedDeposit, "Account balance updated successfully");
            } else {
                return error("Deposit not found");
            }
        } catch (Exception e) {
            logger.error("Error updating account balance: {}", id, e);
            return error("Failed to update account balance");
        }
    }

    /**
     * Get total balance by customer ID
     */
    @GetMapping("/customer/{customerId}/total-balance")
    public ResponseEntity<ServiceResponse<BigDecimal>> getTotalBalanceByCustomerId(@PathVariable String customerId) {
        try {
            List<Deposit> deposits = depositRepository.findByCifNo(customerId);
            BigDecimal totalBalance = deposits.stream()
                    .map(Deposit::getBalance)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return success(totalBalance);
        } catch (Exception e) {
            logger.error("Error getting total balance by customer ID: {}", customerId, e);
            return error("Failed to retrieve total balance");
        }
    }

    /**
     * Update account status
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<ServiceResponse<Deposit>> updateAccountStatus(@PathVariable Long id,
            @RequestParam String status) {
        try {
            Deposit deposit = depositRepository.findById(id).orElse(null);
            if (deposit != null) {
                deposit.setStatus(status);
                Deposit updatedDeposit = depositRepository.save(deposit);
                return success(updatedDeposit, "Account status updated successfully");
            } else {
                return error("Deposit not found");
            }
        } catch (Exception e) {
            logger.error("Error updating account status: {}", id, e);
            return error("Failed to update account status");
        }
    }
}