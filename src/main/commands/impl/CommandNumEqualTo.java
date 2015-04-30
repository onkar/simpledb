package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

/**
 * Class that handles NUMEQUALTO command
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandNumEqualTo implements Command {
  private String value;

  public CommandNumEqualTo(String value) {
    this.value = value;
  }

  public void execute(Container container) {
    DataStore current = container.getDatastore();
    TransactionManager txMgr = container.getTransactionMgr();
    // Get the current values count
    Integer count = current.getValuesCount(value);
    if (count == null) {
      // If count is null, try to get it from the transaction manager and update the current
      // datastore with that value.
      count = txMgr.getOccurencesForValue(value);
      current.setValuesCount(value, count);
    }
    System.out.println(count);
  }

}
