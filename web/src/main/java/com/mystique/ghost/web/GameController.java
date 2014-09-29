package com.mystique.ghost.web;

import java.io.IOException;
import java.util.Scanner;
import javax.servlet.http.HttpServletResponse;
import com.mystique.ghost.core.model.CharacterContext;
import com.mystique.ghost.core.model.DifficultyLevel;
import com.mystique.ghost.core.strategy.GameStrategy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mystique
 */
@Controller
public class GameController {

  @Autowired
  private GameStrategy gameStrategy;

  @RequestMapping("/strategy/{difficulty}")
  @ResponseBody
  public GhostResponse getNextLetter(@PathVariable("difficulty") String difficulty) {
    return getNextLetter(difficulty, StringUtils.EMPTY);
  }

  @RequestMapping("/strategy/{difficulty}/{prefix}")
  @ResponseBody
  public GhostResponse getNextLetter(@PathVariable("difficulty") String difficulty,
                                     @PathVariable("prefix") String prefix) {
    DifficultyLevel difficultyLevel = DifficultyLevel.fromName(difficulty);
    if (difficultyLevel == null) {
      throw new IllegalArgumentException(String.format("'%s' is not a valid difficulty level", difficulty));
    }
    CharacterContext characterContext = gameStrategy.getNext(prefix, difficultyLevel);
    return new GhostResponseBuilder()
        .setCharacterContext(characterContext)
        .setPrefix(prefix)
        .build();
  }

  @RequestMapping("/static/{filename}")
  public void greeting(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
    response.setContentType("application/javascript");
    String filePath = "/webapp/static/" + filename + ".js";
    Scanner scanner = new Scanner(getClass().getResourceAsStream(filePath));
    while (scanner.hasNextLine()) {
      response.getWriter().write(scanner.nextLine());
      response.getWriter().write("\n");
    }
  }
}
