package hr.msutic.lowfareflights.airport;

import hr.msutic.lowfareflights.common.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author c214223
 */
@Service
@RequiredArgsConstructor
public class AirportService {

  private final AirportRepository airportRepository;

  public ServiceResult<List<Airport>> findByName(String code) {
    return new ServiceResult<>(true, this.airportRepository.findByPartialName(code));
  }

}
