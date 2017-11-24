package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.RevalidationDTO;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import com.transformuk.hee.tis.assessment.service.model.Revalidation;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Revalidation and its DTO RevalidationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RevalidationMapper extends EntityMapper<RevalidationDTO, Revalidation> {

  RevalidationDTO toDto(Revalidation revalidation);

  Revalidation toEntity(RevalidationDTO revalidationDTO);

}
