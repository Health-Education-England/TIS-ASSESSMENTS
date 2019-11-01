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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.containsLike;
import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.in;
import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.isNull;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Collections;

/**
 * Service Implementation for managing Assessment.
 */
@Service
@Transactional
public class AssessmentServiceImpl implements AssessmentService {

  private final Logger log = LoggerFactory.getLogger(AssessmentServiceImpl.class);

  @Autowired
  private PermissionService permissionService;

  @Autowired
  private AssessmentRepository assessmentRepository;

  @Autowired
  private AssessmentMapper assessmentMapper;

  @Autowired
  private AssessmentListMapper assessmentListMapper;


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

    return assessmentRepository.findAllBySoftDeletedDate(null, pageable).map(assessmentListMapper::toDto);
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
  public Page<AssessmentListDTO> advancedSearch(String searchString, List<ColumnFilter> columnFilters,
      Pageable pageable) {

    List<Specification<Assessment>> specs = new ArrayList<>();

    Specifications notDeleted = Specifications.where(SpecificationFactory.isNull("softDeletedDate"));
    specs.add(notDeleted);

    // add the text search criteria
    if (StringUtils.isNotEmpty(searchString)) {
      Specifications whereClause = Specifications.where(containsLike("detail.curriculumName", searchString))
          .or(containsLike("firstName", searchString)).or(containsLike("lastName", searchString))
          .or(containsLike("type", searchString))
          .or(SpecificationFactory.equal("gmcNumber", searchString))
          .or(SpecificationFactory.equal("gdcNumber", searchString))
          .or(SpecificationFactory.equal("publicHealthNumber", searchString));

      if (NumberUtils.isNumber(searchString)) {
        whereClause = whereClause.or(SpecificationFactory.equal("traineeId", searchString));
      }
      specs.add(whereClause);
    }

    // limit assessments that belong to specific programmes
    if (permissionService.isProgrammeObserver()) {
      Collection<Object> set = Collections.unmodifiableSet(permissionService.getUsersProgrammeIds());
      Specifications inProgrammes = Specifications.where(SpecificationFactory.in("programmeId", set));
      specs.add(inProgrammes);
    }

    // add the column filters criteria
    if (columnFilters != null && !columnFilters.isEmpty()) {
      columnFilters.forEach(cf -> specs.add(in(cf.getName(), cf.getValues())));
    }

    Specifications<Assessment> fullSpec = Specifications.where(specs.get(0));
    // add the rest of the specs that made it in
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
    return assessmentRepository.findAll(Example.of(example), page).map(assessmentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public List<AssessmentDTO> findAllForTrainee(Long traineeId, Sort sort) {
    Preconditions.checkNotNull(traineeId);

    List<Specification<Assessment>> specs = new ArrayList<>();

    Specifications whereClause = Specifications.where(SpecificationFactory.equal("traineeId", traineeId));
    specs.add(whereClause);

    Specifications notDeleted = Specifications.where(SpecificationFactory.isNull("softDeletedDate"));
    specs.add(notDeleted);

    // limit assessments that belong to specific programmes
    if (permissionService.isProgrammeObserver()) {
      Collection<Object> set = Collections.unmodifiableSet(permissionService.getUsersProgrammeIds());
      Specifications inProgrammes = Specifications.where(SpecificationFactory.in("programmeId", set));
      specs.add(inProgrammes);
    }

    Specifications<Assessment> fullSpec = Specifications.where(specs.get(0));
    // add the rest of the specs that made it in
    for (int i = 1; i < specs.size(); i++) {
      fullSpec = fullSpec.and(specs.get(i));
    }

    List<Assessment> allAssessments = assessmentRepository.findAll(fullSpec, sort);
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
      // cascade delete is enabled on the relating entities so dont need to delete
      // those manually
      assessmentRepository.delete(assessment);
      return true;
    }
    return false;
  }

  @Override
  @Transactional
  public boolean softDeleteTraineeAssessment(Long assessmentId, Long traineeId) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(assessmentId);

    Optional<Assessment> traineeAssessment = findTraineeAssessment(traineeId, assessmentId);
    if (traineeAssessment.isPresent()) {
      Assessment assessment = traineeAssessment.get();

      // set softDeletedDate timestamp
      assessment.setSoftDeletedDate(java.time.LocalDate.now());
      assessmentRepository.save(assessment);

      return true;
    }
    return false;
  }
}
