package hr.msutic.lowfareflights.flightoffer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.msutic.lowfareflights.currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Marko
 */
@Getter
@ToString
@AllArgsConstructor
public class Price {

    private Currency currency;
    private String price;
    @JsonIgnore
    private BigDecimal priceBd;

}
