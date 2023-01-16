package chapter02;

import chapter02.service.SearchEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
  @Bean
  SearchEngine getEngine() {
    return new StaticSearchEngine();
  }
}
