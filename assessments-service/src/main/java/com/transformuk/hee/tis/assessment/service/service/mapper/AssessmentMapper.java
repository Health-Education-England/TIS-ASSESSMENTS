package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for the entity Assessment and its DTO AssessmentDTO.
 */
@Mapper(
    componentModel = "spring",
    uses = {AssessmentDetailMapper.class, AssessmentOutcomeMapper.class, RevalidationMapper.class
    })
public interface AssessmentMapper {

  AssessmentDTO toDto(Assessment assessment);

  List<AssessmentDTO> toDto(List<Assessment> assessment);

  Assessment toEntity(AssessmentDTO assessmentDTO);

  List<Assessment> toEntity(List<AssessmentDTO> assessmentDTO);


}
