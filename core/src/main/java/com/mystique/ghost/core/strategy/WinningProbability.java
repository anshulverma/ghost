package com.mystique.ghost.core.strategy;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.mystique.ghost.core.utils.MathUtils;

/**
 * @author mystique
 */
public class WinningProbability implements Comparable<WinningProbability> {

  private static final double HEIGHT_IMPACT = .05;

  private final double averageHeight;

  private double value;

  public WinningProbability(double value, double averageHeight) {
    this.value = value;
    this.averageHeight = averageHeight;
  }

  @Override
  public int compareTo(WinningProbability other) {
    if (getActual() > other.getActual()) {
      return -1;
    }
    return 1;
  }

  double getActual() {
    double actualValue = value + averageHeight * HEIGHT_IMPACT;
    return MathUtils.ensureProbabilityRange(actualValue);
  }

  public double getAverageHeight() {
    return averageHeight;
  }

  public double getValue() {
    return value;
  }

  public void scale(double factor) {
    value = value * factor;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE)
      .append("value", value)
      .append("averageHeight", averageHeight)
      .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(value)
      .append(averageHeight)
      .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof WinningProbability)) {
      return false;
    }

    WinningProbability other = (WinningProbability) obj;
    return new EqualsBuilder()
      .append(value, other.value)
      .append(averageHeight, other.averageHeight)
      .isEquals();
  }
}
