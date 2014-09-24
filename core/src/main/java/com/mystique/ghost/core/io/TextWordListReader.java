package com.mystique.ghost.core.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mystique
 */
public class TextWordListReader implements WordListReader {

  private static final Logger LOG = LoggerFactory.getLogger(TextWordListReader.class);
  private final String filePath;

  public TextWordListReader(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public Iterable<String> read() {
    return new Iterable<String>() {
      @Override
      public Iterator<String> iterator() {
        try {
          return readInternal();
        } catch (IOException e) {
          throw new RuntimeException("unable to read file " + filePath, e);
        }
      }
    };
  }

  private Iterator<String> readInternal() throws IOException {
    try (InputStream inputStream = new FileInputStream(filePath)) {
      final Scanner scanner = new Scanner(inputStream);
      return new Iterator<String>() {
        @Override
        public boolean hasNext() {
          return scanner.hasNextLine();
        }

        @Override
        public String next() {
          return scanner.nextLine();
        }

        @Override
        public void remove() {
          throw new UnsupportedOperationException("cannot delete words from a word list file");
        }
      };
    }
  }
}
