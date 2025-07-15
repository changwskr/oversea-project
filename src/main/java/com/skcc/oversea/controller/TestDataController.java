package com.skcc.oversea.controller;

import com.skcc.oversea.cashCard.business.cashCard.entity.CashCard;
import com.skcc.oversea.cashCard.business.cashCard.entity.HotCard;
import com.skcc.oversea.cashCard.repository.CashCardRepository;
import com.skcc.oversea.cashCard.repository.HotCardRepository;
import com.skcc.oversea.common.entity.Common;
import com.skcc.oversea.common.repository.CommonRepository;
import com.skcc.oversea.config.DataInitializer;
import com.skcc.oversea.deposit.entity.Deposit;
import com.skcc.oversea.deposit.repository.DepositRepository;
import com.skcc.oversea.eplatonframework.business.entity.TransactionLog;
import com.skcc.oversea.eplatonframework.business.repository.TransactionLogRepository;
import com.skcc.oversea.teller.entity.Teller;
import com.skcc.oversea.teller.repository.TellerRepository;
import com.skcc.oversea.user.entity.User;
import com.skcc.oversea.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test Data Controller
 * 
 * Provides endpoints for viewing and managing test data.
 */
@RestController
@RequestMapping("/api/test-data")
@Tag(name = "Test Data", description = "Test data management endpoints")
public class TestDataController {

    @Autowired
    private CashCardRepository cashCardRepository;

    @Autowired
    private HotCardRepository hotCardRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private CommonRepository commonRepository;

    @Autowired
    private TellerRepository tellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    @Autowired
    private DataInitializer dataInitializer;

    @GetMapping("/summary")
    @Operation(
        summary = "Get Test Data Summary",
        description = "Returns a summary of all test data in the system"
    )
    public ResponseEntity<Map<String, Object>> getTestDataSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        summary.put("cashCards", cashCardRepository.count());
        summary.put("hotCards", hotCardRepository.count());
        summary.put("deposits", depositRepository.count());
        summary.put("commons", commonRepository.count());
        summary.put("tellers", tellerRepository.count());
        summary.put("users", userRepository.count());
        summary.put("transactionLogs", transactionLogRepository.count());
        
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/cash-cards")
    @Operation(
        summary = "Get All Cash Cards",
        description = "Returns all cash card test data"
    )
    public ResponseEntity<List<CashCard>> getAllCashCards() {
        return ResponseEntity.ok(cashCardRepository.findAll());
    }

    @GetMapping("/hot-cards")
    @Operation(
        summary = "Get All Hot Cards",
        description = "Returns all hot card test data"
    )
    public ResponseEntity<List<HotCard>> getAllHotCards() {
        return ResponseEntity.ok(hotCardRepository.findAll());
    }

    @GetMapping("/deposits")
    @Operation(
        summary = "Get All Deposits",
        description = "Returns all deposit test data"
    )
    public ResponseEntity<List<Deposit>> getAllDeposits() {
        return ResponseEntity.ok(depositRepository.findAll());
    }

    @GetMapping("/commons")
    @Operation(
        summary = "Get All Commons",
        description = "Returns all common test data"
    )
    public ResponseEntity<List<Common>> getAllCommons() {
        return ResponseEntity.ok(commonRepository.findAll());
    }

    @GetMapping("/tellers")
    @Operation(
        summary = "Get All Tellers",
        description = "Returns all teller test data"
    )
    public ResponseEntity<List<Teller>> getAllTellers() {
        return ResponseEntity.ok(tellerRepository.findAll());
    }

    @GetMapping("/users")
    @Operation(
        summary = "Get All Users",
        description = "Returns all user test data"
    )
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/transaction-logs")
    @Operation(
        summary = "Get All Transaction Logs",
        description = "Returns all transaction log test data"
    )
    public ResponseEntity<List<TransactionLog>> getAllTransactionLogs() {
        return ResponseEntity.ok(transactionLogRepository.findAll());
    }

    @PostMapping("/create-additional")
    @Operation(
        summary = "Create Additional Test Data",
        description = "Creates additional test data for testing purposes"
    )
    public ResponseEntity<Map<String, Object>> createAdditionalTestData() {
        try {
            dataInitializer.createTestDataForTesting();
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Additional test data created successfully");
            response.put("timestamp", java.time.LocalDateTime.now());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to create additional test data");
            error.put("message", e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @GetMapping("/cash-cards/{cardNumber}")
    @Operation(
        summary = "Get Cash Card by Card Number",
        description = "Returns a specific cash card by its card number"
    )
    public ResponseEntity<CashCard> getCashCardByCardNumber(@PathVariable String cardNumber) {
        // CashCardRepository의 findByCardNumber는 bankCode와 cardNumber 두 개의 파라미터가 필요함
        return cashCardRepository.findByCardNumber("001", cardNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/deposits/{accountNumber}")
    @Operation(
        summary = "Get Deposit by Account Number",
        description = "Returns a specific deposit by its account number"
    )
    public ResponseEntity<Deposit> getDepositByAccountNumber(@PathVariable String accountNumber) {
        return depositRepository.findByAccountNumber(accountNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{userId}")
    @Operation(
        summary = "Get User by User ID",
        description = "Returns a specific user by their user ID"
    )
    public ResponseEntity<User> getUserByUserId(@PathVariable String userId) {
        return userRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/commons/{commonId}")
    @Operation(
        summary = "Get Common by Common ID",
        description = "Returns a specific common by its common ID"
    )
    public ResponseEntity<Common> getCommonByCommonId(@PathVariable String commonId) {
        // CommonRepository에 findByCommonId 메서드가 없으므로 findAll에서 필터링
        return commonRepository.findAll().stream()
                .filter(common -> commonId.equals(common.getCommonId()))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 