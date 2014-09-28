package com.mystique.ghost.cli.parser;

import org.apache.commons.cli.ParseException;

/**
 * @author mystique
 */
public interface CLIParser {

  GameOptions parse(String[] args) throws ParseException, IllegalCLIArgumentException;

  void printUsage();

}
