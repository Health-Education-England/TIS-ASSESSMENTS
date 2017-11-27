package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import com.transformuk.hee.tis.assessment.service.repository.RevalidationRepository;
import com.transformuk.hee.tis.assessment.service.service.RevalidationService;
import com.transformuk.hee.tis.assessment.service.service.mapper.RevalidationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Revalidation.
 */
@Service
@Transactional
public class RevalidationServiceImpl implements RevalidationService {

  private final Logger log = LoggerFactory.getLogger(RevalidationServiceImpl.class);

  private final RevalidationRepository revalidationRepository;

  private final RevalidationMapper revalidationMapper;

  public RevalidationServiceImpl(RevalidationRepository revalidationRepository, RevalidationMapper revalidationMapper) {
    this.revalidationRepository = revalidationRepository;
    this.revalidationMapper = revalidationMapper;
  }

  /**
   * Save a Revalidation.
   *
   * @param revalidationDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public RevalidationDTO save(RevalidationDTO revalidationDTO) {
    log.debug("Request to save revalidation : {}", revalidationDTO);
    Revalidation Revalidation = revalidationMapper.toEntity(revalidationDTO);
    Revalidation = revalidationRepository.save(Revalidation);
    return revalidationMapper.toDto(Revalidation);
  }

  /**
   * Save a collection Revalidations.
   *
   * @param revalidationDTOs the collection of Revalidations to save
   * @return the persisted entity
   */
  @Override
  public List<RevalidationDTO> save(List<RevalidationDTO> revalidationDTOs) {
    log.debug("Request to save a collection of revalidations : {}", revalidationDTOs);
    List<Revalidation> revalidation = revalidationMapper.toEntity(revalidationDTOs);
    revalidation = revalidationRepository.save(revalidation);
    return revalidationMapper.toDto(revalidation);
  }

  /**
   * Get all the Revalidations.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<RevalidationDTO> findAll() {
    log.debug("Request to get all revalidations");
    return revalidationRepository.findAll().stream()
        .map(revalidationMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one Revalidation by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public RevalidationDTO findOne(Long id) {
    log.debug("Request to get revalidation : {}", id);
    Revalidation revalidation = revalidationRepository.findOne(id);
    return revalidationMapper.toDto(revalidation);
  }

  /**
   * Delete the  Revalidation by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete revalidation : {}", id);
    revalidationRepository.delete(id);
  }
}
