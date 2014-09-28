package com.mystique.ghost.core.strategy;

import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.PrefixWordCompleteException;
import com.mystique.ghost.core.WordCompleteException;

/**
 * @author mystique
 */
public interface GameStrategy {

  Character getNext(String prefix) throws NoSuchWordException, WordCompleteException, PrefixWordCompleteException;

}
