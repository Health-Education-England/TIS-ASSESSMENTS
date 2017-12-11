package com.transformuk.hee.tis.assessment.service.service;


import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;

import java.util.Optional;

/**
 * Service Interface for managing Revalidation.
 */
public interface RevalidationService {

  /**
   * Get revalidation by assessment id.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessment entity
   * @return the entity
   */
  Optional<RevalidationDTO> findAssessmentRevalidationBy(String traineeId, Long assessmentId);

  /**
   * Save a revalidation against an assessment.
   *
   * @param assessment      the assessment to link the revalidation against
   * @param revalidationDTO the entity to save
   * @return the persisted entity
   */
  RevalidationDTO save(Assessment assessment, RevalidationDTO revalidationDTO);

  /**
   * Create a revalidation against an assessment.
   *
   * @param assessment      the assessment to link the revalidation against
   * @param revalidationDTO the entity to create
   * @return the persisted entity
   */
  RevalidationDTO create(Assessment assessment, RevalidationDTO revalidationDTO);


  RevalidationDTO findOne(Long revalidationId);
}
