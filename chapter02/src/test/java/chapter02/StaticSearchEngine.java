package chapter02;

import chapter02.model.Document;
import chapter02.model.DocumentType;
import chapter02.service.SearchEngine;

import java.util.List;
import java.util.stream.Collectors;

import static chapter02.model.DocumentType.*;

public class StaticSearchEngine implements SearchEngine {
  private final List<Document> data = populate();

  private List<Document> populate() {
    return List.of(
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
    );
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
