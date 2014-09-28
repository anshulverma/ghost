package com.mystique.ghost.cli.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component
public class PlayerFactory {

  @Autowired
  @Qualifier("human")
  private Player humanPlayer;

  @Autowired
  @Qualifier("computer")
  private Player computerPlayer;

  public Player getPlayer(PlayerType playerType) {
    if (playerType == PlayerType.HUMAN) {
      return humanPlayer;
    } else {
      return computerPlayer;
    }
  }
}
