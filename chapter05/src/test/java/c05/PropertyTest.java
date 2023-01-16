package c05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Configuration
@PropertySource("classpath:/menu.properties")
class PropertyConfiguration {
  @Value("${app.title}")
  String title;

  @Bean
  String getTitle() {
    return title;
  }
}

@ContextConfiguration(classes = {PropertyConfiguration.class})
public class PropertyTest
extends AbstractTestNGSpringContextTests {
  @Autowired
  String title;

  @Test
  void testTitle() {
    assertEquals(title, "My Documents");
  }
}
