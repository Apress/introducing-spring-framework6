package c07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.sql.DataSource;

import static c07.DocumentType.PDF;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@ContextConfiguration(classes = {MyDocsConfiguration.class})
@DataJpaTest
@EnableAutoConfiguration
public class
MyDocsTest extends AbstractTestNGSpringContextTests {
  @Autowired
  SearchEngine engine;

  @Autowired
  DataSource ds;
  SearchEngine getEngine() {
    return engine;
  };

  @Test
  void testEngineNonNull() {
    assertNotNull(ds);
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
