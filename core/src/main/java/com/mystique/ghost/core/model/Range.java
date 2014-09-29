package com.mystique.ghost.core.model;

/**
 * @author mystique
 */
public class Range {
  private final double min;
  private final double max;

  public Range(double min, double max) {
    this.min = min;
    this.max = max;
  }

  public boolean contains(double value) {
    return min <= value && value <= max;
  }
}
