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
import com.skcc.oversea.user.domain.User;
import com.skcc.oversea.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(TestDataController.class);

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
    private UserService userService;

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
        logger.info("==================[TestDataController.getTestDataSummary START]");
        try {
            Map<String, Object> summary = new HashMap<>();
            
            summary.put("cashCards", cashCardRepository.count());
            summary.put("hotCards", hotCardRepository.count());
            summary.put("deposits", depositRepository.count());
            summary.put("commons", commonRepository.count());
            summary.put("tellers", tellerRepository.count());
            summary.put("users", userService.findAllUsers().size());
            summary.put("transactionLogs", transactionLogRepository.count());
            
            logger.info("==================[TestDataController.getTestDataSummary END]");
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getTestDataSummary ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/cash-cards")
    @Operation(
        summary = "Get All Cash Cards",
        description = "Returns all cash card test data"
    )
    public ResponseEntity<List<CashCard>> getAllCashCards() {
        logger.info("==================[TestDataController.getAllCashCards START]");
        try {
            List<CashCard> result = cashCardRepository.findAll();
            logger.info("==================[TestDataController.getAllCashCards END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllCashCards ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/hot-cards")
    @Operation(
        summary = "Get All Hot Cards",
        description = "Returns all hot card test data"
    )
    public ResponseEntity<List<HotCard>> getAllHotCards() {
        logger.info("==================[TestDataController.getAllHotCards START]");
        try {
            List<HotCard> result = hotCardRepository.findAll();
            logger.info("==================[TestDataController.getAllHotCards END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllHotCards ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/deposits")
    @Operation(
        summary = "Get All Deposits",
        description = "Returns all deposit test data"
    )
    public ResponseEntity<List<Deposit>> getAllDeposits() {
        logger.info("==================[TestDataController.getAllDeposits START]");
        try {
            List<Deposit> result = depositRepository.findAll();
            logger.info("==================[TestDataController.getAllDeposits END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllDeposits ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/commons")
    @Operation(
        summary = "Get All Commons",
        description = "Returns all common test data"
    )
    public ResponseEntity<List<Common>> getAllCommons() {
        logger.info("==================[TestDataController.getAllCommons START]");
        try {
            List<Common> result = commonRepository.findAll();
            logger.info("==================[TestDataController.getAllCommons END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllCommons ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/tellers")
    @Operation(
        summary = "Get All Tellers",
        description = "Returns all teller test data"
    )
    public ResponseEntity<List<Teller>> getAllTellers() {
        logger.info("==================[TestDataController.getAllTellers START]");
        try {
            List<Teller> result = tellerRepository.findAll();
            logger.info("==================[TestDataController.getAllTellers END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllTellers ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/users")
    @Operation(
        summary = "Get All Users",
        description = "Returns all user test data"
    )
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("==================[TestDataController.getAllUsers START]");
        try {
            List<User> result = userService.findAllUsers();
            logger.info("==================[TestDataController.getAllUsers END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllUsers ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/transaction-logs")
    @Operation(
        summary = "Get All Transaction Logs",
        description = "Returns all transaction log test data"
    )
    public ResponseEntity<List<TransactionLog>> getAllTransactionLogs() {
        logger.info("==================[TestDataController.getAllTransactionLogs START]");
        try {
            List<TransactionLog> result = transactionLogRepository.findAll();
            logger.info("==================[TestDataController.getAllTransactionLogs END]");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            logger.error("==================[TestDataController.getAllTransactionLogs ERROR] - {}", e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("/create-additional")
    @Operation(
        summary = "Create Additional Test Data",
        description = "Creates additional test data for testing purposes"
    )
    public ResponseEntity<Map<String, Object>> createAdditionalTestData() {
        logger.info("==================[TestDataController.createAdditionalTestData START]");
        try {
            dataInitializer.createTestDataForTesting();
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Additional test data created successfully");
            response.put("timestamp", java.time.LocalDateTime.now());
            
            logger.info("==================[TestDataController.createAdditionalTestData END]");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("==================[TestDataController.createAdditionalTestData ERROR] - {}", e.getMessage(), e);
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
        logger.info("==================[TestDataController.getCashCardByCardNumber START] - 카드번호: {}", cardNumber);
        try {
            // CashCardRepository의 findByCardNumber는 bankCode와 cardNumber 두 개의 파라미터가 필요함
            ResponseEntity<CashCard> result = cashCardRepository.findByCardNumber("001", cardNumber)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            logger.info("==================[TestDataController.getCashCardByCardNumber END] - 카드번호: {}", cardNumber);
            return result;
        } catch (Exception e) {
            logger.error("==================[TestDataController.getCashCardByCardNumber ERROR] - 카드번호: {}, 에러: {}", cardNumber, e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/deposits/{accountNumber}")
    @Operation(
        summary = "Get Deposit by Account Number",
        description = "Returns a specific deposit by its account number"
    )
    public ResponseEntity<Deposit> getDepositByAccountNumber(@PathVariable String accountNumber) {
        logger.info("==================[TestDataController.getDepositByAccountNumber START] - 계좌번호: {}", accountNumber);
        try {
            ResponseEntity<Deposit> result = depositRepository.findByAccountNumber(accountNumber)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            logger.info("==================[TestDataController.getDepositByAccountNumber END] - 계좌번호: {}", accountNumber);
            return result;
        } catch (Exception e) {
            logger.error("==================[TestDataController.getDepositByAccountNumber ERROR] - 계좌번호: {}, 에러: {}", accountNumber, e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/users/{userId}")
    @Operation(
        summary = "Get User by User ID",
        description = "Returns a specific user by their user ID"
    )
    public ResponseEntity<User> getUserByUserId(@PathVariable String userId) {
        logger.info("==================[TestDataController.getUserByUserId START] - 사용자ID: {}", userId);
        try {
            ResponseEntity<User> result = userService.findAllUsers().stream()
                    .filter(user -> userId.equals(user.getUserId()))
                    .findFirst()
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            logger.info("==================[TestDataController.getUserByUserId END] - 사용자ID: {}", userId);
            return result;
        } catch (Exception e) {
            logger.error("==================[TestDataController.getUserByUserId ERROR] - 사용자ID: {}, 에러: {}", userId, e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/commons/{commonId}")
    @Operation(
        summary = "Get Common by Common ID",
        description = "Returns a specific common by its common ID"
    )
    public ResponseEntity<Common> getCommonByCommonId(@PathVariable String commonId) {
        logger.info("==================[TestDataController.getCommonByCommonId START] - 공통ID: {}", commonId);
        try {
            // CommonRepository에 findByCommonCode 메서드가 없으므로 findAll에서 필터링
            ResponseEntity<Common> result = commonRepository.findAll().stream()
                    .filter(common -> commonId.equals(common.getCommonCode()))
                    .findFirst()
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
            logger.info("==================[TestDataController.getCommonByCommonId END] - 공통ID: {}", commonId);
            return result;
        } catch (Exception e) {
            logger.error("==================[TestDataController.getCommonByCommonId ERROR] - 공통ID: {}, 에러: {}", commonId, e.getMessage(), e);
            throw e;
        }
    }
} 