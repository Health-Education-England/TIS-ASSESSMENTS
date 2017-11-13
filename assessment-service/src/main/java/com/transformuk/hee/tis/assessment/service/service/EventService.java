package com.transformuk.hee.tis.assessment.service.service;


import com.transformuk.hee.tis.assessment.api.dto.EventDTO;

import java.util.List;

/**
 * Service Interface for managing Event.
 */
public interface EventService {

  /**
   * Save a event.
   *
   * @param eventDTO the entity to save
   * @return the persisted entity
   */
  EventDTO save(EventDTO eventDTO);

  /**
   * Get all the events.
   *
   * @return the list of entities
   */
  List<EventDTO> findAll();

  /**
   * Get the "id" event.
   *
   * @param id the id of the entity
   * @return the entity
   */
  EventDTO findOne(Long id);

  /**
   * Delete the "id" event.
   *
   * @param id the id of the entity
   */
  void delete(Long id);
}
