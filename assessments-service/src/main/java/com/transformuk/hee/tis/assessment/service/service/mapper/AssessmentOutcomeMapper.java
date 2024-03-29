package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeDTO;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeReasonDTO;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcome;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity AssessmentOutcome and its DTO AssessmentOutcomeDTO.
 */
@Mapper(componentModel = "spring", uses = {AssessmentOutcomeReasonMapper.class})
public interface AssessmentOutcomeMapper extends EntityMapper<AssessmentOutcomeDTO, AssessmentOutcome> {

  AssessmentOutcomeDTO toDto(AssessmentOutcome assessmentOutcome);

  AssessmentOutcome toEntity(AssessmentOutcomeDTO assessmentOutcomeDTO);

  default AssessmentOutcome fromId(Long id) {
    if (id == null) {
      return null;
    }
    AssessmentOutcome assessmentOutcome = new AssessmentOutcome();
    assessmentOutcome.setId(id);
    return assessmentOutcome;
  }
}
