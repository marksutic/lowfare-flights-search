package hr.msutic.lowfareflights.airline;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Marko Sutic
 */
@Getter
@Setter
@Builder
public class Airline {

  private String type;
  private String iataCode;
  private String businessName;
  private String commonName;

}
