package com.skax.eatool.eplatonframework.business.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import com.skax.eatool.foundation.log.Log;

import com.skax.eatool.eplatonframework.transfer.EPlatonEvent;

import com.skax.eatool.foundation.utility.*;
import com.skax.eatool.foundation.logej.*;

import com.skax.eatool.eplatonframework.business.dao.TransactionLogEntity;

/**
 * Delegate DAO(Data Access Object) 구현체다.
 *
 * @author <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/10/08
 */
@Repository
@Transactional
public class EPlatonDelegateDAO implements IEPlatonDelegateDAO {
    @Autowired
    private DataSource dataSource;

    /**
     * Get database connection from DataSource
     */
    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Release database connection and other resources
     */
    protected void release(AutoCloseable resource) throws SQLException {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    throw (SQLException) e;
                }
                throw new SQLException("Error closing resource", e);
            }
        }
    }

    @Override
    public boolean DB_INSERTinlog(EPlatonEvent event) {
        LOGEJ.getInstance().printf(1, (EPlatonEvent) event, "<DAO - transactionInLog-method:start>");
        Connection con = null;

        try {

            LOGEJ.getInstance().printf(1, (EPlatonEvent) event,
                    com.skax.eatool.foundation.utility.Reflector.objectToString(event));

            DTOConverter converter = new DTOConverter();

            com.skax.eatool.framework.transaction.model.TransactionLogDDTO logDDTO = (com.skax.eatool.framework.transaction.model.TransactionLogDDTO) converter
                    .getTransactionLogDDTO(event);

            LOGEJ.getInstance().printf(1, (EPlatonEvent) event, "systemname:-----" + logDDTO.getSystemName());

            con = getConnection();

            LOGEJ.getInstance().printf(1, (EPlatonEvent) event,
                    "logDDTO:" + com.skax.eatool.foundation.utility.Reflector.objectToString(logDDTO));

            new TransactionLogEntity(event).insertTransactionInLog(
                    con,
                    logDDTO.getTransactionId(),
                    logDDTO.getMethodName(),
                    logDDTO.getSystemName(),
                    logDDTO.getHostName(),
                    logDDTO.getBankCode(),
                    logDDTO.getBranchCode(),
                    logDDTO.getUserId(),
                    logDDTO.getChannelType(),
                    logDDTO.getBusinessDate(),
                    logDDTO.getEventNo(),
                    logDDTO.getIPAddress());

        } catch (SQLException e) {
            LOGEJ.getInstance().eprintf(5, (EPlatonEvent) event, e);
            releaseConnection(con);
            return false;
        } catch (Exception e) {
            LOGEJ.getInstance().eprintf(5, (EPlatonEvent) event, e);
            releaseConnection(con);
            return false;
        }
        releaseConnection(con);

        LOGEJ.getInstance().printf(1, (EPlatonEvent) event, "<DAO - transactionInLog-method:end>");
        return true;
    }

    @Override
    public boolean DB_INSERToutlog(EPlatonEvent event) {
        Connection con = null;
        LOGEJ.getInstance().printf(1, (EPlatonEvent) event,
                com.skax.eatool.foundation.utility.Reflector.objectToString(event));

        try {
            DTOConverter converter = new DTOConverter();
            com.skax.eatool.framework.transaction.model.TransactionLogDDTO logDDTO = (com.skax.eatool.framework.transaction.model.TransactionLogDDTO) converter
                    .getTransactionLogDDTO(event);

            con = getConnection();

            // Convert String responseTime to long
            long responseTime = 0L;
            try {
                String responseTimeStr = logDDTO.getResponseTime();
                if (responseTimeStr != null && !responseTimeStr.trim().isEmpty()) {
                    responseTime = Long.parseLong(responseTimeStr.trim());
                }
            } catch (NumberFormatException e) {
                LOGEJ.getInstance().printf(2, (EPlatonEvent) event,
                        "Warning: Invalid responseTime format, using 0: " + logDDTO.getResponseTime());
                responseTime = 0L;
            }

            new TransactionLogEntity(event).insertTransactionOutLog(
                    con,
                    logDDTO.getTransactionId(),
                    logDDTO.getTransactionNo(),
                    responseTime,
                    logDDTO.getErrorCode());
            new TransactionLogEntity(event).insertTransactionLog2File(getLogLine(logDDTO));

        } catch (SQLException e) {
            LOGEJ.getInstance().eprintf(5, (EPlatonEvent) event, e);
            releaseConnection(con);
            return false;
        } catch (Exception e) {
            LOGEJ.getInstance().eprintf(5, (EPlatonEvent) event, e);
            releaseConnection(con);
            return false;
        }

        releaseConnection(con);

        return true;
    }

    private static String getLogLine(com.skax.eatool.framework.transaction.model.TransactionLogDDTO logDDTO) {
        String TAB = " ";
        return logDDTO.getTransactionId() + TAB +
                logDDTO.getHostName() + TAB +
                logDDTO.getSystemName() + TAB +
                logDDTO.getMethodName() + TAB +
                logDDTO.getBankCode() + TAB +
                logDDTO.getBranchCode() + TAB +
                logDDTO.getUserId() + TAB +
                logDDTO.getChannelType() + TAB +
                logDDTO.getBusinessDate() + TAB +
                logDDTO.getRegisterDate() + TAB +
                logDDTO.getInTime() + TAB +
                logDDTO.getEventNo() + TAB +
                logDDTO.getOutTime() + TAB +
                logDDTO.getResponseTime() + TAB +
                logDDTO.getErrorCode() + TAB +
                logDDTO.getIPAddress() + "\n";
    }

    // ------------------------------------------------ helper method

    public void releaseConnection(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                /* ignored */ }
        }
    }

    // --------------------------------------------------- log method

    public String queryForBusinessDate(String bankCode) {
        StringBuffer queryBuffer = new StringBuffer();
        String businessDate = null;

        queryBuffer.append("SELECT DATE_VALUE1 FROM SYSTEM_PARAMETER WHERE SYSTEM='COR' AND KIND='BIZDATE'");

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            con = this.getConnection();
            psmt = con.prepareStatement(queryBuffer.toString());
            rs = psmt.executeQuery();
            if (rs.next()) {
                businessDate = rs.getString("BUSINESS_DATE");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            releaseResource(con, psmt, rs);
        }
        return businessDate;

    }

    public String GetBizDate() {
        String bizdate = null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = this.getConnection();
            String tSQL = "SELECT DATE_VALUE1 FROM SYSTEM_PARAMETER WHERE SYSTEM='COR' AND KIND='BIZDATE'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(tSQL);
            rs.next();
            bizdate = CommonUtil.Int2Str(rs.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                this.release(rs);
            }
            if (stmt != null) {
                this.release(stmt);
            }
            if (conn != null) {
                this.release(conn);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return (bizdate);
    }

    /**
     * ?�원??반환?�다
     * 
     */
    private void releaseResource(Connection con, PreparedStatement ps,
            ResultSet rs) {

        try {
            if (rs != null) {
                this.release(rs);
            }
            if (ps != null) {
                this.release(ps);
            }
            if (con != null) {
                this.release(con);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }// end method

    public boolean isLogabled() {
        return Log.VDCLogger.isDebugEnabled();
    }

}
