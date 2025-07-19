package com.skax.eatool.framework.transaction.delegate.action;

import com.skax.eatool.framework.transfer.IEvent;

public abstract interface IEPlatonBizAction {

  // Methods
  void preAct(IEvent iEvent);
  IEvent act(IEvent iEvent) ;
  void postAct(IEvent iEvent);
}




