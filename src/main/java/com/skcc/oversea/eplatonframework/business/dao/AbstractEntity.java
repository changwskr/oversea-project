package com.skcc.oversea.eplatonframework.business.dao;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.skcc.oversea.foundation.log.Log;
import com.skcc.oversea.foundation.utility.StringUtils;

/**
 * This class is abstract entity.
 *
 * @author <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/11/01
 */
public abstract class AbstractEntity {
    
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
     * DB 변수 문자열을 escape처리한다.
     */
    public static String escape(String value) {
        if (null == value)
            return "";
        value = StringUtils.replace(value, "'", "''");
        return StringUtils.replace(value, "%", "%%");
    }

    /**
     * Release DB resource.
     */
    public static void releaseResource(Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                /* ignored */ }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                /* ignored */ }
        }
    }

    // ------------------------------------------------ helper method

    public static boolean isLogabled() {
        return Log.VDCLogger.isDebugEnabled();
    }

    protected void logDebug(Object message) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.valueOf(message));
        }
    }

    protected void logDebug(Object message, Throwable throwable) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.valueOf(message), throwable);
        }
    }

    protected void logInfo(Object message) {
        logger.info(String.valueOf(message));
    }

    protected void logWarn(Object message) {
        logger.warn(String.valueOf(message));
    }

    protected void logError(Object message, Throwable throwable) {
        logger.error(String.valueOf(message), throwable);
    }
}
