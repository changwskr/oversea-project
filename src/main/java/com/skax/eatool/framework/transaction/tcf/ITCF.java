package com.skax.eatool.framework.transaction.tcf;

import org.springframework.stereotype.Component;

@Component
public interface ITCF {

  void initialize();

  void cleanup();

  boolean isAvailable();

}
