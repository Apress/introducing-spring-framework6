package chapter03.model;

public enum DocumentType {
  PDF("PDF", "Portable Document Format", ".pdf"),
  DOCX("DOCX", "Word Document", ".docx"),
  URL("URL", "Universal Resource Locator", ""),
  DOC("DOC", "Word Document", ".doc"),
  NOTE("NOTE", "Ancillary note", "");

  public final String name;
  public final String desc;
  public final String extension;

  private DocumentType(String name, String desc, String extension) {
    this.name=name;
    this.desc=desc;
    this.extension=extension;
  }
}
