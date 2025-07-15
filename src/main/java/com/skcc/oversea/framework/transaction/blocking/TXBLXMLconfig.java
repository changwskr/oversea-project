package com.skcc.oversea.framework.transaction.blocking;


import java.net.*;
import java.io.*;
import java.util.*;
import com.skcc.oversea.foundation.base.*;

/**
 * 사용예]
 * TXBLXMLconfig.getInstance().GetEnvValue("SERVICE","MAX_TIME_OUT")
 * TXBLXMLconfig.getInstance().GetEnvValue("SERVICE","SERVICE01","MAX_THR_CNT")
 * 파일예
 *
<ENVIRONMENT>

        <TRANSACTION-BLOCKING>

                <TXBLOCKCNT>5</TXBLOCKCNT>
                <TPFQ>
                        <EMODE>OFF</EMODE>
                        <EVNTS1>100</EVNTS1>
                        <EVNTE1>100</EVNTE1>
                        <EVNTS2>200</EVNTS2>
                        <EVNTE2>200</EVNTE2>
                        <EVNTS3>300</EVNTS3>
                        <EVNTE3>300</EVNTE3>
                        <EVNTS4>*</EVNTS4>
                        <EVNTE4>*</EVNTE4>
                        <EVNTS5>*</EVNTS5>
                        <EVNTE5>*</EVNTE5>
                </TPFQ>
                <TXCODE>
                        <EMODE>OFF</EMODE>
                        <EVNTS1>80001</EVNTS1>
                        <EVNTE1>80002</EVNTE1>
                        <EVNTS2>80005</EVNTS2>
                        <EVNTE2>80005</EVNTE2>
                        <EVNTS3>*</EVNTS3>
                        <EVNTE3>*</EVNTE3>
                        <EVNTS4>*</EVNTS4>
                        <EVNTE4>*</EVNTE4>
                        <EVNTS5>*</EVNTS5>
                        <EVNTE5>*</EVNTE5>
                </TXCODE>
                <TELLER>
                        <TMODE>OFF</TMODE>
                        <TELLS>NotUse</TELLS>
                        <TELLE>NotUse</TELLE>
                        <TELLS1>0238001</TELLS1>
                        <TELLE1>0238001</TELLE1>
                        <TELLS2>*</TELLS2>
                        <TELLE2>*</TELLE2>
                        <TELLS3>*</TELLS3>
                        <TELLE3>*</TELLE3>
                        <TELLS4>*</TELLS4>
                        <TELLE4>*</TELLE4>
                        <TELLS5>*</TELLS5>
                        <TELLE5>*</TELLE5>
                </TELLER>
                <BRANCH>
                        <BMODE>OFF</BMODE>
                        <BRCHS>NotUse</BRCHS>
                        <BRCHE>NotUse</BRCHE>
                        <BRCHS1>*</BRCHS1>
                        <BRCHE1>*</BRCHE1>
                        <BRCHS2>*</BRCHS2>
                        <BRCHE2>*</BRCHE2>
                        <BRCHS3>*</BRCHS3>
                        <BRCHE3>*</BRCHE3>
                        <BRCHS4>*</BRCHS4>
                        <BRCHE4>*</BRCHE4>
                        <BRCHS5>*</BRCHS5>
                        <BRCHE5>*</BRCHE5>
                </BRANCH>

        </TRANSACTION-BLOCKING>
</ENVIRONMENT>



 *
 */

import java.io.*;
import java.util.*;
import java.text.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skcc.oversea.foundation.utility.CommonUtil;
import com.skcc.oversea.foundation.utility.FILEapi;
import com.skcc.oversea.eplatonframework.transfer.EPlatonEvent;
import com.skcc.oversea.framework.transaction.constant.TCFConstants;
import com.skcc.oversea.foundation.config.Config;

@Component
public class TXBLXMLconfig
{
  private static TXBLXMLconfig instance;
  public static long otxctlmode=0;
  public static long ntxctlmode=0;
  public static String xml_file_name=TCFConstants.TX_BLOCKING_CONFIG_FILE_NAME ;
  public static Hashtable ht = new Hashtable();
  public EPlatonEvent in;
  
  @Autowired
  private Config config;

  public static synchronized TXBLXMLconfig getInstance() {
    if (instance == null) {
      try{
        instance = new TXBLXMLconfig();
      }
      catch(Exception igex){
        igex.printStackTrace();
        System.out.println(igex);
      }
    }
    return instance;
  }

  public void setInBound(EPlatonEvent in){
     this.in = in;
  }

