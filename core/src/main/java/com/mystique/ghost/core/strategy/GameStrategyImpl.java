package com.mystique.ghost.core.strategy;

import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.StrategicWordTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component
public class GameStrategyImpl implements GameStrategy {

  @Autowired
  private StrategicWordTree wordTree;

  @Override
  public Character getNext(String prefix) throws NoSuchWordException {
    StrategicTreeNode node = wordTree.traverse(prefix);
    return node.getMostProbableChild().getValue();
  }
}
