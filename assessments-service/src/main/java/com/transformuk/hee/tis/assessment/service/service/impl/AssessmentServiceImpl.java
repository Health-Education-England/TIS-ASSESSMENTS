package com.transformuk.hee.tis.assessment.service.service.impl;

import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.containsLike;
import static com.transformuk.hee.tis.assessment.service.service.impl.SpecificationFactory.in;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.ColumnFilter;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentDetailService;
import com.transformuk.hee.tis.assessment.service.service.AssessmentOutcomeService;
import com.transformuk.hee.tis.assessment.service.service.AssessmentService;
import com.transformuk.hee.tis.assessment.service.service.RevalidationService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentListMapper;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import uk.nhs.tis.StringConverter;

/**
 * Service Implementation for managing Assessment.
 */
@Service
public class AssessmentServiceImpl implements AssessmentService {

  protected static final String ASSESSMENT_UPDATE_FAILED_GENERAL =
      "Failed to update Assessment %s. Please try again.";
  protected static final String ASSESSMENT_UPDATE_FAILED_ILLEGAL_STATE =
      "Failed to update Assessment %s. Please check if its outcome is in legacy state.";

  private final Logger log = LoggerFactory.getLogger(AssessmentServiceImpl.class);

  private final PermissionService permissionService;

  private final AssessmentRepository assessmentRepository;

  private final AssessmentMapper assessmentMapper;

  private final AssessmentListMapper assessmentListMapper;

  private AssessmentDetailService assessmentDetailService;

  private AssessmentOutcomeService assessmentOutcomeService;

  private RevalidationService revalidationService;

  AssessmentServiceImpl(AssessmentRepository assessmentRepository,
      AssessmentMapper assessmentMapper, AssessmentListMapper assessmentListMapper,
      PermissionService permissionService, AssessmentDetailService assessmentDetailService,
      AssessmentOutcomeService assessmentOutcomeService, RevalidationService revalidationService) {
    this.assessmentRepository = assessmentRepository;
    this.assessmentMapper = assessmentMapper;
    this.assessmentListMapper = assessmentListMapper;
    this.permissionService = permissionService;
    this.assessmentDetailService = assessmentDetailService;
    this.assessmentOutcomeService = assessmentOutcomeService;
    this.revalidationService = revalidationService;
  }

