package com.mystique.ghost.core.utils;

import java.util.Collection;
import java.util.Iterator;
import com.mystique.ghost.core.model.StrategicTreeNode;

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

  public static StrategicTreeNode selectRandom(Collection<StrategicTreeNode> collection) {
    if (isEmpty(collection)) {
      throw new IllegalArgumentException("cannot select random from a empty collection");
    }
    int index = MathUtils.randomInt(collection.size());
    Iterator<StrategicTreeNode> iterator = collection.iterator();
    while (index-- > 0) {
      iterator.next();
    }
    return iterator.next();
  }

  public static interface Aggregator<T> {
    T apply(T item1, T item2);
  }
}
