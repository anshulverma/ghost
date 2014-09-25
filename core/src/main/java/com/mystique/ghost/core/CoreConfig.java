package com.mystique.ghost.core;

import java.io.FileNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mystique.ghost.core.io.TextWordListReader;
import com.mystique.ghost.core.io.WordListReader;
import com.mystique.ghost.core.model.WordTree;
import com.mystique.ghost.core.model.WordTreeBuilder;

/**
 * @author mystique
 */
@Configuration
public class CoreConfig {

  private static final String WORD_LIST_FILE = "a file";

  @Bean
  public WordTree createWordTree() throws FileNotFoundException {
    WordListReader reader = new TextWordListReader(WORD_LIST_FILE);
    return new WordTreeBuilder(reader).build();
  }
}
