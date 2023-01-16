package c04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Producer {
  Logger logger = LoggerFactory.getLogger(this.getClass());
  int executions = 0;

  Producer() {
    logger.info("constructed as "+Integer.toHexString(hashCode()));
  }

  int execute() {
    return ++executions;
  }
}
