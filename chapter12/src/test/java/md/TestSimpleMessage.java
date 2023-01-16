package md;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(value = "classpath:/simple-context.xml")
@Test
public class TestSimpleMessage extends BaseMessageTest {
  @Override
  public String getExpectedMessage() {
    return "hello world, man";
  }
}
