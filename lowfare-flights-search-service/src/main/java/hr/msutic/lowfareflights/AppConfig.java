package hr.msutic.lowfareflights;

import com.amadeus.Amadeus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Marko Sutic
 */
@Configuration
public class AppConfig {

  @Bean
  Amadeus amadeus() {
    return Amadeus.builder("09xXs4xcjh3Vr3s5ECORos0Oq1RWOr0X", "HHa7aqIfGix3Zj8z").build();
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("DELETE");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

}
