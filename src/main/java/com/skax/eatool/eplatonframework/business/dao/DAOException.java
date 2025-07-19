package com.skax.eatool.eplatonframework.business.dao;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
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
      * ï§£ëŒ?????‰ì‡…åª›Â€ ??ˆë–ï§??¾ëª„???ë¿‰ ?°ë¶½???ë¿¬ ??? ®ä»¥Â€??
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
