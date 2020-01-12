package hr.msutic.lowfareflights.currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author c214223
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, String> {

}
