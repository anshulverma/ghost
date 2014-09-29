package com.mystique.ghost.core.model;

import java.util.Comparator;

/**
 * @author mystique
 */
public final class StrategicTreeNodeComparatorProvider {

  private StrategicTreeNodeComparatorProvider() { }

  public static Comparator<StrategicTreeNode> getComparator(final DifficultyLevel difficultyLevel) {
    return new Comparator<StrategicTreeNode>() {
      @Override
      public int compare(StrategicTreeNode node1, StrategicTreeNode node2) {
        return node1.getWinningProbability().compareTo(node2.getWinningProbability(), difficultyLevel);
      }
    };
  }
}
