package com.mystique.ghost.core.strategy.selector;

import com.mystique.ghost.core.model.DifficultyLevel;

/**
 * @author mystique
 */
public abstract class AbstractNodeSelector implements NodeSelector {
  private final DifficultyLevel difficultyLevel;

  public AbstractNodeSelector(DifficultyLevel difficultyLevel) {
    this.difficultyLevel = difficultyLevel;
  }

  @Override
  public final DifficultyLevel getDifficultyLevel() {
    return difficultyLevel;
  }
}
