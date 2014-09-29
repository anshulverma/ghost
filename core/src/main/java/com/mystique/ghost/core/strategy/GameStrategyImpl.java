package com.mystique.ghost.core.strategy;

import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.model.CharacterContext;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.model.StrategicTreeNode;
import com.mystique.ghost.core.model.StrategicWordTree;
import com.mystique.ghost.core.strategy.selector.NodeSelector;
import com.mystique.ghost.core.strategy.selector.NodeSelectorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component
public class GameStrategyImpl implements GameStrategy {

  private static final Logger LOG = LoggerFactory.getLogger(GameStrategyImpl.class);

  private final StrategicWordTree wordTree;

  @Autowired
  public GameStrategyImpl(StrategicWordTree wordTree) {
    this.wordTree = wordTree;
  }

  @Override
  public CharacterContext getNext(String prefix, DifficultyLevel difficultyLevel) {
    try {
      StrategicTreeNode node = wordTree.traverse(prefix);
      if (node.isLeaf()) {
        return CharacterContext.NULL;
      }
      NodeSelector selector = new NodeSelectorBuilder().setDifficultyLevel(difficultyLevel).build();
      StrategicTreeNode nextNode = node.getChild(selector);
      return new CharacterContext(nextNode.getValue(), nextNode.isLeaf());
    } catch (NoSuchWordException e) {
      LOG.warn("no word found with prefix: " + prefix);
      return CharacterContext.INVALID;
    }
  }
}
