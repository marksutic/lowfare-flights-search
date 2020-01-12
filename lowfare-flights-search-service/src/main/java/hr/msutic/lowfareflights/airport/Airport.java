package hr.msutic.lowfareflights.airport;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author c214223
 */
@Getter
@Setter
@Entity
@Table(name = "IATA_CODES")
public class Airport {

  @Id
  @Column(name = "CODE")
  private String iataCode;
  private String city;
  private String term;
  private String country;
  private Boolean multipleAirports;

}
