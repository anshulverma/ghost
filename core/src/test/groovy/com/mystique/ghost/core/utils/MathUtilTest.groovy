package com.mystique.ghost.core.utils

import org.junit.Assert
import org.junit.Test

/**
 * @author mystique
 */
class MathUtilTest {

  @Test
  public void 'test number addition'() {
    Assert.assertEquals 0,   MathUtils.sum(null), 0
    Assert.assertEquals 0,   MathUtils.sum([]), 0
    Assert.assertEquals 21D, MathUtils.sum([21D]), 0
    Assert.assertEquals 79D, MathUtils.sum([2D, 5D, 6D, 12D, 54D]), 0
  }
}
