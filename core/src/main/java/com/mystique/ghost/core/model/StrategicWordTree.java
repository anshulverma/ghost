package com.mystique.ghost.core.model;

import com.mystique.ghost.core.NoSuchWordException;

/**
 * @author mystique
 */
public class StrategicWordTree {
  private final StrategicTreeNode root;

  public StrategicWordTree(StrategicTreeNode root) {
    this.root = root;
  }

  public StrategicTreeNode traverse(String prefix) throws NoSuchWordException {
    StrategicTreeNode node = root;
    for (int i = 0; i < prefix.length(); i++) {
      Character character = prefix.charAt(i);
      node = node.getChild(character);
    }
    return node;
  }
}
