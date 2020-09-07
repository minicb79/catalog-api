package com.minicdesign.catalog.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

  private static ObjectMapper objectMapper;

  @Bean
  public ObjectMapper objectMapper() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
    }

    return objectMapper;
  }
}
