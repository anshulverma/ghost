package com.mystique.ghost.core.model;

import java.util.Set;
import com.mystique.ghost.core.NoSuchWordException;

/**
 * @author mystique
 */
public class StrategicTreeNode implements Comparable<StrategicTreeNode> {
  private final Character value;
  private final double winningProbability;
  private final SortedHashMap<Character, StrategicTreeNode> children;

  public StrategicTreeNode(Character value, double winningProbability, Set<StrategicTreeNode> childrenNodes) {
    this.value = value;
    this.winningProbability = winningProbability;

    children = new SortedHashMap<>();
    for (StrategicTreeNode node : childrenNodes) {
      children.put(node.value, node);
    }
  }

  public double getWinningProbability() {
    return winningProbability;
  }

  public StrategicTreeNode getChild(Character character) throws NoSuchWordException {
    if (children.isEmpty() || !children.contains(character)) {
      throw new NoSuchWordException("requested word prefix does not exist");
    }
    return children.get(character);
  }

  public Character getValue() {
    return value;
  }

  public StrategicTreeNode getMostProbableChild() {
    return children.getFirst();
  }

  @Override
  public int compareTo(StrategicTreeNode other) {
    if (winningProbability > other.winningProbability) {
      return 1;
    }
    return -1;
  }

  public boolean isLeaf() {
    return children.isEmpty();
  }
}
