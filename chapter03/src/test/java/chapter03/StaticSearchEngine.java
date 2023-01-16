package chapter03;

import chapter03.model.Document;
import chapter03.model.DocumentType;
import chapter03.service.SearchEngine;

import java.util.List;
import java.util.stream.Collectors;

import static chapter03.model.DocumentType.*;

public class StaticSearchEngine implements SearchEngine {
  private List<Document> data;

  public StaticSearchEngine(boolean populate) {
    if (populate) {
      populateData();
    }
  }

  public StaticSearchEngine(List<Document> documents) {
    populateData(documents);
  }

  public StaticSearchEngine() {
    this(false);
  }

  public void populateData(List<Document> documents) {
    this.data = documents;
  }

  public void populateData() {
    populateData(List.of(
      new Document(
        "Book Template.pdf", PDF, "/Docs/Template.pdf"
      ),
      new Document(
        "Apress Home Page", URL, "https://apress.com/"
      ),
      new Document(
        "Chapter Template.doc", DOC, "/Docs/Chapter Sample.doc"
      ),
      new Document(
        "Chapter 01.docx", DOCX, "/Docs/Chapter 01.docx"
      )
    ));
  }

  @Override
  public List<Document> findByType(DocumentType documentType) {
    return data
      .stream()
      .filter(e -> e.getType().equals(documentType))
      .collect(Collectors.toList());
  }

  @Override
  public List<Document> listAll() {
    return data;
  }
}
