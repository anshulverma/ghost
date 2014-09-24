package com.mystique.ghost.core.io

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import org.junit.Assert
import org.junit.Test
/**
 * @author mystique
 */
class WordListReaderTest {

  @Test
  def void 'should be able to read word list text file'() {
    def wordListFilePath = '/word.list.txt'
    def expectedWords = Lists.newArrayList("ad");
    def reader = new TextWordListReader(wordListFilePath)
    def iterable = reader.read()
    Assert.assertTrue("expected reader to return " + expectedWords,
        Iterables.elementsEqual(iterable, expectedWords))
  }
}
