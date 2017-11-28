package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service Interface for managing Assessment.
 */
public interface AssessmentService {

  /**
   * Save a assessment.
   *
   * @param assessmentDTO the entity to save
   * @return the persisted entity
   */
  AssessmentDTO save(AssessmentDTO assessmentDTO);

  /**
   * Save a collection of assessments
   *
   * @param assessmentDTOs
   * @return
   */
  List<AssessmentDTO> save(List<AssessmentDTO> assessmentDTOs);


  /**
   * Get all the assessments.
   *
   * @param pageable the pagination information
   * @return the list of entities
   */
  Page<AssessmentDTO> findAll(Pageable pageable);

  /**
   * Get the "id" assessment.
   *
   * @param id the id of the entity
   * @return the entity
   */
  AssessmentDTO findOne(Long id);


  /**
   * Get an assessment linked to a trainee.
   *
   * @param traineeId the id of the trainee
   * @param assessmentId the id of the assessment
   * @return the entity
   */
  AssessmentDTO findTraineeAssessment(Long traineeId, Long assessmentId);

  /**
   * Delete the "id" assessment.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  Page<AssessmentDTO> advancedSearch(String searchString, List<ColumnFilter> columnFilters, Pageable pageable);

  Page<AssessmentDTO> findAllForTrainee(String traineeId, Pageable page);
}
