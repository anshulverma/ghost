package com.mystique.ghost.core.model;

import com.mystique.ghost.core.WordUtils;
import com.mystique.ghost.core.io.TextWordListReader;
import com.mystique.ghost.core.io.WordListReader;

/**
 * @author mystique
 */
public class WordTreeBuilder {

  private final String wordListFile;

  public WordTreeBuilder(String wordListFile) {
    this.wordListFile = wordListFile;
  }

  public WordTree build() {
    WordListReader reader = new TextWordListReader(wordListFile);
    TreeNode root = new TreeNode();
    TreeBuilderContext context = new TreeBuilderContext(root);
    for (String word : reader.read()) {
      context.append(word);
    }
    return new WordTree(root);
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
      String suffix = currentWord.substring(commonPrefix.length() + 1);
      appendSuffix(suffix);
    }

    private void appendSuffix(String suffix) {
      TreeNode node = currentNode;
      for (int i = 0; i < suffix.length(); i++) {
        node = new TreeNode(suffix.charAt(i), node);
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
