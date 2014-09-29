package com.mystique.ghost.cli;

import com.mystique.ghost.cli.parser.GameOptions;
import com.mystique.ghost.cli.player.Player;
import com.mystique.ghost.cli.player.PlayerFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component
public class GameApplication {

  @Autowired
  private PlayerFactory playerFactory;

  public void start(GameOptions options) {
    Player player1 = playerFactory.getPlayer(options.getPlayer1Type());
    Player player2 = playerFactory.getPlayer(options.getPlayer2Type());
    startGame(player1, player2, options);
  }

  private void startGame(Player player1, Player player2, GameOptions options) {
    StringBuilder currentWord = new StringBuilder(options.startingPrefix());
    TurnSwitch turnSwitch = new TurnSwitch(player1, player2);
    if (options.startingPrefix().length() % 2 == 1) {
      turnSwitch.swithTurns();
    }
    while (true) {
      String prefix = currentWord.toString();
      if (StringUtils.isNoneBlank(prefix)) {
        System.out.println(String.format("Current word: '%s'", prefix));
      }
      System.out.println(String.format("[%s's turn]", turnSwitch.getPlayerName()));
      Character character = turnSwitch.playTurn(prefix);
      if (character == null) {
        System.out.println(String.format("%s wins with word '%s'", turnSwitch.getPlayerName(), prefix));
        break;
      }
      currentWord.append(character);
    }
  }

  private static class TurnSwitch {

    private final Player player1;
    private final Player player2;

    private boolean switcher;

    public TurnSwitch(Player player1, Player player2) {
      this.player1 = player1;
      this.player2 = player2;

      switcher = false;
    }

    public Character playTurn(String prefix) {
      switcher = !switcher;
      if (switcher) {
        return player1.play(prefix);
      } else {
        return player2.play(prefix);
      }
    }

    public String getPlayerName() {
      return switcher ? "Player2" : "Player1";
    }

    public void swithTurns() {
      switcher = !switcher;
    }
  }
}
