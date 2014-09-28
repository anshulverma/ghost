package com.mystique.ghost.core.strategy;

import java.util.Set;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.TreeNode;

/**
 * @author mystique
 */
public interface WinningProbabilityCalculator {

  double calculate(TreeNode treeNode, Set<StrategicTreeNode> children);

}
