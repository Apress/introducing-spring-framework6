package md;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CrontabMonitorService implements SystemMonitor {
  boolean started=false;
  boolean valid=true;
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Scheduled(cron="0 * * * * *")
  void updateWorkingState() {
    if(started) {
      valid=false;
    }
    logger.info(
      String.format(
        "Monitor service status: %b",
        isWorking()));
  }

  @Override
  public void start() {
    started=true;
  }

  @Override
  public boolean isWorking() {
    // it's "working" as long as the tripwire time
    // has not elapsed
    return valid;
  }
}
