package hr.msutic.lowfareflights.flightoffer;

import lombok.Value;

import java.time.LocalDate;

@Value
public class SearchCommand {

  private final String origin;
  private final String destination;
  private final LocalDate departureDate;
  private final LocalDate returnDate;
  private final int adults;
  private final String currency;

}
