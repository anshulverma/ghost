package com.mystique.ghost.core.strategy

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

  def StrategyTest(words, nextLetterSpecs) {
    def wordTree = new GameStrategyBuilder(new WordTreeBuilder(new MockWordListReader(words)).build()).build()
    strategy = new GameStrategyImpl(wordTree);

    this.nextLetterSpecs = nextLetterSpecs;
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
                    "aq": 'u',
                    "aqu": 'e'
                ]
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
                    "aqui"      : 'l',
                    "aquil"     : 'e',
                    "aquili"    : 'n',
                    "aquilin"   : 'i',
                    "aquilini"  : 't',
                    "aquilinit" : 'i'
                ]
            ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'should get next letter'() {
    nextLetterSpecs.each {
      prefix, expectedNextLetter ->
        Character nextLetter = strategy.getNext prefix
        assertNotNull nextLetter
        assertEquals "unexpected next letter for '${prefix}'",
            String.valueOf(expectedNextLetter), String.valueOf(nextLetter)
    }
  }
}
