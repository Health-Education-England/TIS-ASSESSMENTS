package com.transformuk.hee.tis.assessment.api.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

/**
 * A DTO for the Reason entity.
 */
@Data
public class ReasonDTO implements Serializable {

  private Long id;

  private UUID uuid;

  private String code;

  private String label;

  private Set<OutcomeDTO> outcomes;

  private boolean requireOther;

  private boolean isLegacy;
}
