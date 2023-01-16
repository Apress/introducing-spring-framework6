package md;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
  NotFoundException(Integer id) {
    super(String.format("resource not found: %d",id));
  }
}

@RestController()
public class DocumentController {
  private DocumentRepository dao;

  public DocumentController(DocumentRepository dao) {
    this.dao = dao;
  }

  Document loadDocument(Integer id) {
    var doc = dao.findById(id);
    if (doc.isPresent()) {
      return doc.get();
    }
    throw new NotFoundException(id);
  }

  @GetMapping("documents/{id}")
  @Transactional
  Document get(@PathVariable Integer id) {
    return loadDocument(id);
  }

  @GetMapping("documents")
  @Transactional
  List<Document> getAll() {
    return dao.findAll();
  }

  @PostMapping("documents")
  @Transactional
  Document post(@RequestBody Document document) {
    return dao.save(document);
  }

  @DeleteMapping("documents/{id}")
  @Transactional
  void delete(@PathVariable Integer id) {
    var document = loadDocument(id);
    dao.deleteById(id);
  }

  @PutMapping("documents/{id}")
  @Transactional
  void put(@PathVariable Integer id, @RequestBody Document document) {
    var loadedDocument = loadDocument(id);
    if(id!= document.getId()) {
      throw new NotFoundException(id);
    }
    loadedDocument.setName(document.getName());
    loadedDocument.setLocation(document.getLocation());

    // we don't need an explicit save() because updating a managed
    // entity persists changes when the transaction ends.
  }
}
