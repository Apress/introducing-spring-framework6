package c06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


import static c06.DocumentType.PDF;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@ContextConfiguration(locations={"classpath:/explicit.xml"})
public class MyDocsTest extends AbstractTestNGSpringContextTests {
  @Autowired
  SearchEngine engine;

  SearchEngine getEngine() {
    return engine;
  };

  @Test
  void testEngineNonNull() {
    assertNotNull(getEngine());
  }

  @Test
  void testFindByType() {
    var documents = getEngine().findByType(PDF);
    assertNotNull(documents);
    assertEquals(documents.size(), 1);
    assertEquals(PDF.name,
      documents.get(0).getType().name);
    assertEquals(PDF.desc,
      documents.get(0).getType().desc);
    assertEquals(PDF.extension,
      documents.get(0).getType().extension);
  }

  @Test
  void testListAll() {
    var documents = getEngine().listAll();
    assertNotNull(documents);
    assertEquals(documents.size(), 4);
  }

}
