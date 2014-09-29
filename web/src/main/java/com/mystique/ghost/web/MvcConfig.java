package com.mystique.ghost.web;

import com.mystique.ghost.core.CoreConfig;
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
@ComponentScan("com.mystique.ghost")
public class MvcConfig extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(MvcConfig.class, CoreConfig.class);
  }
}
