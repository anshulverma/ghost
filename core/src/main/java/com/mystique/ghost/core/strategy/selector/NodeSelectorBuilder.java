package com.mystique.ghost.core.strategy.selector;

import com.mystique.ghost.core.model.DifficultyLevel;

/**
 * @author mystique
 */
public class NodeSelectorBuilder {
  private DifficultyLevel difficultyLevel;

  public NodeSelectorBuilder setDifficultyLevel(DifficultyLevel difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
    return this;
  }

  public NodeSelector build() {
    if (difficultyLevel == DifficultyLevel.VERY_HARD) {
      return new BestOptionNodeSelector(difficultyLevel);
    } else {
      return new RandomizingNodeSelector(difficultyLevel);
    }
  }
}
