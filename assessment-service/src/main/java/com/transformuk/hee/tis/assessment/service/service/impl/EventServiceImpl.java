package com.transformuk.hee.tis.assessment.service.service.impl;

import com.transformuk.hee.tis.assessment.api.dto.EventDTO;
import com.transformuk.hee.tis.assessment.service.model.Event;
import com.transformuk.hee.tis.assessment.service.repository.EventRepository;
import com.transformuk.hee.tis.assessment.service.service.EventService;
import com.transformuk.hee.tis.assessment.service.service.mapper.EventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Event.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

  private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

  private final EventRepository eventRepository;

  private final EventMapper eventMapper;

  public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
  }

  /**
   * Save a event.
   *
   * @param eventDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public EventDTO save(EventDTO eventDTO) {
    log.debug("Request to save Event : {}", eventDTO);
    Event event = eventMapper.toEntity(eventDTO);
    event = eventRepository.save(event);
    return eventMapper.toDto(event);
  }

  /**
   * Get all the events.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<EventDTO> findAll() {
    log.debug("Request to get all Events");
    return eventRepository.findAll().stream()
        .map(eventMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one event by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public EventDTO findOne(Long id) {
    log.debug("Request to get Event : {}", id);
    Event event = eventRepository.findOne(id);
    return eventMapper.toDto(event);
  }

  /**
   * Delete the  event by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Event : {}", id);
    eventRepository.delete(id);
  }
}
