package com.mystique.ghost.cli.player;

import java.io.InputStreamReader;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component("human")
public class HumanPlayer implements Player {

  @Override
  public Character play(String prefix) {
    while (true) {
      System.out.print("Play next character ('#' if you can't think of anything): ");
      Scanner scanner = new Scanner(new InputStreamReader(System.in));
      String line = scanner.nextLine();
      if (StringUtils.isBlank(line) || line.length() > 1) {
        System.out.println("ERROR: Please input a single character");
      } else {
        Character ch = line.charAt(0);
        return parseInput(ch);
      }
    }
  }

  private Character parseInput(Character ch) {
    if (ch == '#') {
      return null;
    } else {
      return ch;
    }
  }
}
