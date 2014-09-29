package com.mystique.ghost.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

/**
 * @author mystique
 */
public class MvcBootstrap {

  private static final Logger LOG = LoggerFactory.getLogger(MvcBootstrap.class);

  public static void main(String[] args) {
    SpringApplication.run(MvcConfig.class, args);
    LOG.info("Application started...");
  }
}
