package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentType;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentDetailRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the AssessmentResource REST controller.
 *
 * @see AssessmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AssessmentResourceIntTest {

  private static final Long DEFAULT_CURRICULUM_ID = 1L;
  private static final Long UPDATED_CURRICULUM_ID = 2L;

  private static final String DEFAULT_CURRICULUM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_CURRICULUM_NAME = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_CURRICULUM_START_DATE = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_CURRICULUM_START_DATE = LocalDate.now();

  private static final LocalDate DEFAULT_CURRICULUM_END_DATE = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_CURRICULUM_END_DATE = LocalDate.now();

  private static final String DEFAULT_CURRICULUM_SPECIALTY_ID = "11111";
  private static final String UPDATED_CURRICULUM_SPECIALTY_ID = "22222";

  private static final String DEFAULT_CURRICULUM_SPECIALTY = "AAAAAAAAAA";
  private static final String UPDATED_CURRICULUM_SPECIALTY = "BBBBBBBBBB";

  private static final String DEFAULT_CURRICULUM_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_CURRICULUM_SUB_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_MEMBERSHIP_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_MEMBERSHIP_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_GRADE_ABBREVIATION = "AAAAAAAAAA";
  private static final String UPDATED_GRADE_ABBREVIATION = "BBBBBBBBBB";

  private static final String DEFAULT_GRADE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_GRADE_NAME = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_PERIOD_COVERED_FROM = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_PERIOD_COVERED_FROM = LocalDate.now();

  private static final LocalDate DEFAULT_PERIOD_COVERED_TO = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_PERIOD_COVERED_TO = LocalDate.now();

  private static final LocalDate DEFAULT_PORTFOLIO_REVIEW_DATE = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_PORTFOLIO_REVIEW_DATE = LocalDate.now();

  private static final Integer DEFAULT_MONTHS_WTE_DURING_PERIOD = 1;
  private static final Integer UPDATED_MONTHS_WTE_DURING_PERIOD = 2;

  private static final Integer DEFAULT_MONTHS_COUNTED_TO_TRAINING = 1;
  private static final Integer UPDATED_MONTHS_COUNTED_TO_TRAINING = 2;

  private static final String DEFAULT_TRAINEE_NTN = "AAAAAAAAAA";
  private static final String UPDATED_TRAINEE_NTN = "BBBBBBBBBB";

  private static final String DEFAULT_PYA = "AAAAAAAAAA";
  private static final String UPDATED_PYA = "BBBBBBBBBB";

  private static final String DEFAULT_PERSON_ID = "12345";
  private static final String UPDATED_PERSON_ID = "67890";

  private static final String DEFAULT_FIRST_NAME = "firstName-AAAAAA";
  private static final String UPDATED_FIRST_NAME = "firstName-BBBBBB";

  private static final String DEFAULT_LAST_NAME = "lastname-AAAAA";
  private static final String UPDATED_LAST_NAME = "lastname-BBBBB";

  private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_START_DATE = LocalDate.now();

  private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
  private static final LocalDate UPDATED_END_DATE = LocalDate.now();

  private static final String DEFAULT_PROGRAMME_NUMBER = "12345";
  private static final String UPDATED_PROGRAMME_NUMBER = "67890";

  private static final String DEFAULT_PROGRAMME_NAME = "programmeName-AAAAA";
  private static final String UPDATED_PROGRAMME_NAME = "programmeName-BBBBB";

  private static final AssessmentType DEFAULT_ASSESSMENT_TYPE = AssessmentType.ARCP;
  private static final String DEFAULT_INTREPID_ID = "1234567";


  @Autowired
  private AssessmentRepository assessmentRepository;

  @Autowired
  private AssessmentDetailRepository assessmentDetailRepository;

  @Autowired
  private AssessmentMapper assessmentMapper;

  @Autowired
  private AssessmentService assessmentService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restAssessmentMockMvc;

  private Assessment assessment;

  /**
   * Create an entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Assessment createEntity(EntityManager em) {
    AssessmentDetail assessmentDetail = new AssessmentDetail()
        .curriculumId(DEFAULT_CURRICULUM_ID)
        .curriculumName(DEFAULT_CURRICULUM_NAME)
        .curriculumStartDate(DEFAULT_CURRICULUM_START_DATE)
        .curriculumEndDate(DEFAULT_CURRICULUM_END_DATE)
        .curriculumSpecialtyId(DEFAULT_CURRICULUM_SPECIALTY_ID)
        .curriculumSpecialty(DEFAULT_CURRICULUM_SPECIALTY)
        .curriculumSubType(DEFAULT_CURRICULUM_SUB_TYPE)
        .membershipType(DEFAULT_MEMBERSHIP_TYPE)
        .gradeAbbreviation(DEFAULT_GRADE_ABBREVIATION)
        .gradeName(DEFAULT_GRADE_NAME)
        .periodCoveredFrom(DEFAULT_PERIOD_COVERED_FROM)
        .periodCoveredTo(DEFAULT_PERIOD_COVERED_TO)
        .portfolioReviewDate(DEFAULT_PORTFOLIO_REVIEW_DATE)
        .monthsWTEDuringPeriod(DEFAULT_MONTHS_WTE_DURING_PERIOD)
        .monthsCountedToTraining(DEFAULT_MONTHS_COUNTED_TO_TRAINING)
        .traineeNTN(DEFAULT_TRAINEE_NTN)
        .pya(DEFAULT_PYA);

    Assessment assessment = new Assessment()
        .traineeId(DEFAULT_PERSON_ID)
        .firstName(DEFAULT_FIRST_NAME)
        .lastName(DEFAULT_LAST_NAME)
        .startDate(DEFAULT_START_DATE)
        .endDate(DEFAULT_END_DATE)
        .programmeNumber(DEFAULT_PROGRAMME_NUMBER)
        .programmeName(DEFAULT_PROGRAMME_NAME)
        .type(DEFAULT_ASSESSMENT_TYPE)
        .intrepidId(DEFAULT_INTREPID_ID)
        .detail(assessmentDetail);
    return assessment;
  }

  public static AssessmentDTO createDTO() {
    AssessmentDetailDTO assessmentDetailDTO = new AssessmentDetailDTO()
        .curriculumId(DEFAULT_CURRICULUM_ID)
        .curriculumName(DEFAULT_CURRICULUM_NAME)
        .curriculumStartDate(DEFAULT_CURRICULUM_START_DATE)
        .curriculumEndDate(DEFAULT_CURRICULUM_END_DATE)
        .curriculumSpecialtyId(DEFAULT_CURRICULUM_SPECIALTY_ID)
        .curriculumSpecialty(DEFAULT_CURRICULUM_SPECIALTY)
        .curriculumSubType(DEFAULT_CURRICULUM_SUB_TYPE)
        .membershipType(DEFAULT_MEMBERSHIP_TYPE)
        .gradeAbbreviation(DEFAULT_GRADE_ABBREVIATION)
        .gradeName(DEFAULT_GRADE_NAME)
        .periodCoveredFrom(DEFAULT_PERIOD_COVERED_FROM)
        .periodCoveredTo(DEFAULT_PERIOD_COVERED_TO)
        .portfolioReviewDate(DEFAULT_PORTFOLIO_REVIEW_DATE)
        .monthsWTEDuringPeriod(DEFAULT_MONTHS_WTE_DURING_PERIOD)
        .monthsCountedToTraining(DEFAULT_MONTHS_COUNTED_TO_TRAINING)
        .traineeNTN(DEFAULT_TRAINEE_NTN)
        .pya(DEFAULT_PYA);

    AssessmentDTO assessmentDTO = new AssessmentDTO()
        .personId(DEFAULT_PERSON_ID)
        .firstName(DEFAULT_FIRST_NAME)
        .lastName(DEFAULT_LAST_NAME)
        .startDate(DEFAULT_START_DATE)
        .endDate(DEFAULT_END_DATE)
        .programmeNumber(DEFAULT_PROGRAMME_NUMBER)
        .programmeName(DEFAULT_PROGRAMME_NAME)
        .type(DEFAULT_ASSESSMENT_TYPE)
        .intrepidId(DEFAULT_INTREPID_ID)
        .detail(assessmentDetailDTO);
    return assessmentDTO;
  }

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final AssessmentResource assessmentResource = new AssessmentResource(assessmentService);
    this.restAssessmentMockMvc = MockMvcBuilders.standaloneSetup(assessmentResource)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();
  }


  @Test
  @Transactional
  public void createAssessment() throws Exception {
    int databaseSizeBeforeCreate = assessmentRepository.findAll().size();

    // Create the Assessment
    AssessmentDTO assessmentDTO = createDTO();
    restAssessmentMockMvc.perform(post("/api/trainee/{traineeId}/assessments", DEFAULT_PERSON_ID)
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isCreated());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeCreate + 1);
    Assessment testAssessment = assessmentList.get(assessmentList.size() - 1);
    assertThat(testAssessment.getTraineeId()).isEqualTo(DEFAULT_PERSON_ID);
    assertThat(testAssessment.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
    assertThat(testAssessment.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
    assertThat(testAssessment.getStartDate()).isEqualTo(DEFAULT_START_DATE);
    assertThat(testAssessment.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    assertThat(testAssessment.getProgrammeNumber()).isEqualTo(DEFAULT_PROGRAMME_NUMBER);
    assertThat(testAssessment.getProgrammeName()).isEqualTo(DEFAULT_PROGRAMME_NAME);
    assertThat(testAssessment.getType()).isEqualTo(DEFAULT_ASSESSMENT_TYPE);
    assertThat(testAssessment.getDetail()).isNull();
    assertThat(testAssessment.getOutcome()).containsNull();
    assertThat(testAssessment.getRevalidation()).isNull();
  }

  @Test
  @Transactional
  public void createAssessmentWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = assessmentRepository.findAll().size();

    // Create the Assessment with an existing ID
    assessment = createEntity(em);
    assessment.setId(1L);
    AssessmentDTO assessmentDTO = assessmentMapper.toDto(assessment);

    // An entity with an existing ID cannot be created, so this API call must fail
    restAssessmentMockMvc.perform(post("/api/trainee/{traineeId}/assessments", DEFAULT_PERSON_ID)
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isBadRequest());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllAssessments() throws Exception {
    // Initialize the database
    assessment = createEntity(em);
    assessmentDetailRepository.saveAndFlush(assessment.getDetail());
    assessmentRepository.saveAndFlush(assessment);

    // Get all the assessmentList
    restAssessmentMockMvc.perform(get("/api/trainee/assessments?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(assessment.getId().intValue())))
        .andExpect(jsonPath("$.[*].traineeId").value(hasItem(assessment.getTraineeId())))
        .andExpect(jsonPath("$.[*].firstName").value(hasItem(assessment.getFirstName())))
        .andExpect(jsonPath("$.[*].lastName").value(hasItem(assessment.getLastName())))
        .andExpect(jsonPath("$.[*].startDate").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_START_DATE))))
        .andExpect(jsonPath("$.[*].endDate").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_END_DATE))))
        .andExpect(jsonPath("$.[*].programmeNumber").value(hasItem(assessment.getProgrammeNumber())))
        .andExpect(jsonPath("$.[*].programmeName").value(hasItem(assessment.getProgrammeName())))
        .andExpect(jsonPath("$.[*].type").value(hasItem(assessment.getType().toString())))
        .andExpect(jsonPath("$.[*].detail.curriculumId").value(hasItem(DEFAULT_CURRICULUM_ID.intValue())))
        .andExpect(jsonPath("$.[*].detail.curriculumName").value(hasItem(DEFAULT_CURRICULUM_NAME.toString())))
        .andExpect(jsonPath("$.[*].detail.curriculumStartDate").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_CURRICULUM_START_DATE))))
        .andExpect(jsonPath("$.[*].detail.curriculumEndDate").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_CURRICULUM_END_DATE))))
        .andExpect(jsonPath("$.[*].detail.curriculumSpecialtyId").value(hasItem(DEFAULT_CURRICULUM_SPECIALTY_ID)))
        .andExpect(jsonPath("$.[*].detail.curriculumSpecialty").value(hasItem(DEFAULT_CURRICULUM_SPECIALTY.toString())))
        .andExpect(jsonPath("$.[*].detail.curriculumSubType").value(hasItem(DEFAULT_CURRICULUM_SUB_TYPE.toString())))
        .andExpect(jsonPath("$.[*].detail.membershipType").value(hasItem(DEFAULT_MEMBERSHIP_TYPE.toString())))
        .andExpect(jsonPath("$.[*].detail.gradeAbbreviation").value(hasItem(DEFAULT_GRADE_ABBREVIATION.toString())))
        .andExpect(jsonPath("$.[*].detail.gradeName").value(hasItem(DEFAULT_GRADE_NAME.toString())))
        .andExpect(jsonPath("$.[*].detail.periodCoveredFrom").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_PERIOD_COVERED_FROM))))
        .andExpect(jsonPath("$.[*].detail.periodCoveredTo").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_PERIOD_COVERED_TO))))
        .andExpect(jsonPath("$.[*].detail.portfolioReviewDate").value(Matchers.hasItem(TestUtil.sameDate(DEFAULT_PORTFOLIO_REVIEW_DATE))))
        .andExpect(jsonPath("$.[*].detail.monthsWTEDuringPeriod").value(hasItem(DEFAULT_MONTHS_WTE_DURING_PERIOD)))
        .andExpect(jsonPath("$.[*].detail.monthsCountedToTraining").value(hasItem(DEFAULT_MONTHS_COUNTED_TO_TRAINING)))
        .andExpect(jsonPath("$.[*].detail.traineeNTN").value(hasItem(DEFAULT_TRAINEE_NTN.toString())))
        .andExpect(jsonPath("$.[*].detail.pya").value(hasItem(DEFAULT_PYA.toString())));
  }

  @Test
  @Transactional
  public void getAssessment() throws Exception {
    // Initialize the database
    assessment = createEntity(em);
    assessmentDetailRepository.saveAndFlush(assessment.getDetail());
    assessmentRepository.saveAndFlush(assessment);

    // Get the assessment
    restAssessmentMockMvc.perform(get("/api/trainee/{traineeId}/assessments/{assessmentId}", DEFAULT_PERSON_ID, assessment.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(assessment.getId().intValue()))
        .andExpect(jsonPath("$.traineeId").value(assessment.getTraineeId()))
        .andExpect(jsonPath("$.firstName").value(assessment.getFirstName()))
        .andExpect(jsonPath("$.lastName").value(assessment.getLastName()))
        .andExpect(jsonPath("$.startDate").value(TestUtil.sameDate(DEFAULT_START_DATE)))
        .andExpect(jsonPath("$.endDate").value(TestUtil.sameDate(DEFAULT_END_DATE)))
        .andExpect(jsonPath("$.programmeNumber").value(assessment.getProgrammeNumber()))
        .andExpect(jsonPath("$.programmeName").value(assessment.getProgrammeName()))
        .andExpect(jsonPath("$.type").value(assessment.getType().toString()))
        .andExpect(jsonPath("$.detail.curriculumId").value(DEFAULT_CURRICULUM_ID.intValue()))
        .andExpect(jsonPath("$.detail.curriculumName").value(DEFAULT_CURRICULUM_NAME.toString()))
        .andExpect(jsonPath("$.detail.curriculumStartDate").value(TestUtil.sameDate(DEFAULT_CURRICULUM_START_DATE)))
        .andExpect(jsonPath("$.detail.curriculumEndDate").value(TestUtil.sameDate(DEFAULT_CURRICULUM_END_DATE)))
        .andExpect(jsonPath("$.detail.curriculumSpecialtyId").value(DEFAULT_CURRICULUM_SPECIALTY_ID))
        .andExpect(jsonPath("$.detail.curriculumSpecialty").value(DEFAULT_CURRICULUM_SPECIALTY.toString()))
        .andExpect(jsonPath("$.detail.curriculumSubType").value(DEFAULT_CURRICULUM_SUB_TYPE.toString()))
        .andExpect(jsonPath("$.detail.membershipType").value(DEFAULT_MEMBERSHIP_TYPE.toString()))
        .andExpect(jsonPath("$.detail.gradeAbbreviation").value(DEFAULT_GRADE_ABBREVIATION.toString()))
        .andExpect(jsonPath("$.detail.gradeName").value(DEFAULT_GRADE_NAME.toString()))
        .andExpect(jsonPath("$.detail.periodCoveredFrom").value(TestUtil.sameDate(DEFAULT_PERIOD_COVERED_FROM)))
        .andExpect(jsonPath("$.detail.periodCoveredTo").value(TestUtil.sameDate(DEFAULT_PERIOD_COVERED_TO)))
        .andExpect(jsonPath("$.detail.portfolioReviewDate").value(TestUtil.sameDate(DEFAULT_PORTFOLIO_REVIEW_DATE)))
        .andExpect(jsonPath("$.detail.monthsWTEDuringPeriod").value(DEFAULT_MONTHS_WTE_DURING_PERIOD))
        .andExpect(jsonPath("$.detail.monthsCountedToTraining").value(DEFAULT_MONTHS_COUNTED_TO_TRAINING))
        .andExpect(jsonPath("$.detail.traineeNTN").value(DEFAULT_TRAINEE_NTN.toString()))
        .andExpect(jsonPath("$.detail.pya").value(DEFAULT_PYA.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingAssessment() throws Exception {
    // Get the assessment
    restAssessmentMockMvc.perform(get("/api/assessments/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateAssessment() throws Exception {
    // Initialize the database
    assessment = createEntity(em);
    assessmentDetailRepository.saveAndFlush(assessment.getDetail());
    assessmentRepository.saveAndFlush(assessment);
    int databaseSizeBeforeUpdate = assessmentRepository.findAll().size();

    // Update the assessment
    Assessment updatedAssessment = assessmentRepository.findOne(assessment.getId());
    updatedAssessment
//        .personId(UPDATED_PERSON_ID)
        .firstName(UPDATED_FIRST_NAME)
        .lastName(UPDATED_LAST_NAME)
        .startDate(UPDATED_START_DATE)
        .endDate(UPDATED_END_DATE)
        .programmeNumber(UPDATED_PROGRAMME_NUMBER)
        .programmeName(UPDATED_PROGRAMME_NAME);

    updatedAssessment.getDetail()
        .curriculumId(UPDATED_CURRICULUM_ID)
        .curriculumName(UPDATED_CURRICULUM_NAME)
        .curriculumStartDate(UPDATED_CURRICULUM_START_DATE)
        .curriculumEndDate(UPDATED_CURRICULUM_END_DATE)
        .curriculumSpecialtyId(UPDATED_CURRICULUM_SPECIALTY_ID)
        .curriculumSpecialty(UPDATED_CURRICULUM_SPECIALTY)
        .curriculumSubType(UPDATED_CURRICULUM_SUB_TYPE)
        .membershipType(UPDATED_MEMBERSHIP_TYPE)
        .gradeAbbreviation(UPDATED_GRADE_ABBREVIATION)
        .gradeName(UPDATED_GRADE_NAME)
        .periodCoveredFrom(UPDATED_PERIOD_COVERED_FROM)
        .periodCoveredTo(UPDATED_PERIOD_COVERED_TO)
        .portfolioReviewDate(UPDATED_PORTFOLIO_REVIEW_DATE)
        .monthsWTEDuringPeriod(UPDATED_MONTHS_WTE_DURING_PERIOD)
        .monthsCountedToTraining(UPDATED_MONTHS_COUNTED_TO_TRAINING)
        .traineeNTN(UPDATED_TRAINEE_NTN)
        .pya(UPDATED_PYA);

    AssessmentDTO assessmentDTO = assessmentMapper.toDto(updatedAssessment);

    restAssessmentMockMvc.perform(put("/api/trainee/{traineeId}/assessments", DEFAULT_PERSON_ID)
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isOk());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeUpdate);
    Assessment testAssessment = assessmentList.get(assessmentList.size() - 1);
//    assertThat(testAssessment.getTraineeId()).isEqualTo(UPDATED_PERSON_ID);
    assertThat(testAssessment.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
    assertThat(testAssessment.getLastName()).isEqualTo(UPDATED_LAST_NAME);
    assertThat(testAssessment.getStartDate()).isEqualTo(UPDATED_START_DATE);
    assertThat(testAssessment.getEndDate()).isEqualTo(UPDATED_END_DATE);
    assertThat(testAssessment.getProgrammeName()).isEqualTo(UPDATED_PROGRAMME_NAME);
    assertThat(testAssessment.getProgrammeNumber()).isEqualTo(UPDATED_PROGRAMME_NUMBER);
    assertThat(testAssessment.getDetail()).isNull();
  }

  @Test
  @Transactional
  public void updateNonExistingAssessment() throws Exception {
    int databaseSizeBeforeUpdate = assessmentRepository.findAll().size();

    // Create the Assessment
    AssessmentDTO assessmentDTO = createDTO();

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restAssessmentMockMvc.perform(put("/api/trainee/{traineeId}/assessments", DEFAULT_PERSON_ID)
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isCreated());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(Assessment.class);
    Assessment assessment1 = new Assessment();
    assessment1.setId(1L);
    Assessment assessment2 = new Assessment();
    assessment2.setId(assessment1.getId());
    assertThat(assessment1).isEqualTo(assessment2);
    assessment2.setId(2L);
    assertThat(assessment1).isNotEqualTo(assessment2);
    assessment1.setId(null);
    assertThat(assessment1).isNotEqualTo(assessment2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(AssessmentDTO.class);
    AssessmentDTO assessmentDTO1 = new AssessmentDTO();
    assessmentDTO1.setId(1L);
    AssessmentDTO assessmentDTO2 = new AssessmentDTO();
    assertThat(assessmentDTO1).isNotEqualTo(assessmentDTO2);
    assessmentDTO2.setId(assessmentDTO1.getId());
    assertThat(assessmentDTO1).isEqualTo(assessmentDTO2);
    assessmentDTO2.setId(2L);
    assertThat(assessmentDTO1).isNotEqualTo(assessmentDTO2);
    assessmentDTO1.setId(null);
    assertThat(assessmentDTO1).isNotEqualTo(assessmentDTO2);
  }

}
