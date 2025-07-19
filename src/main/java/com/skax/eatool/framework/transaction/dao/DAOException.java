package com.skax.eatool.framework.transaction.dao;

/**
 * =============================================================================
 * 프로그램 명:
 * =============================================================================
 *
 *
 * =============================================================================
 * 변경내역 정보:
 * =============================================================================
 * 2004년 03월 16일 1차버전 release
 *
 *
 * =============================================================================
 * 
 * @version 1.0
 *          =============================================================================
 */

public class DAOException extends Exception {
    private Exception cause;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Exception cause) {
        super();
        this.cause = cause;
    }

    public DAOException(String message, Exception cause) {
        super(message);
        this.cause = cause;
    }

    public Exception getCausedException() {
        return cause;
    }

    /**
     * 원인 예외가 있을 경우에 추가하여 출력한다
     */
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        if (cause != null) {
            sb.append(System.getProperty("line.separator"));
            sb.append("Caused by: ");
            sb.append(cause.toString());
        }
        return sb.toString();
    }
}
