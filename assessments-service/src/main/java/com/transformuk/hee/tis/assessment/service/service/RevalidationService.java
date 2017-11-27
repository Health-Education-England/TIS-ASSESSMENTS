package com.transformuk.hee.tis.assessment.service.service;


import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;

import java.util.List;

/**
 * Service Interface for managing Outcome.
 */
public interface RevalidationService {

  /**
   * Save a revalidation.
   *
   * @param revalidationDTO the entity to save
   * @return the persisted entity
   */
  RevalidationDTO save(RevalidationDTO revalidationDTO);

  /**
   * Save a collection revalidations.
   *
   * @param revalidationDTOs the collection of revalidations to save
   * @return the persisted entity
   */
  List<RevalidationDTO> save(List<RevalidationDTO> revalidationDTOs);

  /**
   * Get all the revalidations.
   *
   * @return the list of entities
   */
  List<RevalidationDTO> findAll();

  /**
   * Get the "id" revalidation.
   *
   * @param id the id of the entity
   * @return the entity
   */
  RevalidationDTO findOne(Long id);

  /**
   * Delete the "id" revalidation.
   *
   * @param id the id of the entity
   */
  void delete(Long id);
}
