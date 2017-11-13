package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.EventDTO;
import com.transformuk.hee.tis.assessment.service.model.Event;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Event and its DTO EventDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EventMapper extends EntityMapper<EventDTO, Event> {


  default Event fromId(Long id) {
    if (id == null) {
      return null;
    }
    Event event = new Event();
    event.setId(id);
    return event;
  }
}
