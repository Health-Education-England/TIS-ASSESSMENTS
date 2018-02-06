package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentListDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
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
      @Mapping(source = "detail.periodCoveredFrom", target = "periodCoveredFrom"),
      @Mapping(source = "detail.periodCoveredTo", target = "periodCoveredTo"),
      @Mapping(source = "detail.curriculumName", target = "curriculumName"),
      @Mapping(source = "outcome.outcome", target = "outcome")
  })
  AssessmentListDTO toDto(Assessment entity);

  List<Assessment> toEntity(List<AssessmentListDTO> dtoList);

  List<AssessmentListDTO> toDto(List<Assessment> entityList);

}
