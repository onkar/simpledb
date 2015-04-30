package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

/**
 * Command that handles commit of a transaction
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class CommandCommit implements Command {

  public void execute(Container container) {
    DataStore dataStore = container.getDatastore();
    TransactionManager txMgr = container.getTransactionMgr();
    DataStore mergedTx = txMgr.commit(dataStore);
    if (mergedTx == null) {
      System.out.println("NO TRANSACTION");
    } else {
      container.setDatastore(mergedTx);
      txMgr.removeOldTransactions();
      System.out.println();
    }
  }

}
