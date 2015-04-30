package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

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
