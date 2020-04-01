package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.ReasonDTO;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Outcome and its DTO OutcomeDTO.
 */
@Mapper(componentModel = "spring")
public interface ReasonMapper {

  ReasonDTO toDto(Reason reason);

  Reason toEntity(ReasonDTO reasonDTO);

  @Mapping(target = "reasons", ignore = true)
  OutcomeDTO outcomeToOutcomeDto(Outcome outcome);

  @Mapping(target = "reasons", ignore = true)
  Outcome outcomeDtoToOutcome(OutcomeDTO outcomeDTO);
}
