package com.transformuk.hee.tis.assessment.service.service.impl;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentOutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentOutcomeService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentOutcomeMapper;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AssessmentOutcome.
 */
@Service
@Transactional
public class AssessmentOutcomeServiceImpl implements AssessmentOutcomeService {

  private final Logger log = LoggerFactory.getLogger(AssessmentOutcomeServiceImpl.class);

  private final AssessmentOutcomeRepository assessmentOutcomeRepository;
  private final AssessmentOutcomeMapper assessmentOutcomeMapper;


  public AssessmentOutcomeServiceImpl(AssessmentOutcomeRepository assessmentOutcomeRepository, AssessmentOutcomeMapper assessmentOutcomeMapper) {
    this.assessmentOutcomeRepository = assessmentOutcomeRepository;
    this.assessmentOutcomeMapper = assessmentOutcomeMapper;
  }

  /**
   * create a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param assessmentOutcomeDTO the entity to create
   * @return the persisted entity
   */
  @Override
  public AssessmentOutcomeDTO create(Assessment assessment, AssessmentOutcomeDTO assessmentOutcomeDTO) {
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(assessmentOutcomeDTO);
    Preconditions.checkState(assessmentOutcomeDTO.getId() == null);

    assessmentOutcomeDTO.setId(assessment.getId());
    return save(assessment, assessmentOutcomeDTO);
  }

  /**
   * Save a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param assessmentOutcomeDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public AssessmentOutcomeDTO save(Assessment assessment, AssessmentOutcomeDTO assessmentOutcomeDTO) {
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(assessmentOutcomeDTO);
    Preconditions.checkState(assessment.getId().equals(assessmentOutcomeDTO.getId()), "AssessmentOutcome id must match the assessment id");

    //get the current version of the entity to see if its a legacy one
    AssessmentOutcome originalAssessmentOutcome = assessmentOutcomeRepository.findOne(assessmentOutcomeDTO.getId());
    if (originalAssessmentOutcome != null && BooleanUtils.isTrue(originalAssessmentOutcome.getLegacy())) { //method is used by create too so there doesn't need to be an assessmentOutcome atm
      throw new IllegalStateException("Cannot modify an assessmentOutcome marked as legacy");
    }

    AssessmentOutcome assessmentOutcome = assessmentOutcomeMapper.toEntity(assessmentOutcomeDTO);
    assessmentOutcome.setIntrepidId(assessment.getIntrepidId());
    assessmentOutcome = assessmentOutcomeRepository.saveAndFlush(assessmentOutcome);
    return assessmentOutcomeMapper.toDto(assessmentOutcome);
  }

  /**
   * Get one outcome by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<AssessmentOutcomeDTO> findOne(Long id) {
    Preconditions.checkNotNull(id);

    log.debug("Request to get AssessmentOutcome : {}", id);
    AssessmentOutcome assessmentOutcome = assessmentOutcomeRepository.findOne(id);
    AssessmentOutcomeDTO assessmentOutcomeDTO = assessmentOutcomeMapper.toDto(assessmentOutcome);
    return Optional.ofNullable(assessmentOutcomeDTO);
  }
}
