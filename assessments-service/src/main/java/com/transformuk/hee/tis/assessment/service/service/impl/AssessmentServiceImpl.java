package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.containsLike;
import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.in;


/**
 * Service Implementation for managing Assessment.
 */
@Service
@Transactional
public class AssessmentServiceImpl implements AssessmentService {

  private final Logger log = LoggerFactory.getLogger(AssessmentServiceImpl.class);

  private final AssessmentRepository assessmentRepository;
  private final AssessmentMapper assessmentMapper;

  public AssessmentServiceImpl(AssessmentRepository assessmentRepository, AssessmentMapper assessmentMapper) {
    this.assessmentRepository = assessmentRepository;
    this.assessmentMapper = assessmentMapper;
  }

  /**
   * Save a assessment.
   *
   * @param assessmentDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public AssessmentDTO save(AssessmentDTO assessmentDTO) {
    log.debug("Request to save Assessment : {}", assessmentDTO);
    assessmentDTO.setDetail(null);
    assessmentDTO.setOutcome(null);
    assessmentDTO.setRevalidation(null);
    Assessment assessment = assessmentMapper.toEntity(assessmentDTO);
    assessment = assessmentRepository.save(assessment);
    return assessmentMapper.toDto(assessment);
  }


  @Override
  public List<AssessmentDTO> save(List<AssessmentDTO> assessmentDTOs) {
    log.debug("Request to save collcetion of Assessment : {}", assessmentDTOs);
    List<Assessment> assessments = assessmentMapper.toEntity(assessmentDTOs);
    assessments = assessmentRepository.save(assessments);
    return assessmentMapper.toDto(assessments);
  }

  /**
   * Get all the assessments.
   *
   * @param pageable the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<AssessmentDTO> findAll(Pageable pageable) {
    log.debug("Request to get all Assessments");
    return assessmentRepository.findAll(pageable)
        .map(assessmentMapper::toDto);
  }

  /**
   * Get one assessment by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public AssessmentDTO findOne(Long id) {
    log.debug("Request to get Assessment : {}", id);
    Assessment assessment = assessmentRepository.findOne(id);
    return assessmentMapper.toDto(assessment);
  }

  /**
   * Delete the  assessment by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Assessment : {}", id);
    assessmentRepository.delete(id);
  }

  @Override
  public Page<AssessmentDTO> advancedSearch(String searchString, List<ColumnFilter> columnFilters, Pageable pageable) {
    List<Specification<Assessment>> specs = new ArrayList<>();
    //add the text search criteria
    if (StringUtils.isNotEmpty(searchString)) {
      specs.add(Specifications.where(containsLike("curriculumName", searchString)).
          or(containsLike("membershipType", searchString)).
          or(containsLike("curriculumSpecialty", searchString)).
          or(containsLike("curriculumSubType", searchString)).
          or(containsLike("gradeAbbreviation", searchString)).
          or(containsLike("gradeName", searchString)));
    }
    //add the column filters criteria
    if (columnFilters != null && !columnFilters.isEmpty()) {
      columnFilters.forEach(cf -> specs.add(in(cf.getName(), cf.getValues())));
    }

    Specifications<Assessment> fullSpec = Specifications.where(specs.get(0));
    //add the rest of the specs that made it in
    for (int i = 1; i < specs.size(); i++) {
      fullSpec = fullSpec.and(specs.get(i));
    }
    Page<Assessment> result = assessmentRepository.findAll(fullSpec, pageable);

    return result.map(person -> assessmentMapper.toDto(person));
  }

  @Override
  public Optional<AssessmentDTO> findTraineeAssessmentDTO(String traineeId, Long assessmentId) {
    Optional<Assessment> traineeAssessment = findTraineeAssessment(traineeId, assessmentId);
    return Optional.ofNullable(assessmentMapper.toDto(traineeAssessment.orElse(null)));
  }

  @Override
  public Optional<Assessment> findTraineeAssessment(String traineeId, Long assessmentId) {
    Assessment example = new Assessment().traineeId(traineeId).id(assessmentId);
    Assessment foundAssessment = assessmentRepository.findOne(Example.of(example));
    return Optional.ofNullable(foundAssessment);
  }

  @Override
  public Page<AssessmentDTO> findAllForTrainee(String traineeId, Pageable page) {
    Assessment example = new Assessment().traineeId(traineeId);
    return assessmentRepository.findAll(Example.of(example), page)
        .map(assessmentMapper::toDto);
  }
}
