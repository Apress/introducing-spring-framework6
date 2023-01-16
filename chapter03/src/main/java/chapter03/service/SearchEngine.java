package chapter03.service;

import chapter03.model.Document;
import chapter03.model.DocumentType;

import java.util.List;

public interface SearchEngine {
  List<Document> findByType(DocumentType documentType);

  List<Document> listAll();
}