  /**
   * Save a assessment.
   *
   * @param assessmentDTO the entity to save
   * @return the persisted entity
   */
  @Override
  @Transactional
  public AssessmentDTO save(AssessmentDTO assessmentDTO) {
    Preconditions.checkNotNull(assessmentDTO);

    log.debug("Request to save Assessment : {}",
        StringConverter.getConverter(assessmentDTO.toString()));
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

    return assessmentRepository.findAllBySoftDeletedDate(null, pageable)
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
  public Page<AssessmentListDTO> advancedSearch(String searchString,
      List<ColumnFilter> columnFilters,
      Pageable pageable) {

    List<Specification<Assessment>> specs = new ArrayList<>();

    Specifications notDeleted = Specifications
        .where(SpecificationFactory.isNull("softDeletedDate"));
    specs.add(notDeleted);

    // add the text search criteria
    if (StringUtils.isNotEmpty(searchString)) {
      Specifications whereClause = Specifications
          .where(containsLike("detail.curriculumName", searchString))
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
      Collection<Object> set = Collections
          .unmodifiableSet(permissionService.getUsersProgrammeIds());
      Specifications inProgrammes = Specifications
          .where(in("programmeId", set));
      specs.add(inProgrammes);
    }

    // add the column filters criteria
    if (columnFilters != null && !columnFilters.isEmpty()) {
      columnFilters.forEach(cf -> specs.add(
          in(cf.getName(), SpecificationFactory.getDateAwareValuesFromColumnFilter(cf))));
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
  public List<AssessmentDTO> findAssessmentsByIds(Set<Long> assessmentIds) {
    List<Assessment> assessments = assessmentRepository.findByIdIn(assessmentIds);
    return assessmentMapper.toDto(assessments);
  }

  @Override
  @Transactional(readOnly = true)
  public List<AssessmentDTO> findAllForTrainee(Long traineeId, Sort sort) {
    Preconditions.checkNotNull(traineeId);

    List<Specification<Assessment>> specs = new ArrayList<>();

    Specifications whereClause = Specifications
        .where(SpecificationFactory.equal("traineeId", traineeId));
    specs.add(whereClause);

    Specifications notDeleted = Specifications
        .where(SpecificationFactory.isNull("softDeletedDate"));
    specs.add(notDeleted);

    // limit assessments that belong to specific programmes
    if (permissionService.isProgrammeObserver()) {
      Collection<Object> set = Collections
          .unmodifiableSet(permissionService.getUsersProgrammeIds());
      Specifications inProgrammes = Specifications
          .where(in("programmeId", set));
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
  public boolean deleteAssessment(Long assessmentId) {
    Preconditions.checkNotNull(assessmentId);

    if (assessmentRepository.exists(assessmentId)) {
      // cascade delete is enabled on the relating entities so don't need to delete
      // those manually
      assessmentRepository.delete(assessmentId);
      return true;
    }
    return false;
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

  private AssessmentDTO updateAssessmentWithNestedDtos(AssessmentDTO assessmentDto) {
    Assessment assessment = assessmentMapper.toEntity(assessmentDto);
    AssessmentDetailDTO assessmentDetailDto = null;
    AssessmentOutcomeDTO assessmentOutcomeDto = null;
    RevalidationDTO revalidationDto = null;

    if (assessmentDto.getDetail() != null) {
      assessmentDetailDto = assessmentDto.getDetail().getId() != null
          ? assessmentDetailService.save(assessment, assessmentDto.getDetail())
          : assessmentDetailService.create(assessment, assessmentDto.getDetail());
    }

    if (assessmentDto.getOutcome() != null) {
      assessmentOutcomeDto = assessmentDto.getOutcome().getId() != null
          ? assessmentOutcomeService.save(assessment, assessmentDto.getOutcome())
          : assessmentOutcomeService.create(assessment, assessmentDto.getOutcome());
    }

    if (assessmentDto.getRevalidation() != null) {
      revalidationDto = assessmentDto.getRevalidation().getId() != null
          ? revalidationService.save(assessment, assessmentDto.getRevalidation())
          : revalidationService.create(assessment, assessmentDto.getRevalidation());
    }

    AssessmentDTO savedAssessmentDto = save(assessmentDto);
    savedAssessmentDto.setDetail(assessmentDetailDto);
    savedAssessmentDto.setOutcome(assessmentOutcomeDto);
    savedAssessmentDto.setRevalidation(revalidationDto);
    return savedAssessmentDto;
  }

  @Override
  public List<AssessmentDTO> patchAssessments(List<AssessmentDTO> assessmentDtos) {
    if (assessmentDtos == null || assessmentDtos.isEmpty()) {
      return assessmentDtos;
    }
    log.debug("Request to bulk update {} Assessments", assessmentDtos.size());
    List<AssessmentDTO> returnDtoList = new ArrayList<>();
    AssessmentDTO returnDto = null;
    for (AssessmentDTO assessmentDto : assessmentDtos) {
      try {
        returnDto = updateAssessmentWithNestedDtos(assessmentDto);
      } catch (IllegalStateException ise) {
        // When outcome is marked as legacy, assessmentOutcomeService.save() throws exception
        returnDto = assessmentDto;
        returnDto.addMessage(
            String.format(ASSESSMENT_UPDATE_FAILED_ILLEGAL_STATE, assessmentDto.getId()));
      } catch (Exception e) {
        returnDto = assessmentDto;
        returnDto.addMessage(
            String.format(ASSESSMENT_UPDATE_FAILED_GENERAL, assessmentDto.getId()));
      }
      returnDtoList.add(returnDto);
    }
    return returnDtoList;
  }
}
