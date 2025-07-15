package com.skcc.oversea.foundation.config;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.net.*;
import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skcc.oversea.foundation.base.*;

/**
 * ?ъ슜??
 * XMLconfig.getInstance().GetEnvValue("SERVICE","MAX_TIME_OUT")
 * XMLconfig.getInstance().GetEnvValue("SERVICE","SERVICE01","MAX_THR_CNT")
 * ?뚯씪?? *
 <ENVIRONMENT>
   <SERVICE>
                <SERVICECNT>1</SERVICECNT>
                <MAX_TIME_OUT>305000</MAX_TIME_OUT>
                <SERVICE01>
                        <PORT>45530</PORT>
                        <MAX_THR_CNT>20</MAX_THR_CNT>
                        <LOG>/home/cosif/atmppp/log/ATMserver.45530.out</LOG>
                        <BOOTINFO>M</BOOTINFO>
                </SERVICE01>
                <SERVICE02>
                        <PORT>45531</PORT>
                        <MAX_THR_CNT>2</MAX_THR_CNT>
                        <LOG>/home/cosif/atmppp/log/ATMserver.45530.out</LOG>
                        <BOOTINFO>M</BOOTINFO>
                </SERVICE02>
                <SERVICE03>
                        <PORT>45532</PORT>
                        <MAX_THR_CNT>2</MAX_THR_CNT>
                        <LOG>/home/cosif/atmppp/log/ATMserver.45530.out</LOG>
                        <BOOTINFO>M</BOOTINFO>
                </SERVICE03>
        </SERVICE>
 </ENVIRONMENT>
 *
 */

@Component
public class XMLconfig {
  private static XMLconfig instance;
  private static String xmlfile = "conf.file";
  
  @Autowired
  private Config config;

  public static synchronized XMLconfig getInstance() {
    if (instance == null) {
      try{
        instance = new XMLconfig();
        XMLconfig.xmlfile = System.getProperty(XMLconfig.xmlfile);
        if( XMLconfig.xmlfile == null )
          XMLconfig.xmlfile = "classpath:config/oversea-config.xml" ;
        }catch(Exception igex){}
    }
    return instance;
  }

  /**
   * XMLconfig.xmlfile = xml?섍꼍 ?뚯씪紐?   *
   * @param ele1 - level 1媛?   * @param ele2 - level 2媛?   * @return
   */
  public String GetEnvValue(String ele1,String ele2){
    if (config != null) {
      return config.getValue(ele1, ele2);
    }
    // fallback for static instance calls
    return System.getProperty(ele1 + "." + ele2, "");
  }

  /**
   * XMLconfig.xmlfile = xml?섍꼍 ?뚯씪紐?   *
   * @param ele1 - level 1媛?   * @param ele2 - level 2媛?   * @param ele3 - level 3媛?   * @return
   */

  public String GetEnvValue(String ele1,String ele2,String ele3){
    if (config != null) {
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
    return System.getProperty(ele1 + "." + ele2 + "." + ele3, "");
  }

  /**
   * XMLconfig.xmlfile = xml?섍꼍 ?뚯씪紐?   * @param ele1
   * @param ele2
   * @param ele3
   * @param ele4
   * @return
   */
  public String GetEnvValue(String ele1,String ele2,String ele3,String ele4){
    if (config != null) {
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
    return System.getProperty(ele1 + "." + ele2 + "." + ele3 + "." + ele4, "");
  }


  public XMLconfig() {
  }

}





