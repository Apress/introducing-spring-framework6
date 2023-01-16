package c04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

public class Consumer {
  Logger logger= LoggerFactory.getLogger(this.getClass());

  Producer producer;

  Consumer(Producer producer) {
    this.producer=producer;
    logger.info("constructed with producer "
      +Integer.toHexString(producer.hashCode()));
  }

  Producer getProducer() {
    return producer;
  }

  int execute() {
    return producer.execute();
  }
}
