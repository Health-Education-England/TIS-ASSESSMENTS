package com.transformuk.hee.tis.assessment.api.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

/**
 * A DTO for the Outcome entity.
 */
@Data
public class OutcomeDTO implements Serializable {

  private Long id;

  private UUID uuid;

  private String code;

  private String label;

  private Set<ReasonDTO> reasons;
}
