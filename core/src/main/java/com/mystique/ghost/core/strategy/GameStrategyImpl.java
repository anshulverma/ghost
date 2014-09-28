package com.mystique.ghost.core.strategy;

import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.PrefixWordCompleteException;
import com.mystique.ghost.core.WordCompleteException;
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
  public Character getNext(String prefix)
      throws NoSuchWordException, WordCompleteException, PrefixWordCompleteException {
    StrategicTreeNode node = wordTree.traverse(prefix);
    if (node.isLeaf()) {
      throw new PrefixWordCompleteException("no more letters after: " + prefix);
    }
    StrategicTreeNode nextNode = node.getMostProbableChild();
    if (nextNode.isLeaf()) {
      throw new WordCompleteException("no more letters after: " + prefix + nextNode.getValue());
    }
    return nextNode.getValue();
  }
}
