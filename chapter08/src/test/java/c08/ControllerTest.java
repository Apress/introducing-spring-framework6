package c08;

import org.springframework.validation.support.BindingAwareModelMap;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ControllerTest {
  @Test
  public void testSearchController() {
    var engine=new SearchEngine() {
      // left empty because we don't use it in the controller.
      @Override
      public List<Document> findByType(DocumentType documentType) {
        return null;
      }

      @Override
      public List<Document> listAll() {
        return List.of(new Document());
      }
    };
    var controller=new SearchController();
    var model=new BindingAwareModelMap();
    controller.setEngine(engine);

    var view=controller.searchAll(model);

    @SuppressWarnings("unchecked")
    List<Document> docs= (List<Document>) model.getAttribute("docs");

    assertNotNull(docs);
    assertEquals(docs.size(),1);
    assertEquals(view, "search/all");
  }
}
