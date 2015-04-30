package main.commands.impl;

import main.core.Container;
import main.core.DataStore;
import main.core.TransactionManager;

public class CommandRollback implements Command {

  public void execute(Container container) {
    TransactionManager txMgr = container.getTransactionMgr();
    DataStore rolledbackTx = txMgr.rollback();
    if (rolledbackTx == null) {
      System.out.println("NO TRANSACTION");
    } else {
      container.setDatastore(rolledbackTx);
      System.out.println();
    }
  }

}
