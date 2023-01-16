package chapter03;

import chapter03.service.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@Configuration
@ComponentScan(basePackages = "chapter03.service")
class MyDocsScanConfig {
}

@ContextConfiguration(classes={MyDocsScanConfig.class})
@Test
public class MyDocsScanTest
  extends MyDocsContextBaseTest {
  @Autowired
  SearchEngine engine;

  @Override
  public SearchEngine getEngine() {
    return engine;
  }
}
