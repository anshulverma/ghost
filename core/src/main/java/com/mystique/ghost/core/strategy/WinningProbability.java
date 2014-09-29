package com.mystique.ghost.core.strategy;

import java.util.Map;
import com.google.common.collect.Maps;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.utils.MathUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author mystique
 */
public class WinningProbability {

  private static final double HEIGHT_IMPACT = .05;

  private final double averageHeight;

  private Map<DifficultyLevel, Double> probabilityValues = Maps.newHashMap();

  public WinningProbability(Map<DifficultyLevel, Double> probabilityValues, double averageHeight) {
    this.probabilityValues = probabilityValues;
    this.averageHeight = averageHeight;
  }

  public int compareTo(WinningProbability other, DifficultyLevel difficultyLevel) {
    if (getActual(difficultyLevel) > other.getActual(difficultyLevel)) {
      return -1;
    }
    return 1;
  }

  double getActual(DifficultyLevel difficultyLevel) {
    double actualValue = probabilityValues.get(difficultyLevel) + averageHeight * HEIGHT_IMPACT;
    return MathUtils.ensureProbabilityRange(actualValue);
  }

  public double getAverageHeight() {
    return averageHeight;
  }

  public double getValue(DifficultyLevel difficultyLevel) {
    return probabilityValues.get(difficultyLevel);
  }

  public void scale(double factor, DifficultyLevel difficultyLevel) {
    double value = getValue(difficultyLevel);
    value = value * factor;
    setValue(value, difficultyLevel);
  }

  private void setValue(double value, DifficultyLevel difficultyLevel) {
    probabilityValues.put(difficultyLevel, value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(new Object(), ToStringStyle.SHORT_PREFIX_STYLE)
      .append("values", probabilityValues)
      .append("averageHeight", averageHeight)
      .toString();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
      .append(probabilityValues)
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
      .append(probabilityValues, other.probabilityValues)
      .append(averageHeight, other.averageHeight)
      .isEquals();
  }
}
