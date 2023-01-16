package c06;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DocumentJDBCDAO implements DocumentDAO {
  private JdbcTemplate template;

  public void setTemplate(JdbcTemplate template) {
    this.template = template;
  }

  private JdbcTemplate getTemplate() {
    return template;
  }

  @Override
  public List<Document> listAll() {
    return getTemplate()
      .query(
        "select * from document",
        new DocumentMapper()
      );
  }

  @Override
  public List<Document> findByType(DocumentType documentType) {
    return getTemplate()
      .query("select * from document d where d.type=?",
        ps -> ps.setString(1, documentType.name()),
        new DocumentMapper()
      );

  }


}
