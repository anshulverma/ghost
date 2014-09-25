package com.mystique.ghost.core.strategy;

import java.util.Set;
import com.mystique.ghost.core.model.TreeNode;
import com.mystique.ghost.core.model.WordTree;

/**
 * @author mystique
 */
public class GameStrategyBuilder {
  private final WordTree wordTree;

  public GameStrategyBuilder(WordTree wordTree) {
    this.wordTree = wordTree;
  }

  public WordTree build() {
    TreeNode node = wordTree.getRootNode();
    assignWinningProbability(node);
    return wordTree;
  }

  private void assignWinningProbability(TreeNode node) {
    winningProbability(node, 0);
  }

  private double winningProbability(TreeNode node, int depth) {
    if (node.isLeaf()) {
      return 0;
    }
    double weightedProbability = calculatedWeightedAverage(node.getChildren(), depth + 1);
    return 0;
  }

  private double calculatedWeightedAverage(Set<TreeNode> nodes, int depth) {
    double total = 0;
    for (TreeNode node : nodes) {
      total += winningProbability(node, depth);
    }
    return 0;
  }
}
