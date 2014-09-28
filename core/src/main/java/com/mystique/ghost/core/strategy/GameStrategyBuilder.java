package com.mystique.ghost.core.strategy;

import java.util.Set;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.StrategicWordTree;
import com.mystique.ghost.core.model.TreeNode;
import com.mystique.ghost.core.model.WordTree;
import static com.google.common.collect.Collections2.transform;
import static com.mystique.ghost.core.utils.MathUtils.avg;

/**
 * @author mystique
 */
public class GameStrategyBuilder {
  private static final double PROBABILITY_NOT_KNOWING = .2;
  private static final Function<StrategicTreeNode, Double> NODE_PROBABILITY_TRANSFORMER =
      new Function<StrategicTreeNode, Double>() {
        @Override
        public Double apply(StrategicTreeNode node) {
          return node.getWinningProbability();
        }
      };
  public static final WinningProbabilityCalculator PROBABILITY_CALCULATOR =
      new WinningProbabilityCalculator() {
        @Override
        public double calculate(TreeNode treeNode, Set<StrategicTreeNode> children) {
          double winningProbability = avg(transform(children, NODE_PROBABILITY_TRANSFORMER));
          if (!treeNode.isLeaf()) {
            winningProbability += PROBABILITY_NOT_KNOWING;
          }
          return  winningProbability;
        }
      };

  private final WordTree wordTree;

  public GameStrategyBuilder(WordTree wordTree) {
    this.wordTree = wordTree;
  }

  public StrategicWordTree build() {
    TreeNode node = wordTree.getRootNode();
    return new StrategicWordTree(traversePostOrder(node, PROBABILITY_CALCULATOR));
  }

  private StrategicTreeNode traversePostOrder(TreeNode node, WinningProbabilityCalculator calculator) {
    Set<StrategicTreeNode> children = Sets.newHashSet();
    if (!Iterables.isEmpty(node.getChildren())) {
      for (TreeNode child : node.getChildren()) {
        children.add(traversePostOrder(child, calculator));
      }
    }
    double winningProbability = calculator.calculate(node, children);
    return new StrategicTreeNodeBuilder()
        .setTreeNode(node)
        .setChildren(children)
        .setWinningProbability(winningProbability)
        .build();
  }
}
