package com.mystique.ghost.cli.player;

import java.util.Map;
import javax.annotation.Nullable;
import com.google.common.collect.Maps;

/**
 * @author mystique
 */
public enum PlayerType {
  HUMAN("human"),
  COMPUTER("computer");

  private static final Map<String, PlayerType> LOOKUP = Maps.newHashMap();

  static {
    for (PlayerType playerType : PlayerType.values()) {
      LOOKUP.put(playerType.name, playerType);
    }
  }

  private final String name;

  PlayerType(String name) {
    this.name = name;
  }

  @Nullable
  public static PlayerType fromName(String name) {
    return LOOKUP.get(name);
  }
}
