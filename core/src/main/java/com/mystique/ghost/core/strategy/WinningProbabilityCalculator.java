package com.mystique.ghost.core.strategy;

import java.util.Set;
import com.google.common.base.Function;
import com.mystique.ghost.core.model.DifficultyLevel;
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

  double calculate(TreeNode treeNode, int depth, Set<StrategicTreeNode> children, DifficultyLevel difficultyLevel) {
    if (treeNode.isLeaf()) {
      return 0;
    } else {
      double pw = MAX_PROBABILITY - avg(transform(children, new ChildProbabilityTransformer(depth + 1, difficultyLevel)));
      pw = calculateProbabilityOfNotKnowing(depth, difficultyLevel) + pw;
      return ensureProbabilityRange(pw);
    }
  }

  private double calculateProbabilityOfKnowing(int depth, DifficultyLevel difficultyLevel) {
    double pk = MAX_PROBABILITY - (depth - 1) * difficultyLevel.getPkincrement();
    return ensureProbabilityRange(pk);
  }

  private double calculateProbabilityOfNotKnowing(int depth, DifficultyLevel difficultyLevel) {
    return MAX_PROBABILITY - calculateProbabilityOfKnowing(depth, difficultyLevel);
  }

  private class ChildProbabilityTransformer implements Function<StrategicTreeNode, Double> {
    private final int depth;
    private final DifficultyLevel difficultyLevel;

    public ChildProbabilityTransformer(int depth, DifficultyLevel difficultyLevel) {
      this.depth = depth;
      this.difficultyLevel = difficultyLevel;
    }

    @Override
    public Double apply(StrategicTreeNode node) {
      double pw = node.getWinningProbability().getValue(difficultyLevel);
      if (!(pw < MAX_PROBABILITY && pw > difficultyLevel.getMinPK())) {
        return pw;
      }
      return ensureProbabilityRange(pw - calculateProbabilityOfNotKnowing(depth, difficultyLevel));
    }
  }
}
