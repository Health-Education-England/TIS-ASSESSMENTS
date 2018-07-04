package com.transformuk.hee.tis.assessment.service.service.impl;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentListMapper;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
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
  private final AssessmentListMapper assessmentListMapper;

  public AssessmentServiceImpl(AssessmentRepository assessmentRepository, AssessmentMapper assessmentMapper,
                               AssessmentListMapper assessmentListMapper) {
    this.assessmentRepository = assessmentRepository;
    this.assessmentMapper = assessmentMapper;
    this.assessmentListMapper = assessmentListMapper;
  }

  /**
   * Save a assessment.
   *
   * @param assessmentDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public AssessmentDTO save(AssessmentDTO assessmentDTO) {
    Preconditions.checkNotNull(assessmentDTO);

    log.debug("Request to save Assessment : {}", assessmentDTO);
    assessmentDTO.setDetail(null);
    assessmentDTO.setOutcome(null);
    assessmentDTO.setRevalidation(null);
    Assessment assessment = assessmentMapper.toEntity(assessmentDTO);
    Assessment savedAssessment = assessmentRepository.saveAndFlush(assessment);
    return assessmentMapper.toDto(savedAssessment);
  }

  /**
   * Get all the assessments.
   *
   * @param pageable the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<AssessmentListDTO> findAll(Pageable pageable) {
    Preconditions.checkNotNull(pageable);

    log.debug("Request to get all Assessments Lists");
    return assessmentRepository.findAll(pageable)
        .map(assessmentListMapper::toDto);
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
    Preconditions.checkNotNull(id);

    log.debug("Request to get Assessment : {}", id);
    Assessment assessment = assessmentRepository.findOne(id);
    return assessmentMapper.toDto(assessment);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<AssessmentListDTO> advancedSearch(String searchString, List<ColumnFilter> columnFilters, Pageable pageable) {
    Preconditions.checkState(StringUtils.isNotEmpty(searchString) ||
        CollectionUtils.isNotEmpty(columnFilters), "cannot use advanced search if both filters or search query are empty");

    List<Specification<Assessment>> specs = new ArrayList<>();
    //add the text search criteria
    if (StringUtils.isNotEmpty(searchString)) {
      Specifications whereClause = Specifications.where(
          containsLike("detail.curriculumName", searchString)).
          or(containsLike("firstName", searchString)).
          or(containsLike("lastName", searchString)).
          or(containsLike("type", searchString));

      if (NumberUtils.isNumber(searchString)) {
        whereClause = whereClause.or(SpecificationFactory.equal("traineeId", searchString));
      }
      specs.add(whereClause);
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

    return result.map(assessmentListMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Assessment> findTraineeAssessment(Long traineeId, Long assessmentId) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(assessmentId);

    Assessment example = new Assessment().traineeId(traineeId).id(assessmentId);
    Assessment foundAssessment = assessmentRepository.findOne(Example.of(example));
    return Optional.ofNullable(foundAssessment);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<AssessmentDTO> findTraineeAssessmentDTO(Long traineeId, Long assessmentId) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(assessmentId);

    Optional<Assessment> traineeAssessment = findTraineeAssessment(traineeId, assessmentId);
    return Optional.ofNullable(assessmentMapper.toDto(traineeAssessment.orElse(null)));
  }

  @Override
  @Transactional(readOnly = true)
  public Page<AssessmentDTO> findForTrainee(Long traineeId, Pageable page) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(page);

    Assessment example = new Assessment().traineeId(traineeId);
    return assessmentRepository.findAll(Example.of(example), page)
        .map(assessmentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<AssessmentDTO> findAllForTrainee(Long traineeId) {
    Preconditions.checkNotNull(traineeId);

    Assessment example = new Assessment().traineeId(traineeId);
    List<Assessment> allAssessments = assessmentRepository.findAll(Example.of(example));
    return assessmentMapper.toDto(allAssessments);
  }

  @Override
  @Transactional
  public boolean deleteTraineeAssessment(Long assessmentId, Long traineeId) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(assessmentId);

    Optional<Assessment> traineeAssessment = findTraineeAssessment(traineeId, assessmentId);
    if (traineeAssessment.isPresent()) {
      Assessment assessment = traineeAssessment.get();
      //cascade delete is enabled on the relating entities so dont need to delete those manually
      assessmentRepository.delete(assessment);
      return true;
    }
    return false;
  }
}
