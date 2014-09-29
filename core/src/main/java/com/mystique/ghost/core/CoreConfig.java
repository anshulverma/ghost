package com.mystique.ghost.core;

import java.io.FileNotFoundException;
import java.io.InputStream;
import com.mystique.ghost.core.io.TextWordListReader;
import com.mystique.ghost.core.io.WordListReader;
import com.mystique.ghost.core.model.StrategicWordTree;
import com.mystique.ghost.core.model.WordTree;
import com.mystique.ghost.core.model.WordTreeBuilder;
import com.mystique.ghost.core.strategy.GameStrategyBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author mystique
 */
@Configuration
@ComponentScan
public class CoreConfig {

  private static final InputStream WORD_LIST_STREAM;

  static {
    ClassLoader classLoader = CoreConfig.class.getClassLoader();
    InputStream wordListStream = classLoader.getResourceAsStream("wordList.txt");
    if (wordListStream == null) {
      throw new IllegalStateException("unable to locate word list file");
    }
    WORD_LIST_STREAM = wordListStream;
  }

  @Bean
  public StrategicWordTree createWordTree() throws FileNotFoundException {
    WordListReader reader = new TextWordListReader(WORD_LIST_STREAM);
    WordTree tree = new WordTreeBuilder(reader).build();
    return new GameStrategyBuilder(tree).build();
  }
}
