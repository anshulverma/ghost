package com.mystique.ghost.core.model;

import java.util.Map;
import java.util.TreeSet;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author mystique
 */
public class SortedChildrenMap {

  private Map<Character, StrategicTreeNode> map = Maps.newHashMap();
  private Map<DifficultyLevel, TreeSet<StrategicTreeNode>> valueMap = Maps.newHashMap();

  public void put(Character key, StrategicTreeNode value) {
    map.put(key, value);
    for (DifficultyLevel difficultyLevel : DifficultyLevel.values()) {
      TreeSet<StrategicTreeNode> valueSet = valueMap.get(difficultyLevel);
      if (valueSet == null) {
        valueSet = Sets.newTreeSet(StrategicTreeNodeComparatorProvider.getComparator(difficultyLevel));
      }
      valueSet.add(value);
      valueMap.put(difficultyLevel, valueSet);
    }
  }

  public boolean contains(Character ch) {
    return map.containsKey(ch);
  }

  public StrategicTreeNode get(Character ch) {
    return map.get(ch);
  }

  @Nullable
  public StrategicTreeNode getFirst(DifficultyLevel difficultyLevel) {
    TreeSet<StrategicTreeNode> valueSet = valueMap.get(difficultyLevel);
    if (valueSet == null) {
      return null;
    }
    return valueSet.first();
  }

  @Nullable
  public TreeSet<StrategicTreeNode> getValues(DifficultyLevel difficultyLevel) {
    return valueMap.get(difficultyLevel);
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public void removeAll() {
    map = null;
    valueMap = null;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE)
        .append(map)
        .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(map)
        .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof SortedChildrenMap)) {
      return false;
    }

    SortedChildrenMap other = (SortedChildrenMap) obj;
    return new EqualsBuilder()
        .append(map, other.map)
        .isEquals();
  }
}
