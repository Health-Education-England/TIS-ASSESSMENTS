package com.transformuk.hee.tis.assessment.service.api;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.config.ApplicationProperties;
import com.transformuk.hee.tis.assessment.service.config.WebConfigurer;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.impl.AssessmentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRestPactRunner.class)
@Provider("assessments-service")
@PactBroker(host = "localhost", port = "8500")
@ActiveProfiles("dev")
@SpringBootTest(classes = {Application.class, ApplicationProperties.class, WebConfigurer.class, TestConfig.class}, properties = {"spring.profiles.active=dev"}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AssessmentResourceTest {

  @MockBean
  private AssessmentService assessmentService;
  @TestTarget
  public final Target target = new HttpTarget(8097);

  @State(value="service-has-data")
  public void derp() {
    AssessmentDTO one = new AssessmentDTO();
    one.setId(1L);

    Optional<AssessmentDTO> one1 = Optional.ofNullable(one);
    when(assessmentService.findTraineeAssessmentDTO(anyString(), anyLong()))
        .thenReturn(one1);
  }
}

@Configuration
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class})
class TestConfig {

  //  @Bean
  //  public AssessmentDetailMapper assessmentDetailMapper() {
  //    return Mockito.mock(AssessmentDetailMapper.class);
  //  }
  //  @Bean
  //  public JsonPatchMapper jsonPatchMapper() {
  //    return Mockito.mock(JsonPatchMapper.class);
  //  }
}
