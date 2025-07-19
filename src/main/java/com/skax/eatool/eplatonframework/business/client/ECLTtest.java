package com.skax.eatool.eplatonframework.business.client;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import com.skax.eatool.eplatonframework.transfer.*;
import com.skax.eatool.foundation.tpmservice.TPCsendrecv;
import com.skax.eatool.foundation.utility.CommonUtil;
import com.skax.eatool.foundation.utility.Reflector;
import com.skax.eatool.cashCard.transfer.AccountQueryCDTO;

public class ECLTtest {
  public TPCsendrecv tpcsendrecv;

  public ECLTtest() throws Exception {
    this.tpcsendrecv = new TPCsendrecv("21.101.3.49", "7001");
    // Note: This is a test client that may run outside Spring context
    // TPCsendrecv will handle the case when Spring services are not available
  }

  public EPlatonEvent doSTF(EPlatonEvent event) {
    EPlatonCommonDTO commonDTO = null;
    TPSVCINFODTO tpsvcinfo = null;

    try {
      commonDTO = (EPlatonCommonDTO) event.getCommon();
      tpsvcinfo = event.getTPSVCINFODTO();

      commonDTO.setBankCode("11");
      commonDTO.setChannelType("03");
      commonDTO.setBranchCode("99");
      commonDTO.setEventNo("000010000");
      commonDTO.setSystemDate("20031031");
      commonDTO.setUserID("wsjang");
      commonDTO.setgetIPAddress("172.21.111.110");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.toString());
      tpsvcinfo.setErrorcode("ETPC001");
      tpsvcinfo.setError_message("TPCsendrecv error");
    } finally {
      return event;
    }
  }

  public EPlatonEvent execute(EPlatonEvent event) throws Exception {
    EPlatonCommonDTO commonDTO = null;
    TPSVCINFODTO tpsvcinfo = null;
    EPlatonEvent resevent = null;
    try {
      commonDTO = (EPlatonCommonDTO) event.getCommon();
      tpsvcinfo = event.getTPSVCINFODTO();

      resevent = doSTF(event);
      System.out.println("SEND-" + Reflector.objectToString(resevent));

      switch (resevent.getTPSVCINFODTO().getErrorcode().charAt(0)) {
        case 'E':
          break;
        case 'I':
          resevent = (EPlatonEvent) tpcsendrecv.callEJB("cashCard-listCashCard", "30", event);
          break;
      }

      resevent = doETF(resevent);
    } catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage());
    }

    return resevent;
  }

  public EPlatonEvent doETF(EPlatonEvent event) {
    try {
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.toString());
    }
    return event;
  }

  public static void main(String args[]) throws Exception {
    try {

      int cnt = 0;
      while (true) {
        cnt++;
        if (cnt == CommonUtil.Str2Int(args[0]))
          break;
        System.out.println("----------------------------------거래를 시작합니다[" + cnt + "]");
        System.out.println("--기본정보를 설정합니다");
        try {
          AccountQueryCDTO acdto = new AccountQueryCDTO();
          acdto.setAccountNumber("0001100100000048");
          acdto.setBankCode("03");

          System.out.println("--------EJB 호출시작");
          EPlatonEvent event = new EPlatonEvent();
          event.setRequest(acdto);
          ECLTtest clt = new ECLTtest();
          event = clt.doSTF(event);
          event = clt.execute(event);
          System.out.println("--------EJB 호출종료");

          if (event.getTPSVCINFODTO().getErrorcode().equals("IZZ000")) {
            System.out.println("거래가 성공으로 종료되었습니다." + event.getTPSVCINFODTO().getErrorcode());
            AccountQueryCDTO result = (AccountQueryCDTO) event.getResponse();
            System.out.println("************************************************");
            System.out.println("***AccountQueryCDTO***" + result.toString());
            System.out.println("--txno:" + event.getTPSVCINFODTO().getHostseq());
            System.out.println("--amount:" + acdto.getBalance());
            System.out.println("************************************************");
          } else
            System.out.println("거래호출중 오류가 발생했습니다.");

          System.out.println("--기본정보를 정리합니다. 성공/실패 무관");
          System.out.println("----------------------------------거래를 종료합니다.");
        } catch (Exception ex) {
          ex.printStackTrace();
        }

      }

      return;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
