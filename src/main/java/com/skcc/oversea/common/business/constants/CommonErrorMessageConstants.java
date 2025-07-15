package com.skcc.oversea.common.business.constants;

/**
 * Common Error Message Constants for SKCC Oversea
 * 
 * Provides centralized error message constants for consistent error handling
 * across the application.
 */
public class CommonErrorMessageConstants {

    // Account related error messages
    public static final String ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST = "ERR_0125";
    public static final String ERR_0126_ACCOUNT_ALREADY_EXISTS = "ERR_0126";
    public static final String ERR_0127_INVALID_ACCOUNT_TYPE = "ERR_0127";
    public static final String ERR_0128_ACCOUNT_CLOSED = "ERR_0128";
    public static final String ERR_0129_INSUFFICIENT_BALANCE = "ERR_0129";

    // Card related error messages
    public static final String ERR_0201_CARD_NOT_FOUND = "ERR_0201";
    public static final String ERR_0202_CARD_EXPIRED = "ERR_0202";
    public static final String ERR_0203_CARD_BLOCKED = "ERR_0203";
    public static final String ERR_0204_INVALID_CARD_NUMBER = "ERR_0204";
    public static final String ERR_0205_CARD_LIMIT_EXCEEDED = "ERR_0205";

    // Transaction related error messages
    public static final String ERR_0301_TRANSACTION_FAILED = "ERR_0301";
    public static final String ERR_0302_INVALID_TRANSACTION_TYPE = "ERR_0302";
    public static final String ERR_0303_TRANSACTION_TIMEOUT = "ERR_0303";
    public static final String ERR_0304_DUPLICATE_TRANSACTION = "ERR_0304";

    // System related error messages
    public static final String ERR_9999_SYSTEM_ERROR = "ERR_9999";
    public static final String ERR_9998_DATABASE_ERROR = "ERR_9998";
    public static final String ERR_9997_NETWORK_ERROR = "ERR_9997";
    public static final String ERR_9996_SERVICE_UNAVAILABLE = "ERR_9996";

    // Validation error messages
    public static final String ERR_0401_INVALID_INPUT = "ERR_0401";
    public static final String ERR_0402_MISSING_REQUIRED_FIELD = "ERR_0402";
    public static final String ERR_0403_INVALID_FORMAT = "ERR_0403";
    public static final String ERR_0404_DATA_TOO_LONG = "ERR_0404";

    // Authentication and authorization error messages
    public static final String ERR_0501_UNAUTHORIZED_ACCESS = "ERR_0501";
    public static final String ERR_0502_INVALID_CREDENTIALS = "ERR_0502";
    public static final String ERR_0503_SESSION_EXPIRED = "ERR_0503";
    public static final String ERR_0504_INSUFFICIENT_PERMISSIONS = "ERR_0504";

    // Business logic error messages
    public static final String ERR_0601_BUSINESS_RULE_VIOLATION = "ERR_0601";
    public static final String ERR_0602_INVALID_BUSINESS_DATE = "ERR_0602";
    public static final String ERR_0603_SERVICE_NOT_AVAILABLE = "ERR_0603";

    /**
     * Get error message description for error code
     * @param errorCode Error code
     * @return Error message description
     */
    public static String getErrorMessage(String errorCode) {
        switch (errorCode) {
            case ERR_0125_ACCOUNT_NUMBER_DOES_NOT_EXIST:
                return "Account number does not exist";
            case ERR_0126_ACCOUNT_ALREADY_EXISTS:
                return "Account already exists";
            case ERR_0127_INVALID_ACCOUNT_TYPE:
                return "Invalid account type";
            case ERR_0128_ACCOUNT_CLOSED:
                return "Account is closed";
            case ERR_0129_INSUFFICIENT_BALANCE:
                return "Insufficient balance";
            case ERR_0201_CARD_NOT_FOUND:
                return "Card not found";
            case ERR_0202_CARD_EXPIRED:
                return "Card has expired";
            case ERR_0203_CARD_BLOCKED:
                return "Card is blocked";
            case ERR_0204_INVALID_CARD_NUMBER:
                return "Invalid card number";
            case ERR_0205_CARD_LIMIT_EXCEEDED:
                return "Card limit exceeded";
            case ERR_0301_TRANSACTION_FAILED:
                return "Transaction failed";
            case ERR_0302_INVALID_TRANSACTION_TYPE:
                return "Invalid transaction type";
            case ERR_0303_TRANSACTION_TIMEOUT:
                return "Transaction timeout";
            case ERR_0304_DUPLICATE_TRANSACTION:
                return "Duplicate transaction";
            case ERR_9999_SYSTEM_ERROR:
                return "System error occurred";
            case ERR_9998_DATABASE_ERROR:
                return "Database error occurred";
            case ERR_9997_NETWORK_ERROR:
                return "Network error occurred";
            case ERR_9996_SERVICE_UNAVAILABLE:
                return "Service unavailable";
            case ERR_0401_INVALID_INPUT:
                return "Invalid input provided";
            case ERR_0402_MISSING_REQUIRED_FIELD:
                return "Missing required field";
            case ERR_0403_INVALID_FORMAT:
                return "Invalid format";
            case ERR_0404_DATA_TOO_LONG:
                return "Data too long";
            case ERR_0501_UNAUTHORIZED_ACCESS:
                return "Unauthorized access";
            case ERR_0502_INVALID_CREDENTIALS:
                return "Invalid credentials";
            case ERR_0503_SESSION_EXPIRED:
                return "Session expired";
            case ERR_0504_INSUFFICIENT_PERMISSIONS:
                return "Insufficient permissions";
            case ERR_0601_BUSINESS_RULE_VIOLATION:
                return "Business rule violation";
            case ERR_0602_INVALID_BUSINESS_DATE:
                return "Invalid business date";
            case ERR_0603_SERVICE_NOT_AVAILABLE:
                return "Service not available";
            default:
                return "Unknown error occurred";
        }
    }
} 