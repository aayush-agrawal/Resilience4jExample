package com.aayush.resilence4j.services.delays;

public class AlwaysSlowNSeconds implements PotentialDelay {
  int delayInSeconds;

  public AlwaysSlowNSeconds(int delayInSeconds) {
    this.delayInSeconds = delayInSeconds;
  }

  @Override
  public void occur() {
    try {
      Thread.sleep(delayInSeconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
