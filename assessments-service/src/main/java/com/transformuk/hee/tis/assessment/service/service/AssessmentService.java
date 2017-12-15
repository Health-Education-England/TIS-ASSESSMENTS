package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
   * Get all the assessments.
   *
   * @param pageable the pagination information
   * @return the list of entities
   */
  Page<AssessmentListDTO> findAll(Pageable pageable);

  /**
   * Get the "id" assessment.
   *
   * @param id the id of the entity
   * @return the entity
   */
  AssessmentDTO findOne(Long id);


  /**
   * Get an assessment dto linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessment
   * @return the entity
   */
  Optional<AssessmentDTO> findTraineeAssessmentDTO(String traineeId, Long assessmentId);

  /**
   * Get an assessment linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessment
   * @return the entity
   */
  Optional<Assessment> findTraineeAssessment(String traineeId, Long assessmentId);

  Page<AssessmentListDTO> advancedSearch(String searchString, List<ColumnFilter> columnFilters, Pageable pageable);

  Page<AssessmentDTO> findAllForTrainee(String traineeId, Pageable page);
}
