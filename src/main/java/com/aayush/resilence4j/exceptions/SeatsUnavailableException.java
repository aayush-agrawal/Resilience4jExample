package com.aayush.resilence4j.exceptions;

public class SeatsUnavailableException extends FlightServiceException {
  public SeatsUnavailableException(String message) {
    super(message);
  }
}
