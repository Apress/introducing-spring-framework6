package chapter01;

import org.springframework.context.annotation.*;

@Configuration
public class Application {

  @Bean
  MessageService helloWorldMessageService() {
    return new HelloService();
  }

  public static void main(String[] args) {
    var context =
      new AnnotationConfigApplicationContext(Application.class);

    var service =
      context.getBean(MessageService.class);
    System.out.println(service.getMessage());
  }
}
