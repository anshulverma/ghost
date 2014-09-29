package com.mystique.ghost.cli;

import com.mystique.ghost.cli.parser.GameOptions;
import com.mystique.ghost.cli.player.Player;
import com.mystique.ghost.cli.player.PlayerFactory;
import com.mystique.ghost.cli.player.PlayerType;
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
    StringBuilder currentWord = new StringBuilder(options.getStartingPrefix());
    TurnSwitch turnSwitch = new TurnSwitch(player1, player2, options);
    if (options.getStartingPrefix().length() % 2 == 1) {
      turnSwitch.swithTurns();
    }
    printWelcomeMessage(options);
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

  private void printWelcomeMessage(GameOptions options) {
    System.out.println("Welcome to GHOST!!");
    System.out.println("Player 1 is " + options.getPlayer1Type().name());
    if (options.getPlayer1Type() == PlayerType.COMPUTER) {
      System.out.println("Player 1 is playing at level " + options.getPlayer1Difficulty().name());
    }
    System.out.println("Player 2 is " + options.getPlayer2Type().name());
    if (options.getPlayer2Type() == PlayerType.COMPUTER) {
      System.out.println("Player 2 is playing at level " + options.getPlayer2Difficulty().name());
    }
  }

  private static class TurnSwitch {

    private final Player player1;
    private final Player player2;
    private final GameOptions options;

    private boolean switcher;

    public TurnSwitch(Player player1, Player player2, GameOptions options) {
      this.player1 = player1;
      this.player2 = player2;
      this.options = options;

      switcher = false;
    }

    public Character playTurn(String prefix) {
      switcher = !switcher;
      if (switcher) {
        return player1.play(prefix, options.getPlayer1Difficulty());
      } else {
        return player2.play(prefix, options.getPlayer2Difficulty());
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
