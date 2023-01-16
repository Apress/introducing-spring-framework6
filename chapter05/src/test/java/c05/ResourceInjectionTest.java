package c05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

@Configuration
class ResourceInjectionConfig {
  @Bean
  public Resource getHelloWorldResource() {
    return new DefaultResourceLoader()
      .getResource("classpath:hello.txt");
  }

  @Bean
  String getContent(Resource resource) throws IOException {
    try(var scanner = new Scanner(
      resource.getInputStream(),
      StandardCharsets.UTF_8)
    ) {
      scanner.useDelimiter("\\A");
      return scanner.hasNext()?scanner.next().trim():"";
    }

  }
}

@ContextConfiguration(classes = {ResourceInjectionConfig.class})
public class ResourceInjectionTest
  extends AbstractTestNGSpringContextTests {
  @Autowired
  String helloWorld;

  @Test
  void testHelloWorld() {
    assertEquals(helloWorld, "Hello, world");
  }
}
