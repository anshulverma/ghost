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
    return Double.min(Double.max(value, MIN_PROBABILITY), MAX_PROBABILITY);
  }
}
