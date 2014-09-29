package com.mystique.ghost.cli.player;

import com.mystique.ghost.core.NoSuchWordException;
import com.mystique.ghost.core.PrefixWordCompleteException;
import com.mystique.ghost.core.WordCompleteException;
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
  public Character play(String prefix) {
    try {
      return gameStrategy.getNext(prefix);
    } catch (NoSuchWordException e) {
      System.out.println("There is no such word. I win by default.");
    } catch (WordCompleteException e) {
      System.out.println(String.format("I completed the word '%s'. You win!!", e.getWord()));
    } catch (PrefixWordCompleteException e) {
      System.out.println("You completed the word. I win!!");
    }
    System.exit(0);
    return null;
  }
}
