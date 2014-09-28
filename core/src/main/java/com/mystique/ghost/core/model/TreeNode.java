package com.mystique.ghost.core.model;

import java.util.Collection;
import java.util.Map;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;
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

  private final Character value;
  private final TreeNode parent;

  private Map<Character, TreeNode> children = Maps.newHashMap();

  public TreeNode(Character value, TreeNode parent) {
    this.value = value;
    this.parent = parent;
  }

  public TreeNode getParent() {
    return parent;
  }

  public Character getValue() {
    return value;
  }

  public boolean hasChild(Character character) {
    return children.containsKey(character);
  }

  public boolean isRoot() {
    return null == parent;
  }

  public boolean isLeaf() {
    return children.isEmpty() && !isRoot();
  }

  public TreeNode addChild(Character character) {
    if (hasChild(character)) {
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

  public Collection<TreeNode> getChildren() {
    return children.values();
  }

  public void makeLeaf() {
    children = Maps.newHashMap();
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
