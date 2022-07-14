package com.aayush.resilence4j.exceptions;

public class RateLimitExceededException extends FlightServiceException {
  String errorCode;

  public RateLimitExceededException(String message, String errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
