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
                ["desk"],
                [
                    "desk" : 0,
                    "des"  : 1.0,
                    "de"   : 0.2,
                    "d"    : 1.0
                ]
            ],
            [
                ["desk", "destiny"],
                [
                    "desk"    : 0,
                    "destiny" : 0,
                    "destin"  : 1,
                    "desti"   : 0.5,
                    "dest"    : 1,
                    "des"     : 0.825,
                    "de"      : 0.775,
                    "d"       : 0.725
                ]
            ],
            [
                ["abcdefg", "abcdert", "abcfgh", "bbcert"],
                [
                    ""     : 0.6125,
                    "a"    : 0.6321,
                    "b"    : 0.8928,
                    "bb"   : 0.3,
                    "bbc"  : 1,
                    "abc"  : 0.875,
                    "abcd" : 0.9192
                ]
            ],
            [
                ["abcd", "ghef", "abcde", "abcdef", "gher", "wxyz"],
                [
                    ""   : 0.3,
                    "a"  : 0.4833,
                    "ab" : 0.2,
                    "wx" : 0.2,
                    "gh" : 0.2
                ]
            ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'strategic word tree builder test'() {
    assertNotNull wordTree
    prefixProbabilitySpecs.each {
      prefix, double expectedProbability ->
        StrategicTreeNode node = wordTree.traverse prefix
        assertNotNull node
        assertEquals "unexpected probability for prefix '${prefix}'",
            expectedProbability, node.winningProbability.actual, 0.0001
    }
  }
}
