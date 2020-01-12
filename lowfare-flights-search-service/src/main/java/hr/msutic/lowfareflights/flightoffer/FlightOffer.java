package hr.msutic.lowfareflights.flightoffer;

import hr.msutic.lowfareflights.airline.Airline;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Marko
 */
@Getter
@Builder
@ToString
public class FlightOffer implements Serializable {

  private String id;
  private String origin;
  private String destination;
  private LocalDate departureDate;
  private LocalDateTime departure;
  private LocalDate returnDate;
  private LocalDateTime arrival;
  private int numberOfPassengers;
  private int numberOfTransfers;
  private Price price;
  private Airline airline;

}
