package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import org.assertj.core.util.Lists;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AssessmentResourceTest {

  private static final String CURRICULUM_NAME = "curriculum name";
  private static final String OUTCOME_1 = "Outcome 1";
  private static final String ANOTHER_CURRICULUM_NAME = "another curriculum name";
  private static final String ANOTHER_FIRST_NAME = "another first name";
  private static final String ANOTHER_LAST_NAME = "another last name";
  private static final String ANOTHER_OUTCOME = "another outcome";
  private static final long ASSESSMENT_ID_1 = 1L;
  private static final long ASSESSMENT_ID_2 = 2L;
  private static final String PROGRAMME_NUMBER = "Programme number";
  private static final String PROGRAMME_NAME = "programme name";
  private static final String TYPE = "type";
  private static final long NEW_ASSESSMENT_ID = 1L;
  private static final Long ASSESSMENT_ID = 1111L;
  private static final Long TRAINEE_ID = 2222L;
  private static final String FIRST_NAME = "first name";
  private static final String LAST_NAME = "last name";
  private static final long PROGRAMME_MEMBERSHIP_ID = 888L;
  @InjectMocks
  private AssessmentResource testObj;
  @Mock
  private AssessmentService assessmentServiceMock;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;
  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
  @Autowired
  private SortHandlerMethodArgumentResolver sortHandlerMethodArgumentResolver;
  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Captor
  private ArgumentCaptor<Pageable> pageableArgumentCaptor;
  @Captor
  private ArgumentCaptor<AssessmentDTO> assessmentDTOArgumentCaptor;
  @Captor
  private ArgumentCaptor<Sort> sortArgumentCaptor;
  @Captor
  private ArgumentCaptor<String> stringArgumentCaptor;

  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(testObj)
        .setCustomArgumentResolvers(pageableArgumentResolver, sortHandlerMethodArgumentResolver)
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

  @Test
  public void getTraineeAssessmentsShouldReturnAssessmentForATraineePaginated() throws Exception {
    AssessmentDTO assessmentDTO1 = new AssessmentDTO(), assessmentDTO2 = new AssessmentDTO();
    assessmentDTO1.id(ASSESSMENT_ID_1).firstName(FIRST_NAME).lastName(LAST_NAME).programmeMembershipId(PROGRAMME_MEMBERSHIP_ID);
    assessmentDTO2.id(ASSESSMENT_ID_2).firstName(FIRST_NAME).lastName(LAST_NAME).programmeMembershipId(null);

    List<AssessmentDTO> assessments = Lists.newArrayList(assessmentDTO1, assessmentDTO2);
    Page<AssessmentDTO> page = new PageImpl<>(assessments);

    when(assessmentServiceMock.findForTrainee(eq(TRAINEE_ID), pageableArgumentCaptor.capture())).thenReturn(page);

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments", TRAINEE_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(ASSESSMENT_ID_1))
        .andExpect(jsonPath("$.[1].id").value(ASSESSMENT_ID_2))
        .andExpect(jsonPath("$.[0].firstName").value(FIRST_NAME))
        .andExpect(jsonPath("$.[1].firstName").value(FIRST_NAME))
        .andExpect(jsonPath("$.[0].lastName").value(LAST_NAME))
        .andExpect(jsonPath("$.[1].lastName").value(LAST_NAME))
        .andExpect(jsonPath("$.[0].programmeMembershipId").value(PROGRAMME_MEMBERSHIP_ID))
        .andExpect(jsonPath("$.[1].programmeMembershipId").value(IsNull.nullValue()))
    ;
  }

  @Test
  public void getAllTraineeAssessmentsShouldReturnAllAssessmentForATrainee() throws Exception {
    AssessmentDTO assessmentDTO1 = new AssessmentDTO(), assessmentDTO2 = new AssessmentDTO();
    assessmentDTO1.id(ASSESSMENT_ID_1).firstName(FIRST_NAME).lastName(LAST_NAME).programmeMembershipId(PROGRAMME_MEMBERSHIP_ID);
    assessmentDTO2.id(ASSESSMENT_ID_2).firstName(FIRST_NAME).lastName(LAST_NAME).programmeMembershipId(null);

    List<AssessmentDTO> assessments = Lists.newArrayList(assessmentDTO1, assessmentDTO2);

    when(assessmentServiceMock.findAllForTrainee(eq(TRAINEE_ID), sortArgumentCaptor.capture())).thenReturn(assessments);

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/all", TRAINEE_ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].id").value(ASSESSMENT_ID_1))
        .andExpect(jsonPath("$.[1].id").value(ASSESSMENT_ID_2))
        .andExpect(jsonPath("$.[0].firstName").value(FIRST_NAME))
        .andExpect(jsonPath("$.[1].firstName").value(FIRST_NAME))
        .andExpect(jsonPath("$.[0].lastName").value(LAST_NAME))
        .andExpect(jsonPath("$.[1].lastName").value(LAST_NAME))
        .andExpect(jsonPath("$.[0].programmeMembershipId").value(PROGRAMME_MEMBERSHIP_ID))
        .andExpect(jsonPath("$.[1].programmeMembershipId").value(IsNull.nullValue()))
    ;
      Sort capturedValue = sortArgumentCaptor.getValue();
      Sort.Order reviewDateOrder = capturedValue.getOrderFor("reviewDate");
      Assert.assertEquals(Sort.Direction.DESC, reviewDateOrder.getDirection());

  }

    @Test
    public void getAllTraineeAssessmentsShouldReturnAllAssessmentForATraineeInTheDefinedSortOrder() throws Exception {
        AssessmentDTO assessmentDTO1 = new AssessmentDTO(), assessmentDTO2 = new AssessmentDTO();
        assessmentDTO1.id(ASSESSMENT_ID_1).firstName(FIRST_NAME).lastName(LAST_NAME).programmeMembershipId(PROGRAMME_MEMBERSHIP_ID);
        assessmentDTO2.id(ASSESSMENT_ID_2).firstName(FIRST_NAME).lastName(LAST_NAME).programmeMembershipId(null);

        List<AssessmentDTO> assessments = Lists.newArrayList(assessmentDTO1, assessmentDTO2);

        when(assessmentServiceMock.findAllForTrainee(eq(TRAINEE_ID), sortArgumentCaptor.capture())).thenReturn(assessments);

        mockMvc.perform(get("/api/trainee/{traineeId}/assessments/all?sort=reviewDate,asc", TRAINEE_ID))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].id").value(ASSESSMENT_ID_1))
            .andExpect(jsonPath("$.[1].id").value(ASSESSMENT_ID_2))
            .andExpect(jsonPath("$.[0].firstName").value(FIRST_NAME))
            .andExpect(jsonPath("$.[1].firstName").value(FIRST_NAME))
            .andExpect(jsonPath("$.[0].lastName").value(LAST_NAME))
            .andExpect(jsonPath("$.[1].lastName").value(LAST_NAME))
            .andExpect(jsonPath("$.[0].programmeMembershipId").value(PROGRAMME_MEMBERSHIP_ID))
            .andExpect(jsonPath("$.[1].programmeMembershipId").value(IsNull.nullValue()))
        ;

        Sort capturedValue = sortArgumentCaptor.getValue();
        Sort.Order reviewDateOrder = capturedValue.getOrderFor("reviewDate");
        Assert.assertEquals(Sort.Direction.ASC, reviewDateOrder.getDirection());
    }

  @Test
  public void createTraineeAssessmentShouldReturnBadRequestWhenPayloadHasId() throws Exception {

    AssessmentDTO assessmentToCreate = new AssessmentDTO();
    assessmentToCreate.setId(12345342L);
    assessmentToCreate.setTraineeId(12345677L);
    assessmentToCreate.setFirstName(FIRST_NAME);
    assessmentToCreate.setLastName(LAST_NAME);
    assessmentToCreate.setProgrammeNumber(PROGRAMME_NUMBER);
    assessmentToCreate.setProgrammeName(PROGRAMME_NAME);
    assessmentToCreate.setType(TYPE);
    mockMvc.perform(post("/api/trainee/{traineeId}/assessments", TRAINEE_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentToCreate)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title").value("Method argument not valid"));
  }

  @Test
  public void createTraineeAssessmentShouldReturnBadRequestWhenTheTraineeInUrlPathDoesNotMatchTraineeInPayload() throws Exception {

    AssessmentDTO assessmentToCreate = new AssessmentDTO();
    assessmentToCreate.setTraineeId(12345677L);
    assessmentToCreate.setFirstName(FIRST_NAME);
    assessmentToCreate.setLastName(LAST_NAME);
    assessmentToCreate.setProgrammeNumber(PROGRAMME_NUMBER);
    assessmentToCreate.setProgrammeName(PROGRAMME_NAME);
    assessmentToCreate.setType(TYPE);

    mockMvc.perform(post("/api/trainee/{traineeId}/assessments", TRAINEE_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentToCreate)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title").value("A new assessment does not have the same trainee id as uri path"));
  }

  @Test
  public void createTraineeAssessmentShouldReturnNewlyCreatedAssessment() throws Exception {

    AssessmentDTO createdAssessment = new AssessmentDTO();
    createdAssessment.setId(NEW_ASSESSMENT_ID);
    createdAssessment.setFirstName(FIRST_NAME);
    createdAssessment.setLastName(LAST_NAME);
    createdAssessment.setProgrammeNumber(PROGRAMME_NUMBER);
    createdAssessment.setProgrammeName(PROGRAMME_NAME);
    createdAssessment.setType(TYPE);
    createdAssessment.setTraineeId(TRAINEE_ID);
    createdAssessment.setProgrammeMembershipId(PROGRAMME_MEMBERSHIP_ID);

    when(assessmentServiceMock.save(assessmentDTOArgumentCaptor.capture())).thenReturn(createdAssessment);

    AssessmentDTO assessmentToCreate = new AssessmentDTO();
    assessmentToCreate.setFirstName(FIRST_NAME);
    assessmentToCreate.setLastName(LAST_NAME);
    assessmentToCreate.setProgrammeNumber(PROGRAMME_NUMBER);
    assessmentToCreate.setProgrammeName(PROGRAMME_NAME);
    assessmentToCreate.setType(TYPE);
    assessmentToCreate.setTraineeId(TRAINEE_ID);
    assessmentToCreate.setProgrammeMembershipId(PROGRAMME_MEMBERSHIP_ID);

    mockMvc.perform(post("/api/trainee/{traineeId}/assessments", TRAINEE_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentToCreate)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(NEW_ASSESSMENT_ID))
        .andExpect(jsonPath("$.programmeMembershipId").value(PROGRAMME_MEMBERSHIP_ID))
    ;


    AssessmentDTO captorValue = assessmentDTOArgumentCaptor.getValue();
    Assert.assertEquals(assessmentToCreate.getId(), captorValue.getId());
    Assert.assertEquals(assessmentToCreate.getFirstName(), captorValue.getFirstName());
    Assert.assertEquals(assessmentToCreate.getLastName(), captorValue.getLastName());
    Assert.assertEquals(assessmentToCreate.getProgrammeNumber(), captorValue.getProgrammeNumber());
    Assert.assertEquals(assessmentToCreate.getProgrammeName(), captorValue.getProgrammeName());
    Assert.assertEquals(assessmentToCreate.getType(), captorValue.getType());
    Assert.assertEquals(assessmentToCreate.getTraineeId(), captorValue.getTraineeId());
  }

  @Test
  public void updateTraineeAssessmentShouldReturnCreatedIfNoIdGivenInEntity() throws Exception {

    AssessmentDTO assessmentToUpdate = new AssessmentDTO();
    assessmentToUpdate.setTraineeId(TRAINEE_ID);
    assessmentToUpdate.setFirstName(FIRST_NAME);
    assessmentToUpdate.setLastName(LAST_NAME);
    assessmentToUpdate.setProgrammeNumber(PROGRAMME_NUMBER);
    assessmentToUpdate.setProgrammeName(PROGRAMME_NAME);
    assessmentToUpdate.setType(TYPE);

    AssessmentDTO createdAssessment = new AssessmentDTO();
    createdAssessment.setId(NEW_ASSESSMENT_ID);
    createdAssessment.setFirstName(FIRST_NAME);
    createdAssessment.setLastName(LAST_NAME);
    createdAssessment.setProgrammeNumber(PROGRAMME_NUMBER);
    createdAssessment.setProgrammeName(PROGRAMME_NAME);
    createdAssessment.setType(TYPE);
    createdAssessment.setTraineeId(TRAINEE_ID);

    when(assessmentServiceMock.save(assessmentDTOArgumentCaptor.capture())).thenReturn(createdAssessment);

    mockMvc.perform(put("/api/trainee/{traineeId}/assessments", TRAINEE_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentToUpdate)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(NEW_ASSESSMENT_ID));

    AssessmentDTO captorValue = assessmentDTOArgumentCaptor.getValue();
    Assert.assertEquals(assessmentToUpdate.getId(), captorValue.getId());
    Assert.assertEquals(assessmentToUpdate.getFirstName(), captorValue.getFirstName());
    Assert.assertEquals(assessmentToUpdate.getLastName(), captorValue.getLastName());
    Assert.assertEquals(assessmentToUpdate.getProgrammeNumber(), captorValue.getProgrammeNumber());
    Assert.assertEquals(assessmentToUpdate.getProgrammeName(), captorValue.getProgrammeName());
    Assert.assertEquals(assessmentToUpdate.getType(), captorValue.getType());
    Assert.assertEquals(assessmentToUpdate.getTraineeId(), captorValue.getTraineeId());

  }

  @Test
  public void updateTraineeAssessmentShouldReturnUpdatedAssessment() throws Exception {

    AssessmentDTO assessmentToUpdate = new AssessmentDTO();
    assessmentToUpdate.setId(ASSESSMENT_ID_1);
    assessmentToUpdate.setTraineeId(TRAINEE_ID);
    assessmentToUpdate.setFirstName(FIRST_NAME);
    assessmentToUpdate.setLastName(LAST_NAME);
    assessmentToUpdate.setProgrammeNumber(PROGRAMME_NUMBER);
    assessmentToUpdate.setProgrammeName(PROGRAMME_NAME);
    assessmentToUpdate.setType(TYPE);

    when(assessmentServiceMock.save(assessmentDTOArgumentCaptor.capture())).thenReturn(assessmentToUpdate);

    mockMvc.perform(put("/api/trainee/{traineeId}/assessments", TRAINEE_ID)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentToUpdate)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID_1));

    AssessmentDTO captorValue = assessmentDTOArgumentCaptor.getValue();
    Assert.assertEquals(assessmentToUpdate.getId(), captorValue.getId());
    Assert.assertEquals(assessmentToUpdate.getFirstName(), captorValue.getFirstName());
    Assert.assertEquals(assessmentToUpdate.getLastName(), captorValue.getLastName());
    Assert.assertEquals(assessmentToUpdate.getProgrammeNumber(), captorValue.getProgrammeNumber());
    Assert.assertEquals(assessmentToUpdate.getProgrammeName(), captorValue.getProgrammeName());
    Assert.assertEquals(assessmentToUpdate.getType(), captorValue.getType());
    Assert.assertEquals(assessmentToUpdate.getTraineeId(), captorValue.getTraineeId());
  }

  @Test
  public void getTraineeAssessmentShouldReturnSpecificTraineeAssessment() throws Exception {

    AssessmentDTO foundAssessment = new AssessmentDTO();
    foundAssessment.setId(ASSESSMENT_ID_1);
    foundAssessment.setTraineeId(TRAINEE_ID);
    foundAssessment.setFirstName(FIRST_NAME);
    foundAssessment.setLastName(LAST_NAME);
    foundAssessment.setProgrammeNumber(PROGRAMME_NUMBER);
    foundAssessment.setProgrammeName(PROGRAMME_NAME);
    foundAssessment.setType(TYPE);
    foundAssessment.setProgrammeMembershipId(PROGRAMME_MEMBERSHIP_ID);

    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID_1)).thenReturn(Optional.of(foundAssessment));

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID_1))
        .andExpect(jsonPath("$.traineeId").value(TRAINEE_ID))
        .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
        .andExpect(jsonPath("$.lastName").value(LAST_NAME))
        .andExpect(jsonPath("$.programmeNumber").value(PROGRAMME_NUMBER))
        .andExpect(jsonPath("$.programmeName").value(PROGRAMME_NAME))
        .andExpect(jsonPath("$.type").value(TYPE))
        .andExpect(jsonPath("$.programmeMembershipId").value(PROGRAMME_MEMBERSHIP_ID))
    ;

  }

  @Test
  public void getTraineeAssessmentShouldReturnNotFoundWhenNoneExists() throws Exception {

    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID_1)).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1))
        .andExpect(status().isNotFound());
  }

  @Test
  public void createTraineeAssessmentShouldBadRequestWhenTraineeIdDoesntMatchTraineeIdInPayload() throws Exception {

    AssessmentDTO toCreate = new AssessmentDTO();
    toCreate.setTraineeId(222222L);
    toCreate.setFirstName(FIRST_NAME);
    toCreate.setLastName(LAST_NAME);
    toCreate.setProgrammeNumber(PROGRAMME_NUMBER);
    toCreate.setProgrammeName(PROGRAMME_NAME);
    toCreate.setType(TYPE);


    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID_1)).thenReturn(Optional.empty());

    mockMvc.perform(post("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(toCreate)))
        .andExpect(status().isBadRequest());
  }


  @Test
  public void updateTraineeAssessmentShouldBadRequestWhenTraineeIdDoesntMatchTraineeIdInPayload() throws Exception {

    AssessmentDTO toUpdate = new AssessmentDTO();
    toUpdate.setTraineeId(222222L);
    toUpdate.setFirstName(FIRST_NAME);
    toUpdate.setLastName(LAST_NAME);
    toUpdate.setProgrammeNumber(PROGRAMME_NUMBER);
    toUpdate.setProgrammeName(PROGRAMME_NAME);
    toUpdate.setType(TYPE);


    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID_1)).thenReturn(Optional.empty());

    mockMvc.perform(put("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(toUpdate)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateTraineeAssessmentShouldBadRequestWhenAssessmentIdDoesntMatchAssessmentIdInPayload() throws Exception {

    AssessmentDTO toUpdate = new AssessmentDTO();
    toUpdate.setId(222222L);
    toUpdate.setTraineeId(TRAINEE_ID);
    toUpdate.setFirstName(FIRST_NAME);
    toUpdate.setLastName(LAST_NAME);
    toUpdate.setProgrammeNumber(PROGRAMME_NUMBER);
    toUpdate.setProgrammeName(PROGRAMME_NAME);
    toUpdate.setType(TYPE);


    when(assessmentServiceMock.findTraineeAssessmentDTO(TRAINEE_ID, ASSESSMENT_ID_1)).thenReturn(Optional.empty());

    mockMvc.perform(put("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(toUpdate)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateSpecificTraineeAssessmentShouldBadRequestWhenAssessmentIdDoesntMatchAssessmentIdInPayload() throws Exception {

    AssessmentDTO assessmentToUpdate = new AssessmentDTO();
    assessmentToUpdate.setId(ASSESSMENT_ID_1);
    assessmentToUpdate.setTraineeId(TRAINEE_ID);
    assessmentToUpdate.setFirstName(FIRST_NAME);
    assessmentToUpdate.setLastName(LAST_NAME);
    assessmentToUpdate.setProgrammeNumber(PROGRAMME_NUMBER);
    assessmentToUpdate.setProgrammeName(PROGRAMME_NAME);
    assessmentToUpdate.setType(TYPE);

    when(assessmentServiceMock.save(assessmentDTOArgumentCaptor.capture())).thenReturn(assessmentToUpdate);

    mockMvc.perform(put("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentToUpdate)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(ASSESSMENT_ID_1));

    AssessmentDTO captorValue = assessmentDTOArgumentCaptor.getValue();
    Assert.assertEquals(assessmentToUpdate.getId(), captorValue.getId());
    Assert.assertEquals(assessmentToUpdate.getFirstName(), captorValue.getFirstName());
    Assert.assertEquals(assessmentToUpdate.getLastName(), captorValue.getLastName());
    Assert.assertEquals(assessmentToUpdate.getProgrammeNumber(), captorValue.getProgrammeNumber());
    Assert.assertEquals(assessmentToUpdate.getProgrammeName(), captorValue.getProgrammeName());
    Assert.assertEquals(assessmentToUpdate.getType(), captorValue.getType());
    Assert.assertEquals(assessmentToUpdate.getTraineeId(), captorValue.getTraineeId());
  }

  @Test
  public void deleteTraineeAssessmentShouldReturnBadRequestWhenDeletionFails() throws Exception {

    when(assessmentServiceMock.deleteTraineeAssessment(ASSESSMENT_ID_1, TRAINEE_ID)).thenReturn(false);

    mockMvc.perform(delete("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1))
        .andExpect(status().isBadRequest());

  }

  @Test
  public void deleteTraineeAssessmentShouldReturnOkAfterSuccessfulDeletion() throws Exception {

    when(assessmentServiceMock.deleteTraineeAssessment(ASSESSMENT_ID_1, TRAINEE_ID)).thenReturn(true);

    mockMvc.perform(delete("/api/trainee/{traineeId}/assessments/{assessmentId}", TRAINEE_ID, ASSESSMENT_ID_1))
        .andExpect(status().isOk());

  }

  @Test
  public void getAllAssessmentsShouldReturnAllAssessmentsPaginatedWhenNoSearchQueryProvided() throws Exception {
    AssessmentListDTO assessmentListDTO = new AssessmentListDTO();
    assessmentListDTO.setId(1L);
    assessmentListDTO.setCurriculumName(CURRICULUM_NAME);
    assessmentListDTO.setEventStatus(EventStatus.COMPLETED);
    assessmentListDTO.setFirstName(FIRST_NAME);
    assessmentListDTO.setLastName(LAST_NAME);
    assessmentListDTO.setOutcome(OUTCOME_1);

    AssessmentListDTO anotherAssessment = new AssessmentListDTO();
    anotherAssessment.setId(2L);
    anotherAssessment.setCurriculumName(ANOTHER_CURRICULUM_NAME);
    anotherAssessment.setEventStatus(EventStatus.APPEALED);
    anotherAssessment.setFirstName(ANOTHER_FIRST_NAME);
    anotherAssessment.setLastName(ANOTHER_LAST_NAME);
    anotherAssessment.setOutcome(ANOTHER_OUTCOME);

    Page<AssessmentListDTO> pagedResponse = new PageImpl<>(Lists.newArrayList(assessmentListDTO, anotherAssessment));
    when(assessmentServiceMock.advancedSearch(eq(null), eq(null), pageableArgumentCaptor.capture())).thenReturn(pagedResponse);

    mockMvc.perform(get("/api/trainee/assessments"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.*").isArray())
        .andExpect(jsonPath("$.[*].id", hasItems(1, 2)))
        .andExpect(jsonPath("$.[*].curriculumName", hasItems(CURRICULUM_NAME, ANOTHER_CURRICULUM_NAME)))
        .andExpect(jsonPath("$.[*].eventStatus", hasItems(EventStatus.COMPLETED.name(), EventStatus.APPEALED.name())))
        .andExpect(jsonPath("$.[*].firstName", hasItems(FIRST_NAME, ANOTHER_FIRST_NAME)))
        .andExpect(jsonPath("$.[*].lastName", hasItems(LAST_NAME, ANOTHER_LAST_NAME)))
        .andExpect(jsonPath("$.[*].outcome", hasItems(OUTCOME_1, ANOTHER_OUTCOME)))
    ;
  }

  @Test
  public void shouldSanitizeParamWhenGetAllAssessment() throws Exception{
      AssessmentListDTO assessmentListDTO = new AssessmentListDTO();
      assessmentListDTO.setId(1L);
      assessmentListDTO.setCurriculumName(CURRICULUM_NAME);
      assessmentListDTO.setEventStatus(EventStatus.COMPLETED);
      assessmentListDTO.setFirstName(FIRST_NAME);
      assessmentListDTO.setLastName(LAST_NAME);
      assessmentListDTO.setOutcome(OUTCOME_1);

      Page<AssessmentListDTO> pagedResponse = new PageImpl<>(Lists.newArrayList(assessmentListDTO));
      when(assessmentServiceMock.advancedSearch(stringArgumentCaptor.capture(), Matchers.any(), pageableArgumentCaptor.capture())).thenReturn(pagedResponse);

      mockMvc.perform(get(new URI("/api/trainee/assessments?page=0&size=20&sort=id,desc&searchQuery=%22King%27s%2520College%2520Dental%2520institute%2520-%2520ACF%2520(NIHR)%22")))
          .andExpect(status().isOk());

      String str = stringArgumentCaptor.getValue();
      Assert.assertThat("should sanitize param", str, CoreMatchers.equalTo("King\\'s College Dental institute - ACF (NIHR)"));
  }
}
