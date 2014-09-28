package com.mystique.ghost.core.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @author mystique
 */
public class SortedHashMap<K, V extends Comparable> {

  private final Map<K, V> map = Maps.newHashMap();
  private final TreeSet<V> values = Sets.newTreeSet();

  public void put(K key, V value) {
    map.put(key, value);
    values.add(value);
  }

  public boolean contains(K key) {
    return map.containsKey(key);
  }

  public V get(K key) {
    return map.get(key);
  }

  public V getFirst() {
    return values.first();
  }

  public Set<V> getValues() {
    return values;
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public void removeAll() {
    for (Map.Entry<K, V> entry : map.entrySet()) {
      map.remove(entry.getKey());
      values.remove(entry.getValue());
    }
  }
}
