package com.mystique.ghost.core.model;

import java.util.Collection;
import java.util.Set;
import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.strategy.selector.NodeSelector;
import com.mystique.ghost.core.strategy.WinningProbability;
import com.mystique.ghost.core.utils.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author mystique
 */
public class StrategicTreeNode {
  private final Character value;
  private final SortedChildrenMap children;
  private final WinningProbability winningProbability;

  public StrategicTreeNode(Character value, WinningProbability winningProbability, Set<StrategicTreeNode> childrenNodes) {
    this.value = value;
    this.winningProbability = winningProbability;

    children = new SortedChildrenMap();
    for (StrategicTreeNode node : childrenNodes) {
      children.put(node.value, node);
    }
  }

  public WinningProbability getWinningProbability() {
    return winningProbability;
  }

  public StrategicTreeNode getChild(Character character) throws NoSuchWordException {
    if (children.isEmpty() || !children.contains(character)) {
      throw new NoSuchWordException("requested word prefix does not exist");
    }
    return children.get(character);
  }

  public StrategicTreeNode getChild(NodeSelector selector) {
    Collection<StrategicTreeNode> filtered = selector.apply(children.getValues(selector.getDifficultyLevel()));
    return CollectionUtils.selectRandom(filtered);
  }

  public Character getValue() {
    return value;
  }

  public StrategicTreeNode getMostProbableChild(DifficultyLevel difficultyLevel) {
    return children.getFirst(difficultyLevel);
  }

  public boolean isLeaf() {
    return children.isEmpty();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE)
      .append("value", value)
      .append("children", children)
      .append("winningProbability", winningProbability)
      .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(value)
      .append(children)
      .append(winningProbability)
      .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof StrategicTreeNode)) {
      return false;
    }

    StrategicTreeNode other = (StrategicTreeNode) obj;
    return new EqualsBuilder()
      .append(value, other.value)
      .append(children, other.children)
      .append(winningProbability, other.winningProbability)
      .isEquals();
  }
}
