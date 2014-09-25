package com.mystique.ghost.core.model;

import com.mystique.ghost.core.io.WordListReader;
import com.mystique.ghost.core.strategy.GameStrategyBuilder;
import com.mystique.ghost.core.utils.WordUtils;

/**
 * @author mystique
 */
public class WordTreeBuilder {

  private final WordListReader reader;

  public WordTreeBuilder(WordListReader reader) {
    this.reader = reader;
  }

  public WordTree build() {
    TreeNode root = TreeNode.newRootNode();
    TreeBuilderContext context = new TreeBuilderContext(root);
    for (String word : reader.read()) {
      context.append(word);
    }
    return new GameStrategyBuilder(new WordTree(root)).build();
  }

  private class TreeBuilderContext {
    private TreeNode currentNode;
    private StringBuilder currentWordBuilder;

    public TreeBuilderContext(TreeNode root) {
      currentNode = root;
      currentWordBuilder = new StringBuilder();
    }

    public String getCurrentWord() {
      return currentWordBuilder.toString();
    }

    public void append(String word) {
      String currentWord = getCurrentWord();
      String commonPrefix = WordUtils.commonPrefix(word, currentWord);
      backtrack(currentWord.length() - commonPrefix.length(), currentWord.length());
      String suffix = word.substring(commonPrefix.length());
      appendSuffix(suffix);
    }

    private void appendSuffix(String suffix) {
      TreeNode node = currentNode;
      for (int i = 0; i < suffix.length(); i++) {
        Character character = suffix.charAt(i);
        node = node.getOrAddChild(character);
      }
      currentNode = node;
      currentWordBuilder.append(suffix);
    }

    private int backtrack(int count, int length) {
      if (count == 0) {
        return length;
      }
      currentNode = currentNode.getParent();
      currentWordBuilder.deleteCharAt(length - 1);
      return backtrack(count - 1, length - 1);
    }
  }
}
