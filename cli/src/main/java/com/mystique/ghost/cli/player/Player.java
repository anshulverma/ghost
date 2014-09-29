package com.mystique.ghost.cli.player;

import javax.annotation.Nullable;
import com.mystique.ghost.core.model.DifficultyLevel;

/**
 * @author mystique
 */
public interface Player {

  @Nullable
  Character play(String prefix, DifficultyLevel difficultyLevel);
}
