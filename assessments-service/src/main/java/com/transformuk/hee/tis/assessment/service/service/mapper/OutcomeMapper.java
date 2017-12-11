package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Outcome and its DTO OutcomeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutcomeMapper extends EntityMapper<OutcomeDTO, Outcome> {

  OutcomeDTO toDto(Outcome outcome);

  Outcome toEntity(OutcomeDTO outcomeDTO);


  default Outcome fromId(Long id) {
    if (id == null) {
      return null;
    }
    Outcome outcome = new Outcome();
    outcome.setId(id);
    return outcome;
  }
}
