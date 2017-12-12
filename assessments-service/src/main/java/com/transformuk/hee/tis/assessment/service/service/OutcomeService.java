package com.transformuk.hee.tis.assessment.service.service;


import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;

import java.util.List;

/**
 * Service Interface for managing Outcome.
 */
public interface OutcomeService {

  /**
   * Save a outcome.
   *
   * @param outcomeDTO the entity to save
   * @return the persisted entity
   */
  OutcomeDTO save(OutcomeDTO outcomeDTO);

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
   * Save a collection outcomes.
   *
   * @param outcomeDTOs the collection of outcomes to save
   * @return the persisted entity
   */
  List<OutcomeDTO> save(List<OutcomeDTO> outcomeDTOs);

  /**
   * Get all the outcomes.
   *
   * @return the list of entities
   */
  List<OutcomeDTO> findAll();

  /**
   * Get the "id" outcome.
   *
   * @param id the id of the entity
   * @return the entity
   */
  OutcomeDTO findOne(Long id);

  /**
   * Delete the "id" outcome.
   *
   * @param id the id of the entity
   */
  void delete(Long id);
}
