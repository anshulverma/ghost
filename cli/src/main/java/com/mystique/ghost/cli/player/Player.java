package com.mystique.ghost.cli.player;

import javax.annotation.Nullable;

/**
 * @author mystique
 */
public interface Player {

  @Nullable
  Character play(String prefix);

}
