package md;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FixedRateMonitorService implements SystemMonitor {
  boolean started = false;
  int countdown = 3;
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start() {
    started = true;
  }

  @Override
  public boolean isWorking() {
    // it's "working" as long as the tripwire time
    // has not elapsed
    return countdown > 0;
  }

  @Scheduled(fixedRate = 1000)
  void updateWorkingState() {
    if (started) {
      countdown = Math.max(countdown - 1, 0);

      logger.info(
        String.format(
          "Monitor service countdown: %d, status: %b",
          countdown,
          isWorking()));
    }
  }
}
