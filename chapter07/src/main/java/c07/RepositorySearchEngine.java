package c07;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RepositorySearchEngine implements SearchEngine {
  private final DocumentRepository dao;

  public RepositorySearchEngine(DocumentRepository repository) {
    this.dao = repository;
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
