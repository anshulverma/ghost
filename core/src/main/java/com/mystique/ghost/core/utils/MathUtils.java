package com.mystique.ghost.core.utils;

import java.util.Collection;
import static com.mystique.ghost.core.utils.CollectionUtils.Aggregator;

/**
 * @author mystique
 */
public final class MathUtils {
  public static final double MIN_PROBABILITY = 0;
  public static final double MAX_PROBABILITY = 1;

  private MathUtils() { }

  public static double sum(Collection<Double> numbers) {
    if (CollectionUtils.isEmpty(numbers)) {
      return 0;
    }

    return CollectionUtils.aggregate(numbers, new Aggregator<Double>() {
      @Override
      public Double apply(Double number1, Double number2) {
        return number1 + number2;
      }
    });
  }

  public static double avg(Collection<Double> numbers) {
    if (CollectionUtils.isEmpty(numbers)) {
      return 0;
    }

    return sum(numbers) / numbers.size();
  }

  public static double ensureProbabilityRange(double value) {
    return min(max(value, MIN_PROBABILITY), MAX_PROBABILITY);
  }

  public static double min(double value1, double value2) {
    return value1 > value2 ? value2 : value1;
  }

  public static double max(double value1, double value2) {
    return value1 > value2 ? value1 : value2;
  }

  public static int randomInt(int max) {
    return (int) ((Math.random() * 1000) % max);
  }
}
