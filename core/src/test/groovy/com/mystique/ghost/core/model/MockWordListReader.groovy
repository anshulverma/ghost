package com.mystique.ghost.core.model

import com.mystique.ghost.core.io.WordListReader

/**
 * @author mystique
 */
class MockWordListReader implements WordListReader{

  final wordList

  public MockWordListReader(wordList = []) {
    this.wordList = wordList
  }

  @Override
  Iterable<String> read() {
    return wordList
  }
}
