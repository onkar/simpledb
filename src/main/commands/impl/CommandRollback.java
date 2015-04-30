package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

/**
 * Class that handles rollback command.
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandRollback implements Command {

  public void execute(Container container) {
    TransactionManager txMgr = container.getTransactionMgr();
    DataStore rolledbackTx = txMgr.rollback();

    if (rolledbackTx == null) {
      // If there is no transaction to rollback, print NO TRANSACTION
      System.out.println("NO TRANSACTION");
    } else {
      // Else set container's datastore with rolled back transaction
      container.setDatastore(rolledbackTx);
      System.out.println();
    }
  }

}
