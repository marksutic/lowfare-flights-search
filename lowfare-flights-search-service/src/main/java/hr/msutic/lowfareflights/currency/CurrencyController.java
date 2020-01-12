package hr.msutic.lowfareflights.currency;

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
public class CurrencyController {

  private final CurrencyService currencyService;

  @GetMapping("/currencies")
  public ServiceResult<List<Currency>> getAirports() {
    return currencyService.getAll();
  }

}
