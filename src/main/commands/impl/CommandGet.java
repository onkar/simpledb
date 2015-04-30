package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

/**
 * Command that handles get operation
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
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
      // If the key is already deleted, print NULL
      System.out.println(nullResult);
    } else {
      String value = dataStore.getKeyValue(name);
      if (value == null) {
        // If current value for the key is null, try to get the value from the most recent
        // transaction.
        value = txMgr.getMostRecentValueForKey(name);
      }

      if (value == null) {
        // If still no value is found, print NULL
        System.out.println(nullResult);
      } else {
        // Otherwise, print the value and set the key-value in the cache.
        System.out.println(value);
        dataStore.setKeyValue(name, value);
      }
    }
  }
}
