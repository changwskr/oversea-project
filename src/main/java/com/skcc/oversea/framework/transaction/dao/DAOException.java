package com.skcc.oversea.framework.transaction.dao;


/**
 * =============================================================================
 * ?꾨줈洹몃옩 ?ㅻ챸:
 * =============================================================================
 *
 *
 * =============================================================================
 * 蹂寃쎈궡???뺣낫:
 * =============================================================================
 *  2004??03??16??1李⑤쾭??release
 *
 *
 * =============================================================================
 *                                                        @version 1.0
 *  =============================================================================
 */

public class DAOException extends Exception
{
    private Exception cause;

    public DAOException()
    {
        super();
    }

    public DAOException(String message)
    {
        super(message);
    }

    public DAOException(Exception cause)
    {
        super();
        this.cause = cause;
    }

    public DAOException(String message, Exception cause)
    {
        super(message);
        this.cause = cause;
    }

    public Exception getCausedException()
    {
        return cause;
    }

    /**
      * 泥댁씤???덉쇅媛 ?덈떎硫?臾몄옄?댁뿉 異붽??섏뿬 ?뚮젮以??
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer(super.toString());
        if (cause != null) {
            sb.append(System.getProperty("line.separator"));
            sb.append("Caused by: ");
            sb.append(cause.toString());
        }
        return sb.toString();
    }
}
