package hr.msutic.lowfareflights.airport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author c214223
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {

  @Query("from Airport a where lower(a.term) like CONCAT('%', lower(:name), '%')")
  List<Airport> findByPartialName(@Param("name")String name);

  List<Airport> findByIataCode(String name);

}
