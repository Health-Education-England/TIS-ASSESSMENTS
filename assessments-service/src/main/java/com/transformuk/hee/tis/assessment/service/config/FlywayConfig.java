package com.transformuk.hee.tis.assessment.service.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.*;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * Flyway configuration
 */
@Configuration
@ConditionalOnProperty("flyway.enabled")
public class FlywayConfig {

  @Value("${flyway.url}")
  private String url;

  @Value("${flyway.password}")
  private String password;

  @Value("${flyway.user}")
  private String user;

  @Value("${flyway.locations}")
  private String[] migrationFilesLocations;

  @Value("${flyway.schemas}")
  private String schemas;

  @Value("${flyway.baseline-on-migrate}")
  private boolean baseLineOnMigrate;

  @Value("${flyway.clean-on-validation-error}")
  private boolean cleanOnValidationError;

  @Value("${flyway.out-of-order}")
  private boolean outOfOrder;

  @Bean(initMethod = "migrate")
  Flyway flyway() {
    org.flywaydb.core.api.configuration.ClassicConfiguration flywayConfiguration = new ClassicConfiguration();

    List<Location> locations = new ArrayList<>();
    for (String fileLocation : migrationFilesLocations) {
      org.flywaydb.core.api.Location location = new Location(fileLocation);
      locations.add(location);
    }
    flywayConfiguration.setLocations(locations.toArray(new Location[0]));

    flywayConfiguration.setBaselineOnMigrate(baseLineOnMigrate);
    flywayConfiguration.setLocations();
    flywayConfiguration.setDataSource(url, user, password);
    flywayConfiguration.setCleanOnValidationError(cleanOnValidationError);
    flywayConfiguration.setOutOfOrder(outOfOrder);

    Flyway flyway = new Flyway(flywayConfiguration);
    flyway.info();
    return flyway;
  }
}
