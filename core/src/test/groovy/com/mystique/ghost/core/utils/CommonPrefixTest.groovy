package com.mystique.ghost.core.utils

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import static org.apache.commons.lang3.StringUtils.EMPTY
/**
 * @author mystique
 */
@RunWith(Parameterized.class)
class CommonPrefixTest {
  final String word1
  final String word2
  final String commonPrefix

  def CommonPrefixTest(String word1, String word2, String commonPrefix) {
    this.word1 = word1
    this.word2 = word2
    this.commonPrefix = commonPrefix
  }

  @Parameterized.Parameters
  static Collection<Object[]> 'test parameters'() {
    Object[][] testCases =
        [
          [
            "something",
            "someone",
            "some"
          ],
          [
            "test",
            "case",
            EMPTY
          ],
          [
            EMPTY,
            "test",
            EMPTY
          ],
          [
            "test",
            EMPTY,
            EMPTY
          ],
          [
            EMPTY,
            EMPTY,
            EMPTY
          ]
        ]
    Arrays.asList(testCases)
  }

  @Test
  def void 'check common prefix util'() {
    Assert.assertEquals commonPrefix, WordUtils.commonPrefix(word1, word2)
  }
}
