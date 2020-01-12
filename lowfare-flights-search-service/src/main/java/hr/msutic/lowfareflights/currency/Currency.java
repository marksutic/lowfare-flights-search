package hr.msutic.lowfareflights.currency;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Marko Sutic
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CURRENCIES")
public class Currency {

  @Id
  private int id;
  private String iso;

  @JsonCreator
  public Currency(String iso) {
    this.iso = iso;
  }

}
