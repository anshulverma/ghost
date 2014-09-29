package com.mystique.ghost.core.strategy;

import java.util.Map;
import java.util.Set;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.TreeNode;

/**
 * @author mystique
 */
public final class StrategicTreeNodeBuilder {
  private TreeNode node;
  private Set<StrategicTreeNode> children;
  private Map<DifficultyLevel, Double> winningProbabilityMap;
  private double averageHeight;

  public StrategicTreeNodeBuilder setTreeNode(TreeNode node) {
    this.node = node;
    return this;
  }

  public StrategicTreeNodeBuilder setChildren(Set<StrategicTreeNode> children) {
    this.children = children;
    return this;
  }

  public StrategicTreeNodeBuilder setWinningProbabilityMap(Map<DifficultyLevel, Double> winningProbabilityMap) {
    this.winningProbabilityMap = winningProbabilityMap;
    return this;
  }

  public StrategicTreeNodeBuilder setAverageHeight(double averageHeight) {
    this.averageHeight = averageHeight;
    return this;
  }

  public StrategicTreeNode build() {
    return new StrategicTreeNode(node.getValue(), new WinningProbability(winningProbabilityMap, averageHeight), children);
  }
}
