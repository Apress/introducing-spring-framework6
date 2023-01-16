package c04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class AnnotatedProducer {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  int executions = 0;

  AnnotatedProducer() {
    logger.info("constructed");
  }

  int execute() {
    return ++executions;
  }
}
