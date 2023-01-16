package md;

import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Service
public class EmailService {
  // attributes
  private final JavaMailSender sender;
  private final Properties sessionProperties;
  private final String username;
  private final String password;
  private final int pop3Port;
  private final String pop3Host;

  EmailService(JavaMailSender sender,
               @Value("${pop3.host}")
               String pop3Host,
               @Value("${pop3.port}")
               int pop3Port,
               @Value("${spring.mail.username}")
               String username,
               @Value("${spring.mail.password}")
               String password) {
    this.sender = sender;
    this.username = username;
    this.password = password;
    this.pop3Host = pop3Host;
    this.pop3Port = pop3Port;
    sessionProperties = new Properties();
    sessionProperties.put("mail.pop3.host", pop3Host);
  }

  /**
   * This method is responsible for sending an email
   * via the <code></code>JavaMailSender</code> reference.
   * It uses parameters implicitly used to construct the <code>sender</code>
   * via Spring's application configuration.
   */
  public void send(
    String from,
    String to,
    String subject,
    String content
  ) {
    var message = new SimpleMailMessage();
    message.setFrom(from);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(content);
    sender.send(message);
  }

  /*
   * All of these methods are used to <i>read</i> emails.
   */
  private SimpleMailMessage cloneMessage(Message m) {
    try {
      var msg = new SimpleMailMessage();
      msg.setFrom(m.getFrom()[0].toString());
      msg.setTo(m.getAllRecipients()[0].toString());
      msg.setSubject(m.getSubject());
      msg.setText(m.getContent().toString());

      return msg;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Session getSession() {
    var emailSession = Session.getDefaultInstance(sessionProperties);
    return emailSession;
  }

  private Store getStore(Session session) throws MessagingException {
    var emailStore = session.getStore(
      new URLName("pop3",
        pop3Host,
        pop3Port,
        null,
        username,
        password
      ));
    emailStore.connect();
    return emailStore;
  }

  private Folder getFolder(Store store) throws MessagingException {
    var emailFolder = store.getFolder("INBOX");
    emailFolder.open(Folder.READ_WRITE);
    return emailFolder;
  }

  public void processMessages(
    Predicate<Message> predicate,
    BiConsumer<Folder, Message> consumer) {
    var session = getSession();
    try (var store = getStore(session)) {
      try (var folder = getFolder(store)) {
        var messages = folder.getMessages();
        Arrays
          .stream(messages)
          .filter(predicate)
          .forEach(m -> consumer.accept(folder, m));
      }
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  public List<SimpleMailMessage> getMessages() {
    var messages = new ArrayList<SimpleMailMessage>();
    processMessages(
      message -> true,
      (folder, message) -> {
        SimpleMailMessage m = cloneMessage(message);
        messages.add(m);
      });
    return messages;
  }

  public void deleteMessage(Folder folder, Message message) {
    try {
      var flags = new Flags(Flags.Flag.DELETED);
      folder.setFlags(new Message[]{message}, flags, true);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }
}
