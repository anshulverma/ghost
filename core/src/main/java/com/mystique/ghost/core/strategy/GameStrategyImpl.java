package com.mystique.ghost.core.strategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.model.TreeNode;
import com.mystique.ghost.core.model.WordTree;

/**
 * @author mystique
 */
@Component
public class GameStrategyImpl implements GameStrategy {

  @Autowired
  private WordTree wordTree;

  @Override
  public Character getNext(String prefix) throws NoSuchWordException {
    TreeNode node = traverse(prefix);
    return node.getTopChild();
  }

  private TreeNode traverse(String prefix) throws NoSuchWordException {
    if (StringUtils.isBlank(prefix)) {
      throw new IllegalArgumentException("cannot traverse empty word");
    }
    TreeNode node = wordTree.getRootNode();
    for (int i = 0; i < prefix.length(); i++) {
      Character character = prefix.charAt(i);
      node = node.getChild(character);
      if (node == null) {
        throw new NoSuchWordException("no word found with prefix: " + prefix);
      }
    }
    return node;
  }
}
