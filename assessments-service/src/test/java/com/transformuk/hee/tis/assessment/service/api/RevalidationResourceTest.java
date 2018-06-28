package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.RevalidationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
public class RevalidationResourceTest {

  public static final String INTREPID_ID = "123456";
  private static final Long ASSESSMENT_ID = 2222L;
  private static final Long TRAINEE_ID = 1111L;
  private static final String CONCERN_SUMMARY = "CONCERN SUMMARY";
  private static final String CONCERN_SUMMARY_UPDATE = "CONCERN SUMMARY UPDATE";
  private static final String RESP_COMMENTS = "RESP COMMENTS";
  private static final String RESP_COMMENTS_UPDATE = "RESP COMMENTS UPDATE";
  @InjectMocks
  private RevalidationResource testObj;

  @Mock
  private RevalidationService revalidationServiceMock;
  @Mock
  private AssessmentService assessmentServiceMock;
  @Mock
  private Assessment assessmentMock;
  @Captor
  private ArgumentCaptor<RevalidationDTO> revalidationDTOArgumentCaptor;

  private MockMvc mockMvc;
  private RevalidationDTO revalidationDTO, revalidationToCreateDTO, revalidationToUpdateDTO;

  @Before
  public void setup() {

    mockMvc = MockMvcBuilders.standaloneSetup(testObj).build();

    revalidationDTO = new RevalidationDTO()
        .intrepidId(INTREPID_ID)
        .id(ASSESSMENT_ID)
        .knownConcerns(true)
        .concernSummary(CONCERN_SUMMARY)
        .responsibleOfficerComments(RESP_COMMENTS);

    revalidationToCreateDTO = new RevalidationDTO()
        .intrepidId(INTREPID_ID)
        .id(null)
        .knownConcerns(true)
        .concernSummary(CONCERN_SUMMARY)
        .responsibleOfficerComments(RESP_COMMENTS);

    revalidationToUpdateDTO = new RevalidationDTO()
        .intrepidId(INTREPID_ID)
        .id(ASSESSMENT_ID)
        .knownConcerns(true)
        .concernSummary(CONCERN_SUMMARY_UPDATE)
        .responsibleOfficerComments(RESP_COMMENTS_UPDATE);
  }

  @Test
  public void getTraineeAssessmentRevalidationShouldReturnNotFoundWhenDoesntExist() throws Exception {

    when(revalidationServiceMock.findAssessmentRevalidationBy(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    mockMvc.perform(
        get("/api/trainee/{traineeId}/assessments/{assessmentId}/revalidations", TRAINEE_ID, ASSESSMENT_ID)
    ).andExpect(status().isNotFound());

    verify(revalidationServiceMock).findAssessmentRevalidationBy(TRAINEE_ID, ASSESSMENT_ID);
  }

  @Test
  public void getTraineeAssessmentRevalidationShouldRevalidationWhenExists() throws Exception {

    when(revalidationServiceMock.findAssessmentRevalidationBy(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(revalidationDTO));

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}/revalidations", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID))
        .andExpect(jsonPath("$.intrepidId").value(INTREPID_ID))
        .andExpect(jsonPath("$.knownConcerns").value(true))
        .andExpect(jsonPath("$.concernSummary").value(CONCERN_SUMMARY))
        .andExpect(jsonPath("$.responsibleOfficerComments").value(RESP_COMMENTS));

    verify(revalidationServiceMock).findAssessmentRevalidationBy(TRAINEE_ID, ASSESSMENT_ID);
  }

  @Test
  public void createTraineeAssessmentRevalidationShouldReturnCreateRevalWhenAssessmentExists() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentMock));
    when(revalidationServiceMock.create(eq(assessmentMock), revalidationDTOArgumentCaptor.capture())).thenReturn(revalidationDTO);

