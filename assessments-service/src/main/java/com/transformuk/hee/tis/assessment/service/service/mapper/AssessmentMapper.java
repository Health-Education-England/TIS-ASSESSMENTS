package com.transformuk.hee.tis.assessment.service.service.mapper;

import com.google.common.collect.Lists;
import com.transformuk.hee.tis.assessment.api.dto.AssessmentDTO;
import com.transformuk.hee.tis.assessment.api.dto.OutcomeDTO;
import com.transformuk.hee.tis.assessment.service.model.Assessment;
import com.transformuk.hee.tis.assessment.service.model.Outcome;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Mapper for the entity Assessment and its DTO AssessmentDTO.
 */
@Mapper(componentModel = "spring", uses = {AssessmentDetailMapper.class, RevalidationMapper.class})
public abstract class AssessmentMapper {

  @Autowired
  private OutcomeMapper outcomeMapper;

  public abstract AssessmentDTO toDto(Assessment assessment);

  public abstract List<AssessmentDTO> toDto(List<Assessment> assessment);

  public abstract Assessment toEntity(AssessmentDTO assessmentDTO);

  public abstract List<Assessment> toEntity(List<AssessmentDTO> assessmentDTO);

  public OutcomeDTO map(List<Outcome> outcome) {
    OutcomeDTO outcomeDTO = null;
    if (CollectionUtils.isNotEmpty(outcome)) {
      outcome.sort((o1, o2) -> o2.getId().compareTo(o1.getId()));
      Outcome latestOutcome = outcome.get(0);
      outcomeDTO = outcomeMapper.toDto(latestOutcome);
    }
    return outcomeDTO;
  }

  public List<Outcome> map(OutcomeDTO outcomeDTO) {
    Outcome outcome = outcomeMapper.toEntity(outcomeDTO);
    return Lists.newArrayList(outcome);
  }

}
