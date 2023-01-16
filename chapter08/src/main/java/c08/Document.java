package c08;

import jakarta.persistence.*;

@Entity
public class Document {
  @Id
  Integer id;
  @Column(nullable = false)
  String name;
  @Column(nullable = false)
  String location;
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  DocumentType type;

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

  public DocumentType getType() {
    return type;
  }

  public void setType(DocumentType type) {
    this.type = type;
  }
}
