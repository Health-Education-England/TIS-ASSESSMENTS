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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReasonDTO implements Serializable {

  private Long id;

  private String code;

  private String label;

  private Set<OutcomeDTO> outcomes;

  private boolean requireOther;

  private boolean isLegacy;
}
