package chapter03;

import chapter03.service.SearchEngine;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static chapter03.model.DocumentType.PDF;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MyDocsAllXMLsTest {
  @DataProvider
  Object[][] getConfigs() {
    return new Object[][]{
      new Object[]{"classpath:/documents.xml"},
      new Object[]{"classpath:/doccons.xml"},
      new Object[]{"classpath:/docdata.xml"},
      new Object[]{"classpath:/docscan.xml"}};
  }

  @Test(dataProvider = "getConfigs")
  public void testSpring(String configLocation) {
    var context=
      new ClassPathXmlApplicationContext(configLocation);

    var engine=context.getBean(SearchEngine.class);
    assertNotNull(engine);
    assertEquals(engine.listAll().size(), 4);
    assertEquals(
      engine.findByType(PDF).get(0).getName(),
      "Book Template.pdf");
  }
}
