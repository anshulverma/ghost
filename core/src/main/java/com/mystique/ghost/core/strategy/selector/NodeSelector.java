package com.mystique.ghost.core.strategy.selector;

import java.util.Collection;
import java.util.TreeSet;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.model.StrategicTreeNode;

/**
 * @author mystique
 */
public interface NodeSelector {

  Collection<StrategicTreeNode> apply(TreeSet<StrategicTreeNode> nodes);

  DifficultyLevel getDifficultyLevel();

}
