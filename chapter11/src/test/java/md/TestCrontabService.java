package md;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.testng.Assert.*;

@SpringBootTest(classes = TestConfiguration.class)
public class TestCrontabService extends AbstractTestNGSpringContextTests {
  @Autowired
  CrontabMonitorService crontab;

  @Test
  void crontabTest() throws InterruptedException {
    assertNotNull(crontab);
    var delay = Instant
      .now()
      .truncatedTo(ChronoUnit.MINUTES)
      .plus(1, ChronoUnit.MINUTES)
      .plus(2, ChronoUnit.SECONDS)
      .minusMillis(System.currentTimeMillis())
      .toEpochMilli();
    crontab.start();
    assertTrue(crontab.isWorking());
    Thread.sleep(delay);
    assertFalse(crontab.isWorking());
  }
}
