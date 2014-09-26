package com.mystique.ghost.core.model;

import java.util.Comparator;
import java.util.Set;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author mystique
 */
public final class TreeNode {
  private static final Character ROOT_NODE_VALUE = '*';
  private static final Comparator<TreeNode> COMPARATOR = new Comparator<TreeNode>() {
    @Override
    public int compare(TreeNode node1, TreeNode node2) {
      if (node1.winningProbability > node2.winningProbability) {
        return 1;
      }
      return -1;
    }
  };

  private final Character value;
  private final TreeNode parent;
  private final double winningProbability;
  private final SortedHashMap<Character, TreeNode> children = new SortedHashMap<>(COMPARATOR);

  public TreeNode(Character value, TreeNode parent) {
    this.value = value;
    this.parent = parent;
    winningProbability = 0;
  }

  public TreeNode getParent() {
    return parent;
  }

  public Character getValue() {
    return value;
  }

  public boolean hasChild(Character character) {
    return children.contains(character);
  }

  public boolean isRoot() {
    return null == parent;
  }

  public boolean isLeaf() {
    return children.isEmpty() && !isRoot();
  }

  public TreeNode addChild(Character character) {
    if (children.contains(character)) {
      throw new IllegalArgumentException("character '" + character + "' already exists in " + getPrefix());
    }
    TreeNode child = new TreeNode(character, this);
    children.put(character, child);
    return child;
  }

  @Nullable
  public TreeNode getChild(Character character) {
    return children.get(character);
  }

  public String getPrefix() {
    if (isRoot()) {
      return StringUtils.EMPTY;
    }
    return parent.getPrefix() + value;
  }

  public static TreeNode newRootNode() {
    return new TreeNode(ROOT_NODE_VALUE, null);
  }

  public Character getTopChild() {
    return children.getFirst().value;
  }

  public Set<TreeNode> getChildren() {
    return children.getValues();
  }

  public void makeLeaf() {
    children.removeAll();
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(value)
      .append(parent)
      .append(children)
      .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof TreeNode)) {
      return false;
    }

    TreeNode other = (TreeNode) obj;
    return new EqualsBuilder()
      .append(value, other.value)
      .append(parent, other.parent)
      .append(children, other.parent)
      .isEquals();
  }
}
