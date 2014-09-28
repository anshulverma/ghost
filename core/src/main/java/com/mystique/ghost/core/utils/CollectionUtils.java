package com.mystique.ghost.core.utils;

import java.util.Collection;

/**
 * @author mystique
 */
public final class CollectionUtils {

  private CollectionUtils() { }

  public static <T> T aggregate(Collection<T> items, Aggregator<T> aggregator) {
    T aggregatedValue = null;
    for (T item : items) {
      if (aggregatedValue != null) {
        aggregatedValue = aggregator.apply(aggregatedValue, item);
      } else {
        aggregatedValue = item;
      }
    }
    return aggregatedValue;
  }

  public static <T> boolean isEmpty(Collection<T> collection) {
    return collection == null || collection.isEmpty();
  }

  public static interface Aggregator<T> {
    T apply(T item1, T item2);
  }
}
