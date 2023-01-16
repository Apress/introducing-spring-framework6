package md;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(value = "classpath:/inline-context.xml")
@Test
public class TestInlineMessage extends BaseMessageTest {
}
