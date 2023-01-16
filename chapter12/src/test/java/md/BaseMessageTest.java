package md;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public abstract class BaseMessageTest extends AbstractTestNGSpringContextTests {
  @Autowired
  MessageService messageService;
  @Autowired
  ApplicationContext context;

  public String getExpectedMessage() {
    return context.getBean("message", String.class);
  }

  @Test
  void testMessageService() {
    assertEquals(
      messageService.getMessage(),
      getExpectedMessage());
  }
}
