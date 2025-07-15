package com.skcc.oversea.config;

import com.skcc.oversea.cashCard.business.cashCard.entity.CashCard;
import com.skcc.oversea.cashCard.business.cashCard.entity.HotCard;
import com.skcc.oversea.cashCard.repository.CashCardRepository;
import com.skcc.oversea.cashCard.repository.HotCardRepository;
import com.skcc.oversea.common.entity.Common;
import com.skcc.oversea.common.repository.CommonRepository;
import com.skcc.oversea.deposit.entity.Deposit;
import com.skcc.oversea.deposit.repository.DepositRepository;
import com.skcc.oversea.eplatonframework.business.entity.TransactionLog;
import com.skcc.oversea.eplatonframework.business.repository.TransactionLogRepository;
import com.skcc.oversea.teller.entity.Teller;
import com.skcc.oversea.teller.repository.TellerRepository;
import com.skcc.oversea.user.entity.User;
import com.skcc.oversea.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Data Initializer
 * 
 * Initializes test data for the SKCC Oversea application.
 * This component runs after the application starts and ensures
 * that all necessary test data is available.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");

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

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting data initialization...");

        try {
            // Check if data already exists
            if (isDataAlreadyInitialized()) {
                logger.info("Data already initialized, skipping...");
                return;
            }

            // Initialize test data
            initializeTestData();

            logger.info("Data initialization completed successfully!");

        } catch (Exception e) {
            logger.error("Error during data initialization", e);
            throw e;
        }
    }

    private boolean isDataAlreadyInitialized() {
        return cashCardRepository.count() > 0 ||
                depositRepository.count() > 0 ||
                commonRepository.count() > 0;
    }

    private void initializeTestData() {
        logger.info("Creating additional test data...");

        String currentDate = LocalDateTime.now().format(DATE_FORMATTER);
        String currentTime = LocalDateTime.now().format(TIME_FORMATTER);

        // Create additional test data if needed
        createAdditionalTestData(currentDate, currentTime);

        logger.info("Additional test data created successfully!");
    }

    private void createAdditionalTestData(String currentDate, String currentTime) {
        // Create additional cash cards for testing
        createAdditionalCashCards(currentDate, currentTime);

        // Create additional hot cards for testing
        createAdditionalHotCards(currentDate, currentTime);

        // Create additional deposits for testing
        createAdditionalDeposits(currentDate, currentTime);

        // Create additional transaction logs for testing
        createAdditionalTransactionLogs(currentDate, currentTime);
    }

    private void createAdditionalCashCards(String currentDate, String currentTime) {
        // Additional cash card for testing
        CashCard additionalCard = new CashCard(1, "9999999999999999", "001", "999999999999");
        additionalCard.setCardNo("9999999999999999");
        additionalCard.setBankType("OVS");
        additionalCard.setBranchCode("001");
        additionalCard.setType("CASH");
        additionalCard.setCifNo("CIF999");
        additionalCard.setCifName("테스트고객");
        additionalCard.setDailyLimitCcy("KRW");
        additionalCard.setDailyLimitAmount(new BigDecimal("500000.00"));
        additionalCard.setDailyAccumAmount(new BigDecimal("0.00"));
        additionalCard.setStatus("A");
        additionalCard.setEffectiveDate(currentDate);
        additionalCard.setExpiryDate("20261231");
        additionalCard.setRegisterDate(currentDate);
        additionalCard.setRegisterTime(currentTime);
        additionalCard.setRegisterBy("SYSTEM");
        additionalCard.setIsDeleted(false);
        additionalCard.setCreatedDate(LocalDateTime.now());

        cashCardRepository.save(additionalCard);
        logger.info("Created additional cash card: {}", additionalCard.getCardNumber());
    }

    private void createAdditionalHotCards(String currentDate, String currentTime) {
        // Additional hot card for testing
        HotCard additionalHotCard = new HotCard(1, "9999999999999998");
        additionalHotCard.setPrimaryAccountNo("999999999998");
        additionalHotCard.setCifNo("CIF999");
        additionalHotCard.setCifName("테스트고객");
        additionalHotCard.setStatus("A");
        additionalHotCard.setRegisterDate(currentDate);
        additionalHotCard.setRegisterTime(currentTime);
        additionalHotCard.setRegisterBy("SYSTEM");
        additionalHotCard.setIsDeleted(false);
        additionalHotCard.setCreatedDate(LocalDateTime.now());

        hotCardRepository.save(additionalHotCard);
        logger.info("Created additional hot card: {}", additionalHotCard.getCardNumber());
    }

    private void createAdditionalDeposits(String currentDate, String currentTime) {
        // Additional deposit for testing
        Deposit additionalDeposit = new Deposit();
        additionalDeposit.setAccountNumber("999999999999");
        additionalDeposit.setBankCode("001");
        additionalDeposit.setBranchCode("001");
        additionalDeposit.setCifNo("CIF999");
        additionalDeposit.setCifName("테스트고객");
        additionalDeposit.setCurrency("KRW");
        additionalDeposit.setBalance(new BigDecimal("10000000.00"));
        additionalDeposit.setInterestRate(new BigDecimal("3.50"));
        additionalDeposit.setStatus("A");
        additionalDeposit.setOpenDate(currentDate);
        additionalDeposit.setMaturityDate(LocalDateTime.of(2024, 12, 31, 0, 0));
        additionalDeposit.setRegisterDate(currentDate);
        additionalDeposit.setRegisterTime(currentTime);
        additionalDeposit.setRegisterBy("SYSTEM");
        additionalDeposit.setIsDeleted(false);
        additionalDeposit.setCreatedDate(LocalDateTime.now());

        depositRepository.save(additionalDeposit);
        logger.info("Created additional deposit: {}", additionalDeposit.getAccountNumber());
    }

    private void createAdditionalTransactionLogs(String currentDate, String currentTime) {
        // Additional transaction log for testing
        TransactionLog additionalLog = new TransactionLog();
        additionalLog.setTransactionId("TXN_TEST_" + System.currentTimeMillis());
        additionalLog.setTransactionNo("TXN_TEST_001");
        additionalLog.setHostName("TEST_HOST");
        additionalLog.setSystemName("TEST_SYSTEM");
        additionalLog.setMethodName("testMethod");
        additionalLog.setBankCode("001");
        additionalLog.setBranchCode("001");
        additionalLog.setUserId("TEST_USER");
        additionalLog.setChannelType("TEST");
        additionalLog.setBusinessDate(currentDate);
        additionalLog.setRegisterDate(currentDate);
        additionalLog.setInTime(currentTime);
        additionalLog.setOutTime(currentTime);
        additionalLog.setResponseTime(1000L);
        additionalLog.setErrorCode("I0000");
        additionalLog.setEventNo("TEST_EVT");
        additionalLog.setIpAddress("127.0.0.1");
        additionalLog.setUpdateDate(LocalDateTime.now());
        additionalLog.setCreatedDate(LocalDateTime.now());

        transactionLogRepository.save(additionalLog);
        logger.info("Created additional transaction log: {}", additionalLog.getTransactionId());
    }

    /**
     * Utility method to create test data programmatically
     */
    public void createTestDataForTesting() {
        logger.info("Creating test data for testing purposes...");

        String currentDate = LocalDateTime.now().format(DATE_FORMATTER);
        String currentTime = LocalDateTime.now().format(TIME_FORMATTER);

        createAdditionalTestData(currentDate, currentTime);

        logger.info("Test data created for testing purposes!");
    }
}