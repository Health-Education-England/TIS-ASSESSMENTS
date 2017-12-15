package com.transformuk.hee.tis.assessment.service.service.impl;

import com.google.common.base.Preconditions;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import com.transformuk.hee.tis.assessment.service.repository.OutcomeRepository;
import com.transformuk.hee.tis.assessment.service.service.OutcomeService;
import com.transformuk.hee.tis.assessment.service.service.mapper.OutcomeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Outcome.
 */
@Service
@Transactional
public class OutcomeServiceImpl implements OutcomeService {

  private final Logger log = LoggerFactory.getLogger(OutcomeServiceImpl.class);

  private final OutcomeRepository outcomeRepository;
  private final OutcomeMapper outcomeMapper;


  public OutcomeServiceImpl(OutcomeRepository outcomeRepository, OutcomeMapper outcomeMapper) {
    this.outcomeRepository = outcomeRepository;
    this.outcomeMapper = outcomeMapper;
  }

  /**
   * create a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param outcomeDTO the entity to create
   * @return the persisted entity
   */
  @Override
  public OutcomeDTO create(Assessment assessment, OutcomeDTO outcomeDTO) {
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(outcomeDTO);
    Preconditions.checkState(outcomeDTO.getId() == null);

    outcomeDTO.setId(assessment.getId());
    return save(assessment, outcomeDTO);
  }

  /**
   * Save a outcome and link it to the assessment
   *
   * @param assessment the assessment to link the outcome to
   * @param outcomeDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public OutcomeDTO save(Assessment assessment, OutcomeDTO outcomeDTO) {
    Preconditions.checkNotNull(assessment);
    Preconditions.checkNotNull(outcomeDTO);
    Preconditions.checkState(assessment.getId().equals(outcomeDTO.getId()), "Outcome id must match the assessment id");

    Outcome outcome = outcomeMapper.toEntity(outcomeDTO);
    outcome = outcomeRepository.save(outcome);
    return outcomeMapper.toDto(outcome);
  }

  /**
   * Get one outcome by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<OutcomeDTO> findOne(Long id) {
    Preconditions.checkNotNull(id);

    log.debug("Request to get Outcome : {}", id);
    Outcome outcome = outcomeRepository.findOne(id);
    OutcomeDTO outcomeDTO = outcomeMapper.toDto(outcome);
    return Optional.ofNullable(outcomeDTO);
  }
}
