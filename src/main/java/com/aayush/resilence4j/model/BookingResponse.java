package com.aayush.resilence4j.model;

public class BookingResponse {
  String status;

  public BookingResponse(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "BookingResponse{" +
      "status='" + status + '\'' +
      '}';
  }
}
