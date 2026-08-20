package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentOutcomeService;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import gherkin.deps.com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AssessmentOutcomeResourceTest {

  private static final Long ASSESSMENT_ID = 22222L;
  private static final Long TRAINEE_ID = 11111L;
  private static final String OUTCOME = "1";
  private static final Long OUTCOME_ID = 1L;
  private static final String COMMENT = "comment";
  private static final String NOT_ASSESSED = "not assessed";
  private static final Long NOT_ASSESSED_OUTCOME_ID = 2L;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;
  @Autowired
  private ExceptionTranslator exceptionTranslator;


  @InjectMocks
  private AssessmentOutcomeResource testObj;

  @Mock
  private AssessmentOutcomeService assessmentOutcomeServiceMock;
  @Mock
  private AssessmentService assessmentServiceMock;
  @Mock
  private AssessmentDTO assessmentDTOMock;
  @Mock
  private Assessment assessmentMock;
  @Captor
  private ArgumentCaptor<AssessmentOutcomeDTO> assessmentOutcomeDTOArgumentCaptor;

  private MockMvc mockMvc;
  private Gson gson;

  private AssessmentOutcomeDTO assessmentOutcomeDTO, assessmentOutcomeToCreateDTO, assessmentOutcomeAfterCreateDTO,
      assessmentOutcomeToUpdateDTO;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(testObj)
        .setMessageConverters(jacksonMessageConverter)
        .setControllerAdvice(exceptionTranslator).build();

    gson = new Gson();

    assessmentOutcomeDTO = new AssessmentOutcomeDTO()
        .id(ASSESSMENT_ID)
        .outcome(OUTCOME)
        .outcomeId(OUTCOME_ID)
        .comments(COMMENT);

    assessmentOutcomeToCreateDTO = new AssessmentOutcomeDTO()
        .outcome(NOT_ASSESSED)
        .outcomeId(NOT_ASSESSED_OUTCOME_ID);

    assessmentOutcomeAfterCreateDTO = new AssessmentOutcomeDTO()
        .id(ASSESSMENT_ID)
        .outcome(NOT_ASSESSED)
        .outcomeId(NOT_ASSESSED_OUTCOME_ID);

    assessmentOutcomeToUpdateDTO = new AssessmentOutcomeDTO()
        .id(ASSESSMENT_ID)
        .outcome(OUTCOME)
        .outcomeId(OUTCOME_ID);
    assessmentOutcomeToUpdateDTO.setAmendedDate(LocalDateTime.now());

  }

  @Test
  public void getTraineeAssessmentOutcomesShouldReturnNotFoundWhenAssessmentForTraineeDoesntExist() throws Exception {

    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}/outcomes", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isNotFound());

    verify(assessmentServiceMock).findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID);
  }


  @Test
  public void getTraineeAssessmentOutcomesShouldReturnOkAndTheOutcomeForTheAssessment() throws Exception {
    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentDTOMock));
    when(assessmentDTOMock.getOutcome()).thenReturn(assessmentOutcomeDTO);

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}/outcomes", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID))
        .andExpect(jsonPath("$.outcome").value(OUTCOME))
        .andExpect(jsonPath("$.outcomeId").value(OUTCOME_ID))
        .andExpect(jsonPath("$.comments").value(COMMENT));

    verify(assessmentServiceMock).findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID);
  }

  @Test
  public void createTraineeAssessmentOutcomesShouldReturnNotFoundWhenAssessmentForTraineeDoesntExist() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());
    String createPayload = gson.toJson(assessmentOutcomeToCreateDTO);

    mockMvc.perform(
        post("/api/trainee/{traineeId}/assessments/{assessmentId}/outcomes", TRAINEE_ID, ASSESSMENT_ID)
            .content(createPayload)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);
    verify(assessmentOutcomeServiceMock, never()).create(any(Assessment.class), any(AssessmentOutcomeDTO.class));
  }

  @Test
  public void createTraineeAssessmentOutcomesShouldReturnOkAndTheOutcomeForTheAssessment() throws Exception {
    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentMock));
    when(assessmentOutcomeServiceMock.create(eq(assessmentMock), assessmentOutcomeDTOArgumentCaptor.capture())).thenReturn(assessmentOutcomeAfterCreateDTO);
    String createPayload = gson.toJson(assessmentOutcomeToCreateDTO);

    mockMvc.perform(
        post("/api/trainee/{traineeId}/assessments/{assessmentId}/outcomes", TRAINEE_ID, ASSESSMENT_ID)
            .content(createPayload)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID))
        .andExpect(jsonPath("$.outcome").value(NOT_ASSESSED))
        .andExpect(jsonPath("$.outcomeId").value(NOT_ASSESSED_OUTCOME_ID));

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    AssessmentOutcomeDTO captorValue = assessmentOutcomeDTOArgumentCaptor.getValue();
    Assert.assertNull(captorValue.getId());
    Assert.assertEquals(NOT_ASSESSED, captorValue.getOutcome());
    Assert.assertEquals(NOT_ASSESSED_OUTCOME_ID, captorValue.getOutcomeId());
  }

  @Test
  public void updateTraineeAssessmentOutcomesShouldReturnNotFoundWhenAssessmentForTraineeDoesntExist() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());
    byte[] updatePayload = TestUtil.convertObjectToJsonBytes(assessmentOutcomeToUpdateDTO);

    mockMvc.perform(
        put("/api/trainee/{traineeId}/assessments/{assessmentId}/outcomes", TRAINEE_ID, ASSESSMENT_ID)
            .content(updatePayload)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);
    verify(assessmentOutcomeServiceMock, never()).create(any(Assessment.class), any(AssessmentOutcomeDTO.class));
  }

  @Test
  public void updateTraineeAssessmentOutcomesShouldReturnOkAndTheOutcomeForTheAssessment() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentMock));
    when(assessmentOutcomeServiceMock.save(eq(assessmentMock), assessmentOutcomeDTOArgumentCaptor.capture())).thenReturn(assessmentOutcomeToUpdateDTO);
    byte[] updatePayload = TestUtil.convertObjectToJsonBytes(assessmentOutcomeToUpdateDTO);

    mockMvc.perform(
        put("/api/trainee/{traineeId}/assessments/{assessmentId}/outcomes", TRAINEE_ID, ASSESSMENT_ID)
            .content(updatePayload)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID))
        .andExpect(jsonPath("$.outcome").value(OUTCOME))
        .andExpect(jsonPath("$.outcomeId").value(OUTCOME_ID));

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    AssessmentOutcomeDTO captorValue = assessmentOutcomeDTOArgumentCaptor.getValue();
    Assert.assertEquals(ASSESSMENT_ID, captorValue.getId());
    Assert.assertEquals(OUTCOME, captorValue.getOutcome());
    Assert.assertEquals(OUTCOME_ID, captorValue.getOutcomeId());
  }
}
