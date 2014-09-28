package com.mystique.ghost.cli.parser;

import com.mystique.ghost.cli.player.PlayerType;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component
public class CLIParserImpl implements CLIParser {
  private static final String PLAYER_1_LONG_OPT = "player-1";
  private static final String PLAYER_2_LONG_OPT = "player-2";

  private final Options options;

  public CLIParserImpl() {
    this.options = new Options();
    options.addOption("1", PLAYER_1_LONG_OPT, true, "set player 1 to 'human' or 'computer'");
    options.addOption("2", PLAYER_2_LONG_OPT, true, "set player 2 to 'human' or 'computer'");
  }

  @Override
  public GameOptions parse(String[] args) throws ParseException, IllegalCLIArgumentException {
    CommandLineParser parser = new BasicParser();
    CommandLine commandLine = parser.parse(options, args, true);
    return new GameOptionsBuilder()
        .setCommandLine(commandLine)
        .build();
  }

  @Override
  public void printUsage() {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("java -jar ghost.jar [options]", options);
  }

  private static final class GameOptionsBuilder {
    private static final PlayerType DEFAULT_PLAYER1_TYPE = PlayerType.HUMAN;
    private static final PlayerType DEFAULT_PLAYER2_TYPE = PlayerType.COMPUTER;

    private CommandLine commandLine;

    public GameOptionsBuilder setCommandLine(CommandLine commandLine) {
      this.commandLine = commandLine;
      return this;
    }

    public GameOptions build() throws IllegalCLIArgumentException {
      GameOptions gameOptions = new GameOptions();
      gameOptions.setPlayer1Type(getPlayerType(PLAYER_1_LONG_OPT, DEFAULT_PLAYER1_TYPE));
      gameOptions.setPlayer2Type(getPlayerType(PLAYER_2_LONG_OPT, DEFAULT_PLAYER2_TYPE));
      return gameOptions;
    }

    private PlayerType getPlayerType(String longOpt, PlayerType defaultType) throws IllegalCLIArgumentException {
      String arg = commandLine.getOptionValue(longOpt);
      if (StringUtils.isBlank(arg)) {
        return defaultType;
      }
      PlayerType playerType = PlayerType.fromName(arg);
      if (playerType == null) {
        throw new IllegalCLIArgumentException("Invalid player type. Please specify either 'human' or 'computer'.");
      }
      return playerType;
    }
  }
}
