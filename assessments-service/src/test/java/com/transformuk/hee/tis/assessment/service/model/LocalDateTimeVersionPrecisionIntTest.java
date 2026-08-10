package com.transformuk.hee.tis.assessment.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentDetailRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentOutcomeRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.repository.RevalidationRepository;
import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * These tests verify that {@code LocalDateTime} version fields are truncated to the same precision
 * when persisted and when retrieved from the database. This is important for optimistic locking to
 * work correctly, as the version field is used to detect concurrent modifications.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class LocalDateTimeVersionPrecisionIntTest {

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Autowired
  private AssessmentDetailRepository assessmentDetailRepository;

  @Autowired
  private AssessmentOutcomeRepository assessmentOutcomeRepository;

  @Autowired
  private RevalidationRepository revalidationRepository;

  @Autowired
  private EntityManagerFactory entityManagerFactory;

  @Test
  public void assessmentVersionShouldUseConsistentTruncation() {
    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    Statistics statistics = sessionFactory.getStatistics();
    statistics.clear();

    Assessment assessment = new Assessment();
    assessment.setFirstName("Joe");
    assessment = assessmentRepository.saveAndFlush(assessment);

    int databaseSizeBeforeUpdate = assessmentRepository.findAll().size();
    assertThat(statistics.getEntityUpdateCount()).isZero();

    assessment.setLastName("Bloggs");
    assessmentRepository.saveAndFlush(assessment);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);

    int databaseSizeAfterUpdate = assessmentRepository.findAll().size();
    assertThat(databaseSizeAfterUpdate).isEqualTo(databaseSizeBeforeUpdate);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);
  }

  @Test
  public void assessmentDetailVersionShouldUseConsistentTruncation() {
    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    Statistics statistics = sessionFactory.getStatistics();
    statistics.clear();

    Assessment parent = new Assessment();
    parent.setFirstName("ParentDetail");
    parent = assessmentRepository.saveAndFlush(parent);

    AssessmentDetail detail = new AssessmentDetail();
    detail.setId(parent.getId());
    detail.setAssessment(parent);
    detail.setCurriculumName("Initial");
    detail = assessmentDetailRepository.saveAndFlush(detail);

    statistics.clear();
    int databaseSizeBeforeUpdate = assessmentDetailRepository.findAll().size();
    assertThat(statistics.getEntityUpdateCount()).isZero();

    detail.setCurriculumName("Updated");
    assessmentDetailRepository.saveAndFlush(detail);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);

    int databaseSizeAfterUpdate = assessmentDetailRepository.findAll().size();
    assertThat(databaseSizeAfterUpdate).isEqualTo(databaseSizeBeforeUpdate);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);
  }

  @Test
  public void assessmentOutcomeVersionShouldUseConsistentTruncation() {
    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    Statistics statistics = sessionFactory.getStatistics();
    statistics.clear();

    Assessment parent = new Assessment();
    parent.setFirstName("ParentOutcome");
    parent = assessmentRepository.saveAndFlush(parent);

    AssessmentOutcome outcome = new AssessmentOutcome();
    outcome.setId(parent.getId());
    outcome.setAssessment(parent);
    outcome.setOutcome("Initial");
    outcome = assessmentOutcomeRepository.saveAndFlush(outcome);

    statistics.clear();
    int databaseSizeBeforeUpdate = assessmentOutcomeRepository.findAll().size();
    assertThat(statistics.getEntityUpdateCount()).isZero();

    outcome.setOutcome("Updated");
    assessmentOutcomeRepository.saveAndFlush(outcome);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);

    int databaseSizeAfterUpdate = assessmentOutcomeRepository.findAll().size();
    assertThat(databaseSizeAfterUpdate).isEqualTo(databaseSizeBeforeUpdate);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);
  }

  @Test
  public void revalidationVersionShouldUseConsistentTruncation() {
    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    Statistics statistics = sessionFactory.getStatistics();
    statistics.clear();

    Assessment parent = new Assessment();
    parent.setFirstName("ParentRevalidation");
    parent = assessmentRepository.saveAndFlush(parent);

    Revalidation revalidation = new Revalidation();
    revalidation.setId(parent.getId());
    revalidation.setAssessment(parent);
    revalidation.setConcernSummary("Initial");
    revalidation = revalidationRepository.saveAndFlush(revalidation);

    statistics.clear();
    int databaseSizeBeforeUpdate = revalidationRepository.findAll().size();
    assertThat(statistics.getEntityUpdateCount()).isZero();

    revalidation.setConcernSummary("Updated");
    revalidationRepository.saveAndFlush(revalidation);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);

    int databaseSizeAfterUpdate = revalidationRepository.findAll().size();
    assertThat(databaseSizeAfterUpdate).isEqualTo(databaseSizeBeforeUpdate);
    assertThat(statistics.getEntityUpdateCount()).isEqualTo(1);
  }
}
