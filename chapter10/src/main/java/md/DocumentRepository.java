package md;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository
  extends CrudRepository<Document, Integer> {
  List<Document> findAll();
  @Query("select max(d.id) from Document d")
  Integer findMaxId();
}