    byte[] createPayload = TestUtil.convertObjectToJsonBytes(revalidationToCreateDTO);
    mockMvc.perform(post("/api/trainee/{traineeId}/assessments/{assessmentId}/revalidations", TRAINEE_ID, ASSESSMENT_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(createPayload))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID))
        .andExpect(jsonPath("$.intrepidId").value(INTREPID_ID))
        .andExpect(jsonPath("$.knownConcerns").value(true))
        .andExpect(jsonPath("$.concernSummary").value(CONCERN_SUMMARY))
        .andExpect(jsonPath("$.responsibleOfficerComments").value(RESP_COMMENTS));

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    RevalidationDTO captorValue = revalidationDTOArgumentCaptor.getValue();
    Assert.assertNull(captorValue.getId());
    Assert.assertEquals(INTREPID_ID, captorValue.getIntrepidId());
    Assert.assertEquals(CONCERN_SUMMARY, captorValue.getConcernSummary());
    Assert.assertEquals(RESP_COMMENTS, captorValue.getResponsibleOfficerComments());
    Assert.assertTrue(captorValue.getKnownConcerns());
    verify(revalidationServiceMock).create(assessmentMock, captorValue);
  }

  @Test
  public void createTraineeAssessmentRevalidationShouldReturnNotFoundWhenAssessmentDoesntExist() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    byte[] createPayload = TestUtil.convertObjectToJsonBytes(revalidationToCreateDTO);
    mockMvc.perform(post("/api/trainee/{traineeId}/assessments/{assessmentId}/revalidations", TRAINEE_ID, ASSESSMENT_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(createPayload))
        .andExpect(status().isNotFound());

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);
    verify(revalidationServiceMock, never()).create(any(Assessment.class), any(RevalidationDTO.class));

  }

  @Test
  public void updateTraineeAssessmentRevalidationShouldReturnNotFoundWhenAssessmentDoesntExist() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    byte[] updatePayload = TestUtil.convertObjectToJsonBytes(revalidationToCreateDTO);
    mockMvc.perform(put("/api/trainee/{traineeId}/assessments/{assessmentId}/revalidations", TRAINEE_ID, ASSESSMENT_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(updatePayload))
        .andExpect(status().isNotFound());

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);
    verify(revalidationServiceMock, never()).save(any(Assessment.class), any(RevalidationDTO.class));

  }

  @Test
  public void updateTraineeAssessmentRevalidationShouldReturnCreateRevalWhenAssessmentExists() throws Exception {

    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentMock));
    when(revalidationServiceMock.save(eq(assessmentMock), revalidationDTOArgumentCaptor.capture())).thenReturn(revalidationToUpdateDTO);

    byte[] updatePayload = TestUtil.convertObjectToJsonBytes(revalidationToUpdateDTO);
    mockMvc.perform(put("/api/trainee/{traineeId}/assessments/{assessmentId}/revalidations", TRAINEE_ID, ASSESSMENT_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(updatePayload))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID))
        .andExpect(jsonPath("$.intrepidId").value(INTREPID_ID))
        .andExpect(jsonPath("$.knownConcerns").value(true))
        .andExpect(jsonPath("$.concernSummary").value(CONCERN_SUMMARY_UPDATE))
        .andExpect(jsonPath("$.responsibleOfficerComments").value(RESP_COMMENTS_UPDATE));

    verify(assessmentServiceMock).findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID);

    RevalidationDTO captorValue = revalidationDTOArgumentCaptor.getValue();
    Assert.assertEquals(ASSESSMENT_ID, captorValue.getId());
    Assert.assertEquals(INTREPID_ID, captorValue.getIntrepidId());
    Assert.assertEquals(CONCERN_SUMMARY_UPDATE, captorValue.getConcernSummary());
    Assert.assertEquals(RESP_COMMENTS_UPDATE, captorValue.getResponsibleOfficerComments());
    Assert.assertTrue(captorValue.getKnownConcerns());
    verify(revalidationServiceMock).save(assessmentMock, captorValue);
  }

}