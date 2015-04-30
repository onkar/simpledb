package main.core;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class TransactionManager {
  private Deque<DataStore> previousTransactions;

  public TransactionManager() {
    this.previousTransactions = new LinkedList<DataStore>();
  }

  public DataStore begin(DataStore previousDataStore) {
    previousTransactions.add(previousDataStore);
    return new DataStore();
  }

  public DataStore rollback() {
    if (previousTransactions.isEmpty())
      return null;
    return previousTransactions.removeLast();
  }

  public DataStore commit(DataStore lastTransaction) {
    if (previousTransactions.isEmpty())
      return null;
    DataStore oldestTransaction = previousTransactions.getFirst();
    Iterator<DataStore> it = previousTransactions.iterator();
    while (it.hasNext()) {
      DataStore transactionToBeResolved = it.next();
      oldestTransaction.resolveTransaction(transactionToBeResolved);
    }
    oldestTransaction.resolveTransaction(lastTransaction);
    return oldestTransaction;
  }

  public String getMostRecentValueForKey(String key) {
    Iterator<DataStore> reverseIterator = previousTransactions.descendingIterator();
    while (reverseIterator.hasNext()) {
      DataStore tx = reverseIterator.next();
      if (tx.isKeyDeleted(key))
        return null;
      else {
        String value = tx.getKeyValue(key);
        if (value != null)
          return value;
      }
    }
    return null;
  }

  public Integer getOccurencesForValue(String value) {
    Iterator<DataStore> reverseIterator = previousTransactions.descendingIterator();
    while (reverseIterator.hasNext()) {
      DataStore tx = reverseIterator.next();
      Integer valueCount = tx.getValuesCount(value);
      if (valueCount != null)
        return valueCount;
    }
    return 0;
  }

  public void removeOldTransactions() {
    previousTransactions = new LinkedList<DataStore>();
  }

}
