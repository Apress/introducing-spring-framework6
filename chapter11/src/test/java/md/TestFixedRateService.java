package md;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SpringBootTest(classes = TestConfiguration.class)
public class TestFixedRateService extends AbstractTestNGSpringContextTests {
  @Autowired
  FixedRateMonitorService fixedRate;

  @Test
  void fixedRateTest() throws InterruptedException {
    fixedRate.start();
    // fixed rate iterates every second
    Thread.sleep(4000L);
    assertFalse(fixedRate.isWorking());
  }
}
