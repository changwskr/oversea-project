package com.skax.eatool.framework.transaction.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.net.*;
import java.text.*;
import java.util.*;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import com.skax.eatool.foundation.log.Log;
import com.skax.eatool.foundation.config.Config;
import com.skax.eatool.framework.transaction.helper.DTOConverter;
import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.framework.transaction.model.TransactionUpDownDDTO;
import com.skax.eatool.framework.transaction.model.TransactionLogDDTO;

import com.skax.eatool.foundation.utility.*;
import com.skax.eatool.foundation.logej.*;

/**
 * Transaction Control DAO for SKCC Oversea
 * Spring repository replacing EJB DAO
 */
@Repository
@Transactional
public class TransactionControlDAO implements ITransactionControlDAO {

  private static final Logger logger = LoggerFactory.getLogger(TransactionControlDAO.class);

  @Autowired
  private DataSource dataSource;

  /**
   * Get database connection from Spring DataSource
   */
  protected Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  /**
   * Release database resources
   */
  protected void release(ResultSet rs) throws SQLException {
    if (rs != null) {
      rs.close();
    }
  }

  protected void release(Statement stmt) throws SQLException {
    if (stmt != null) {
      stmt.close();
    }
  }

  protected void release(Connection conn) throws SQLException {
    if (conn != null) {
      conn.close();
    }
  }

  /**
   * STF(Start Transaction Framework)에서 트랜잭션 입력문을 기록하기 위한 로직
   */
  @Transactional(readOnly = false)
  public boolean DB_INSERTinlog(EPlatonEvent event) throws DAOException {
    Connection con = null;
    try {
      TransactionUpDownDDTO logDDTO = new DTOConverter().getTransactionUpDownDDTO(event);
      con = getConnection();

      logger.debug("TransactionUpDownDDTO: {}", logDDTO.toString());

      TransactionUpDownEntity.insertTransactionInLog(con, event);

    } catch (SQLException e) {
      logger.error("Error inserting transaction in log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert transaction in log", e);
    } catch (Exception e) {
      logger.error("Error inserting transaction in log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert transaction in log", e);
    }

    releaseConnection(con);
    return true;
  }

  /**
   * TPMDBSrecv()모듈에서 사용하는 모듈에서 입력문을 관리하기 위해 사용한다.
   */
  @Transactional(readOnly = false)
  public boolean DB_INSERT_tpminlog(EPlatonEvent event) throws DAOException {
    Connection con = null;
    EPlatonCommonDTO commonDTO = event.getCommon();
    TPSVCINFODTO tpsvcinfoDTO = event.getTPSVCINFODTO();

    try {
      con = getConnection();
      TransactionLogEntity.insertTransactionInLog(con, event);

    } catch (SQLException e) {
      logger.error("Error inserting TPM in log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert TPM in log", e);
    } catch (Exception e) {
      logger.error("Error inserting TPM in log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert TPM in log", e);
    }
    releaseConnection(con);
    return true;
  }

  @Transactional(readOnly = false)
  public boolean DB_INSERTtpmoutlog(EPlatonEvent event, long interval_seconds) throws DAOException {
    Connection con = null;
    try {
      con = getConnection();
      TransactionLogEntity.insertTransactionOutLog(con, interval_seconds, event);

    } catch (SQLException e) {
      logger.error("Error inserting TPM out log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert TPM out log", e);
    } catch (Exception e) {
      logger.error("Error inserting TPM out log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert TPM out log", e);
    }

    releaseConnection(con);
    return true;
  }

  @Transactional(readOnly = false)
  public boolean DB_INSERToutlog(EPlatonEvent event) throws DAOException {
    Connection con = null;
    try {
      TransactionUpDownDDTO logDDTO = new DTOConverter().getTransactionUpDownDDTO(event);
      con = getConnection();

      logger.debug("TransactionUpDownDDTO: {}", logDDTO.toString());

      TransactionUpDownEntity.insertTransactionOutLog(con, event);

    } catch (SQLException e) {
      logger.error("Error inserting transaction out log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert transaction out log", e);
    } catch (Exception e) {
      logger.error("Error inserting transaction out log", e);
      releaseConnection(con);
      throw new DAOException("Failed to insert transaction out log", e);
    }

    releaseConnection(con);
    return true;
  }

  public void releaseConnection(Connection con) {
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) {
        logger.warn("Error closing connection", e);
      }
    }
  }

  @Transactional(readOnly = true)
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
        businessDate = rs.getString("DATE_VALUE1");
      }
    } catch (SQLException se) {
      logger.error("Error querying business date", se);
    } catch (Exception e) {
      logger.error("Error querying business date", e);
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
      if (rs.next()) {
        bizdate = CommonUtil.Int2Str(rs.getInt(1));
      }
    } catch (Exception e) {
      logger.error("Error getting business date", e);
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
      logger.error("Error releasing resources", sqle);
    }

    return (bizdate);
  }

  /**
   * 리소스를 반환한다
   * 
   * @param Connection
   * @param PreparedStatement
   * @param ResultSet
   * @return void
   */
  private void releaseResource(Connection con, PreparedStatement ps, ResultSet rs) {
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
      logger.error("Error releasing resources", sqle);
    }
  }

  public boolean isLogabled() {
    return Log.VDCLogger.isDebugEnabled();
  }
}