  public TXBLXMLconfig() throws IOException {
    try{
      ht.put("TXBLOCKCNT",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT"));

      ht.put("TMODE",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TELLER","TMODE"));
      for ( int ii=1 ; ii <= CommonUtil.Str2Int(TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT")) ; ii++ )
      {
        ht.put("TELLS"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TELLER","TELLS"+ii));
        ht.put("TELLE"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TELLER","TELLE"+ii));
        System.out.println((String)ht.get("TELLS"+ii));
        System.out.println((String)ht.get("TELLE"+ii));
      }

      ht.put("EMODE",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXCODE","EMODE"));
      for ( int ii=1 ; ii <= CommonUtil.Str2Int(TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT")) ; ii++ )
      {
        ht.put("EVNTS"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXCODE","EVNTS"+ii));
        ht.put("EVNTE"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXCODE","EVNTE"+ii));
        System.out.println((String)ht.get("EVNTS"+ii));
        System.out.println((String)ht.get("EVNTE"+ii));
      }

      ht.put("BMODE",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","BRANCH","BMODE"));
      for ( int ii=1 ; ii <= CommonUtil.Str2Int(TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT")) ; ii++ )
      {
        ht.put("BRCHS"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","BRANCH","BRCHS"+ii));
        ht.put("BRCHE"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","BRANCH","BRCHE"+ii));
        System.out.println((String)ht.get("BRCHS"+ii));
        System.out.println((String)ht.get("BRCHE"+ii));
      }

    }
    catch(Exception ex){}
  }

  public boolean changeFileMode()
  {
    ntxctlmode = FILEapi.FILElastmodified(TXBLXMLconfig.xml_file_name);
    if( ntxctlmode != otxctlmode )
    {
      return true;
    }
    return false;
  }

  public void updateTxCtlInfo() throws IOException
  {
    int loopcnt=0;

    if( changeFileMode() )
    {
      System.out.println("JBBCONF.INI 파일속성이 변경");

      try{
        ht.put("TXBLOCKCNT",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT"));

        ht.put("TMODE",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TELLER","TMODE"));
        for ( int ii=1 ; ii <= CommonUtil.Str2Int(TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT")) ; ii++ )
        {
          ht.put("TELLS"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TELLER","TELLS"+ii));
          ht.put("TELLE"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TELLER","TELLE"+ii));
        }

        ht.put("EMODE",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXCODE","EMODE"));
        for ( int ii=1 ; ii <= CommonUtil.Str2Int(TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT")) ; ii++ )
        {
          ht.put("EVNTS"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXCODE","EVNTS"+ii));
          ht.put("EVNTE"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXCODE","EVNTE"+ii));
        }

        ht.put("BMODE",TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","BRANCH","BMODE"));
        for ( int ii=1 ; ii <= CommonUtil.Str2Int(TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","TXBLOCKCNT")) ; ii++ ){
          ht.put("BRCHS"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","BRANCH","BRCHS"+ii));
          ht.put("BRCHE"+ii,TXBLXMLconfig.getInstance().GetEnvValue("TRANSACTION-BLOCKING","BRANCH","BRCHE"+ii));
        }
        otxctlmode = FILEapi.FILElastmodified(TXBLXMLconfig.xml_file_name);
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  public boolean isBlockingTx(String tellerid,String eventno,String branch) throws IOException
  {

    updateTxCtlInfo();

    if( (ht.get("TMODE").toString().substring(0,2) ).equals("ON") )
    {
      for( int ii=1 ; ii <= CommonUtil.Str2Int((String)ht.get("TXBLOCKCNT")); ii++ )
      {
        System.out.println("TELLS"+ii+":"+ht.get("TELLS"+ii));
        System.out.println("TELLE"+ii+":"+ht.get("TELLE"+ii));

        if( ((String)ht.get("TELLS"+ii)).equals("*") ||  ht.get("TELLS"+ii)==null )
          continue;
        int st = CommonUtil.Str2Int((String)ht.get("TELLS"+ii));
        int et = CommonUtil.Str2Int((String)ht.get("TELLE"+ii));
        int ct = CommonUtil.Str2Int(tellerid.substring(3,10));

        System.out.println("CurrentTellerid:"+CommonUtil.Str2Int(tellerid.substring(3,10)));
        System.out.println("TMODE:"+ht.get("TMODE"));
        System.out.println("TELLS"+ii+":"+ht.get("TELLS"+ii));
        System.out.println("TELLE"+ii+":"+ht.get("TELLE"+ii));

        if( ct >= st && ct <= et )
        {
          return true;
        }
      }
    }
    else if( (ht.get("EMODE").toString().substring(0,2)).equals("ON") )
    {
      for( int ii=1 ; ii <= CommonUtil.Str2Int((String)ht.get("TXBLOCKCNT")); ii++ )
      {
        System.out.println("EVNTS"+ii+":"+ht.get("EVNTS"+ii));
        System.out.println("EVNTE"+ii+":"+ht.get("EVNTE"+ii));

        if( ((String)ht.get("EVNTS"+ii)).equals("*") ||  ht.get("EVNTS"+ii)==null )
          continue;
        int st = CommonUtil.Str2Int((String)ht.get("EVNTS"+ii));
        int et = CommonUtil.Str2Int((String)ht.get("EVNTE"+ii));
        int ct = CommonUtil.Str2Int(eventno);

        System.out.println("CurrentEvent:"+eventno);
        System.out.println("EMODE:"+ht.get("EMODE"));
        System.out.println("EVNTS"+ii+":"+ht.get("EVNTS"+ii));
        System.out.println("EVNTE"+ii+":"+ht.get("EVNTE"+ii));

        if( ct >= st && ct <= et )
        {
          System.out.println("CurrentEvent:"+eventno);
          System.out.println("EMODE:"+ht.get("EMODE"));
          System.out.println("EVNTS"+ii+":"+ht.get("EVNTS"+ii));
          System.out.println("EVNTE"+ii+":"+ht.get("EVNTE"+ii));
          return true;
        }
      }
    }
    else if( (ht.get("BMODE").toString().substring(0,2)).equals("ON") )
    {
      for( int ii=1 ; ii <= CommonUtil.Str2Int((String)ht.get("TXBLOCKCNT")); ii++ )
      {
        System.out.println("BRCHS"+ii+":"+ht.get("BRCHS"+ii));
        System.out.println("BRCHE"+ii+":"+ht.get("BRCHE"+ii));

        if( ((String)ht.get("BRCHS"+ii)).equals("*") ||  ht.get("BRCHS"+ii)==null )
          continue;
        int st = CommonUtil.Str2Int((String)ht.get("BRCHS"+ii));
        int et = CommonUtil.Str2Int((String)ht.get("BRCHE"+ii));
        int ct = CommonUtil.Str2Int(branch);

        System.out.println("Currentbranch:"+CommonUtil.Str2Int(branch));
        System.out.println("BMODE:"+ht.get("BMODE"));
        System.out.println("BRCHS"+ii+":"+ht.get("BRCHS"+ii));
        System.out.println("BRCHE"+ii+":"+ht.get("BRCHE"+ii));

        if( ct >= st && ct <= et )
        {
          return true;
        }
      }
    }
    return false;
  }


  /**
   * Spring Config를 통한 XML 설정값 조회
   *
   * @param ele1 - level 1값
   * @param ele2 - level 2값
   * @return
   */
  public String GetEnvValue(String ele1,String ele2){
    if (config != null) {
      return config.getValue(ele1, ele2);
    }
    // fallback for static instance calls
    return getStaticConfigValue(ele1, ele2);
  }
  
  /**
   * Static method for backward compatibility
   */
  private static String getStaticConfigValue(String ele1, String ele2) {
    // Return default values or handle configuration through Spring Environment
    return System.getProperty(ele1 + "." + ele2, "");
  }

  /**
   * Spring Config를 통한 XML 설정값 조회 (3-level)
   *
   * @param ele1 - level 1값
   * @param ele2 - level 2값
   * @param ele3 - level 3값
   * @return
   */
  public String GetEnvValue(String ele1,String ele2,String ele3){
    if (config != null) {
      // Try to get nested configuration value
      try {
        Config.ConfigElement configElement = config.getElement(ele1);
        if (configElement != null && configElement.getElement() != null) {
          org.w3c.dom.Element element = configElement.getElement();
          org.w3c.dom.NodeList childNodes = element.getElementsByTagName(ele2);
          if (childNodes.getLength() > 0) {
            org.w3c.dom.Element child = (org.w3c.dom.Element) childNodes.item(0);
            org.w3c.dom.NodeList grandChildNodes = child.getElementsByTagName(ele3);
            if (grandChildNodes.getLength() > 0) {
              return grandChildNodes.item(0).getTextContent().trim();
            }
          }
        }
      } catch (Exception e) {
        System.out.println("Failed to get config value: " + ele1 + "." + ele2 + "." + ele3);
  }
    }
    // fallback
    return getStaticConfigValue(ele1, ele2, ele3);
  }
  
  private static String getStaticConfigValue(String ele1, String ele2, String ele3) {
    return System.getProperty(ele1 + "." + ele2 + "." + ele3, "");
  }

  /**
   * Spring Config를 통한 XML 설정값 조회 (4-level)
   * @param ele1
   * @param ele2
   * @param ele3
   * @param ele4
   * @return
   */
  public String GetEnvValue(String ele1,String ele2,String ele3,String ele4){
    if (config != null) {
      // Try to get deeply nested configuration value
      try {
        Config.ConfigElement configElement = config.getElement(ele1);
        if (configElement != null && configElement.getElement() != null) {
          org.w3c.dom.Element element = configElement.getElement();
          org.w3c.dom.NodeList level2Nodes = element.getElementsByTagName(ele2);
          if (level2Nodes.getLength() > 0) {
            org.w3c.dom.Element level2 = (org.w3c.dom.Element) level2Nodes.item(0);
            org.w3c.dom.NodeList level3Nodes = level2.getElementsByTagName(ele3);
            if (level3Nodes.getLength() > 0) {
              org.w3c.dom.Element level3 = (org.w3c.dom.Element) level3Nodes.item(0);
              org.w3c.dom.NodeList level4Nodes = level3.getElementsByTagName(ele4);
              if (level4Nodes.getLength() > 0) {
                return level4Nodes.item(0).getTextContent().trim();
              }
            }
          }
        }
      } catch (Exception e) {
        System.out.println("Failed to get config value: " + ele1 + "." + ele2 + "." + ele3 + "." + ele4);
      }
    }
    // fallback
    return System.getProperty(ele1 + "." + ele2 + "." + ele3 + "." + ele4, "");
  }


}


