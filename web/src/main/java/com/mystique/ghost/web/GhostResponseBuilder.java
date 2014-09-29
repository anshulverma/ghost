package com.mystique.ghost.web;

import com.mystique.ghost.core.model.CharacterContext;

/**
 * @author mystique
 */
public class GhostResponseBuilder {
  private CharacterContext characterContext;
  private String prefix;

  public GhostResponseBuilder setCharacterContext(CharacterContext characterContext) {
    this.characterContext = characterContext;
    return this;
  }

  public GhostResponseBuilder setPrefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

  public GhostResponse build() {
    GhostResponseStatus status = GhostResponseStatus.SUCCESS;
    if (characterContext == CharacterContext.INVALID) {
      status = GhostResponseStatus.INVALID;
    } else if (characterContext == CharacterContext.NULL) {
      status = GhostResponseStatus.PREFIX_COMPLETE;
    } else if (characterContext.isLeaf()) {
      status = GhostResponseStatus.COMPLETE;
    }
    return new GhostResponse(prefix, characterContext.getValue(), status);
  }
}
