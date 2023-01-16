package md;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name="document")
public class Document {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;
  @Column(nullable = false)
  String name;
  @Column(nullable = false)
  String location;

  public Document() {
  }

  Document(String name, String location) {
    setName(name);
    setLocation(location);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    return String.format(
      "%s[id=%d,name='%s',location='%s']",
      Document.class.getSimpleName(),
      id,
      name,
      location);
  }
}
