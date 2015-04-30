package main.core;

/**
 * A container class that holds actual data store in database and transaction manager
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class Container {
  private DataStore dataStore;
  private TransactionManager transactionMgr;

  public Container() {
    this.dataStore = new DataStore();
    this.transactionMgr = new TransactionManager();
  }

  public DataStore getDatastore() {
    return this.dataStore;
  }

  public void setDatastore(DataStore dataStore) {
    this.dataStore = dataStore;
  }

  public TransactionManager getTransactionMgr() {
    return this.transactionMgr;
  }

  public void updateToNewTransaction() {
    dataStore = transactionMgr.begin(dataStore);
  }

}
