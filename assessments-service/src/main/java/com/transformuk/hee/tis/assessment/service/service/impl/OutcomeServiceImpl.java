package com.transformuk.hee.tis.assessment.service.service.impl;

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

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
   * Save a outcome.
   *
   * @param outcomeDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public OutcomeDTO save(OutcomeDTO outcomeDTO) {
    log.debug("Request to save Outcome : {}", outcomeDTO);
    Outcome outcome = outcomeMapper.toEntity(outcomeDTO);
    outcome = outcomeRepository.save(outcome);
    return outcomeMapper.toDto(outcome);
  }

  @Override
  public OutcomeDTO create(Assessment assessment, OutcomeDTO outcomeDTO) {
    outcomeDTO.setId(assessment.getId());
    return save(assessment, outcomeDTO);
  }

  @Override
  public OutcomeDTO save(Assessment assessment, OutcomeDTO outcomeDTO) {
    Outcome outcome = outcomeMapper.toEntity(outcomeDTO);
    outcome = outcomeRepository.save(outcome);
    return outcomeMapper.toDto(outcome);
  }

  /**
   * Save a collection outcomes.
   *
   * @param outcomeDTOs the collection of outcomes to save
   * @return the persisted entity
   */
  @Override

  public List<OutcomeDTO> save(List<OutcomeDTO> outcomeDTOs) {
    log.debug("Request to save a collection of Outcomes : {}", outcomeDTOs);
    List<Outcome> outcome = outcomeMapper.toEntity(outcomeDTOs);
    outcome = outcomeRepository.save(outcome);
    return outcomeMapper.toDto(outcome);
  }

  /**
   * Get all the outcomes.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<OutcomeDTO> findAll() {
    log.debug("Request to get all Outcomes");
    return outcomeRepository.findAll().stream()
        .map(outcomeMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one outcome by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public OutcomeDTO findOne(Long id) {
    log.debug("Request to get Outcome : {}", id);
    Outcome outcome = outcomeRepository.findOne(id);
    return outcomeMapper.toDto(outcome);
  }

  /**
   * Delete the  outcome by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Outcome : {}", id);
    outcomeRepository.delete(id);
  }
}
