package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AssessmentListMapper extends EntityMapper<AssessmentListDTO, Assessment> {

  @InheritInverseConfiguration
  Assessment toEntity(AssessmentListDTO dto);

  @Mappings({
      @Mapping(source = "type", target = "assessmentType"),
      @Mapping(source = "detail.periodCoveredFrom", target = "periodCoveredFrom"),
      @Mapping(source = "detail.periodCoveredTo", target = "periodCoveredTo"),
      @Mapping(source = "detail.curriculumName", target = "curriculumName")
  })
  AssessmentListDTO toDto(Assessment entity);

  List<Assessment> toEntity(List<AssessmentListDTO> dtoList);

  List<AssessmentListDTO> toDto(List<Assessment> entityList);

  default String map(List<Outcome> outcome) {
    if (CollectionUtils.isNotEmpty(outcome)) {
      outcome.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
      Outcome latestOutcome = outcome.get(0);
      if (latestOutcome.getOutcome() != null) {
        return latestOutcome.getOutcome().getLabel();
      }
    }
    return null;
  }

  default List<Outcome> map(String outcome) {
    return Lists.newArrayList(new Outcome().outcome(OutcomeStatus.valueOf(outcome)));
  }
}
