package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.transformuk.hee.tis.assessment.api.dto.AssessmentDetailDTO;
import com.transformuk.hee.tis.assessment.service.model.AssessmentDetail;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity AssessmentDetail and its DTO AssessmentDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AssessmentDetailMapper extends EntityMapper<AssessmentDetailDTO, AssessmentDetail> {

  AssessmentDetailDTO toDto(AssessmentDetail assessmentDetail);

  AssessmentDetail toEntity(AssessmentDetailDTO assessmentDetailDTO);

  default AssessmentDetail fromId(Long id) {
    if (id == null) {
      return null;
    }
    AssessmentDetail assessmentDetail = new AssessmentDetail();
    assessmentDetail.setId(id);
    return assessmentDetail;
  }
}
