package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

public class CommandNumEqualTo implements Command {
  private String value;

  public CommandNumEqualTo(String value) {
    this.value = value;
  }

  public void execute(Container container) {
    DataStore current = container.getDatastore();
    TransactionManager txMgr = container.getTransactionMgr();
    Integer count = current.getValuesCount(value);
    if (count == null) {
      count = txMgr.getOccurencesForValue(value);
      current.setValuesCount(value, count);
    }
    System.out.println(count);
  }

}
