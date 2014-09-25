package com.mystique.ghost.core.io

import com.google.common.collect.Iterables
import org.junit.Assert
import org.junit.Test
import static org.junit.Assert.assertNotNull

/**
 * @author mystique
 */
class WordListReaderTest {

  @Test
  def void 'should be able to read word list text file'() {
    def wordListFilePath =  getClass().getResource("/testFile.txt").getFile()
    def reader = new TextWordListReader(wordListFilePath)
    def iterable = reader.read()
    def expectedWords = ["aeithist", "airplane", "air", "agnostic", "bombay", "bucket", "cactus", "cat"]
    Assert.assertTrue("expected reader to return " + expectedWords,
        Iterables.elementsEqual(iterable, expectedWords))
    reader.close()
  }

  @Test
  public void testStreamToString() {
    assertNotNull("Test file missing", getClass().getResource("/testFile.txt"));
  }
}
