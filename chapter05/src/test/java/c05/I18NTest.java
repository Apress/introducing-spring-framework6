package c05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.assertEquals;

@Configuration
class I18NConfiguration {
  @Bean("messages")
  MessageSource getMessageSource() {
    var messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("i18nmenu");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
}

@ContextConfiguration(classes = {I18NConfiguration.class})
public class I18NTest
  extends AbstractTestNGSpringContextTests {
  @Autowired
  MessageSource messages;

  @DataProvider
  Object[][] getTitleTranslations() {
    return new Object[][]{
      {"en", "My Documents"},
      {"nl", "Mijn Documenten"}
    };
  }

  @DataProvider
  Object[][] getCopyrightTranslations() {
    return new Object[][]{
      {"en"},
      {"nl"},
    };
  }

  @DataProvider
  Object[][] getHelloTranslations() {
    return new Object[][]{
      {"en", "Hello, Joseph", "Joseph"},
      {"nl", "Hallo, Mattijs", "Mattijs"}
    };
  }

  @Test(dataProvider = "getTitleTranslations")
  void testTitle(String encoding, String translation) {
    assertEquals(
      messages.getMessage("app.title", null, new Locale(encoding)),
      translation);
  }

  @Test(dataProvider = "getHelloTranslations")
  void testGreeting(String encoding, String translation, String name) {
    assertEquals(
      messages.getMessage(
        "app.greeting",
        new Object[]{name},
        new Locale(encoding)
      ),
      translation);
  }

  @Test(dataProvider = "getCopyrightTranslations")
  void testCopyright(String encoding) {
    assertEquals(
      messages.getMessage("app.copyright", null, new Locale(encoding)),
      "(c) 2022 by Apress Media"
    );
  }
}
