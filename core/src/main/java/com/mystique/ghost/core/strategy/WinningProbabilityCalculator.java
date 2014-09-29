package com.mystique.ghost.core.strategy;

import java.util.Set;
import com.google.common.base.Function;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.TreeNode;
import static com.google.common.collect.Collections2.transform;
import static com.mystique.ghost.core.utils.MathUtils.MAX_PROBABILITY;
import static com.mystique.ghost.core.utils.MathUtils.avg;
import static com.mystique.ghost.core.utils.MathUtils.ensureProbabilityRange;

/**
 * @author mystique
 */
public class WinningProbabilityCalculator {

  private static final double MIN_PROBABILITY_KNOWING = 0.2;
  private static final double PROBABILITY_KNOWING_INCREMENT = 0.1;

  double calculate(TreeNode treeNode, int depth, Set<StrategicTreeNode> children) {
    if (treeNode.isLeaf()) {
      return 0;
    } else {
      double pw = MAX_PROBABILITY - avg(transform(children, new ChildProbabilityTransformer(depth + 1)));
      pw = calculateProbabilityOfNotKnowing(depth) + pw;
      return ensureProbabilityRange(pw);
    }
  }

  private double calculateProbabilityOfKnowing(int depth) {
    double pk = MAX_PROBABILITY - (depth - 1) * PROBABILITY_KNOWING_INCREMENT;
    return ensureProbabilityRange(pk);
  }

  private double calculateProbabilityOfNotKnowing(int depth) {
    return MAX_PROBABILITY - calculateProbabilityOfKnowing(depth);
  }

  private class ChildProbabilityTransformer implements Function<StrategicTreeNode, Double> {
    private final int depth;

    public ChildProbabilityTransformer(int depth) {
      this.depth = depth;
    }

    @Override
    public Double apply(StrategicTreeNode node) {
      double pw = node.getWinningProbability().getValue();
      if (!(pw < MAX_PROBABILITY && pw > MIN_PROBABILITY_KNOWING)) {
        return pw;
      }
      return ensureProbabilityRange(pw - calculateProbabilityOfNotKnowing(depth));
    }
  }
}
