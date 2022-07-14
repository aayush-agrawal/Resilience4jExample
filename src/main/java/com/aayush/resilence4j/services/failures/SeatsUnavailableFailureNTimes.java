package com.aayush.resilence4j.services.failures;

import com.aayush.resilence4j.exceptions.SeatsUnavailableException;

public class SeatsUnavailableFailureNTimes implements PotentialFailure {
  int times;
  int failedCount;

  public SeatsUnavailableFailureNTimes(int times) {
    this.times = times;
  }

  @Override
  public void occur() {
    if (failedCount++ < times) {
      System.out.println("Seats not available");
      throw new SeatsUnavailableException("Seats not available");
    }
  }
}
