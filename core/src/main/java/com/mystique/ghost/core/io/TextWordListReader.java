package com.mystique.ghost.core.io;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author mystique
 */
public class TextWordListReader implements WordListReader, Closeable {
  private final String filePath;
  private final Scanner scanner;

  public TextWordListReader(String filePath) throws FileNotFoundException {
    this.filePath = filePath;
    InputStream inputStream = new FileInputStream(filePath);
    scanner = new Scanner(inputStream);
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

  @Override
  public void close() {
    scanner.close();
  }
}
