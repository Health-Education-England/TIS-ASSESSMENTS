package com.transformuk.hee.tis.assessment.service.repository;

import com.transformuk.hee.tis.assessment.service.Application;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcomeReason;
import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import io.jsonwebtoken.lang.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class AssessmentRepositoryIntTest {

  public static final long ASSESSMENT_ID = 1L;
  @Autowired
  private AssessmentRepository testObj;

  @Autowired
  private EntityManager entityManager;


  @Test
  @Sql(scripts = "/scripts/createAssessment.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  public void deleteShouldDeleteAssessmentWithAllRelations() {

    //check that the data is in the DB
    Assessment assessment = entityManager.find(Assessment.class, ASSESSMENT_ID);
    Assert.notNull(assessment);
    AssessmentDetail assessmentDetail = entityManager.find(AssessmentDetail.class, ASSESSMENT_ID);
    Assert.notNull(assessmentDetail);
    AssessmentOutcome assessmentOutcome = entityManager.find(AssessmentOutcome.class, ASSESSMENT_ID);
    Assert.notNull(assessmentOutcome);
    List<AssessmentOutcomeReason> reasons = assessmentOutcome.getReasons();
    Assert.notEmpty(reasons);
    Revalidation revalidation = entityManager.find(Revalidation.class, ASSESSMENT_ID);
    Assert.notNull(revalidation);

    //when we delete an assessment, all relating entities get deleted
    testObj.delete(ASSESSMENT_ID);

    Assert.isNull(entityManager.find(Assessment.class, ASSESSMENT_ID));
    Assert.isNull(entityManager.find(AssessmentDetail.class, ASSESSMENT_ID));
    Assert.isNull(entityManager.find(AssessmentOutcome.class, ASSESSMENT_ID));
    Assert.isNull(entityManager.find(AssessmentOutcomeReason.class, 1L));
    Assert.isNull(entityManager.find(AssessmentOutcomeReason.class, 2L));
    Assert.isNull(entityManager.find(Revalidation.class, ASSESSMENT_ID));

  }

}