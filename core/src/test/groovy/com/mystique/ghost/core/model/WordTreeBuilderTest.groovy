package com.mystique.ghost.core.model

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * @author mystique
 */
@RunWith(Parameterized.class)
class WordTreeBuilderTest {
  final WordTree wordTree
  final int wordCount

  def WordTreeBuilderTest(words, wordCount) {
    wordTree = new WordTreeBuilder(new MockWordListReader(words)).build()
    this.wordCount = wordCount
  }

  @Parameterized.Parameters
  static Collection<Object[]> 'test parameters'() {
    Object[][] testCases =
        [
          [
            [],
            0
          ],
          [
            ["test"],
            1
          ],
          [
            ["aaa", "abcd", "abce", "bcde"],
            4
          ],
          [
            ["abcd", "abcde", "abc"],
            1
          ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'word tree builder test'() {
    Assert.assertNotNull wordTree.rootNode
    Assert.assertEquals wordCount, getWordCount(wordTree.rootNode)
  }

  def getWordCount(TreeNode node) {
    def count = node.leaf ? 1 : node.children.sum { TreeNode childNode -> getWordCount(childNode) }
    count == null ? 0 : count
  }
}
