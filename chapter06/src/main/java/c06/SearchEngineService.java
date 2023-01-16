package c06;

import java.util.List;

public class SearchEngineService implements SearchEngine {
  private DocumentDAO dao;

  public void setDao(DocumentDAO dao) {
    this.dao = dao;
  }

  public DocumentDAO getDao() {
    return dao;
  }

  @Override
  public List<Document> findByType(DocumentType documentType) {
    return getDao().findByType(documentType);
  }

  @Override
  public List<Document> listAll() {
    return getDao().listAll();
  }
}
