package hr.msutic.lowfareflights.flightoffer;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import hr.msutic.lowfareflights.airline.AirlineService;
import hr.msutic.lowfareflights.currency.Currency;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author c214223
 */
@Slf4j
@Service
@AllArgsConstructor
public class AmadeusFlightOfferService {

  private Amadeus amadeus;
  private AirlineService airlineService;

  public List<FlightOffer> searchFlightOffers(SearchCommand searchCommand) throws ResponseException {
    log.info("searchCommand.hashCode={}", searchCommand.hashCode());

    Params params = Params.with("origin", searchCommand.getOrigin())
                          .and("destination", searchCommand.getDestination())
                          .and("departureDate", searchCommand.getDepartureDate())
                          .and("max", 200);
    if (searchCommand.getReturnDate() != null) {
      params.and("returnDate", searchCommand.getReturnDate());
    }
    params.and("adults", searchCommand.getAdults());
    params.and("currency", searchCommand.getCurrency());
    log.debug("Calling amadeus api with params={}", params);
    com.amadeus.resources.FlightOffer[] flightOffers = amadeus.shopping.flightOffers.get(params);

    return Arrays.stream(flightOffers)
                 .map(f -> {
                   com.amadeus.resources.FlightOffer.Segment[] segments = f.getOfferItems()[0].getServices()[0].getSegments();
                   com.amadeus.resources.FlightOffer.Segment first = segments[0];
                   com.amadeus.resources.FlightOffer.Segment last = segments[segments.length - 1];

                   return FlightOffer.builder()
                                     .id(f.getId())
                                     .origin(searchCommand.getOrigin())
                                     .destination(searchCommand.getDestination())
                                     .departureDate(searchCommand.getDepartureDate())
                                     .returnDate(searchCommand.getReturnDate())
                                     .numberOfPassengers(searchCommand.getAdults())
                                     .numberOfTransfers(segments.length - 1)
                                     .price(new Price(new Currency(searchCommand.getCurrency()),
                                       String.valueOf(f.getOfferItems()[0].getPrice().getTotal())
                                       , BigDecimal.valueOf(f.getOfferItems()[0].getPrice().getTotal())))
                                     .departure(ZonedDateTime.parse(first.getFlightSegment().getDeparture().getAt()).toLocalDateTime())
                                     .arrival(ZonedDateTime.parse(last.getFlightSegment().getArrival().getAt()).toLocalDateTime())
                                     .airline(this.airlineService.getAirline(first.getFlightSegment().getCarrierCode()))
                                     .build();
                 })
                 .sorted(Comparator.comparing(f -> f.getPrice().getPriceBd()))
                 .collect(Collectors.toList());
  }
}
