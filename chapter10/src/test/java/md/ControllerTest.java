package md;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest
  extends AbstractTestNGSpringContextTests {
  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private DocumentRepository dao;

  private String buildUrl() {
    return String.format("http://localhost:%d/documents", port);
  }

  private Document getFirstDocument() {
    var allDocs = dao.findAll();
    var first = allDocs.get(0);
    return first;
  }

  private Document getDocument(int id) {
    var resp = restTemplate
      .getForEntity(buildUrl() + "/" + id, Document.class);
    assertEquals(resp.getStatusCode(), HttpStatus.OK);
    return resp.getBody();
  }

  @Test
  void testGetDocument() {
    Document first = getFirstDocument();
    var document = getDocument(first.getId());
    assertEquals(document.getName(), first.getName());
  }

  @Test
  void testGetBadDocument() {
    var highest = dao.findMaxId();
    var resp = restTemplate
      .getForEntity(buildUrl() + "/" + (highest + 1), Document.class);
    assertEquals(resp.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  void testGetAllDocuments() {
    var allDocs = dao.findAll();
    var resp = restTemplate
      .getForEntity(buildUrl(), Document[].class);
    var documents = resp.getBody();
    assertEquals(allDocs.size(), documents.length);
  }

  @Test
  void testPostDocument() {
    // preserve the original list of documents
    var allDocs = dao.findAll();
    var newDocument = new Document("new document", "/new document.docx");
    var resp = restTemplate
      .postForEntity(buildUrl(), newDocument, Document.class);
    var savedDocument = resp.getBody();
    var allDocsAfterPOST = dao.findAll();
    assertEquals(allDocsAfterPOST.size(), allDocs.size() + 1);
  }

  @Test
  void testDeleteDocument() {
    // preserve the original list of documents
    var allDocs = dao.findAll();
    var first = allDocs.get(0);
    restTemplate.delete(buildUrl() + "/" + first.getId());

    var allDocsAfterPOST = dao.findAll();
    assertEquals(allDocsAfterPOST.size(), allDocs.size() - 1);
  }

  @Test
  void testPutDocument() {
    var first = getFirstDocument();
    // we are copying the document over to a new copy
    var updatedDocument = new Document(first.getName(), "/updated location");
    updatedDocument.setId(first.getId());
    restTemplate.put(buildUrl() + "/" + first.getId(), updatedDocument);
    var updated = getDocument(first.getId());
    assertEquals(updated.getName(), first.getName());
    assertEquals(updated.getLocation(), "/updated location");
  }

  @Test
  void testPutDocumentWithBadId() {
    var first = getFirstDocument();
    // we are copying the document over to a new copy
    var updatedDocument = new Document(first.getName(), "/another location");

    // mangle the id.
    updatedDocument.setId(first.getId()+1);

    restTemplate.put(buildUrl() + "/" + first.getId(), updatedDocument);

    // it should be the same entity: no changes applied
    var updated = getDocument(first.getId());
    assertEquals(updated.getName(), first.getName());
    assertEquals(updated.getLocation(), "/updated location");
  }

}
