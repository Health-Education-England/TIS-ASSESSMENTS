package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;

public interface AssessmentDetailService {

  /**
   * Get one assessment detail by assessment id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  AssessmentDetailDTO findOne(Long id);

  /**
   * Save an assessment detail.
   *
   * @param assessmentId the id of the assessment to link the details to
   * @param assessmentDetailDTO the entity to save
   * @return the persisted entity
   */
  AssessmentDetailDTO save(Long assessmentId, AssessmentDetailDTO assessmentDetailDTO);

}
