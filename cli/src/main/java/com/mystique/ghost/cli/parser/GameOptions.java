package com.mystique.ghost.cli.parser;

import com.mystique.ghost.cli.player.PlayerType;
import com.mystique.ghost.core.model.DifficultyLevel;

/**
 * @author mystique
 */
public class GameOptions {
  private PlayerType player1Type;
  private PlayerType player2Type;
  private String startingPrefix;
  private DifficultyLevel player1Difficulty;
  private DifficultyLevel player2Difficulty;

  public void setPlayer1Type(PlayerType player1Type) {
    this.player1Type = player1Type;
  }

  public void setPlayer2Type(PlayerType player2Type) {
    this.player2Type = player2Type;
  }

  public void setStartingPrefix(String startingPrefix) {
    this.startingPrefix = startingPrefix;
  }

  public PlayerType getPlayer1Type() {
    return player1Type;
  }

  public PlayerType getPlayer2Type() {
    return player2Type;
  }

  public String getStartingPrefix() {
    return startingPrefix;
  }

  public void setPlayer1Difficulty(DifficultyLevel player1Difficulty) {
    this.player1Difficulty = player1Difficulty;
  }

  public void setPlayer2Difficulty(DifficultyLevel player2Difficulty) {
    this.player2Difficulty = player2Difficulty;
  }

  public DifficultyLevel getPlayer1Difficulty() {
    return player1Difficulty;
  }

  public DifficultyLevel getPlayer2Difficulty() {
    return player2Difficulty;
  }
}
