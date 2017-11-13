package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
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
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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

  private static final ZonedDateTime DEFAULT_CURRICULUM_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_CURRICULUM_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final ZonedDateTime DEFAULT_CURRICULUM_END_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_CURRICULUM_END_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final Long DEFAULT_CURRICULUM_SPECIALTY_ID = 1L;
  private static final Long UPDATED_CURRICULUM_SPECIALTY_ID = 2L;

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

  private static final ZonedDateTime DEFAULT_PERIOD_COVERED_FROM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_PERIOD_COVERED_FROM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final ZonedDateTime DEFAULT_PERIOD_COVERED_TO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_PERIOD_COVERED_TO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final ZonedDateTime DEFAULT_PORTFOLIO_REVIEW_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_PORTFOLIO_REVIEW_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final Integer DEFAULT_MONTHS_WTE_DURING_PERIOD = 1;
  private static final Integer UPDATED_MONTHS_WTE_DURING_PERIOD = 2;

  private static final Integer DEFAULT_MONTHS_COUNTED_TO_TRAINING = 1;
  private static final Integer UPDATED_MONTHS_COUNTED_TO_TRAINING = 2;

  private static final String DEFAULT_TRAINEE_NTN = "AAAAAAAAAA";
  private static final String UPDATED_TRAINEE_NTN = "BBBBBBBBBB";

  private static final String DEFAULT_PYA = "AAAAAAAAAA";
  private static final String UPDATED_PYA = "BBBBBBBBBB";

  @Autowired
  private AssessmentRepository assessmentRepository;

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
    Assessment assessment = new Assessment()
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
    return assessment;
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

  @Before
  public void initTest() {
    assessment = createEntity(em);
  }

  @Test
  @Transactional
  public void createAssessment() throws Exception {
    int databaseSizeBeforeCreate = assessmentRepository.findAll().size();

    // Create the Assessment
    AssessmentDTO assessmentDTO = assessmentMapper.toDto(assessment);
    restAssessmentMockMvc.perform(post("/api/assessments")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isCreated());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeCreate + 1);
    Assessment testAssessment = assessmentList.get(assessmentList.size() - 1);
    assertThat(testAssessment.getCurriculumId()).isEqualTo(DEFAULT_CURRICULUM_ID);
    assertThat(testAssessment.getCurriculumName()).isEqualTo(DEFAULT_CURRICULUM_NAME);
    assertThat(testAssessment.getCurriculumStartDate()).isEqualTo(DEFAULT_CURRICULUM_START_DATE);
    assertThat(testAssessment.getCurriculumEndDate()).isEqualTo(DEFAULT_CURRICULUM_END_DATE);
    assertThat(testAssessment.getCurriculumSpecialtyId()).isEqualTo(DEFAULT_CURRICULUM_SPECIALTY_ID);
    assertThat(testAssessment.getCurriculumSpecialty()).isEqualTo(DEFAULT_CURRICULUM_SPECIALTY);
    assertThat(testAssessment.getCurriculumSubType()).isEqualTo(DEFAULT_CURRICULUM_SUB_TYPE);
    assertThat(testAssessment.getMembershipType()).isEqualTo(DEFAULT_MEMBERSHIP_TYPE);
    assertThat(testAssessment.getGradeAbbreviation()).isEqualTo(DEFAULT_GRADE_ABBREVIATION);
    assertThat(testAssessment.getGradeName()).isEqualTo(DEFAULT_GRADE_NAME);
    assertThat(testAssessment.getPeriodCoveredFrom()).isEqualTo(DEFAULT_PERIOD_COVERED_FROM);
    assertThat(testAssessment.getPeriodCoveredTo()).isEqualTo(DEFAULT_PERIOD_COVERED_TO);
    assertThat(testAssessment.getPortfolioReviewDate()).isEqualTo(DEFAULT_PORTFOLIO_REVIEW_DATE);
    assertThat(testAssessment.getMonthsWTEDuringPeriod()).isEqualTo(DEFAULT_MONTHS_WTE_DURING_PERIOD);
    assertThat(testAssessment.getMonthsCountedToTraining()).isEqualTo(DEFAULT_MONTHS_COUNTED_TO_TRAINING);
    assertThat(testAssessment.getTraineeNTN()).isEqualTo(DEFAULT_TRAINEE_NTN);
    assertThat(testAssessment.getPya()).isEqualTo(DEFAULT_PYA);
  }

  @Test
  @Transactional
  public void createAssessmentWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = assessmentRepository.findAll().size();

    // Create the Assessment with an existing ID
    assessment.setId(1L);
    AssessmentDTO assessmentDTO = assessmentMapper.toDto(assessment);

    // An entity with an existing ID cannot be created, so this API call must fail
    restAssessmentMockMvc.perform(post("/api/assessments")
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
    assessmentRepository.saveAndFlush(assessment);

    // Get all the assessmentList
    restAssessmentMockMvc.perform(get("/api/assessments?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(assessment.getId().intValue())))
        .andExpect(jsonPath("$.[*].curriculumId").value(hasItem(DEFAULT_CURRICULUM_ID.intValue())))
        .andExpect(jsonPath("$.[*].curriculumName").value(hasItem(DEFAULT_CURRICULUM_NAME.toString())))
        .andExpect(jsonPath("$.[*].curriculumStartDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_CURRICULUM_START_DATE))))
        .andExpect(jsonPath("$.[*].curriculumEndDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_CURRICULUM_END_DATE))))
        .andExpect(jsonPath("$.[*].curriculumSpecialtyId").value(hasItem(DEFAULT_CURRICULUM_SPECIALTY_ID.intValue())))
        .andExpect(jsonPath("$.[*].curriculumSpecialty").value(hasItem(DEFAULT_CURRICULUM_SPECIALTY.toString())))
        .andExpect(jsonPath("$.[*].curriculumSubType").value(hasItem(DEFAULT_CURRICULUM_SUB_TYPE.toString())))
        .andExpect(jsonPath("$.[*].membershipType").value(hasItem(DEFAULT_MEMBERSHIP_TYPE.toString())))
        .andExpect(jsonPath("$.[*].gradeAbbreviation").value(hasItem(DEFAULT_GRADE_ABBREVIATION.toString())))
        .andExpect(jsonPath("$.[*].gradeName").value(hasItem(DEFAULT_GRADE_NAME.toString())))
        .andExpect(jsonPath("$.[*].periodCoveredFrom").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_PERIOD_COVERED_FROM))))
        .andExpect(jsonPath("$.[*].periodCoveredTo").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_PERIOD_COVERED_TO))))
        .andExpect(jsonPath("$.[*].portfolioReviewDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_PORTFOLIO_REVIEW_DATE))))
        .andExpect(jsonPath("$.[*].monthsWTEDuringPeriod").value(hasItem(DEFAULT_MONTHS_WTE_DURING_PERIOD)))
        .andExpect(jsonPath("$.[*].monthsCountedToTraining").value(hasItem(DEFAULT_MONTHS_COUNTED_TO_TRAINING)))
        .andExpect(jsonPath("$.[*].traineeNTN").value(hasItem(DEFAULT_TRAINEE_NTN.toString())))
        .andExpect(jsonPath("$.[*].pya").value(hasItem(DEFAULT_PYA.toString())));
  }

  @Test
  @Transactional
  public void getAssessment() throws Exception {
    // Initialize the database
    assessmentRepository.saveAndFlush(assessment);

    // Get the assessment
    restAssessmentMockMvc.perform(get("/api/assessments/{id}", assessment.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(assessment.getId().intValue()))
        .andExpect(jsonPath("$.curriculumId").value(DEFAULT_CURRICULUM_ID.intValue()))
        .andExpect(jsonPath("$.curriculumName").value(DEFAULT_CURRICULUM_NAME.toString()))
        .andExpect(jsonPath("$.curriculumStartDate").value(TestUtil.sameInstant(DEFAULT_CURRICULUM_START_DATE)))
        .andExpect(jsonPath("$.curriculumEndDate").value(TestUtil.sameInstant(DEFAULT_CURRICULUM_END_DATE)))
        .andExpect(jsonPath("$.curriculumSpecialtyId").value(DEFAULT_CURRICULUM_SPECIALTY_ID.intValue()))
        .andExpect(jsonPath("$.curriculumSpecialty").value(DEFAULT_CURRICULUM_SPECIALTY.toString()))
        .andExpect(jsonPath("$.curriculumSubType").value(DEFAULT_CURRICULUM_SUB_TYPE.toString()))
        .andExpect(jsonPath("$.membershipType").value(DEFAULT_MEMBERSHIP_TYPE.toString()))
        .andExpect(jsonPath("$.gradeAbbreviation").value(DEFAULT_GRADE_ABBREVIATION.toString()))
        .andExpect(jsonPath("$.gradeName").value(DEFAULT_GRADE_NAME.toString()))
        .andExpect(jsonPath("$.periodCoveredFrom").value(TestUtil.sameInstant(DEFAULT_PERIOD_COVERED_FROM)))
        .andExpect(jsonPath("$.periodCoveredTo").value(TestUtil.sameInstant(DEFAULT_PERIOD_COVERED_TO)))
        .andExpect(jsonPath("$.portfolioReviewDate").value(TestUtil.sameInstant(DEFAULT_PORTFOLIO_REVIEW_DATE)))
        .andExpect(jsonPath("$.monthsWTEDuringPeriod").value(DEFAULT_MONTHS_WTE_DURING_PERIOD))
        .andExpect(jsonPath("$.monthsCountedToTraining").value(DEFAULT_MONTHS_COUNTED_TO_TRAINING))
        .andExpect(jsonPath("$.traineeNTN").value(DEFAULT_TRAINEE_NTN.toString()))
        .andExpect(jsonPath("$.pya").value(DEFAULT_PYA.toString()));
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
    assessmentRepository.saveAndFlush(assessment);
    int databaseSizeBeforeUpdate = assessmentRepository.findAll().size();

    // Update the assessment
    Assessment updatedAssessment = assessmentRepository.findOne(assessment.getId());
    updatedAssessment
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

    restAssessmentMockMvc.perform(put("/api/assessments")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isOk());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeUpdate);
    Assessment testAssessment = assessmentList.get(assessmentList.size() - 1);
    assertThat(testAssessment.getCurriculumId()).isEqualTo(UPDATED_CURRICULUM_ID);
    assertThat(testAssessment.getCurriculumName()).isEqualTo(UPDATED_CURRICULUM_NAME);
    assertThat(testAssessment.getCurriculumStartDate()).isEqualTo(UPDATED_CURRICULUM_START_DATE);
    assertThat(testAssessment.getCurriculumEndDate()).isEqualTo(UPDATED_CURRICULUM_END_DATE);
    assertThat(testAssessment.getCurriculumSpecialtyId()).isEqualTo(UPDATED_CURRICULUM_SPECIALTY_ID);
    assertThat(testAssessment.getCurriculumSpecialty()).isEqualTo(UPDATED_CURRICULUM_SPECIALTY);
    assertThat(testAssessment.getCurriculumSubType()).isEqualTo(UPDATED_CURRICULUM_SUB_TYPE);
    assertThat(testAssessment.getMembershipType()).isEqualTo(UPDATED_MEMBERSHIP_TYPE);
    assertThat(testAssessment.getGradeAbbreviation()).isEqualTo(UPDATED_GRADE_ABBREVIATION);
    assertThat(testAssessment.getGradeName()).isEqualTo(UPDATED_GRADE_NAME);
    assertThat(testAssessment.getPeriodCoveredFrom()).isEqualTo(UPDATED_PERIOD_COVERED_FROM);
    assertThat(testAssessment.getPeriodCoveredTo()).isEqualTo(UPDATED_PERIOD_COVERED_TO);
    assertThat(testAssessment.getPortfolioReviewDate()).isEqualTo(UPDATED_PORTFOLIO_REVIEW_DATE);
    assertThat(testAssessment.getMonthsWTEDuringPeriod()).isEqualTo(UPDATED_MONTHS_WTE_DURING_PERIOD);
    assertThat(testAssessment.getMonthsCountedToTraining()).isEqualTo(UPDATED_MONTHS_COUNTED_TO_TRAINING);
    assertThat(testAssessment.getTraineeNTN()).isEqualTo(UPDATED_TRAINEE_NTN);
    assertThat(testAssessment.getPya()).isEqualTo(UPDATED_PYA);
  }

  @Test
  @Transactional
  public void updateNonExistingAssessment() throws Exception {
    int databaseSizeBeforeUpdate = assessmentRepository.findAll().size();

    // Create the Assessment
    AssessmentDTO assessmentDTO = assessmentMapper.toDto(assessment);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restAssessmentMockMvc.perform(put("/api/assessments")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(assessmentDTO)))
        .andExpect(status().isCreated());

    // Validate the Assessment in the database
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteAssessment() throws Exception {
    // Initialize the database
    assessmentRepository.saveAndFlush(assessment);
    int databaseSizeBeforeDelete = assessmentRepository.findAll().size();

    // Get the assessment
    restAssessmentMockMvc.perform(delete("/api/assessments/{id}", assessment.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<Assessment> assessmentList = assessmentRepository.findAll();
    assertThat(assessmentList).hasSize(databaseSizeBeforeDelete - 1);
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

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(assessmentMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(assessmentMapper.fromId(null)).isNull();
  }
}
