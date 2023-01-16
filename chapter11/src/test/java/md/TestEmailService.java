package md;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.testng.Assert.assertTrue;

@SpringBootTest(classes = TestConfiguration.class)
public class TestEmailService extends AbstractTestNGSpringContextTests {
  @Autowired
  EmailService mailService;

  @Value("${mail.address}")
  String source;
  String title = UUID.randomUUID().toString();

  @Test
  void testSendEmail() {
    mailService.send(
      source,
      source,
      title,
      "This is a sample email, sent at " + LocalDate.now()
    );
  }

  @Test(dependsOnMethods = "testSendEmail")
  void checkReceiveEmailFromInbox() {
    var messages = mailService.getMessages();
    boolean found = false;
    for (var message : messages) {
      if (title.equals(message.getSubject())) {
        found = true;
      }
    }
    assertTrue(found, "failed to find message with matching title");
  }

  @Test(dependsOnMethods = "testSendEmail")
  void checkReceivedEmailByTitle() {
    var found = new AtomicBoolean(false);
    mailService.processMessages(message -> {
        try {
          return message.getSubject().equals(title);
        } catch (MessagingException e) {
          throw new RuntimeException(e);
        }
      },
      (folder, message) -> {
        mailService.deleteMessage(folder, message);
        found.set(true);

      });
    assertTrue(found.get(), "failed to find message with matching title");
  }
}
