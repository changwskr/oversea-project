package com.skax.eatool.foundation.tpmservice;

/**
 * <p>Title: Spring-based TPM Service API Interface</p>
 * <p>Description: Converted from EJB to Spring Framework</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 2.0
 */
import com.skax.eatool.eplatonframework.transfer.*;

/**
 * Spring-based TPM Service API Interface
 * Transaction management is handled by Spring's @Transactional annotation
 */
public interface ITPMSVCAPI
{
  public int TPinfo() throws Exception;
  public boolean TPbegin(String second) throws Exception;
  public boolean TPcommit() throws Exception;
  public boolean TProllback() throws Exception;
  public EPlatonEvent TPSsend(EPlatonEvent event) throws Exception;
  public EPlatonEvent TPSrecv(EPlatonEvent event) throws Exception;

}
