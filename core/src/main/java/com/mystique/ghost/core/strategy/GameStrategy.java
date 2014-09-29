package com.mystique.ghost.core.strategy;

import com.mystique.ghost.core.model.CharacterContext;
import com.mystique.ghost.core.model.DifficultyLevel;

/**
 * @author mystique
 */
public interface GameStrategy {

  CharacterContext getNext(String prefix, DifficultyLevel difficultyLevel);

}
