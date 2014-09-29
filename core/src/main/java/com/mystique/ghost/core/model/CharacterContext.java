package com.mystique.ghost.core.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author mystique
 */
public class CharacterContext {
  public static final CharacterContext NULL = new CharacterContext('#', false);
  public static final CharacterContext INVALID = new CharacterContext('!', false);

  private final Character value;
  private final boolean leaf;

  public CharacterContext(Character value, boolean leaf) {
    this.value = value;
    this.leaf = leaf;
  }

  public Character getValue() {
    return value;
  }

  public boolean isLeaf() {
    return leaf;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(value)
        .append(leaf)
        .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj instanceof Character && value == obj) {
      return true;
    }

    if (!(obj instanceof CharacterContext)) {
      return false;
    }

    CharacterContext other = (CharacterContext) obj;
    return new EqualsBuilder()
        .append(value, other.value)
        .append(leaf, other.leaf)
        .isEquals();
  }
}
