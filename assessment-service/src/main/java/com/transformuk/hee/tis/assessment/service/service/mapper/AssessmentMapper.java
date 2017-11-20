package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity Assessment and its DTO AssessmentDTO.
 */
@Mapper(componentModel = "spring", uses = {OutcomeMapper.class})
public interface AssessmentMapper extends EntityMapper<AssessmentDTO, Assessment> {

  AssessmentDTO toDto(Assessment assessment);

  Assessment toEntity(AssessmentDTO assessmentDTO);

  default Assessment fromId(Long id) {
    if (id == null) {
      return null;
    }
    Assessment assessment = new Assessment();
    assessment.setId(id);
    return assessment;
  }
}
