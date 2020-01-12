package hr.msutic.lowfareflights.currency;

import hr.msutic.lowfareflights.common.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author c214223
 */
@Service
@RequiredArgsConstructor
public class CurrencyService {

  private final CurrencyRepository currencyRepository;

  public ServiceResult<List<Currency>> getAll() {
    return new ServiceResult<>(true, this.currencyRepository.findAll());
  }

}
