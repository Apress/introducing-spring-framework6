package c06;

public class Document {
  private Integer id;
  private String name;
  private String location;
  private DocumentType type;

  public Document(int id, String name, DocumentType type, String location) {
    this.id=id;
    this.name=name;
    this.type=type;
    this.location=location;
  }
  public Document() {
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

  public DocumentType getType() {
    return type;
  }

  public void setType(DocumentType type) {
    this.type = type;
  }

  public String toString() {
    return String.format(
      "%s[name=%s,type=%s,location=%s]",
      this.getClass().getName(),
      name,
      type,
      location
    );
  }

  // we're not including equals() or hashcode() because this is
  // purely demo code and our tests don't use them.
}
