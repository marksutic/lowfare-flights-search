package hr.msutic.lowfareflights.airport;

import hr.msutic.lowfareflights.common.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author c214223
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AirportController {

  private final AirportService airportService;

  @GetMapping("/airports")
  public ServiceResult<List<Airport>> getAirports(@RequestParam String name) {
    return airportService.findByName(name);
  }

}
