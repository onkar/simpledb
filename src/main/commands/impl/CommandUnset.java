package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

/**
 * Class that handles Unset command
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
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
      // If there is no value associated with the key, try to get it from the most recent
      // transaction.
      oldValue = txMgr.getMostRecentValueForKey(name);
    }
    if (oldValue != null) {
      // If we found a non-null value, get the occurrence count from all transactions and decrement
      // it. Update the key with this decremented value.
      Integer occurenceCount = getOccurenceCountFromAllTransactions(oldValue, container);
      --occurenceCount;
      current.setValuesCount(oldValue, occurenceCount);
    }
    // Unset the key
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
