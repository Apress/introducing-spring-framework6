package c07;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository
  extends CrudRepository<Document, Integer> {
  List<Document> findByType(DocumentType type);
}
