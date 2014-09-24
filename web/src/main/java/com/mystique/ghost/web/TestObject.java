package com.mystique.ghost.web;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author mystique
 */
public class TestObject {

  @JsonProperty
  private final String property;

  public TestObject(String property) {
    this.property = property;
  }

  public String getProperty() {
    return property;
  }
}
