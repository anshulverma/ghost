package com.mystique.ghost.core.strategy;

import java.util.Set;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.StrategicWordTree;
import com.mystique.ghost.core.model.TreeNode;
import com.mystique.ghost.core.model.WordTree;
import com.mystique.ghost.core.utils.CollectionUtils;
import com.mystique.ghost.core.utils.MathUtils;
import static com.google.common.collect.Collections2.transform;
import static com.mystique.ghost.core.utils.MathUtils.MAX_PROBABILITY;
import static com.mystique.ghost.core.utils.MathUtils.avg;

/**
 * @author mystique
 */
public class GameStrategyBuilder {
  private static final Function<StrategicTreeNode, Double> NODE_PROBABILITY_TRANSFORMER =
      new Function<StrategicTreeNode, Double>() {
        @Override
        public Double apply(StrategicTreeNode node) {
          return node.getWinningProbability().getValue();
        }
      };
  private static final Function<StrategicTreeNode, Double> NODE_HEIGHT_TRANSFORMER =
      new Function<StrategicTreeNode, Double>() {
        @Override
        public Double apply(StrategicTreeNode node) {
          return 1 + node.getWinningProbability().getAverageHeight();
        }
      };
  private static final WinningProbabilityCalculator PROBABILITY_CALCULATOR = new WinningProbabilityCalculator();

  private final WordTree wordTree;

  public GameStrategyBuilder(WordTree wordTree) {
    this.wordTree = wordTree;
  }

  public StrategicWordTree build() {
    TreeNode node = wordTree.getRootNode();
    return new StrategicWordTree(processPostOrder(node, 0));
  }

  private StrategicTreeNode processPostOrder(TreeNode node, int depth) {
    Set<StrategicTreeNode> children = Sets.newHashSet();
    if (!Iterables.isEmpty(node.getChildren())) {
      for (TreeNode child : node.getChildren()) {
        children.add(processPostOrder(child, depth + 1));
      }
    }
    double winningProbability = PROBABILITY_CALCULATOR.calculate(node, depth, children);
    double averageHeight = avg(transform(children, NODE_HEIGHT_TRANSFORMER));
    scaleProbabilities(children);
    return new StrategicTreeNodeBuilder()
        .setTreeNode(node)
        .setChildren(children)
        .setWinningProbability(winningProbability)
        .setAverageHeight(averageHeight)
        .build();
  }

  private void scaleProbabilities(Set<StrategicTreeNode> nodes) {
    if (CollectionUtils.isEmpty(nodes)) {
      return;
    }

    double sum = MathUtils.sum(transform(nodes, NODE_PROBABILITY_TRANSFORMER));
    if (sum < MAX_PROBABILITY) {
      return;
    }

    double scaleFactor = 1 / sum;
    for (StrategicTreeNode node : nodes) {
      node.getWinningProbability().scale(scaleFactor);
    }
  }
}
