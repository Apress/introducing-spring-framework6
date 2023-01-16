package chapter03;

import chapter03.service.SearchEngine;
import org.testng.annotations.Test;

import static chapter03.model.DocumentType.PDF;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public interface MyDocsBaseTest {
  SearchEngine getEngine();

  @Test
  default void testEngineNonNull() {
    assertNotNull(getEngine());
  }

  @Test
  default void testFindByType() {
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
  default void testListAll() {
    var documents = getEngine().listAll();
    assertNotNull(documents);
    assertEquals(documents.size(), 4);
  }

}
