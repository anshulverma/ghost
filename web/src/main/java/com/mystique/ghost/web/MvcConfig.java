package com.mystique.ghost.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author mystique
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MvcConfig extends SpringBootServletInitializer {

  private static final Logger LOG = LoggerFactory.getLogger(MvcConfig.class);

  public static void main(String[] args) {
    SpringApplication.run(MvcConfig.class, args);
    LOG.info("Application started...");
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(MvcConfig.class);
  }
}
