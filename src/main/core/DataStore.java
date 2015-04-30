package main.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
  private Map<String, String> data;
  private Map<String, Integer> valuesCountMap;
  private List<String> entriesToBeDeleted;

  public DataStore() {
    this.data = new HashMap<String, String>();
    this.valuesCountMap = new HashMap<String, Integer>();
    this.entriesToBeDeleted = new ArrayList<String>();
  }

  public Map<String, String> getData() {
    return data;
  }

  public void setKeyValue(String key, String value) {
    if (entriesToBeDeleted.contains(key)) {
      entriesToBeDeleted.remove(key);
    }
    data.put(key, value);
  }

  public String getKeyValue(String key) {
    if (entriesToBeDeleted.contains(key)) {
      return null;
    }
    return data.get(key);
  }

  public boolean isKeyDeleted(String key) {
    return entriesToBeDeleted.contains(key);
  }

  public void unsetKey(String key) {
    entriesToBeDeleted.add(key);
    data.remove(key);
  }

  public Map<String, Integer> getValuesCountMap() {
    return valuesCountMap;
  }

  public void setValuesCountMap(Map<String, Integer> valuesCountMap) {
    this.valuesCountMap = valuesCountMap;
  }

  public Integer getValuesCount(String key) {
    return valuesCountMap.get(key);
  }

  public void setValuesCount(String key, Integer count) {
    valuesCountMap.put(key, count);
  }

  public List<String> getEntriesToBeDeleted() {
    return entriesToBeDeleted;
  }

  public void resolveTransaction(DataStore transaction) {
    // Resolve keys
    for (String key : transaction.getData().keySet()) {
      data.put(key, transaction.getKeyValue(key));
    }

    // Resolve deleted entries
    for (String deletedEntry : transaction.getEntriesToBeDeleted()) {
      data.remove(deletedEntry);
    }

    // Resolve values count
    for (String value : transaction.getValuesCountMap().keySet()) {
      valuesCountMap.put(value, transaction.getValuesCount(value));
    }
  }
}
