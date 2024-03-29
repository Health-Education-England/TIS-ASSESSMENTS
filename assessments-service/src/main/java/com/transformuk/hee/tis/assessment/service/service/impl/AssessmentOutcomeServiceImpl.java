package com.transformuk.hee.tis.assessment.service.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcomeReason;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentOutcomeReasonRepository;
import com.transformuk.hee.tis.assessment.service.repository.AssessmentOutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.AssessmentOutcomeService;
import com.transformuk.hee.tis.assessment.service.service.mapper.AssessmentOutcomeMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AssessmentOutcome.
 */
@Service
@Transactional
public class AssessmentOutcomeServiceImpl implements AssessmentOutcomeService {

  private final Logger log = LoggerFactory.getLogger(AssessmentOutcomeServiceImpl.class);

  private final AssessmentOutcomeRepository assessmentOutcomeRepository;
  private final AssessmentOutcomeMapper assessmentOutcomeMapper;
  private final AssessmentOutcomeReasonRepository assessmentOutcomeReasonRepository;


  public AssessmentOutcomeServiceImpl(AssessmentOutcomeRepository assessmentOutcomeRepository,
                                      AssessmentOutcomeMapper assessmentOutcomeMapper,
                                      AssessmentOutcomeReasonRepository assessmentOutcomeReasonRepository) {
    this.assessmentOutcomeRepository = assessmentOutcomeRepository;
    this.assessmentOutcomeMapper = assessmentOutcomeMapper;
    this.assessmentOutcomeReasonRepository = assessmentOutcomeReasonRepository;
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

    final AssessmentOutcome assessmentOutcome = assessmentOutcomeMapper.toEntity(assessmentOutcomeDTO);
    assessmentOutcome.setIntrepidId(assessment.getIntrepidId());

    updateReasons(assessmentOutcome, originalAssessmentOutcome);

    AssessmentOutcome savedAssessmentOutcome = assessmentOutcomeRepository.saveAndFlush(assessmentOutcome);
    return assessmentOutcomeMapper.toDto(savedAssessmentOutcome);
  }

  private void updateReasons(AssessmentOutcome assessmentOutcome, AssessmentOutcome originalAssessmentOutcome) {
    // delete any reasons that have been removed from the list
    List<AssessmentOutcomeReason> reasonsToSave = assessmentOutcome.getReasons();

    if (reasonsToSave == null) {
      reasonsToSave = Lists.newArrayList();
    }

    List<Long> reasonsIdsToSave = reasonsToSave.stream().map(AssessmentOutcomeReason::getId).collect(Collectors.toList());

    List<AssessmentOutcomeReason> originalReasons = Lists.newArrayList();
    if (originalAssessmentOutcome != null && originalAssessmentOutcome.getReasons() != null) {
      originalReasons = originalAssessmentOutcome.getReasons();
    }

    List<AssessmentOutcomeReason> reasonsToRemove = originalReasons.stream()
        .filter(assessmentOutcomeReason -> !reasonsIdsToSave.contains(assessmentOutcomeReason.getId()))
        .collect(Collectors.toList());

    assessmentOutcomeReasonRepository.delete(reasonsToRemove);

    //update the outcome reason
    if(CollectionUtils.isNotEmpty(reasonsToSave)) {
      reasonsToSave.stream().forEach(
          assessmentOutcomeReason -> assessmentOutcomeReason.setAssessmentOutcome(assessmentOutcome)
      );
    }
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
