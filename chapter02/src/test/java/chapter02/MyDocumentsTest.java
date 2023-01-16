package chapter02;

import chapter02.service.SearchEngine;
import org.testng.annotations.Test;

import static chapter02.model.DocumentType.PDF;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class MyDocumentsTest {
  SearchEngine engine = new StaticSearchEngine();

  @Test
  public void testFindByType() {
    var documents = engine.findByType(PDF);
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
  public void testListAll() {
    var documents = engine.listAll();
    assertNotNull(documents);
    assertEquals(documents.size(), 4);
  }

}
