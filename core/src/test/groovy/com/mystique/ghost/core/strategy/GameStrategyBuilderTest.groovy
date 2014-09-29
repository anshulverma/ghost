package com.mystique.ghost.core.strategy

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import com.mystique.ghost.core.model.DifficultyLevel
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
  final difficultyLevel

  def GameStrategyBuilderTest(words, prefixProbabilitySpecs, difficultyLevel) {
    wordTree = new GameStrategyBuilder(new WordTreeBuilder(new MockWordListReader(words)).build()).build()
    this.prefixProbabilitySpecs = prefixProbabilitySpecs;
    this.difficultyLevel = difficultyLevel;
  }

  @Parameterized.Parameters
  static Collection<Object[]> 'test parameters'() {
    Object[][] testCases =
        [
            [
                [],
                [],
                DifficultyLevel.VERY_HARD
            ],
            [
                ["desk"],
                [
                    "desk" : 0,
                    "des"  : 1.0,
                    "de"   : 0.15,
                    "d"    : 1.0
                ],
                DifficultyLevel.VERY_HARD
            ],
            [
                ["desk", "destiny"],
                [
                    "desk"    : 0,
                    "destiny" : 0,
                    "destin"  : 1,
                    "desti"   : 0.3,
                    "dest"    : 1,
                    "des"     : 0.825,
                    "de"      : 0.625,
                    "d"       : 0.775
                ],
                DifficultyLevel.VERY_HARD
            ],
            [
                ["abcdefg", "abcdert", "abcfgh", "bbcert"],
                [
                    ""     : 0.675,
                    "a"    : 0.6475,
                    "b"    : 0.8774,
                    "bb"   : 0.4,
                    "bbc"  : 1,
                    "abc"  : 0.8,
                    "abcd" : 1
                ],
                DifficultyLevel.VERY_HARD
            ],
            [
                ["abcd", "ghef", "abcde", "abcdef", "gher", "wxyz"],
                [
                    ""   : 0.25,
                    "a"  : 0.4833,
                    "ab" : 0.15,
                    "wx" : 0.15,
                    "gh" : 0.15
                ],
                DifficultyLevel.VERY_HARD
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
            expectedProbability, node.winningProbability.getActual(difficultyLevel), 0.0001
    }
  }
}
