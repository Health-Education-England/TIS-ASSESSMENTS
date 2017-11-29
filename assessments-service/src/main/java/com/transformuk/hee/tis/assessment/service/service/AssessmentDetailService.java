package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;

import java.util.Optional;

public interface AssessmentDetailService {

  /**
   * Get one assessment detail by assessment assessmentId.
   *
   * @param assessmentId the assessmentId of the entity
   * @return the entity
   */
  Optional<AssessmentDetailDTO> findAssessmentDetailBy(String traineeId, Long assessmentId);

  /**
   * Save an assessment detail.
   *
   * @param assessment          the assessment to link the details to
   * @param assessmentDetailDTO the entity to save
   * @return the persisted entity
   */
  AssessmentDetailDTO save(Assessment assessment, AssessmentDetailDTO assessmentDetailDTO);


  /**
   * Get the AssessmentDetail by ID.
   *
   * @param assessmentDetailId the id of the entity
   * @return the entity
   */
  AssessmentDetailDTO findOne(Long assessmentDetailId);
}
