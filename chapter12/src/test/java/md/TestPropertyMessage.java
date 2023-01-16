package md;

import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

@ContextConfiguration(value = "classpath:/property-context.xml")
@Test
public class TestPropertyMessage extends BaseMessageTest {
}
