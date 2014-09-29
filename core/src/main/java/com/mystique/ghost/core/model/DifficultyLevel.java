package com.mystique.ghost.core.model;

import java.util.Map;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;

/**
 * @author mystique
 */
public enum DifficultyLevel {
  VERY_HARD("very-hard", 0.05, 0.6, new Range(0.75, 2)),
  HARD("hard", 0.07, 0.4, new Range(0.5, 2)),
  MEDIUM("medium", 0.085, 0.25, new Range(-1, 0.5)),
  EASY("easy", 0.1, 0.15, new Range(-1, 0.25));

  private static final Map<String, DifficultyLevel> LOOKUP = Maps.newHashMap();

  static {
    for (DifficultyLevel difficultyLevel : values()) {
      LOOKUP.put(difficultyLevel.name, difficultyLevel);
    }
  }

  private final String name;
  private final double pkincrement;
  private final double minPK;
  private final Range selectionRange;

  DifficultyLevel(String name, double pkincrement, double minPK, Range selectionRange) {
    this.name = name;
    this.pkincrement = pkincrement;
    this.minPK = minPK;
    this.selectionRange = selectionRange;
  }

  public double getPkincrement() {
    return pkincrement;
  }

  public double getMinPK() {
    return minPK;
  }

  public Range getSelectionRange() {
    return selectionRange;
  }

  @Nullable
  public static DifficultyLevel fromName(String name) {
    return LOOKUP.get(name);
  }
}
