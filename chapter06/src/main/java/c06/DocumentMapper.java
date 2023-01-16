package c06;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class DocumentMapper implements RowMapper<Document> {
  @Override
  public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
    Document document=new Document();
    document.setId(rs.getInt("id"));
    document.setLocation(rs.getString("location"));
    document.setType(DocumentType.valueOf(rs.getString("type")));
    document.setName(rs.getString("name"));
    return document;
  }
}
