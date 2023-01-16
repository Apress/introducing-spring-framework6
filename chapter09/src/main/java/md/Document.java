package md;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name="document")
public class Document implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Integer id;
  @Column(nullable = false)
  String name;
  @Column(nullable = false)
  String location;

  Document() {
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
    return new StringJoiner(", ", Document.class.getSimpleName() + "[", "]")
      .add("id=" + id)
      .add("name='" + name + "'")
      .add("location='" + location + "'")
      .toString();
  }
}
