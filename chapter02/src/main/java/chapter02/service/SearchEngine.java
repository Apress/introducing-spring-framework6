package chapter02.service;

import chapter02.model.Document;
import chapter02.model.DocumentType;

import java.util.List;

public interface SearchEngine {
  List<Document> findByType(DocumentType documentType);

  List<Document> listAll();
}
