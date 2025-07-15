package com.skcc.oversea.common.business.constants;

/**
 * Common System Constants for SKCC Oversea
 * 
 * Provides centralized system constants for consistent system configuration
 * across the application.
 */
public class CommonSystemConstants {

    // System identifiers
    public static final String SYSTEM_CORE = "CORE";
    public static final String SYSTEM_LENDING = "LENDING";
    public static final String SYSTEM_DEPOSIT = "DEPOSIT";
    public static final String SYSTEM_CARD = "CARD";
    public static final String SYSTEM_TRANSFER = "TRANSFER";
    public static final String SYSTEM_PAYMENT = "PAYMENT";
    public static final String SYSTEM_REFERENCE = "REFERENCE";
    public static final String SYSTEM_COMMON = "COMMON";

    // Sub-system identifiers
    public static final String SUB_SYSTEM_CASH_CARD = "CASH_CARD";
    public static final String SUB_SYSTEM_HOT_CARD = "HOT_CARD";
    public static final String SUB_SYSTEM_ACCOUNT = "ACCOUNT";
    public static final String SUB_SYSTEM_TRANSACTION = "TRANSACTION";
    public static final String SUB_SYSTEM_USER = "USER";
    public static final String SUB_SYSTEM_TELLER = "TELLER";
    public static final String SUB_SYSTEM_COMMON = "COMMON";

    // Environment constants
    public static final String ENV_DEVELOPMENT = "DEV";
    public static final String ENV_TEST = "TEST";
    public static final String ENV_STAGING = "STG";
    public static final String ENV_PRODUCTION = "PROD";

    // Database constants
    public static final String DB_TYPE_ORACLE = "ORACLE";
    public static final String DB_TYPE_MYSQL = "MYSQL";
    public static final String DB_TYPE_POSTGRES = "POSTGRES";
    public static final String DB_TYPE_H2 = "H2";

    // Transaction constants
    public static final String TX_STATUS_PENDING = "P";
    public static final String TX_STATUS_COMPLETED = "C";
    public static final String TX_STATUS_FAILED = "F";
    public static final String TX_STATUS_CANCELLED = "X";
    public static final String TX_STATUS_REVERSED = "R";

    // Channel types
    public static final String CHANNEL_INTERNET = "01";
    public static final String CHANNEL_MOBILE = "02";
    public static final String CHANNEL_BRANCH = "03";
    public static final String CHANNEL_ATM = "04";
    public static final String CHANNEL_POS = "05";

    // User types
    public static final String USER_TYPE_ADMIN = "ADMIN";
    public static final String USER_TYPE_TELLER = "TELLER";
    public static final String USER_TYPE_CUSTOMER = "CUSTOMER";
    public static final String USER_TYPE_SYSTEM = "SYSTEM";

    // Date formats
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TIME_FORMAT_HHMMSS = "HHmmss";
    public static final String DATETIME_FORMAT_YYYYMMDD_HHMMSS = "yyyyMMddHHmmss";

    // Currency codes
    public static final String CURRENCY_KRW = "KRW";
    public static final String CURRENCY_USD = "USD";
    public static final String CURRENCY_EUR = "EUR";
    public static final String CURRENCY_JPY = "JPY";
    public static final String CURRENCY_CNY = "CNY";

    // Language codes
    public static final String LANGUAGE_KO = "KO";
    public static final String LANGUAGE_EN = "EN";
    public static final String LANGUAGE_JA = "JA";
    public static final String LANGUAGE_ZH = "ZH";

    // Time zone
    public static final String TIMEZONE_ASIA_SEOUL = "Asia/Seoul";
    public static final String TIMEZONE_UTC = "UTC";
    public static final String TIMEZONE_GMT = "GMT";

    // Default values
    public static final String DEFAULT_BANK_CODE = "001";
    public static final String DEFAULT_BRANCH_CODE = "000";
    public static final String DEFAULT_USER_ID = "SYSTEM";
    public static final String DEFAULT_CURRENCY = CURRENCY_KRW;
    public static final String DEFAULT_LANGUAGE = LANGUAGE_KO;

    // System limits
    public static final int MAX_TRANSACTION_AMOUNT = 999999999;
    public static final int MAX_ACCOUNT_LENGTH = 20;
    public static final int MAX_CARD_NUMBER_LENGTH = 20;
    public static final int MAX_CUSTOMER_NAME_LENGTH = 100;
    public static final int MAX_DESCRIPTION_LENGTH = 200;

    // Cache constants
    public static final int CACHE_DEFAULT_TTL = 3600; // 1 hour
    public static final int CACHE_SHORT_TTL = 300;    // 5 minutes
    public static final int CACHE_LONG_TTL = 86400;   // 24 hours

    // Logging constants
    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_WARN = "WARN";
    public static final String LOG_LEVEL_ERROR = "ERROR";

    /**
     * Check if system is production environment
     * @param environment Environment string
     * @return true if production, false otherwise
     */
    public static boolean isProduction(String environment) {
        return ENV_PRODUCTION.equals(environment);
    }

    /**
     * Check if system is development environment
     * @param environment Environment string
     * @return true if development, false otherwise
     */
    public static boolean isDevelopment(String environment) {
        return ENV_DEVELOPMENT.equals(environment);
    }

    /**
     * Get default system configuration
     * @return Default system configuration string
     */
    public static String getDefaultSystemConfig() {
        return SYSTEM_CORE + ":" + SUB_SYSTEM_COMMON;
    }
} 