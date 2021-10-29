package com.transformuk.hee.tis.assessment.service.service;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
  Optional<AssessmentDTO> findTraineeAssessmentDTO(Long traineeId, Long assessmentId);

  /**
   * Get an assessment linked to a trainee.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessment
   * @return the entity
   */
  Optional<Assessment> findTraineeAssessment(Long traineeId, Long assessmentId);

  Page<AssessmentListDTO> advancedSearch(String searchString, List<ColumnFilter> columnFilters, Pageable pageable);

  Page<AssessmentDTO> findForTrainee(Long traineeId, Pageable page);

  List<AssessmentDTO> findAllForTrainee(Long traineeId, Sort sort);

  /**
   * Get a list of Assessment for the set of Ids.
   *
   * @param assessmentIds the set of Assessment ids
   * @return the list of Assessment DTOs
   */
  List<AssessmentDTO> findAssessmentsByIds(Set<Long> assessmentIds);

  /**
   * delete an assessment with all associated links
   *
   * @param assessmentId the TIS id of the assessment
   * @param traineeId the TIS id of the trainee
   * @return true if the deletion was successful, otherwise false
   */
  boolean deleteTraineeAssessment(Long assessmentId, Long traineeId);

  /**
   * soft delete an assessment by adding a timestamp
   *
   * @param assessmentId the TIS id of the assessment
   * @param traineeId the TIS id of the trainee
   * @return true if the deletion was successful, otherwise false
   */
  boolean softDeleteTraineeAssessment(Long assessmentId, Long traineeId);

  /**
   * delete an assessment with all associated links
   *
   * @param assessmentId the TIS id of the assessment
   * @return true if the deletion was successful, otherwise false
   */
  boolean deleteAssessment(Long assessmentId);

  /**
   * bulk update assessments.
   *
   * @param assessmentDtos the list of assessmentDTOs to update
   * @return saved assessmentDTO list
   */
  List<AssessmentDTO> patchAssessments(List<AssessmentDTO> assessmentDtos);
}
