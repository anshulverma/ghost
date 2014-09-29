package com.mystique.ghost.core.strategy.selector;

import java.util.Collection;
import java.util.TreeSet;
import com.google.common.collect.Lists;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.model.StrategicTreeNode;

/**
 * @author mystique
 */
public class BestOptionNodeSelector extends AbstractNodeSelector {

  public BestOptionNodeSelector(DifficultyLevel difficultyLevel) {
    super(difficultyLevel);
  }

  @Override
  public Collection<StrategicTreeNode> apply(TreeSet<StrategicTreeNode> nodes) {
    return Lists.newArrayList(nodes.first());
  }
}
