package chapter03;

import chapter03.service.SearchEngine;
import org.testng.annotations.Test;

@Test
public class MyDocsJavaTest
  implements MyDocsBaseTest {
  SearchEngine engine = new StaticSearchEngine(true);

  @Override
  public SearchEngine getEngine() {
    return engine;
  }
}
