package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

public class CommandUnset implements Command {
  private String name;

  public CommandUnset(String name) {
    this.name = name;
  }

  public void execute(Container container) {
    DataStore current = container.getDatastore();
    TransactionManager txMgr = container.getTransactionMgr();
    String oldValue = current.getKeyValue(name);
    if (oldValue == null) {
      oldValue = txMgr.getMostRecentValueForKey(name);
    }
    if (oldValue != null) {
      Integer occurenceCount = getOccurenceCountFromAllTransactions(oldValue, container);
      --occurenceCount;
      current.setValuesCount(oldValue, occurenceCount);
    }
    current.unsetKey(name);
    System.out.println();
  }

  private Integer getOccurenceCountFromAllTransactions(String v, Container container) {
    Integer occurenceCount = container.getDatastore().getValuesCount(v);
    if (occurenceCount == null) {
      occurenceCount = container.getTransactionMgr().getOccurencesForValue(v);
    }
    return occurenceCount;
  }

}
