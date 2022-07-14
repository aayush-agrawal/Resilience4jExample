package com.aayush.resilence4j.services;

import com.aayush.resilence4j.exceptions.SeatsUnavailableException;
import com.aayush.resilence4j.model.BookingRequest;
import com.aayush.resilence4j.model.BookingResponse;
import com.aayush.resilence4j.services.failures.NoFailure;
import com.aayush.resilence4j.services.failures.PotentialFailure;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FlightBookingService {
  PotentialFailure potentialFailure = new NoFailure();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");

  public BookingResponse bookFlight(BookingRequest request) throws SeatsUnavailableException {
    System.out.println("Booking flight; current time = " + LocalDateTime.now().format(formatter));
    potentialFailure.occur();

    if (request.getFlight().getFlightNumber().contains("765")) {
      potentialFailure.occur();
      throw new SeatsUnavailableException("No seats available");
    }
    // book seats on flight
    System.out.println("Flight booking successful");
    return new BookingResponse("success");
  }

  public void setPotentialFailure(PotentialFailure potentialFailure) {
    this.potentialFailure = potentialFailure;
  }
}
