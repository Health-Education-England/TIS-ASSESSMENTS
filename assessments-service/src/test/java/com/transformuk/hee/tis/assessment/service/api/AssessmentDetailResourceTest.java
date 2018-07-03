package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.service.AssessmentDetailService;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AssessmentDetailResourceTest {

  public static final long ASSESSMENT_ID = 22222L;
  public static final long TRAINEE_ID = 11111L;
  public static final Long CURRICULUM_ID = 123L;
  public static final Long CURRICULUM_ID_OLD = 456L;
  public static final String CURRICULUM_NAME = "CURRICULUM NAME";
  public static final String CURRICULUM_NAME_OLD = "CURRICULUM NAME OLD";
  public static final Long ASSESSMENT_DETAIL_ID = 1L;
  @InjectMocks
  private AssessmentDetailResource testObj;

  @Mock
  private AssessmentDetailService assessmentDetailServiceMock;
  @Mock
  private AssessmentService assessmentServiceMock;
  @Mock
  private Assessment assessmentMock;
  @Captor
  private ArgumentCaptor<AssessmentDetailDTO> assessmentDetailDTOCaptor;

  private MockMvc mockMvc;
  private Gson gson;
  private AssessmentDetailDTO assessmentDetailDTO, assessmentDetailToCreate, assessmentDetailAfterCreate, assessmentDetailToUpdate;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(testObj).build();
    gson = new Gson();
    assessmentDetailDTO = new AssessmentDetailDTO()
        .id(1L)
        .gradeName("GRADE NAME")
        .gradeId(2L)
        .intrepidId("12345678")
        .pya(true);

    assessmentDetailToCreate = new AssessmentDetailDTO()
        .curriculumId(CURRICULUM_ID)
        .curriculumName(CURRICULUM_NAME);

    assessmentDetailAfterCreate = new AssessmentDetailDTO()
        .id(ASSESSMENT_DETAIL_ID)
        .curriculumId(CURRICULUM_ID)
        .curriculumName(CURRICULUM_NAME);

    assessmentDetailToUpdate = new AssessmentDetailDTO()
        .id(ASSESSMENT_DETAIL_ID)
        .curriculumId(CURRICULUM_ID_OLD)
        .curriculumName(CURRICULUM_NAME_OLD);
  }


  @Test
  public void getTraineeAssessmentDetailsShouldReturn404NotFoundWhenAssessmentDetailForTraineeNotFound() throws Exception {
    when(assessmentDetailServiceMock.findAssessmentDetailBy(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}/details", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isNotFound());

    verify(assessmentDetailServiceMock).findAssessmentDetailBy(TRAINEE_ID, ASSESSMENT_ID);
  }


  @Test
  public void getTraineeAssessmentDetailsShouldReturnAssessmentDetail() throws Exception {
    when(assessmentDetailServiceMock.findAssessmentDetailBy(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentDetailDTO));

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}/details", TRAINEE_ID, ASSESSMENT_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.gradeName").value("GRADE NAME"))
        .andExpect(jsonPath("$.gradeId").value(2))
        .andExpect(jsonPath("$.intrepidId").value("12345678"))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.pya").value(true));

    verify(assessmentDetailServiceMock).findAssessmentDetailBy(TRAINEE_ID, ASSESSMENT_ID);
  }

  @Test
  public void createTraineeAssessmentDetailsShouldReturnNotFoundWhenAssessmentForTraineeDoesntExist() throws Exception {
    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    String postPayload = gson.toJson(assessmentDetailToCreate);
    mockMvc.perform(
        post("/api/trainee/{traineeId}/assessments/{assessmentId}/details", TRAINEE_ID, ASSESSMENT_ID)
            .content(postPayload)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isNotFound());

    verify(assessmentDetailServiceMock, never()).create(any(Assessment.class), any(AssessmentDetailDTO.class));
  }

  @Test
  public void createTraineeAssessmentDetailsShouldCreateAndReturnNewAssessmentDetail() throws Exception {
    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentMock));
    when(assessmentDetailServiceMock.create(eq(assessmentMock), assessmentDetailDTOCaptor.capture())).thenReturn(assessmentDetailAfterCreate);

    String postPayload = gson.toJson(assessmentDetailToCreate);
    mockMvc.perform(
        post("/api/trainee/{traineeId}/assessments/{assessmentId}/details", TRAINEE_ID, ASSESSMENT_ID)
            .content(postPayload)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
    .andExpect(jsonPath("$.curriculumId").value(CURRICULUM_ID))
    .andExpect(jsonPath("$.curriculumName").value(CURRICULUM_NAME))
    .andExpect(jsonPath("$.id").value(ASSESSMENT_DETAIL_ID));

    AssessmentDetailDTO capturedAssessmentDetail = assessmentDetailDTOCaptor.getValue();
    Assert.assertNull(capturedAssessmentDetail.getId());
    Assert.assertEquals(CURRICULUM_ID, capturedAssessmentDetail.getCurriculumId());
    Assert.assertEquals(CURRICULUM_NAME, capturedAssessmentDetail.getCurriculumName());

    verify(assessmentDetailServiceMock).create(assessmentMock, capturedAssessmentDetail);
  }

  @Test
  public void updateTraineeAssessmentDetailsShouldReturnNotFoundWhenAssessmentForTraineeDoesntExist() throws Exception {
    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.empty());

    String putPayload = gson.toJson(assessmentDetailToCreate);
    mockMvc.perform(
        put("/api/trainee/{traineeId}/assessments/{assessmentId}/details", TRAINEE_ID, ASSESSMENT_ID)
            .content(putPayload)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isNotFound());

    verify(assessmentDetailServiceMock, never()).save(any(Assessment.class), any(AssessmentDetailDTO.class));
  }

  @Test
  public void updateTraineeAssessmentDetailsShouldUpdateAndReturnNewAssessmentDetail() throws Exception {
    when(assessmentServiceMock.findTraineeAssessment(TRAINEE_ID, ASSESSMENT_ID)).thenReturn(Optional.of(assessmentMock));
    when(assessmentDetailServiceMock.save(eq(assessmentMock), assessmentDetailDTOCaptor.capture())).thenReturn(assessmentDetailToUpdate);

    String putPayload = gson.toJson(assessmentDetailToUpdate);
    mockMvc.perform(
        put("/api/trainee/{traineeId}/assessments/{assessmentId}/details", TRAINEE_ID, ASSESSMENT_ID)
            .content(putPayload)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.curriculumId").value(CURRICULUM_ID_OLD))
        .andExpect(jsonPath("$.curriculumName").value(CURRICULUM_NAME_OLD))
        .andExpect(jsonPath("$.id").value(ASSESSMENT_DETAIL_ID));

    AssessmentDetailDTO capturedAssessmentDetail = assessmentDetailDTOCaptor.getValue();
    Assert.assertEquals(ASSESSMENT_DETAIL_ID, capturedAssessmentDetail.getId());
    Assert.assertEquals(CURRICULUM_ID_OLD, capturedAssessmentDetail.getCurriculumId());
    Assert.assertEquals(CURRICULUM_NAME_OLD, capturedAssessmentDetail.getCurriculumName());

    verify(assessmentDetailServiceMock).save(assessmentMock, capturedAssessmentDetail);
  }
}