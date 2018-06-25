package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AssessmentResourceTest {

  public static final long ASSESSMENT_ID = 1111L;
  public static final long TRAINEE_ID = 2222L;

  @InjectMocks
  private AssessmentResource testObj;
  @Mock
  private AssessmentService assessmentServiceMock;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;
  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
  @Autowired
  private ExceptionTranslator exceptionTranslator;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(testObj)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();
  }

  @Test
  public void deleteTraineeAssessmentShouldReturnBadRequestWhenAssessmentForTraineeNotFound() throws Exception {
    when(assessmentServiceMock.deleteTraineeAssessment(ASSESSMENT_ID, TRAINEE_ID)).thenReturn(false);
    mockMvc.perform(delete("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void deleteTraineeAssessmentShouldReturnOKWhenAssessmentForTraineeIsFound() throws Exception {
    when(assessmentServiceMock.deleteTraineeAssessment(ASSESSMENT_ID, TRAINEE_ID)).thenReturn(true);
    mockMvc.perform(delete("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isOk());
  }
}