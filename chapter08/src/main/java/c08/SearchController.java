package c08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {
  private SearchEngine engine;

  @Autowired
  public void setEngine(SearchEngine engine) {
    this.engine = engine;
  }

  @GetMapping(value = "/all")
  public String searchAll(Model model) {
    model.addAttribute("docs", engine.listAll());
    return "search/all";
  }
}
