package c08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositorySearchEngine implements SearchEngine {
  private final DocumentRepository dao;

  public RepositorySearchEngine(@Autowired DocumentRepository repository) {
    this.dao=repository;
  }

  public DocumentRepository getDao() {
    return dao;
  }

  @Override
  public List<Document> findByType(DocumentType documentType) {
    return getDao().findByType(documentType);
  }

  @Override
  public List<Document> listAll() {

    var documents = new ArrayList<Document>();
    getDao().findAll().forEach(documents::add);
    return documents;
  }
}
