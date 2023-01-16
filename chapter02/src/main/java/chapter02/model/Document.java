package chapter02.model;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Document {
  private final String name;
  private final DocumentType type;
  private final String location;
  private final LocalDate created;
  private LocalDate modified;

  public Document(String name, DocumentType type, String location) {
    this.name = name;
    this.type = type;
    this.location = location;
    created = LocalDate.now();
    modified = LocalDate.now();
  }

  public String getName() {
    return name;
  }

  public DocumentType getType() {
    return type;
  }

  public String getLocation() {
    return location;
  }

  public LocalDate getCreated() {
    return created;
  }

  public LocalDate getModified() {
    return modified;
  }

  public void setModified(LocalDate modified) {
    this.modified = modified;
  }

  public String toString() {
    return String.format(
      "%s[name=%s,type=%s,location=%s,created=%tD,modified=%tD]",
      this.getClass().getName(),
      name,
      type,
      location,
      created,
      modified
    );
  }
}
