package com.mystique.ghost.core.model;

import java.util.Set;
import com.google.common.collect.Sets;

/**
 * @author mystique
 */
public final class TreeNode {
  private final char value;
  private final TreeNode parent;
  private final Set<TreeNode> children = Sets.newHashSet();

  public TreeNode(char value, TreeNode parent) {
    this.value = value;
    this.parent = parent;
  }

  public TreeNode() {
    this('*', null);
  }

  public TreeNode getParent() {
    return parent;
  }

  public char getValue() {
    return value;
  }

  public boolean isLeaf() {
    return children.isEmpty();
  }

  public boolean isRoot() {
    return parent == null;
  }
}
