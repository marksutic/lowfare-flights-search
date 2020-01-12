package hr.msutic.lowfareflights.flightoffer;

import com.amadeus.exceptions.ResponseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.msutic.lowfareflights.common.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author c214223
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FlightOfferService {

  private final AmadeusFlightOfferService amadeusFlightOfferService;
  private final FlightOfferSearchRepository flightOfferSearchRepository;
  private final ObjectMapper objectMapper;

  @SneakyThrows
  public ServiceResult<List<FlightOffer>> searchFlightOffers(SearchCommand searchCommand) throws ResponseException {
    log.debug("start searchFlightOffers");
    Optional<FlightOfferSearch> search = this.flightOfferSearchRepository.findById(searchCommand.hashCode());

    if (search.isPresent()) {
      log.debug("return cached result for search id: {}", searchCommand.hashCode());
      return new ServiceResult<>(true, objectMapper.readValue(search.get().getResult(), new TypeReference<>() {
      }));
    } else {
      log.debug("new search with amadeus api");
      final List<FlightOffer> flightOffers = amadeusFlightOfferService.searchFlightOffers(searchCommand);
      FlightOfferSearch newSearch = new FlightOfferSearch();
      newSearch.setId(searchCommand.hashCode());
      newSearch.setResult(objectMapper.writeValueAsString(flightOffers));
      log.debug("saving found flightOffers to db");
      this.flightOfferSearchRepository.save(newSearch);
      return new ServiceResult<>(true, flightOffers);
    }
  }

}
