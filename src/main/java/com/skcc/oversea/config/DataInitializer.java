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
import java.util.Arrays;
import java.util.List;

/**
 * Comprehensive Data Initializer
 * 
 * Initializes comprehensive test data for the SKCC Oversea application.
 * This component runs after the application starts and ensures
 * that all necessary test data is available for various testing scenarios.
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
        logger.info("Starting comprehensive data initialization...");

        try {
            // Check if data already exists
            if (isDataAlreadyInitialized()) {
                logger.info("Data already initialized, skipping...");
                return;
            }

            // Initialize comprehensive test data
            initializeComprehensiveTestData();

            logger.info("Comprehensive data initialization completed successfully!");

        } catch (Exception e) {
            logger.error("Error during data initialization", e);
            throw e;
        }
    }

    private boolean isDataAlreadyInitialized() {
        return cashCardRepository.count() > 0 ||
                depositRepository.count() > 0 ||
                commonRepository.count() > 0 ||
                userRepository.count() > 0 ||
                tellerRepository.count() > 0;
    }

    private void initializeComprehensiveTestData() {
        logger.info("Creating comprehensive test data...");

        String currentDate = LocalDateTime.now().format(DATE_FORMATTER);
        String currentTime = LocalDateTime.now().format(TIME_FORMATTER);

        // Initialize all test data
        createCommonCodes(currentDate, currentTime);
        createUsers(currentDate, currentTime);
        createTellers(currentDate, currentTime);
        createDeposits(currentDate, currentTime);
        createCashCards(currentDate, currentTime);
        createHotCards(currentDate, currentTime);
        createTransactionLogs(currentDate, currentTime);

        logger.info("Comprehensive test data created successfully!");
    }

    private void createCommonCodes(String currentDate, String currentTime) {
        logger.info("Creating common codes...");

        List<Common> commonCodes = Arrays.asList(
            // Bank Codes
            createCommon("BANK_001", "SKCC Main Bank", "001", "A", "SKCC Main Bank", true, currentDate, currentTime),
            createCommon("BANK_002", "SKCC Overseas", "002", "A", "SKCC Overseas", true, currentDate, currentTime),
            createCommon("BANK_003", "SKCC Investment", "003", "A", "SKCC Investment", true, currentDate, currentTime),

            // Branch Codes
            createCommon("BRANCH_001", "Seoul Main Branch", "001", "A", "Seoul Main Branch", true, currentDate, currentTime),
            createCommon("BRANCH_002", "Busan Branch", "002", "A", "Busan Branch", true, currentDate, currentTime),
            createCommon("BRANCH_003", "Incheon Branch", "003", "A", "Incheon Branch", true, currentDate, currentTime),
            createCommon("BRANCH_004", "Daegu Branch", "004", "A", "Daegu Branch", true, currentDate, currentTime),
            createCommon("BRANCH_005", "Daejeon Branch", "005", "A", "Daejeon Branch", true, currentDate, currentTime),

            // Currency Codes
            createCommon("CURRENCY_KRW", "Korean Won", "KRW", "A", "Korean Won", true, currentDate, currentTime),
            createCommon("CURRENCY_USD", "US Dollar", "USD", "A", "US Dollar", true, currentDate, currentTime),
            createCommon("CURRENCY_EUR", "Euro", "EUR", "A", "Euro", true, currentDate, currentTime),
            createCommon("CURRENCY_JPY", "Japanese Yen", "JPY", "A", "Japanese Yen", true, currentDate, currentTime),
            createCommon("CURRENCY_CNY", "Chinese Yuan", "CNY", "A", "Chinese Yuan", true, currentDate, currentTime),

            // Card Status Codes
            createCommon("CARD_STATUS_A", "Active", "A", "A", "Active Card Status", true, currentDate, currentTime),
            createCommon("CARD_STATUS_I", "Inactive", "I", "A", "Inactive Card Status", true, currentDate, currentTime),
            createCommon("CARD_STATUS_H", "Hot Card", "H", "A", "Hot Card Status", true, currentDate, currentTime),
            createCommon("CARD_STATUS_S", "Suspended", "S", "A", "Suspended Card Status", true, currentDate, currentTime),
            createCommon("CARD_STATUS_P", "Pending", "P", "A", "Pending Card Status", true, currentDate, currentTime),

            // Account Status Codes
            createCommon("ACCOUNT_STATUS_A", "Active", "A", "A", "Active Account Status", true, currentDate, currentTime),
            createCommon("ACCOUNT_STATUS_C", "Closed", "C", "A", "Closed Account Status", true, currentDate, currentTime),
            createCommon("ACCOUNT_STATUS_F", "Frozen", "F", "A", "Frozen Account Status", true, currentDate, currentTime),
            createCommon("ACCOUNT_STATUS_D", "Dormant", "D", "A", "Dormant Account Status", true, currentDate, currentTime),

            // User Status Codes
            createCommon("USER_STATUS_A", "Active", "A", "A", "Active User Status", true, currentDate, currentTime),
            createCommon("USER_STATUS_I", "Inactive", "I", "A", "Inactive User Status", true, currentDate, currentTime),
            createCommon("USER_STATUS_L", "Locked", "L", "A", "Locked User Status", true, currentDate, currentTime),

            // Teller Status Codes
            createCommon("TELLER_STATUS_A", "Active", "A", "A", "Active Teller Status", true, currentDate, currentTime),
            createCommon("TELLER_STATUS_I", "Inactive", "I", "A", "Inactive Teller Status", true, currentDate, currentTime),
            createCommon("TELLER_STATUS_V", "Vacation", "V", "A", "Vacation Teller Status", true, currentDate, currentTime),

            // Card Types
            createCommon("CARD_TYPE_DEBIT", "Debit Card", "DEBIT", "A", "Debit Card Type", true, currentDate, currentTime),
            createCommon("CARD_TYPE_CREDIT", "Credit Card", "CREDIT", "A", "Credit Card Type", true, currentDate, currentTime),
            createCommon("CARD_TYPE_PREPAID", "Prepaid Card", "PREPAID", "A", "Prepaid Card Type", true, currentDate, currentTime),

            // Account Types
            createCommon("ACCOUNT_TYPE_SAVINGS", "Savings Account", "SAVINGS", "A", "Savings Account Type", true, currentDate, currentTime),
            createCommon("ACCOUNT_TYPE_CHECKING", "Checking Account", "CHECKING", "A", "Checking Account Type", true, currentDate, currentTime),
            createCommon("ACCOUNT_TYPE_FIXED", "Fixed Deposit", "FIXED", "A", "Fixed Deposit Type", true, currentDate, currentTime),
            createCommon("ACCOUNT_TYPE_CURRENT", "Current Account", "CURRENT", "A", "Current Account Type", true, currentDate, currentTime)
        );

        commonRepository.saveAll(commonCodes);
        logger.info("Created {} common codes", commonCodes.size());
    }

    private Common createCommon(String commonCode, String commonName, String commonType, String commonValue, 
                               String description, Boolean isActive, String currentDate, String currentTime) {
        Common common = new Common(commonCode, commonName, commonType, commonValue);
        common.setDescription(description);
        common.setIsActive(isActive);
        common.setEffectiveDate(currentDate);
        common.setRegisterDate(currentDate);
        common.setRegisterTime(currentTime);
        common.setRegisterBy("SYSTEM");
        return common;
    }

    private void createUsers(String currentDate, String currentTime) {
        logger.info("Creating users...");

        List<User> users = Arrays.asList(
            // Regular Users
            createUser("USER001", "김철수", "kim.cs@skcc.com", "010-1234-5678", "A", currentDate, currentTime),
            createUser("USER002", "이영희", "lee.yh@skcc.com", "010-2345-6789", "A", currentDate, currentTime),
            createUser("USER003", "박민수", "park.ms@skcc.com", "010-3456-7890", "A", currentDate, currentTime),
            createUser("USER004", "최영수", "choi.ys@skcc.com", "010-4567-8901", "A", currentDate, currentTime),
            createUser("USER005", "정미영", "jung.my@skcc.com", "010-5678-9012", "A", currentDate, currentTime),
            createUser("USER006", "한지민", "han.jm@skcc.com", "010-6789-0123", "A", currentDate, currentTime),
            createUser("USER007", "송혜교", "song.hg@skcc.com", "010-7890-1234", "A", currentDate, currentTime),
            createUser("USER008", "강동원", "kang.dw@skcc.com", "010-8901-2345", "A", currentDate, currentTime),
            createUser("USER009", "배두나", "bae.dn@skcc.com", "010-9012-3456", "A", currentDate, currentTime),
            createUser("USER010", "원빈", "won.b@skcc.com", "010-0123-4567", "A", currentDate, currentTime),

            // Inactive Users
            createUser("USER011", "김비활성", "kim.inactive@skcc.com", "010-1111-1111", "I", currentDate, currentTime),
            createUser("USER012", "이잠금", "lee.locked@skcc.com", "010-2222-2222", "L", currentDate, currentTime),

            // Admin Users
            createUser("ADMIN001", "시스템관리자", "admin@skcc.com", "010-9999-9999", "A", currentDate, currentTime),
            createUser("ADMIN002", "운영관리자", "operator@skcc.com", "010-8888-8888", "A", currentDate, currentTime),
            createUser("ADMIN003", "보안관리자", "security@skcc.com", "010-7777-7777", "A", currentDate, currentTime)
        );

        userRepository.saveAll(users);
        logger.info("Created {} users", users.size());
    }

    private User createUser(String userId, String userName, String email, String phone, String status, 
                           String currentDate, String currentTime) {
        User user = new User(userId, userName, email);
        user.setPhone(phone);
        user.setStatus(status);
        user.setRegisterDate(currentDate);
        user.setRegisterTime(currentTime);
        user.setRegisterBy("SYSTEM");
        return user;
    }

    private void createTellers(String currentDate, String currentTime) {
        logger.info("Creating tellers...");

        List<Teller> tellers = Arrays.asList(
            // Seoul Main Branch Tellers
            createTeller("TELLER001", "김텔러", "001", "001", "A", currentDate, currentTime),
            createTeller("TELLER002", "이텔러", "001", "001", "A", currentDate, currentTime),
            createTeller("TELLER003", "박텔러", "001", "001", "A", currentDate, currentTime),
            createTeller("TELLER004", "최텔러", "001", "001", "V", currentDate, currentTime),

            // Busan Branch Tellers
            createTeller("TELLER005", "정텔러", "002", "001", "A", currentDate, currentTime),
            createTeller("TELLER006", "한텔러", "002", "001", "A", currentDate, currentTime),
            createTeller("TELLER007", "송텔러", "002", "001", "A", currentDate, currentTime),

            // Incheon Branch Tellers
            createTeller("TELLER008", "강텔러", "003", "001", "A", currentDate, currentTime),
            createTeller("TELLER009", "배텔러", "003", "001", "A", currentDate, currentTime),

            // Daegu Branch Tellers
            createTeller("TELLER010", "원텔러", "004", "001", "A", currentDate, currentTime),
            createTeller("TELLER011", "윤텔러", "004", "001", "I", currentDate, currentTime),

            // Daejeon Branch Tellers
            createTeller("TELLER012", "임텔러", "005", "001", "A", currentDate, currentTime),
            createTeller("TELLER013", "조텔러", "005", "001", "A", currentDate, currentTime)
        );

        tellerRepository.saveAll(tellers);
        logger.info("Created {} tellers", tellers.size());
    }

    private Teller createTeller(String tellerId, String tellerName, String branchCode, String bankCode, 
                               String status, String currentDate, String currentTime) {
        Teller teller = new Teller(tellerId, tellerName, branchCode);
        teller.setBankCode(bankCode);
        teller.setStatus(status);
        teller.setRegisterDate(currentDate);
        teller.setRegisterTime(currentTime);
        teller.setRegisterBy("SYSTEM");
        return teller;
    }

    private void createDeposits(String currentDate, String currentTime) {
        logger.info("Creating deposits...");

        List<Deposit> deposits = Arrays.asList(
            // Active Savings Accounts
            createDeposit("110123456789", "001", "001", "CIF001", "김철수", "KRW", 5000000.00, 2.50, "A", currentDate, currentTime),
            createDeposit("110123456790", "001", "001", "CIF002", "이영희", "KRW", 7500000.00, 3.00, "A", currentDate, currentTime),
            createDeposit("110123456791", "001", "002", "CIF003", "박민수", "KRW", 3000000.00, 2.75, "A", currentDate, currentTime),
            createDeposit("110123456792", "001", "002", "CIF004", "최영수", "KRW", 12000000.00, 3.25, "A", currentDate, currentTime),
            createDeposit("110123456793", "001", "001", "CIF005", "정미영", "USD", 50000.00, 1.50, "A", currentDate, currentTime),
            createDeposit("110123456794", "001", "001", "CIF006", "한지민", "KRW", 12000000.00, 3.25, "A", currentDate, currentTime),
            createDeposit("110123456795", "001", "002", "CIF007", "송혜교", "KRW", 8000000.00, 2.80, "A", currentDate, currentTime),
            createDeposit("110123456796", "001", "003", "CIF008", "강동원", "KRW", 15000000.00, 3.50, "A", currentDate, currentTime),
            createDeposit("110123456797", "001", "003", "CIF009", "배두나", "EUR", 25000.00, 1.75, "A", currentDate, currentTime),
            createDeposit("110123456798", "001", "004", "CIF010", "원빈", "KRW", 25000000.00, 4.00, "A", currentDate, currentTime),

            // Foreign Currency Accounts
            createDeposit("110123456799", "001", "001", "CIF011", "김외화", "USD", 100000.00, 2.00, "A", currentDate, currentTime),
            createDeposit("110123456800", "001", "002", "CIF012", "이외화", "EUR", 50000.00, 1.80, "A", currentDate, currentTime),
            createDeposit("110123456801", "001", "003", "CIF013", "박외화", "JPY", 5000000.00, 0.50, "A", currentDate, currentTime),
            createDeposit("110123456802", "001", "004", "CIF014", "최외화", "CNY", 200000.00, 2.20, "A", currentDate, currentTime),

            // Closed Accounts
            createDeposit("110123456803", "001", "001", "CIF015", "김해지", "KRW", 0.00, 0.00, "C", currentDate, currentTime),
            createDeposit("110123456804", "001", "002", "CIF016", "이해지", "KRW", 0.00, 0.00, "C", currentDate, currentTime),

            // Frozen Accounts
            createDeposit("110123456805", "001", "003", "CIF017", "김동결", "KRW", 5000000.00, 2.50, "F", currentDate, currentTime),

            // Dormant Accounts
            createDeposit("110123456806", "001", "004", "CIF018", "김휴면", "KRW", 1000000.00, 2.50, "D", currentDate, currentTime)
        );

        depositRepository.saveAll(deposits);
        logger.info("Created {} deposits", deposits.size());
    }

    private Deposit createDeposit(String accountNumber, String bankCode, String branchCode, String cifNo, 
                                 String cifName, String currency, double balance, double interestRate, 
                                 String status, String currentDate, String currentTime) {
        Deposit deposit = new Deposit();
        deposit.setAccountNumber(accountNumber);
        deposit.setBankCode(bankCode);
        deposit.setBranchCode(branchCode);
        deposit.setCifNo(cifNo);
        deposit.setCifName(cifName);
        deposit.setCurrency(currency);
        deposit.setBalance(new BigDecimal(balance));
        deposit.setInterestRate(new BigDecimal(interestRate));
        deposit.setStatus(status);
        deposit.setOpenDate(currentDate);
        deposit.setMaturityDate(LocalDateTime.of(2024, 12, 31, 0, 0));
        deposit.setRegisterDate(currentDate);
        deposit.setRegisterTime(currentTime);
        deposit.setRegisterBy("SYSTEM");
        deposit.setCreatedDate(LocalDateTime.now());
        return deposit;
    }

    private void createCashCards(String currentDate, String currentTime) {
        logger.info("Creating cash cards...");

        List<CashCard> cashCards = Arrays.asList(
            // Active Cards
            createCashCard("1234567890123456", "110123456789", "001", "001", "CIF001", "김철수", "KRW", 1000000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123457", "110123456790", "001", "001", "CIF002", "이영희", "KRW", 2000000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123458", "110123456791", "001", "002", "CIF003", "박민수", "KRW", 1500000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123459", "110123456792", "001", "002", "CIF004", "최영수", "KRW", 3000000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123460", "110123456793", "001", "001", "CIF005", "정미영", "USD", 50000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123461", "110123456794", "001", "001", "CIF006", "한지민", "KRW", 5000000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123462", "110123456795", "001", "002", "CIF007", "송혜교", "KRW", 2500000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123463", "110123456796", "001", "003", "CIF008", "강동원", "KRW", 8000000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123464", "110123456797", "001", "003", "CIF009", "배두나", "EUR", 10000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123465", "110123456798", "001", "004", "CIF010", "원빈", "KRW", 10000000.00, 0.00, "A", currentDate, currentTime),

            // Foreign Currency Cards
            createCashCard("1234567890123466", "110123456799", "001", "001", "CIF011", "김외화", "USD", 10000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123467", "110123456800", "001", "002", "CIF012", "이외화", "EUR", 5000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123468", "110123456801", "001", "003", "CIF013", "박외화", "JPY", 500000.00, 0.00, "A", currentDate, currentTime),
            createCashCard("1234567890123469", "110123456802", "001", "004", "CIF014", "최외화", "CNY", 20000.00, 0.00, "A", currentDate, currentTime),

            // Inactive Cards
            createCashCard("1234567890123470", "110123456803", "001", "001", "CIF015", "김해지", "KRW", 1000000.00, 0.00, "I", currentDate, currentTime),
            createCashCard("1234567890123471", "110123456804", "001", "002", "CIF016", "이해지", "KRW", 1500000.00, 0.00, "I", currentDate, currentTime),

            // Suspended Cards
            createCashCard("1234567890123472", "110123456805", "001", "003", "CIF017", "김동결", "KRW", 2000000.00, 0.00, "S", currentDate, currentTime),

            // Pending Cards
            createCashCard("1234567890123473", "110123456806", "001", "004", "CIF018", "김휴면", "KRW", 1000000.00, 0.00, "P", currentDate, currentTime),

            // Cards with Daily Accumulation
            createCashCard("1234567890123474", "110123456789", "001", "001", "CIF001", "김철수", "KRW", 1000000.00, 250000.00, "A", currentDate, currentTime),
            createCashCard("1234567890123475", "110123456790", "001", "001", "CIF002", "이영희", "KRW", 2000000.00, 1800000.00, "A", currentDate, currentTime),
            createCashCard("1234567890123476", "110123456791", "001", "002", "CIF003", "박민수", "KRW", 1500000.00, 1200000.00, "A", currentDate, currentTime)
        );

        cashCardRepository.saveAll(cashCards);
        logger.info("Created {} cash cards", cashCards.size());
    }

    private CashCard createCashCard(String cardNumber, String primaryAccountNo, String bankCode, String branchCode,
                                   String cifNo, String cifName, String dailyLimitCcy, double dailyLimitAmount, 
                                   double dailyAccumAmount, String status, String currentDate, String currentTime) {
        CashCard cashCard = new CashCard(cardNumber, bankCode, primaryAccountNo);
        cashCard.setCardNo(cardNumber); // cardNo 필드 설정
        cashCard.setBranchCode(branchCode);
        cashCard.setCifNo(cifNo);
        cashCard.setCifName(cifName);
        cashCard.setDailyLimitCcy(dailyLimitCcy);
        cashCard.setDailyLimitAmount(new BigDecimal(dailyLimitAmount));
        cashCard.setDailyAccumAmount(new BigDecimal(dailyAccumAmount));
        cashCard.setStatus(status);
        cashCard.setEffectiveDate(currentDate);
        cashCard.setExpiryDate("20261231");
        cashCard.setRegisterDate(currentDate);
        cashCard.setRegisterTime(currentTime);
        cashCard.setRegisterBy("SYSTEM");
        cashCard.setCreatedDate(LocalDateTime.now());
        return cashCard;
    }

    private void createHotCards(String currentDate, String currentTime) {
        logger.info("Creating hot cards...");

        List<HotCard> hotCards = Arrays.asList(
            // Lost Cards
            createHotCard("1234567890123470", 1, "110123456803", "CIF015", "김해지", "H", "Lost card reported by customer", currentDate, currentTime),
            createHotCard("1234567890123471", 1, "110123456804", "CIF016", "이해지", "H", "Lost card reported by customer", currentDate, currentTime),

            // Stolen Cards
            createHotCard("1234567890123472", 1, "110123456805", "CIF017", "김동결", "H", "Stolen card reported by customer", currentDate, currentTime),

            // Fraudulent Activity
            createHotCard("1234567890123473", 1, "110123456806", "CIF018", "김휴면", "H", "Fraudulent activity detected by system", currentDate, currentTime),

            // Multiple Hot Card Entries
            createHotCard("1234567890123470", 2, "110123456803", "CIF015", "김해지", "H", "Second hot card registration - suspicious activity", currentDate, currentTime),
            createHotCard("1234567890123471", 2, "110123456804", "CIF016", "이해지", "H", "Second hot card registration - unauthorized usage", currentDate, currentTime)
        );

        hotCardRepository.saveAll(hotCards);
        logger.info("Created {} hot cards", hotCards.size());
    }

    private HotCard createHotCard(String cardNumber, int sequenceNo, String primaryAccountNo, String cifNo,
                                 String cifName, String status, String remark, String currentDate, String currentTime) {
        HotCard hotCard = new HotCard(sequenceNo, cardNumber);
        hotCard.setPrimaryAccountNo(primaryAccountNo);
        hotCard.setCifNo(cifNo);
        hotCard.setCifName(cifName);
        hotCard.setStatus(status);
        hotCard.setRemark(remark);
        hotCard.setRegisterDate(currentDate);
        hotCard.setRegisterTime(currentTime);
        hotCard.setRegisterBy("SYSTEM");
        hotCard.setCreatedDate(LocalDateTime.now());
        return hotCard;
    }

    private void createTransactionLogs(String currentDate, String currentTime) {
        logger.info("Creating transaction logs...");

        List<TransactionLog> transactionLogs = Arrays.asList(
            // Cash Card Transactions
            createTransactionLog("TXN001", "TXN20240101001", "HOST001", "CASH_CARD", "createCashCard", "001", "001", "USER001", "WEB", currentDate, currentTime, 15000, "I0000"),
            createTransactionLog("TXN002", "TXN20240101002", "HOST001", "CASH_CARD", "getCardInfo", "001", "001", "USER002", "WEB", currentDate, currentTime, 5000, "I0000"),
            createTransactionLog("TXN003", "TXN20240101003", "HOST001", "CASH_CARD", "updateCardInfo", "001", "001", "USER001", "WEB", currentDate, currentTime, 15000, "I0000"),
            createTransactionLog("TXN004", "TXN20240101004", "HOST001", "CASH_CARD", "issueCard", "001", "002", "USER003", "WEB", currentDate, currentTime, 20000, "I0000"),
            createTransactionLog("TXN005", "TXN20240101005", "HOST001", "CASH_CARD", "registerHotCard", "001", "001", "USER001", "WEB", currentDate, currentTime, 10000, "I0000"),

            // Deposit Transactions
            createTransactionLog("TXN006", "TXN20240101006", "HOST001", "DEPOSIT", "createDeposit", "001", "001", "USER001", "WEB", currentDate, currentTime, 10000, "I0000"),
            createTransactionLog("TXN007", "TXN20240101007", "HOST001", "DEPOSIT", "getDepositInfo", "001", "002", "USER002", "WEB", currentDate, currentTime, 5000, "I0000"),
            createTransactionLog("TXN008", "TXN20240101008", "HOST001", "DEPOSIT", "updateDeposit", "001", "002", "USER002", "WEB", currentDate, currentTime, 10000, "I0000"),

            // Common Code Transactions
            createTransactionLog("TXN009", "TXN20240101009", "HOST001", "COMMON", "getCommonInfo", "001", "001", "USER003", "WEB", currentDate, currentTime, 2000, "I0000"),
            createTransactionLog("TXN010", "TXN20240101010", "HOST001", "COMMON", "getCommonByType", "001", "001", "USER003", "WEB", currentDate, currentTime, 3000, "I0000"),

            // Teller Transactions
            createTransactionLog("TXN011", "TXN20240101011", "HOST001", "TELLER", "createTeller", "001", "001", "ADMIN001", "WEB", currentDate, currentTime, 12000, "I0000"),
            createTransactionLog("TXN012", "TXN20240101012", "HOST001", "TELLER", "getTellerInfo", "001", "002", "ADMIN002", "WEB", currentDate, currentTime, 5000, "I0000"),

            // User Transactions
            createTransactionLog("TXN013", "TXN20240101013", "HOST001", "USER", "createUser", "001", "001", "ADMIN001", "WEB", currentDate, currentTime, 10000, "I0000"),
            createTransactionLog("TXN014", "TXN20240101014", "HOST001", "USER", "getUserInfo", "001", "001", "ADMIN002", "WEB", currentDate, currentTime, 5000, "I0000"),

            // Error Transactions
            createTransactionLog("TXN015", "TXN20240101015", "HOST001", "CASH_CARD", "getCardInfo", "001", "001", "USER001", "WEB", currentDate, currentTime, 5000, "E0001"),
            createTransactionLog("TXN016", "TXN20240101016", "HOST001", "DEPOSIT", "updateDeposit", "001", "002", "USER002", "WEB", currentDate, currentTime, 10000, "E0002"),

            // Mobile Channel Transactions
            createTransactionLog("TXN017", "TXN20240101017", "HOST001", "CASH_CARD", "getCardInfo", "001", "001", "USER001", "MOBILE", currentDate, currentTime, 5000, "I0000"),
            createTransactionLog("TXN018", "TXN20240101018", "HOST001", "DEPOSIT", "getDepositInfo", "001", "002", "USER002", "MOBILE", currentDate, currentTime, 5000, "I0000"),

            // ATM Channel Transactions
            createTransactionLog("TXN019", "TXN20240101019", "HOST001", "CASH_CARD", "withdraw", "001", "001", "USER001", "ATM", currentDate, currentTime, 20000, "I0000"),
            createTransactionLog("TXN020", "TXN20240101020", "HOST001", "DEPOSIT", "deposit", "001", "002", "USER002", "ATM", currentDate, currentTime, 15000, "I0000"),

            // High Value Transactions
            createTransactionLog("TXN021", "TXN20240101021", "HOST001", "CASH_CARD", "transfer", "001", "001", "USER001", "WEB", currentDate, currentTime, 30000, "I0000"),
            createTransactionLog("TXN022", "TXN20240101022", "HOST001", "DEPOSIT", "transfer", "001", "002", "USER002", "WEB", currentDate, currentTime, 30000, "I0000"),

            // System Maintenance Transactions
            createTransactionLog("TXN023", "TXN20240101023", "HOST001", "SYSTEM", "backup", "001", "001", "ADMIN001", "SYSTEM", currentDate, currentTime, 300000, "I0000"),
            createTransactionLog("TXN024", "TXN20240101024", "HOST001", "SYSTEM", "cleanup", "001", "001", "ADMIN002", "SYSTEM", currentDate, currentTime, 60000, "I0000"),

            // Batch Job Transactions
            createTransactionLog("TXN025", "TXN20240101025", "HOST001", "BATCH", "dailySettlement", "001", "001", "SYSTEM", "BATCH", currentDate, currentTime, 300000, "I0000"),
            createTransactionLog("TXN026", "TXN20240101026", "HOST001", "BATCH", "monthlyReport", "001", "001", "SYSTEM", "BATCH", currentDate, currentTime, 180000, "I0000")
        );

        transactionLogRepository.saveAll(transactionLogs);
        logger.info("Created {} transaction logs", transactionLogs.size());
    }

    private TransactionLog createTransactionLog(String transactionId, String transactionNo, String hostName,
                                               String systemName, String methodName, String bankCode, String branchCode,
                                               String userId, String channelType, String currentDate, String currentTime,
                                               long responseTime, String errorCode) {
        TransactionLog transactionLog = new TransactionLog();
        transactionLog.setTransactionId(transactionId);
        transactionLog.setTransactionNo(transactionNo);
        transactionLog.setHostName(hostName);
        transactionLog.setSystemName(systemName);
        transactionLog.setMethodName(methodName);
        transactionLog.setBankCode(bankCode);
        transactionLog.setBranchCode(branchCode);
        transactionLog.setUserId(userId);
        transactionLog.setChannelType(channelType);
        transactionLog.setBusinessDate(currentDate);
        transactionLog.setRegisterDate(currentDate);
        transactionLog.setInTime(currentTime);
        transactionLog.setOutTime(currentTime);
        transactionLog.setResponseTime(responseTime);
        transactionLog.setErrorCode(errorCode);
        transactionLog.setEventNo("EVT" + transactionId.substring(3));
        transactionLog.setIpAddress("192.168.1." + (100 + Integer.parseInt(transactionId.substring(3)) % 10));
        transactionLog.setUpdateDate(LocalDateTime.now());
        transactionLog.setCreatedDate(LocalDateTime.now());
        return transactionLog;
    }

    /**
     * Utility method to create test data programmatically for testing purposes
     */
    public void createTestDataForTesting() {
        logger.info("Creating test data for testing purposes...");

        String currentDate = LocalDateTime.now().format(DATE_FORMATTER);
        String currentTime = LocalDateTime.now().format(TIME_FORMATTER);

        initializeComprehensiveTestData();

        logger.info("Test data created for testing purposes!");
    }
}