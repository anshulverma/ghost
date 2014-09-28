package com.mystique.ghost.core.strategy

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import com.mystique.ghost.core.model.MockWordListReader
import com.mystique.ghost.core.model.StrategicTreeNode
import com.mystique.ghost.core.model.StrategicWordTree
import com.mystique.ghost.core.model.WordTreeBuilder
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull
/**
 * @author mystique
 */
@RunWith(Parameterized.class)
class GameStrategyBuilderTest {
  final StrategicWordTree wordTree
  final prefixProbabilitySpecs

  def GameStrategyBuilderTest(words, prefixProbabilitySpecs) {
    wordTree = new GameStrategyBuilder(new WordTreeBuilder(new MockWordListReader(words)).build()).build()
    this.prefixProbabilitySpecs = prefixProbabilitySpecs;
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
                [
                    ""     : 0.8,
                    "t"    : 0.6,
                    "te"   : 0.4,
                    "tes"  : 0.2,
                    "test" : 0
                ]
            ],
            [
                ["aaa", "abcd", "abce", "bcde"],
                [
                    ""   : 0.8,
                    "a"  : 0.6,
                    "b"  : 0.6,
                    "ab" : 0.4
                ]
            ],
            [
                ["abcdefg", "abcdert", "abcfgh", "bbcert"],
                [
                    ""     : 1.25,
                    "a"    : 1.1,
                    "b"    : 1.0,
                    "bb"   : 0.8,
                    "abc"  : 0.7,
                    "abcd" : 0.6
                ]
            ],
            [
                ["abcd", "ghef", "abcde", "abcdef", "gher", "wxyz"],
                [
                    ""   : 0.8,
                    "a"  : 0.6,
                    "ab" : 0.4,
                    "wx" : 0.4,
                    "gh" : 0.4
                ]
            ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'strategic word tree builder test'() {
    assertNotNull wordTree
    prefixProbabilitySpecs.each {
      prefix, expectedProbability ->
        StrategicTreeNode node = wordTree.traverse prefix
        assertNotNull node
        assertEquals "unexpected probability for prefix '${prefix}'",
            expectedProbability, node.winningProbability, 0.00001
    }
  }
}
