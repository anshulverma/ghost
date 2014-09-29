package com.mystique.ghost.core;

/**
 * @author mystique
 */
public class WordCompleteException extends Exception {

  private final String word;

  public WordCompleteException(String message, String word) {
    super(message);
    this.word = word;
  }

  public String getWord() {
    return word;
  }
}
