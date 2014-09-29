package com.mystique.ghost.cli.parser;

import com.mystique.ghost.cli.player.PlayerType;

/**
 * @author mystique
 */
public class GameOptions {
  private PlayerType player1Type;
  private PlayerType player2Type;
  private String startingPrefix;

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

  public String startingPrefix() {
    return startingPrefix;
  }
}
