package com.skcc.oversea.eplatonframework.business.dao;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

import com.skcc.oversea.foundation.log.Log;
import com.skcc.oversea.eplatonframework.transfer.*;
import com.skcc.oversea.eplatonframework.business.model.TransactionLogDDTO;
import com.skcc.oversea.eplatonframework.business.helper.*;
import com.skcc.oversea.foundation.config.Config;
import com.skcc.oversea.foundation.logej.LOGEJ;
import com.skcc.oversea.foundation.utility.CommonUtil;

/**
 * Delegate DAO(Data Access Object) 구현이다.
 *
 * @author  <a href="mailto:ghyu@imssystem.com">Gwanghyeok Yu</a>
 * @version 1.0, 2002/10/08
 */

/*

CREATE TABLE transaction_info(
        orgseq          varchar2(100),
        hostseq         varchar2(100),
        branchcode      varchar2(100),
        system          varchar2(100),
        method          varchar2(100),
        eventno         varchar2(100),
        tx_intime       varchar2(100),
        tx_outtime      varchar2(100),
        tx_interval     varchar2(100),
        tx_errorcode    varchar2(100),
        tx_level        varchar2(100),
        tpfq            varchar2(100),
        STF_intime      varchar2(100),
        STF_outtime     varchar2(100),
        BTF_intime      varchar2(100),
        BTF_outtime     varchar2(100),
        ETF_intime      varchar2(100),
        ETF_outtime     varchar2(100),
        CONSTRAINT PK_TRANSACTION_IN_LOG PRIMARY KEY (orgseq,system,tx_intime,tx_outtime,tx_interval)
        USING INDEX TABLESPACE TSP_CORE_IND
)
TABLESPACE TSP_CORE
/

 */
@Repository("eplatonTxTimeProcDAO")
@Transactional
public class TxTimeProcDAO implements ITxTimeProcDAO
{
    private static final Logger logger = LoggerFactory.getLogger(TxTimeProcDAO.class);

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

    protected void release(PreparedStatement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    protected void release(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Transactional(readOnly = false)
    public boolean TRANSACTION_INFO(EPlatonEvent event) throws DAOException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        EPlatonCommonDTO commonDTO = (EPlatonCommonDTO)event.getCommon();
        TPSVCINFODTO tpsvcinfo = event.getTPSVCINFODTO();

        String STF_intime=tpsvcinfo.getSTF_intime()  ;
        String STF_outtime=tpsvcinfo.getSTF_outtime();
        String BTF_intime=tpsvcinfo.getBTF_intime()  ;
        String BTF_outtime=tpsvcinfo.getBTF_outtime();
        String ETF_intime=tpsvcinfo.getETF_intime()  ;
        String ETF_outtime=tpsvcinfo.getETF_outtime();
        String hostseq = tpsvcinfo.getHostseq();
        String orgseq = tpsvcinfo.getOrgseq();
        String branchcode = commonDTO.getBranchCode();
        String system = tpsvcinfo.getSystem_name();
        String method = tpsvcinfo.getOperation_name();
        String eventno = commonDTO.getEventNo();
        String tx_intime = commonDTO.getSystemInTime();
        String tx_outtime = commonDTO.getSystemOutTime();
        String tx_interval = CommonUtil.Int2Str( CommonUtil.ItvSec(tx_intime,tx_outtime) );
        String tx_errorcode = tpsvcinfo.getErrorcode();
        String tx_level = tpsvcinfo.getLogic_level();
        String tpfq = tpsvcinfo.getTpfq();

        try {
            con = getConnection();
            pstmt = null;
            StringBuffer query = new StringBuffer();
            query.append("INSERT INTO TRANSACTION_INFO (                     ");
            query.append("                      orgseq          , ");
            query.append("                      hostseq         , ");
            query.append("                      branchcode      , ");
            query.append("                      system          , ");
            query.append("                      method          , ");
            query.append("                      eventno         , ");
            query.append("                      tx_intime       , ");
            query.append("                      tx_outtime      , ");
            query.append("                      tx_interval     , ");
            query.append("                      tx_errorcode    , ");
            query.append("                      tx_level        , ");
            query.append("                      tpfq            , ");
            query.append("                      STF_intime      , ");
            query.append("                      STF_outtime     , ");
            query.append("                      BTF_intime      , ");
            query.append("                      BTF_outtime     , ");
            query.append("                      ETF_intime      , ");
            query.append("                      ETF_outtime     ) ");
            query.append("VALUES (                                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?,                ");
            query.append("                      ?                 ");
            query.append("        )                               ");

            try
            {
                pstmt = con.prepareStatement(query.toString());
                int index = 0;
                pstmt.setString(++index, orgseq          );
                pstmt.setString(++index, hostseq         );
                pstmt.setString(++index, branchcode      );
                pstmt.setString(++index, system          );
                pstmt.setString(++index, method          );
                pstmt.setString(++index, eventno         );
                pstmt.setString(++index, tx_intime       );
                pstmt.setString(++index, tx_outtime      );
                pstmt.setString(++index, tx_interval     );
                pstmt.setString(++index, tx_errorcode    );
                pstmt.setString(++index, tx_level        );
                pstmt.setString(++index, tpfq            );
                pstmt.setString(++index, STF_intime      );
                pstmt.setString(++index, STF_outtime     );
                pstmt.setString(++index, BTF_intime      );
                pstmt.setString(++index, BTF_outtime     );
                pstmt.setString(++index, ETF_intime      );
                pstmt.setString(++index, ETF_outtime     );

                LOGEJ.getInstance().printf(1,(EPlatonEvent)event,"TRANSACTION_INFO Query : " + query.toString());

                pstmt.executeUpdate();
                con.commit();
            }
            finally
            {
              releaseResource(con,pstmt,null);
            }

        } catch (SQLException e) {
            logger.error("Error in TRANSACTION_INFO", e);
            LOGEJ.getInstance().eprintf(5,(EPlatonEvent)event,e);
            releaseResource(con,pstmt,null);
            throw new DAOException("Failed to insert transaction info", e);
        } catch (Exception e) {
            logger.error("Error in TRANSACTION_INFO", e);
            LOGEJ.getInstance().eprintf(5,(EPlatonEvent)event,e);
            releaseResource(con,pstmt,null);
            throw new DAOException("Failed to insert transaction info", e);
        }
        releaseResource(con,pstmt,null);

        return true;
    }

    /**
     * 자원을 반환한다
     * @param     Connection
     * @param     PreparedStatement
     * @param     ResultSet
     * @return    void
     */
    private void releaseResource(Connection con, PreparedStatement ps, ResultSet rs)
    {
        try{
            if(rs != null){ this.release(rs);}
            if(ps != null){ this.release(ps);}
            if(con != null){ this.release(con);}
        }catch(SQLException sqle){
            logger.error("Error releasing resources", sqle);
        }
    }

    public boolean isLogabled()
    {
        return Log.VDCLogger.isDebugEnabled();
    }
    
    public void releaseConnection(Connection con)
    {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.warn("Error closing connection", e);
            }
        }
    }
}





