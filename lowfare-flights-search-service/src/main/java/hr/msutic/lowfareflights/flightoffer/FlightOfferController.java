package hr.msutic.lowfareflights.flightoffer;

import com.amadeus.exceptions.ResponseException;
import hr.msutic.lowfareflights.common.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author c214223
 */
@Slf4j
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class FlightOfferController {

  private final FlightOfferService flightOfferService;

  @GetMapping("/flight-offers")
  ServiceResult<List<FlightOffer>> searchFlightOffers(@RequestParam String origin,
                                                      @RequestParam String destination,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate,
                                                      @RequestParam(required = false) String adults,
                                                      @RequestParam(required = false) String currency) throws ResponseException {
    log.info("origin={}, destination={}, departureDate={}, returnDate={}, adults={}, currency={}"
      , origin, destination, departureDate, returnDate, adults, currency);
    String c = StringUtils.isBlank(currency) ? "EUR" : currency;
    int adultsP = (adults == null || adults.equals("0") ) ? 1 : Integer.parseInt(adults);
    return flightOfferService.searchFlightOffers(new SearchCommand(origin, destination, departureDate, returnDate, adultsP, c));
  }

}
