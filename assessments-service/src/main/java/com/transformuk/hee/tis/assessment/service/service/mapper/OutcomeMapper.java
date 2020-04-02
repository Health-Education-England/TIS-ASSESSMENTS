package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.ReasonDTO;
import com.transformuk.hee.tis.assessment.service.model.reference.Outcome;
import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Outcome and its DTO OutcomeDTO.
 */
@Mapper(componentModel = "spring")
public interface OutcomeMapper {

  OutcomeDTO toDto(Outcome outcome);

  Outcome toEntity(OutcomeDTO outcomeDto);

  @Mapping(target = "outcomes", ignore = true)
  ReasonDTO reasonToReasonDto(Reason reason);

  @Mapping(target = "outcomes", ignore = true)
  Reason reasonDtoToReason(ReasonDTO reasonDto);
}
