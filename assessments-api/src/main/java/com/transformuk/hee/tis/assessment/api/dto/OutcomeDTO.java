package com.transformuk.hee.tis.assessment.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the Outcome entity.
 */
@Data
public class OutcomeDTO implements Serializable {

  private Long id;

  private String code;

  private String label;

  private Set<ReasonDTO> reasons;
}
