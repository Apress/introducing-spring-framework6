package chapter01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class HelloWorldController {
  @GetMapping(path = "/", produces = "text/plain")
  @ResponseBody
  public String getMessage() {
    return "Hello, world!";
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloWorldController.class,args);
  }
}
