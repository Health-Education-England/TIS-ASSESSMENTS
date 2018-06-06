package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentOutcomeReasonDTO;
import com.transformuk.hee.tis.assessment.service.model.AssessmentOutcomeReason;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Mapper for the entity AssessmentOutcome and its DTO AssessmentOutcomeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AssessmentOutcomeReasonMapper extends EntityMapper<AssessmentOutcomeReasonDTO, AssessmentOutcomeReason> {

  @Mappings({
      @Mapping(source = "reason.id", target = "reasonId"),
      @Mapping(source = "reason.label", target = "reason"),
      @Mapping(source = "reason.requireOther", target = "requireOther")
  })
  AssessmentOutcomeReasonDTO toDto(AssessmentOutcomeReason assessmentOutcomeReason);

  @InheritInverseConfiguration
  AssessmentOutcomeReason toEntity(AssessmentOutcomeReasonDTO assessmentOutcomeReasonDTO);

  List<AssessmentOutcomeReasonDTO> toDto(List<AssessmentOutcomeReason> assessmentOutcomeReasons);

  List<AssessmentOutcomeReason> toEntity(List<AssessmentOutcomeReasonDTO> assessmentOutcomeReasonDTOs);


//  default AssessmentOutcome fromId(Long id) {
////    if (id == null) {
////      return null;
////    }
////    AssessmentOutcome assessmentOutcome = new AssessmentOutcome();
////    assessmentOutcome.setId(id);
////    return assessmentOutcome;
////  }
}
