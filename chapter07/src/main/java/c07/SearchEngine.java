package c07;

import java.util.List;

public interface SearchEngine {
  List<Document> findByType(DocumentType documentType);

  List<Document> listAll();
}
