package com.transformuk.hee.tis.assessment.service.service;


import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;

import java.util.Optional;

/**
 * Service Interface for managing AssessmentOutcome.
 */
public interface AssessmentOutcomeService {

  /**
   * create a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param assessmentOutcomeDTO the entity to create
   * @return the persisted entity
   */
  AssessmentOutcomeDTO create(Assessment assessment, AssessmentOutcomeDTO assessmentOutcomeDTO);

  /**
   * Save a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param assessmentOutcomeDTO the entity to save
   * @return the persisted entity
   */
  AssessmentOutcomeDTO save(Assessment assessment, AssessmentOutcomeDTO assessmentOutcomeDTO);

  /**
   * Get the "id" outcome.
   *
   * @param id the id of the entity
   * @return the entity
   */
  Optional<AssessmentOutcomeDTO> findOne(Long id);

}
