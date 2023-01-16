package chapter03;

import chapter03.service.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(locations={"/docdata.xml"})
@Test
public class MyDocsXMLDataTest
  extends MyDocsContextBaseTest {
  @Autowired
  SearchEngine engine;

  @Override
  public SearchEngine getEngine() {
    return engine;
  }
}
