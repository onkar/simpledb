package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

/**
 * Command that handles Set operation
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandSet implements Command {
  private String name;
  private String value;

  public CommandSet(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public void execute(Container container) {
    DataStore current = container.getDatastore();
    TransactionManager txMgr = container.getTransactionMgr();

    if (!current.isKeyDeleted(name)) {
      String oldValue = current.getKeyValue(name);
      if (oldValue == null) {
        oldValue = txMgr.getMostRecentValueForKey(name);
      }
      if (oldValue != null) {
        int occurenceCount = getOccurenceCountFromAllTransactions(oldValue, container);
        --occurenceCount;
        current.setValuesCount(oldValue, occurenceCount);
      }
    }

    Integer occurences = getOccurenceCountFromAllTransactions(value, container);
    current.setValuesCount(value, occurences + 1);

    current.setKeyValue(name, value);
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
