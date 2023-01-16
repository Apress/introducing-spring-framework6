package md;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsPublisher {
  private final JmsTemplate jmsTemplate;

  public JmsPublisher(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void send(Document document) {
    jmsTemplate.send("documents",
      session -> session.createObjectMessage(document));
  }
}
