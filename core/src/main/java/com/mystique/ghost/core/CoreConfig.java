package com.mystique.ghost.core;

import java.io.FileNotFoundException;
import com.mystique.ghost.core.io.TextWordListReader;
import com.mystique.ghost.core.io.WordListReader;
import com.mystique.ghost.core.model.StrategicWordTree;
import com.mystique.ghost.core.model.WordTree;
import com.mystique.ghost.core.model.WordTreeBuilder;
import com.mystique.ghost.core.strategy.GameStrategyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mystique
 */
@Configuration
public class CoreConfig {

  private static final String WORD_LIST_FILE = "a file";

  @Bean
  public StrategicWordTree createWordTree() throws FileNotFoundException {
    WordListReader reader = new TextWordListReader(WORD_LIST_FILE);
    WordTree tree = new WordTreeBuilder(reader).build();
    return new GameStrategyBuilder(tree).build();
  }
}
