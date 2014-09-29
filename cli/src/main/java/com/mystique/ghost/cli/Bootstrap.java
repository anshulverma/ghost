package com.mystique.ghost.cli;

import com.mystique.ghost.cli.parser.GameOptions;
import com.mystique.ghost.cli.parser.IllegalCLIArgumentException;
import com.mystique.ghost.cli.parser.ProgrammableParser;
import com.mystique.ghost.core.CoreConfig;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author mystique
 */
@Component
public class Bootstrap {
  private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

  @Autowired
  private GameApplication gameApplication;

  public static void main(String[] args) {
    ProgrammableParser parser = new ProgrammableParser();
    try {
      GameOptions options = parser.parse(args);
      startApplication(options);
    } catch (ParseException e) {
      LOG.error("unable to parse options", e);
      parser.printUsage();
    } catch (IllegalCLIArgumentException e) {
      LOG.error("illegal arguments", e);
      System.out.println(e.getMessage());
      parser.printUsage();
    }
  }

  private static void startApplication(GameOptions options) {
    ApplicationContext context = new AnnotationConfigApplicationContext(CLIConfig.class, CoreConfig.class);
    Bootstrap application = context.getBean(Bootstrap.class);
    application.start(options);
  }

  private void start(GameOptions options) {
    gameApplication.start(options);
  }
}
