package com.transformuk.hee.tis.assessment.service.service;


import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;

import java.util.Optional;

/**
 * Service Interface for managing Outcome.
 */
public interface OutcomeService {

  /**
   * create a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param outcomeDTO the entity to create
   * @return the persisted entity
   */
  OutcomeDTO create(Assessment assessment, OutcomeDTO outcomeDTO);

  /**
   * Save a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param outcomeDTO the entity to save
   * @return the persisted entity
   */
  OutcomeDTO save(Assessment assessment, OutcomeDTO outcomeDTO);

  /**
   * Get the "id" outcome.
   *
   * @param id the id of the entity
   * @return the entity
   */
  Optional<OutcomeDTO> findOne(Long id);

}
