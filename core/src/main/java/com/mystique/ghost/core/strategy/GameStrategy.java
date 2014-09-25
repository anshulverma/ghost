package com.mystique.ghost.core.strategy;

import com.mystique.ghost.core.NoSuchWordException;

/**
 * @author mystique
 */
public interface GameStrategy {
  Character getNext(String prefix) throws NoSuchWordException;
}
