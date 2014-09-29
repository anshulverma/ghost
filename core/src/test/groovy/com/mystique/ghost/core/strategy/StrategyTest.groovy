package com.mystique.ghost.core.strategy

import com.mystique.ghost.core.model.CharacterContext
import com.mystique.ghost.core.model.DifficultyLevel
import com.mystique.ghost.core.model.MockWordListReader
import com.mystique.ghost.core.model.WordTreeBuilder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNotNull

/**
 * @author mystique
 */
@RunWith(Parameterized.class)
class StrategyTest {
  final GameStrategy strategy
  final nextLetterSpecs
  final difficultyLevel

  def StrategyTest(words, nextLetterSpecs, difficultyLevel) {
    def wordTree = new GameStrategyBuilder(new WordTreeBuilder(new MockWordListReader(words)).build()).build()
    strategy = new GameStrategyImpl(wordTree);

    this.nextLetterSpecs = nextLetterSpecs;
    this.difficultyLevel = difficultyLevel;
  }

  @Parameterized.Parameters
  static Collection<Object[]> 'test parameters'() {
    Object[][] testCases =
        [
            [
                [
                    "aqua",
                    "aqueduct"
                ],
                [
                    "aq"  : 'u',
                    "aqu" : 'a'
                ],
                DifficultyLevel.EASY
            ],
            [
                [
                    "aqua",
                    "aqueduct"
                ],
                [
                    "aq"  : 'u',
                    "aqu" : 'a'
                ],
                DifficultyLevel.MEDIUM
            ],
            [
                [
                    "aqua",
                    "aqueduct"
                ],
                [
                    "aq"  : 'u',
                    "aqu" : 'e'
                ],
                DifficultyLevel.HARD
            ],
            [
                [
                    "aqua",
                    "aqueduct"
                ],
                [
                    "aq"  : 'u',
                    "aqu" : 'e'
                ],
                DifficultyLevel.VERY_HARD
            ],
            [
                [
                    "aqua",
                    "aqueduct",
                    "aqueous",
                    "aquiculture",
                    "aquifer",
                    "aquilegia",
                    "aquiline",
                    "aquilinities",
                    "aquilinity",
                    "aquiver"
                ],
                [
                    "a"         : 'q',
                    "aq"        : 'u',
                    "aqu"       : 'i',
                    "aqui"      : 'c',
                    "aquil"     : 'e',
                    "aquili"    : 'n',
                    "aquilin"   : 'i',
                    "aquilini"  : 't',
                    "aquilinit" : 'i'
                ],
                DifficultyLevel.VERY_HARD
            ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'should get next letter'() {
    nextLetterSpecs.each {
      prefix, expectedNextLetter ->
        CharacterContext nextLetter = strategy.getNext(prefix, difficultyLevel)
        assertNotNull nextLetter
        assertEquals "unexpected next letter for '${prefix}'",
            String.valueOf(expectedNextLetter), String.valueOf(nextLetter.value)
    }
  }
}
