package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

public class CommandGet implements Command {
  private String name;
  private static final String nullResult = "NULL";

  public CommandGet(String name) {
    this.name = name;
  }

  public void execute(Container container) {
    DataStore dataStore = container.getDatastore();
    TransactionManager txMgr = container.getTransactionMgr();
    if (dataStore.isKeyDeleted(name)) {
      System.out.println(nullResult);
    } else {
      String value = dataStore.getKeyValue(name);
      if (value == null) {
        value = txMgr.getMostRecentValueForKey(name);
      }

      if (value == null) {
        System.out.println(nullResult);
      } else {
        System.out.println(value);
        dataStore.setKeyValue(name, value);
      }
    }
  }
}
