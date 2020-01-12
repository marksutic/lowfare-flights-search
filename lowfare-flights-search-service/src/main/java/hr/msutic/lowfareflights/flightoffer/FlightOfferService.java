package hr.msutic.lowfareflights.flightoffer;

import com.amadeus.exceptions.ResponseException;
import hr.msutic.lowfareflights.common.ServiceResult;

import java.util.List;

/**
 * @author Marko
 */
public interface FlightOfferService {

  ServiceResult<List<FlightOffer>> searchFlightOffers(SearchCommand searchCommand) throws ResponseException;

}
