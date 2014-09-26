package com.mystique.ghost.core.model;

import com.mystique.ghost.core.io.WordListReader;
import com.mystique.ghost.core.strategy.GameStrategyBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mystique
 */
public class WordTreeBuilder {

  private static final int MIN_WORD_LENGTH = 4;
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
    private final TreeNode root;

    public TreeBuilderContext(TreeNode root) {
      this.root = root;
    }

    public void append(String word) {
      TreeNode node = root;

      // only words with atleast `MIN_WORD_LENGTH` are added to the tree
      if (StringUtils.length(word) < MIN_WORD_LENGTH) {
        return;
      }

      // find the leftmost letter int he word that already exists in the tree by traversing the tree in sequence
      int index = 0;
      for (; index < word.length(); index++) {
        Character character = word.charAt(index);
        TreeNode child = node.getChild(character);
        if (child == null) {
          break;
        }
        node = child;
      }

      // ignore the word if we found a prefix that itself is a word (eg. "some" and "something")
      if (node.isLeaf()) {
        return;
      }

      // add any characters left in the word
      for (; index < word.length(); index++) {
        Character character = word.charAt(index);
        node = node.addChild(character);
      }

      // a word should always end at a leaf node
      node.makeLeaf();
    }
  }
}
