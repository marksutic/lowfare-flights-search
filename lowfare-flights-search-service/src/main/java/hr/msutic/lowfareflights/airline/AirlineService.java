package hr.msutic.lowfareflights.airline;

import com.amadeus.Amadeus;
import com.amadeus.exceptions.ResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author c214223
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AirlineService {

  private final Amadeus amadeus;
  private Map<String, Airline> airlines;
  private LocalDateTime lastUpdate;

  public Airline getAirline(String iataCode) {
    LocalDateTime current = ZonedDateTime.now().toLocalDateTime();
    log.trace("current {} ", current);
    log.trace("last update {}", this.lastUpdate);
    if (lastUpdate == null || current.isAfter(lastUpdate.plusMinutes(120))) {
      lastUpdate = current;
      this.airlines = loadAirlines();
      log.debug("updated airlines map");
    }
    return this.airlines.get(iataCode);
  }

  private Map<String, Airline> loadAirlines() {
    try {
      com.amadeus.resources.Airline[] airs = this.amadeus.referenceData.airlines.get();
      return Arrays.stream(airs)
                   .map(airline -> Airline.builder()
                                          .iataCode(airline.getIataCode())
                                          .businessName(airline.getBusinessName())
                                          .commonName(airline.getCommonName())
                                          .type(airline.getType())
                                          .build())
                   .collect(Collectors.toMap(Airline::getIataCode, airline -> airline));
    } catch (ResponseException e) {
      log.error("ResponseException", e);
      return Collections.emptyMap();
    }
  }


}
