package hr.msutic.lowfareflights.flightoffer;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author c214223
 */
@Data
@Entity
@Table(name = "FLIGHT_SEARCHES")
public class FlightOfferSearch {

  @Id
  private int id;
  @Lob
  private String result;

}
