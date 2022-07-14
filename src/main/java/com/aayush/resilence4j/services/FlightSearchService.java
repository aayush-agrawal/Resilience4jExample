package com.aayush.resilence4j.services;

import com.aayush.resilence4j.model.Flight;
import com.aayush.resilence4j.model.SearchRequest;
import com.aayush.resilence4j.model.SearchResponse;
import com.aayush.resilence4j.services.delays.NoDelay;
import com.aayush.resilence4j.services.delays.PotentialDelay;
import com.aayush.resilence4j.services.failures.NoFailure;
import com.aayush.resilence4j.services.failures.PotentialFailure;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class FlightSearchService {

  PotentialFailure potentialFailure = new NoFailure();
  PotentialDelay potentialDelay = new NoDelay();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
  Random random = new Random();

  public List<Flight> searchFlights(SearchRequest request) {
    System.out.println("Searching for flights; current time = " + LocalDateTime.now().format(formatter));
    potentialDelay.occur();
    potentialFailure.occur();

    List<Flight> flights = Arrays.asList(
      new Flight("XY 765", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 781", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 732", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 746", request.getFlightDate(), request.getFrom(), request.getTo())
    );
    System.out.println("Flight search successful");
    return flights;
  }

  public List<Flight> searchFlightsTakingTwoSeconds(SearchRequest request) {
    System.out.println("Searching for flights; current time = " + LocalDateTime.now().format(formatter));
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    List<Flight> flights = Arrays.asList(
      new Flight("XY 765", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 781", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 732", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 746", request.getFlightDate(), request.getFrom(), request.getTo())
    );
    System.out.println("Flight search successful");
    return flights;
  }

  public List<Flight> searchFlightsThrowingException(SearchRequest request) throws Exception {
    System.out.println("Searching for flights; current time = " + LocalDateTime.now().format(formatter));
    throw new Exception("Exception when searching for flights");
  }

  public SearchResponse httpSearchFlights(SearchRequest request) throws IOException {
    System.out.println("Searching for flights; current time = " + LocalDateTime.now().format(formatter));
    potentialFailure.occur();

    String date = request.getFlightDate();
    String from = request.getFrom();
    String to = request.getTo();
    if (request.getFlightDate().equals("07/25/2020")) { // Simulating an error scenario
      System.out.println("Flight data initialization in progress, cannot search at this time");
      SearchResponse response = new SearchResponse();
      response.setErrorCode("FS-167");
      response.setFlights(Collections.emptyList());
      return response;
    }

    List<Flight> flights = Arrays.asList(
      new Flight("XY 765", date, from, to),
      new Flight("XY 781", date, from, to),
      new Flight("XY 732", date, from, to),
      new Flight("XY 746", date, from, to)
    );
    System.out.println("Flight search successful");
    SearchResponse response = new SearchResponse();
    response.setFlights(flights);
    return response;
  }

  public List<Flight> searchFlightsTakingRandomTime(SearchRequest request) {
    long delay = random.nextInt(3000);
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Searching for flights; "
      + "current time = " + LocalDateTime.now().format(formatter) +
      "; current thread = " + Thread.currentThread().getName());

    List<Flight> flights = Arrays.asList(
      new Flight("XY 765", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 746", request.getFlightDate(), request.getFrom(), request.getTo())
    );
    System.out.println("Flight search successful");
    return flights;
  }

  public List<Flight> searchFlightsTakingOneSecond(SearchRequest request) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Searching for flights; "
      + "current time = " + LocalDateTime.now().format(formatter) +
      "; current thread = " + Thread.currentThread().getName());

    List<Flight> flights = Arrays.asList(
      new Flight("XY 765", request.getFlightDate(), request.getFrom(), request.getTo()),
      new Flight("XY 746", request.getFlightDate(), request.getFrom(), request.getTo())
    );
    System.out.println("Flight search successful at " + LocalDateTime.now().format(formatter));
    return flights;
  }

  public void setPotentialFailure(PotentialFailure potentialFailure) {
    this.potentialFailure = potentialFailure;
  }

  public void setPotentialDelay(PotentialDelay potentialDelay) {
    this.potentialDelay = potentialDelay;
  }
}
