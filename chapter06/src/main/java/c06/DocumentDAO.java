package c06;

import java.util.List;

public interface DocumentDAO {
  List<Document> findByType(DocumentType documentType);

  List<Document> listAll();
}
