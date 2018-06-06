package com.transformuk.hee.tis.assessment.api.dto;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the reasons against an outcome.
 */
public class AssessmentOutcomeReasonDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new AssessmentOutcomeReason")
  @NotNull(groups = Update.class, message = "id must be provided when updating an AssessmentOutcomeReason")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  @ApiModelProperty(value = "The id of this AssessmentOutcomeReason, must be null for POST, required for PUT")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "reason id must be provided when updating/creating an AssessmentOutcomeReason")
  @ApiModelProperty(value = "The reason id of this AssessmentOutcomeReason")
  private Long reasonId;

  @ApiModelProperty(value = "The reason related to the linked reason id")
  private String reason;

  @NotNull(groups = {Create.class, Update.class}, message = "other cannot be null")
  @ApiModelProperty(value = "Additional information stored in an 'other' field")
  private String other;

  @ApiModelProperty(value = "indicator stating if the 'other' field is required, helps to enable/disable show/hide the other input field for the FE")
  private boolean requireOther;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getReasonId() {
    return reasonId;
  }

  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }


  public boolean isRequireOther() {
    return requireOther;
  }

  public void setRequireOther(boolean requireOther) {
    this.requireOther = requireOther;
  }

  public AssessmentOutcomeReasonDTO id(Long id) {
    this.id = id;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AssessmentOutcomeReasonDTO outcomeDTO = (AssessmentOutcomeReasonDTO) o;
    if (outcomeDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), outcomeDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "AssessmentOutcomeReasonDTO{" +
        "id=" + id +
        ", reasonId=" + reasonId +
        ", reason='" + reason + '\'' +
        ", other='" + other + '\'' +
        ", requireOther=" + requireOther +
        '}';
  }
}
