package com.transformuk.hee.tis.assessment.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SimplifiedFailureExample {

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Test
  @Transactional
  public void simplifiedFailureExample() {
    Assessment assessment = new Assessment();
    assessment.setFirstName("Joe");
    assessment = assessmentRepository.saveAndFlush(assessment);

    int databaseSizeBeforeUpdate = assessmentRepository.findAll().size();

    assessment.setLastName("Bloggs");
    assessmentRepository.saveAndFlush(assessment); // ObjectOptimisticLockingFailureException here.

    int databaseSizeAfterUpdate = assessmentRepository.findAll().size();
    assertThat(databaseSizeAfterUpdate).isEqualTo(databaseSizeBeforeUpdate);
  }
}
