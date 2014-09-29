package com.mystique.ghost.cli.player;

import com.mystique.ghost.core.model.CharacterContext;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.strategy.GameStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component("computer")
public class ComputerPlayer implements Player {

  @Autowired
  private GameStrategy gameStrategy;

  @Override
  public Character play(String prefix, DifficultyLevel difficultyLevel) {
    CharacterContext characterContext = gameStrategy.getNext(prefix, difficultyLevel);
    if (characterContext == CharacterContext.INVALID) {
      System.out.println("There is no such word. I win by default.");
    } else if (characterContext == CharacterContext.NULL) {
      System.out.println("You completed the word. I win!!");
    } else if (characterContext.isLeaf()) {
      System.out.println(String.format("I completed the word '%s'. You win!!", prefix + characterContext.getValue()));
    } else {
      return characterContext.getValue();
    }
    return null;
  }
}
