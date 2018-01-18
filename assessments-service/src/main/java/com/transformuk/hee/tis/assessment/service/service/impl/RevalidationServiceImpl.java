package com.transformuk.hee.tis.assessment.service.service.impl;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentRepository;
import com.transformuk.hee.tis.assessment.service.repository.RevalidationRepository;
import com.transformuk.hee.tis.assessment.service.service.RevalidationService;
import com.transformuk.hee.tis.assessment.service.service.mapper.RevalidationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Revalidation.
 */
@Service
@Transactional
public class RevalidationServiceImpl implements RevalidationService {

  private final Logger log = LoggerFactory.getLogger(RevalidationServiceImpl.class);

  private final RevalidationRepository revalidationRepository;
  private final RevalidationMapper revalidationMapper;
  private final AssessmentRepository assessmentRepository;

  public RevalidationServiceImpl(RevalidationRepository revalidationRepository, RevalidationMapper revalidationMapper,
                                 AssessmentRepository assessmentRepository) {
    this.revalidationRepository = revalidationRepository;
    this.revalidationMapper = revalidationMapper;
    this.assessmentRepository = assessmentRepository;
  }

  /**
   * Get revalidation by assessment id.
   *
   * @param traineeId    the id of the trainee
   * @param assessmentId the id of the assessment entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<RevalidationDTO> findAssessmentRevalidationBy(Long traineeId, Long assessmentId) {
    Preconditions.checkNotNull(traineeId);
    Preconditions.checkNotNull(assessmentId);

    Assessment example = new Assessment().traineeId(traineeId).id(assessmentId);
    Assessment foundAssessment = assessmentRepository.findOne(Example.of(example));
    RevalidationDTO revalidationDTO = revalidationMapper.toDto(foundAssessment.getRevalidation());
    return Optional.ofNullable(revalidationDTO);
  }


  /**
   * Save a revalidation against an assessment.
   *
   * @param assessment      the assessment to link the revalidation against
   * @param revalidationDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public RevalidationDTO save(Assessment assessment, RevalidationDTO revalidationDTO) {
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(revalidationDTO);
    Preconditions.checkState(assessment.getId().equals(revalidationDTO.getId()));

    log.debug("Request to save revalidation : {}", revalidationDTO);
    Revalidation revalidation = revalidationMapper.toEntity(revalidationDTO);
    revalidation.setIntrepidId(assessment.getIntrepidId());
    revalidation = revalidationRepository.save(revalidation);
    return revalidationMapper.toDto(revalidation);
  }

  @Override
  public RevalidationDTO create(Assessment assessment, RevalidationDTO revalidationDTO) {
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(revalidationDTO);
    Preconditions.checkState(revalidationDTO.getId() == null);

    log.debug("Request to create revalidation : {}", revalidationDTO);
    revalidationDTO.setId(assessment.getId());
    return save(assessment, revalidationDTO);
  }

  /**
   * Get one Revalidation by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<RevalidationDTO> findOne(Long id) {
    Preconditions.checkNotNull(id);

    log.debug("Request to get revalidation : {}", id);
    Revalidation revalidation = revalidationRepository.findOne(id);
    RevalidationDTO revalidationDTO = revalidationMapper.toDto(revalidation);
    return Optional.ofNullable(revalidationDTO);
  }

}
