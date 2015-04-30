package main.core;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that handles transactions. It uses a deque to store data in previous transactions.
 * 
 * @author onkar.deshpande@gmail.com (Onkar Deshpande)
 *
 */
public class TransactionManager {
  private Deque<DataStore> previousTransactions;

  public TransactionManager() {
    this.previousTransactions = new LinkedList<DataStore>();
  }

  public DataStore begin(DataStore previousDataStore) {
    // A transaction has begun, add it to the deque
    previousTransactions.add(previousDataStore);
    return new DataStore();
  }

  public DataStore rollback() {
    if (previousTransactions.isEmpty()) {
      // If there is no outstanding transaction, there is nothing to rollback. Return null.
      return null;
    }
    // Remove the most recent transaction from deque
    return previousTransactions.removeLast();
  }

  public DataStore commit(DataStore lastTransaction) {
    if (previousTransactions.isEmpty()) {
      // If there is no transaction to commit, return null.
      return null;
    }
    // Transactions are always committed from oldest to the most recent transactions. So, get the
    // oldest transaction, iterate through remaining transactions one by one and resolve it with the
    // oldest transaction. Once the last transaction is resolved, return the oldest transaction. The
    // oldest transaction will now have resolved all outstanding transactions.
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
    // Iterate through the deque in reverse order (most recent to oldest). If the key is already
    // deleted, null. Otherwise, get the value for that key from transaction and return it.
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
    // Iterate through the deque in reverse order (most recent to oldest). Get the count for the
    // value from transaction. If found, return it otherwise return null.
    Iterator<DataStore> reverseIterator = previousTransactions.descendingIterator();
    while (reverseIterator.hasNext()) {
      DataStore tx = reverseIterator.next();
      Integer valueCount = tx.getValuesCount(value);
      if (valueCount != null)
        return valueCount;
    }
    return 0;
  }

  // Reinitialize the data for transactions
  public void removeOldTransactions() {
    previousTransactions = new LinkedList<DataStore>();
  }

}
