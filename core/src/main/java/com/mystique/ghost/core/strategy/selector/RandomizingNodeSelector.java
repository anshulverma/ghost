package com.mystique.ghost.core.strategy.selector;

import java.util.Collection;
import java.util.TreeSet;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.model.StrategicTreeNode;

/**
 * @author mystique
 */
public class RandomizingNodeSelector extends AbstractNodeSelector {

  public RandomizingNodeSelector(DifficultyLevel difficultyLevel) {
    super(difficultyLevel);
  }

  @Override
  public Collection<StrategicTreeNode> apply(TreeSet<StrategicTreeNode> nodes) {
    if (nodes.size() == 1) {
      return nodes;
    }

    double largest = nodes.first().getWinningProbability().getValue(getDifficultyLevel());
    double smallest = nodes.last().getWinningProbability().getValue(getDifficultyLevel());
    return Collections2.filter(nodes, new RangeFilter(largest, smallest, getDifficultyLevel()));
  }

  private class RangeFilter implements Predicate<StrategicTreeNode> {
    private static final double MIN_DIFF_THRESHOLD = 0.1;

    private final double smallest;
    private final double diff;
    private final DifficultyLevel difficultyLevel;

    public RangeFilter(double largest, double smallest, DifficultyLevel difficultyLevel) {
      this.smallest = smallest;
      this.diff = largest - smallest;
      this.difficultyLevel = difficultyLevel;
    }

    @Override
    public boolean apply(StrategicTreeNode node) {
      if (diff < MIN_DIFF_THRESHOLD) {
        return true;
      }

      double winningProbability = node.getWinningProbability().getValue(difficultyLevel);
      double scaledProbability = (winningProbability - smallest) / diff;
      return difficultyLevel.getSelectionRange().contains(scaledProbability);
    }
  }
}
