package com.transformuk.hee.tis.assessment.service.api;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeReason;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.TestUtil;
import com.transformuk.hee.tis.assessment.service.exception.ExceptionTranslator;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import com.transformuk.hee.tis.assessment.service.repository.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import com.transformuk.hee.tis.assessment.service.service.mapper.OutcomeMapper;
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
 * Test class for the OutcomeResource REST controller.
 *
 * @see OutcomeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OutcomeResourceIntTest {

  private static final OutcomeStatus DEFAULT_OUTCOME = OutcomeStatus.OUTCOME_1;
  private static final OutcomeStatus UPDATED_OUTCOME = OutcomeStatus.OUTCOME_2;

  private static final Boolean DEFAULT_UNDER_APPEAL = false;
  private static final Boolean UPDATED_UNDER_APPEAL = true;

  private static final OutcomeReason DEFAULT_REASON = OutcomeReason.UNSATISFACTORY_OUTCOME_REASON;
  private static final OutcomeReason UPDATED_REASON = OutcomeReason.UNSATISFACTORY_OUTCOME_REASON_OTHER;

  private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
  private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

  private static final ZonedDateTime DEFAULT_TRAINING_COMPLETION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_TRAINING_COMPLETION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final ZonedDateTime DEFAULT_EXTENDED_TRAINING_COMPLETION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_EXTENDED_TRAINING_COMPLETION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  private static final Integer DEFAULT_EXTENDED_TRAINING_TIME_IN_MONTHS = 1;
  private static final Integer UPDATED_EXTENDED_TRAINING_TIME_IN_MONTHS = 2;

  private static final Boolean DEFAULT_TEN_PERCENT_AUDIT = false;
  private static final Boolean UPDATED_TEN_PERCENT_AUDIT = true;

  private static final Boolean DEFAULT_EXTERNAL_TRAINER = false;
  private static final Boolean UPDATED_EXTERNAL_TRAINER = true;

  private static final Long DEFAULT_NEXT_ROTATION_GRADE_ID = 1L;
  private static final Long UPDATED_NEXT_ROTATION_GRADE_ID = 2L;

  private static final String DEFAULT_NEXT_ROTATION_GRADE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NEXT_ROTATION_GRADE_NAME = "BBBBBBBBBB";

  private static final Boolean DEFAULT_TRAINEE_NOTIFIED_OF_OUTCOME = false;
  private static final Boolean UPDATED_TRAINEE_NOTIFIED_OF_OUTCOME = true;

  private static final ZonedDateTime DEFAULT_NEXT_REVIEW_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
  private static final ZonedDateTime UPDATED_NEXT_REVIEW_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

  @Autowired
  private OutcomeRepository outcomeRepository;

  @Autowired
  private OutcomeMapper outcomeMapper;

  @Autowired
  private OutcomeService outcomeService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restOutcomeMockMvc;

  private Outcome outcome;

  /**
   * Create an entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static Outcome createEntity(EntityManager em) {
    Outcome outcome = new Outcome()
        .outcome(DEFAULT_OUTCOME)
        .underAppeal(DEFAULT_UNDER_APPEAL)
        .reason(DEFAULT_REASON)
        .comments(DEFAULT_COMMENTS)
        .trainingCompletionDate(DEFAULT_TRAINING_COMPLETION_DATE)
        .extendedTrainingCompletionDate(DEFAULT_EXTENDED_TRAINING_COMPLETION_DATE)
        .extendedTrainingTimeInMonths(DEFAULT_EXTENDED_TRAINING_TIME_IN_MONTHS)
        .tenPercentAudit(DEFAULT_TEN_PERCENT_AUDIT)
        .externalTrainer(DEFAULT_EXTERNAL_TRAINER)
        .nextRotationGradeId(DEFAULT_NEXT_ROTATION_GRADE_ID)
        .nextRotationGradeName(DEFAULT_NEXT_ROTATION_GRADE_NAME)
        .traineeNotifiedOfOutcome(DEFAULT_TRAINEE_NOTIFIED_OF_OUTCOME)
        .nextReviewDate(DEFAULT_NEXT_REVIEW_DATE);
    return outcome;
  }

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final OutcomeResource outcomeResource = new OutcomeResource(outcomeService);
    this.restOutcomeMockMvc = MockMvcBuilders.standaloneSetup(outcomeResource)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();
  }

  @Before
  public void initTest() {
    outcome = createEntity(em);
  }

  @Test
  @Transactional
  public void createOutcome() throws Exception {
    int databaseSizeBeforeCreate = outcomeRepository.findAll().size();

    // Create the Outcome
    OutcomeDTO outcomeDTO = outcomeMapper.toDto(outcome);
    restOutcomeMockMvc.perform(post("/api/outcomes")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(outcomeDTO)))
        .andExpect(status().isCreated());

    // Validate the Outcome in the database
    List<Outcome> outcomeList = outcomeRepository.findAll();
    assertThat(outcomeList).hasSize(databaseSizeBeforeCreate + 1);
    Outcome testOutcome = outcomeList.get(outcomeList.size() - 1);
    assertThat(testOutcome.getOutcome()).isEqualTo(DEFAULT_OUTCOME);
    assertThat(testOutcome.isUnderAppeal()).isEqualTo(DEFAULT_UNDER_APPEAL);
    assertThat(testOutcome.getReason()).isEqualTo(DEFAULT_REASON);
    assertThat(testOutcome.getComments()).isEqualTo(DEFAULT_COMMENTS);
    assertThat(testOutcome.getTrainingCompletionDate()).isEqualTo(DEFAULT_TRAINING_COMPLETION_DATE);
    assertThat(testOutcome.getExtendedTrainingCompletionDate()).isEqualTo(DEFAULT_EXTENDED_TRAINING_COMPLETION_DATE);
    assertThat(testOutcome.getExtendedTrainingTimeInMonths()).isEqualTo(DEFAULT_EXTENDED_TRAINING_TIME_IN_MONTHS);
    assertThat(testOutcome.isTenPercentAudit()).isEqualTo(DEFAULT_TEN_PERCENT_AUDIT);
    assertThat(testOutcome.isExternalTrainer()).isEqualTo(DEFAULT_EXTERNAL_TRAINER);
    assertThat(testOutcome.getNextRotationGradeId()).isEqualTo(DEFAULT_NEXT_ROTATION_GRADE_ID);
    assertThat(testOutcome.getNextRotationGradeName()).isEqualTo(DEFAULT_NEXT_ROTATION_GRADE_NAME);
    assertThat(testOutcome.isTraineeNotifiedOfOutcome()).isEqualTo(DEFAULT_TRAINEE_NOTIFIED_OF_OUTCOME);
    assertThat(testOutcome.getNextReviewDate()).isEqualTo(DEFAULT_NEXT_REVIEW_DATE);
  }

  @Test
  @Transactional
  public void createOutcomeWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = outcomeRepository.findAll().size();

    // Create the Outcome with an existing ID
    outcome.setId(1L);
    OutcomeDTO outcomeDTO = outcomeMapper.toDto(outcome);

    // An entity with an existing ID cannot be created, so this API call must fail
    restOutcomeMockMvc.perform(post("/api/outcomes")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(outcomeDTO)))
        .andExpect(status().isBadRequest());

    // Validate the Outcome in the database
    List<Outcome> outcomeList = outcomeRepository.findAll();
    assertThat(outcomeList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllOutcomes() throws Exception {
    // Initialize the database
    outcomeRepository.saveAndFlush(outcome);

    // Get all the outcomeList
    restOutcomeMockMvc.perform(get("/api/outcomes?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(outcome.getId().intValue())))
        .andExpect(jsonPath("$.[*].outcome").value(hasItem(DEFAULT_OUTCOME.toString())))
        .andExpect(jsonPath("$.[*].underAppeal").value(hasItem(DEFAULT_UNDER_APPEAL.booleanValue())))
        .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
        .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS.toString())))
        .andExpect(jsonPath("$.[*].trainingCompletionDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_TRAINING_COMPLETION_DATE))))
        .andExpect(jsonPath("$.[*].extendedTrainingCompletionDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_EXTENDED_TRAINING_COMPLETION_DATE))))
        .andExpect(jsonPath("$.[*].extendedTrainingTimeInMonths").value(hasItem(DEFAULT_EXTENDED_TRAINING_TIME_IN_MONTHS)))
        .andExpect(jsonPath("$.[*].tenPercentAudit").value(hasItem(DEFAULT_TEN_PERCENT_AUDIT.booleanValue())))
        .andExpect(jsonPath("$.[*].externalTrainer").value(hasItem(DEFAULT_EXTERNAL_TRAINER.booleanValue())))
        .andExpect(jsonPath("$.[*].nextRotationGradeId").value(hasItem(DEFAULT_NEXT_ROTATION_GRADE_ID.intValue())))
        .andExpect(jsonPath("$.[*].nextRotationGradeName").value(hasItem(DEFAULT_NEXT_ROTATION_GRADE_NAME.toString())))
        .andExpect(jsonPath("$.[*].traineeNotifiedOfOutcome").value(hasItem(DEFAULT_TRAINEE_NOTIFIED_OF_OUTCOME.booleanValue())))
        .andExpect(jsonPath("$.[*].nextReviewDate").value(Matchers.hasItem(TestUtil.sameInstant(DEFAULT_NEXT_REVIEW_DATE))));
  }

  @Test
  @Transactional
  public void getOutcome() throws Exception {
    // Initialize the database
    outcomeRepository.saveAndFlush(outcome);

    // Get the outcome
    restOutcomeMockMvc.perform(get("/api/outcomes/{id}", outcome.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(outcome.getId().intValue()))
        .andExpect(jsonPath("$.outcome").value(DEFAULT_OUTCOME.toString()))
        .andExpect(jsonPath("$.underAppeal").value(DEFAULT_UNDER_APPEAL.booleanValue()))
        .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
        .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS.toString()))
        .andExpect(jsonPath("$.trainingCompletionDate").value(TestUtil.sameInstant(DEFAULT_TRAINING_COMPLETION_DATE)))
        .andExpect(jsonPath("$.extendedTrainingCompletionDate").value(TestUtil.sameInstant(DEFAULT_EXTENDED_TRAINING_COMPLETION_DATE)))
        .andExpect(jsonPath("$.extendedTrainingTimeInMonths").value(DEFAULT_EXTENDED_TRAINING_TIME_IN_MONTHS))
        .andExpect(jsonPath("$.tenPercentAudit").value(DEFAULT_TEN_PERCENT_AUDIT.booleanValue()))
        .andExpect(jsonPath("$.externalTrainer").value(DEFAULT_EXTERNAL_TRAINER.booleanValue()))
        .andExpect(jsonPath("$.nextRotationGradeId").value(DEFAULT_NEXT_ROTATION_GRADE_ID.intValue()))
        .andExpect(jsonPath("$.nextRotationGradeName").value(DEFAULT_NEXT_ROTATION_GRADE_NAME.toString()))
        .andExpect(jsonPath("$.traineeNotifiedOfOutcome").value(DEFAULT_TRAINEE_NOTIFIED_OF_OUTCOME.booleanValue()))
        .andExpect(jsonPath("$.nextReviewDate").value(TestUtil.sameInstant(DEFAULT_NEXT_REVIEW_DATE)));
  }

  @Test
  @Transactional
  public void getNonExistingOutcome() throws Exception {
    // Get the outcome
    restOutcomeMockMvc.perform(get("/api/outcomes/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateOutcome() throws Exception {
    // Initialize the database
    outcomeRepository.saveAndFlush(outcome);
    int databaseSizeBeforeUpdate = outcomeRepository.findAll().size();

    // Update the outcome
    Outcome updatedOutcome = outcomeRepository.findOne(outcome.getId());
    updatedOutcome
        .outcome(UPDATED_OUTCOME)
        .underAppeal(UPDATED_UNDER_APPEAL)
        .reason(UPDATED_REASON)
        .comments(UPDATED_COMMENTS)
        .trainingCompletionDate(UPDATED_TRAINING_COMPLETION_DATE)
        .extendedTrainingCompletionDate(UPDATED_EXTENDED_TRAINING_COMPLETION_DATE)
        .extendedTrainingTimeInMonths(UPDATED_EXTENDED_TRAINING_TIME_IN_MONTHS)
        .tenPercentAudit(UPDATED_TEN_PERCENT_AUDIT)
        .externalTrainer(UPDATED_EXTERNAL_TRAINER)
        .nextRotationGradeId(UPDATED_NEXT_ROTATION_GRADE_ID)
        .nextRotationGradeName(UPDATED_NEXT_ROTATION_GRADE_NAME)
        .traineeNotifiedOfOutcome(UPDATED_TRAINEE_NOTIFIED_OF_OUTCOME)
        .nextReviewDate(UPDATED_NEXT_REVIEW_DATE);
    OutcomeDTO outcomeDTO = outcomeMapper.toDto(updatedOutcome);

    restOutcomeMockMvc.perform(put("/api/outcomes")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(outcomeDTO)))
        .andExpect(status().isOk());

    // Validate the Outcome in the database
    List<Outcome> outcomeList = outcomeRepository.findAll();
    assertThat(outcomeList).hasSize(databaseSizeBeforeUpdate);
    Outcome testOutcome = outcomeList.get(outcomeList.size() - 1);
    assertThat(testOutcome.getOutcome()).isEqualTo(UPDATED_OUTCOME);
    assertThat(testOutcome.isUnderAppeal()).isEqualTo(UPDATED_UNDER_APPEAL);
    assertThat(testOutcome.getReason()).isEqualTo(UPDATED_REASON);
    assertThat(testOutcome.getComments()).isEqualTo(UPDATED_COMMENTS);
    assertThat(testOutcome.getTrainingCompletionDate()).isEqualTo(UPDATED_TRAINING_COMPLETION_DATE);
    assertThat(testOutcome.getExtendedTrainingCompletionDate()).isEqualTo(UPDATED_EXTENDED_TRAINING_COMPLETION_DATE);
    assertThat(testOutcome.getExtendedTrainingTimeInMonths()).isEqualTo(UPDATED_EXTENDED_TRAINING_TIME_IN_MONTHS);
    assertThat(testOutcome.isTenPercentAudit()).isEqualTo(UPDATED_TEN_PERCENT_AUDIT);
    assertThat(testOutcome.isExternalTrainer()).isEqualTo(UPDATED_EXTERNAL_TRAINER);
    assertThat(testOutcome.getNextRotationGradeId()).isEqualTo(UPDATED_NEXT_ROTATION_GRADE_ID);
    assertThat(testOutcome.getNextRotationGradeName()).isEqualTo(UPDATED_NEXT_ROTATION_GRADE_NAME);
    assertThat(testOutcome.isTraineeNotifiedOfOutcome()).isEqualTo(UPDATED_TRAINEE_NOTIFIED_OF_OUTCOME);
    assertThat(testOutcome.getNextReviewDate()).isEqualTo(UPDATED_NEXT_REVIEW_DATE);
  }

  @Test
  @Transactional
  public void updateNonExistingOutcome() throws Exception {
    int databaseSizeBeforeUpdate = outcomeRepository.findAll().size();

    // Create the Outcome
    OutcomeDTO outcomeDTO = outcomeMapper.toDto(outcome);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restOutcomeMockMvc.perform(put("/api/outcomes")
        .contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(outcomeDTO)))
        .andExpect(status().isCreated());

    // Validate the Outcome in the database
    List<Outcome> outcomeList = outcomeRepository.findAll();
    assertThat(outcomeList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteOutcome() throws Exception {
    // Initialize the database
    outcomeRepository.saveAndFlush(outcome);
    int databaseSizeBeforeDelete = outcomeRepository.findAll().size();

    // Get the outcome
    restOutcomeMockMvc.perform(delete("/api/outcomes/{id}", outcome.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<Outcome> outcomeList = outcomeRepository.findAll();
    assertThat(outcomeList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(Outcome.class);
    Outcome outcome1 = new Outcome();
    outcome1.setId(1L);
    Outcome outcome2 = new Outcome();
    outcome2.setId(outcome1.getId());
    assertThat(outcome1).isEqualTo(outcome2);
    outcome2.setId(2L);
    assertThat(outcome1).isNotEqualTo(outcome2);
    outcome1.setId(null);
    assertThat(outcome1).isNotEqualTo(outcome2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(OutcomeDTO.class);
    OutcomeDTO outcomeDTO1 = new OutcomeDTO();
    outcomeDTO1.setId(1L);
    OutcomeDTO outcomeDTO2 = new OutcomeDTO();
    assertThat(outcomeDTO1).isNotEqualTo(outcomeDTO2);
    outcomeDTO2.setId(outcomeDTO1.getId());
    assertThat(outcomeDTO1).isEqualTo(outcomeDTO2);
    outcomeDTO2.setId(2L);
    assertThat(outcomeDTO1).isNotEqualTo(outcomeDTO2);
    outcomeDTO1.setId(null);
    assertThat(outcomeDTO1).isNotEqualTo(outcomeDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(outcomeMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(outcomeMapper.fromId(null)).isNull();
  }
}
