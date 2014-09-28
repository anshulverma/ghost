package com.mystique.ghost.core.model

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
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
  final String[] expectedWords

  def WordTreeBuilderTest(words, expectedWords) {
    wordTree = new WordTreeBuilder(new MockWordListReader(words)).build()
    this.expectedWords = expectedWords;
  }

  @Parameterized.Parameters
  static Collection<Object[]> 'test parameters'() {
    Object[][] testCases =
        [
          [
            [],
            []
          ],
          [
            ["test"],
            ["test"]
          ],
          [
            ["aaa", "abcd", "abce", "bcde"],
            ["abcd", "abce", "bcde"]
          ],
          [
            ["abcdefg", "abcd", "abcde", "abc"],
            ["abcd"]
          ],
          [
            ["abcd", "ghef", "abcde", "abcdef", "gher", "wxyz"],
            ["abcd", "ghef", "gher", "wxyz"]
          ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'word tree builder test'() {
    Assert.assertNotNull wordTree.rootNode
    MatcherAssert.assertThat getWords(), Matchers.containsInAnyOrder(expectedWords)
  }

  def getWords() {
    getWords wordTree.rootNode, ""
  }

  def getWords(node, prefix) {
    def words = []
    if (node.leaf) {
      words << prefix
    } else {
      node.children.each {
        childNode -> getWords(childNode, prefix + childNode.value).each {
          words << it
        }
      }
    }
    return words;
  }
}
