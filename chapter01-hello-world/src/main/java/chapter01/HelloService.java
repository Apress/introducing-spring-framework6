package chapter01;

public class HelloService
  implements MessageService {
  @Override
  public String getMessage() {
    return "Hello, world!";
  }
}
