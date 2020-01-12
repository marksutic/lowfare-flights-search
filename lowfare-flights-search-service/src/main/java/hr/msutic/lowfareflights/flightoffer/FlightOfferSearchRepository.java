package hr.msutic.lowfareflights.flightoffer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author c214223
 */
@Repository
public interface FlightOfferSearchRepository extends CrudRepository<FlightOfferSearch, Integer> {

}
