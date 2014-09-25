package com.mystique.ghost.core.utils;

/**
 * @author mystique
 */
public final class WordUtils {

  private WordUtils() { }

  public static String commonPrefix(String word1, String word2) {
    if (word1.length() > word2.length()) {
      return commonPrefix(word2, word1);
    }
    StringBuilder prefix = new StringBuilder();
    for (int i = 0; i < word1.length(); i++) {
      if (word1.charAt(i) == word2.charAt(i)) {
        prefix.append(word1.charAt(i));
      } else {
        break;
      }
    }
    return prefix.toString();
  }
}
