package md;

public interface SystemMonitor {
  default boolean isWorking() {
    return true;
  }

  default void start() {
  }
}
