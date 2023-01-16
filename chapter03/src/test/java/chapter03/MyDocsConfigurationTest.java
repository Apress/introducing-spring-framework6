package chapter03;

import chapter03.service.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@Configuration
class MyDocsConfig {
  @Bean
  SearchEngine getEngineBean() {
    return new StaticSearchEngine(true);
  }
}

@ContextConfiguration(classes={MyDocsConfig.class})
@Test
public class MyDocsConfigurationTest
  extends MyDocsContextBaseTest {
  @Autowired
  SearchEngine engine;

  @Override
  public SearchEngine getEngine() {
    return engine;
  }
}
