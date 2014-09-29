package com.mystique.ghost.web;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mystique
 */
public class GhostResponse {

  @JsonProperty
  private final String prefix;

  @JsonProperty
  private final Character nextCharacter;

  @JsonProperty
  private final GhostResponseStatus status;

  public GhostResponse(String prefix, Character nextCharacter, GhostResponseStatus status) {
    this.prefix = prefix;
    this.nextCharacter = nextCharacter;
    this.status = status;
  }

  public String getPrefix() {
    return prefix;
  }

  public Character getNextCharacter() {
    return nextCharacter;
  }

  public GhostResponseStatus getStatus() {
    return status;
  }
}
