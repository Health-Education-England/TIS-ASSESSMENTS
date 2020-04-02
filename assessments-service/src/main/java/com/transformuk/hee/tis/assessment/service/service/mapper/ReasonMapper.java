package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.ReasonDTO;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Reason and its DTO ReasonDTO.
 */
@Mapper(componentModel = "spring")
public interface ReasonMapper {

  ReasonDTO toDto(Reason reason);

  Reason toEntity(ReasonDTO reasonDto);

  @Mapping(target = "reasons", ignore = true)
  OutcomeDTO outcomeToOutcomeDto(Outcome outcome);

  @Mapping(target = "reasons", ignore = true)
  Outcome outcomeDtoToOutcome(OutcomeDTO outcomeDto);
}
