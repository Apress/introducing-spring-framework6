package c04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class ScopesTest {
  @DataProvider
  public Object[][] getConfigurations() {
    var resolver =
      ResourcePatternUtils.getResourcePatternResolver(null);
    try {
      var resourceNames = Arrays
        .stream(resolver.getResources("classpath*:*.xml"))
        .map(Resource::getFilename)
        .map(e -> new String[]{e})
        .toList()
        .toArray(new Object[0][]);
      return resourceNames;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  boolean getValue(ApplicationContext context,
                   String name) {
    try {
      return context.getBean(name, Boolean.class);
    } catch (Exception ignored) {
      return false;
    }
  }

  @Test(dataProvider = "getConfigurations")
  public void testConfiguration(String configName) {
    var context =
      new ClassPathXmlApplicationContext(configName);
    var p1 = context.getBean(Producer.class);
    var p2 = context.getBean(Producer.class);
    var c1 = context.getBean(Consumer.class);
    var c2 = context.getBean(Consumer.class);
    var producerPrototype = getValue(context, "producerBean");
    var consumerPrototype = getValue(context, "consumerBean");

    assertEquals(p1 == p2, !producerPrototype);
    assertEquals(c1 == c2, !consumerPrototype);

    // the consumer is the *same reference* if
    // consumerPrototype is true, so the
    // producerPrototype is not used more than once.
    assertEquals(c1.getProducer() == c2.getProducer(),
      !(producerPrototype && consumerPrototype));
    // if producerPrototype is true, then the sequence will fail
    assertEquals((p1.execute() + 1) == p2.execute(), !producerPrototype);
  }
}
