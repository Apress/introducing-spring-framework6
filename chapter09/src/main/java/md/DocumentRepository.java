package md;

import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository
  extends CrudRepository<Document, Integer> {
}
