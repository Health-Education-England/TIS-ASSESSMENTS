package com.transformuk.hee.tis.assessment.api.dto;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the Revalidation entity.
 */
public class RevalidationDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new assessment revalidation")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  @ApiModelProperty(value = "The id of this revalidation linked to the assessment, must match the id of the assessment." +
      " Required during PUT and must be null during POST")
  private Long id;

  @ApiModelProperty(value = "True of there are any known concerns linked to this trainee")
  private Boolean knownConcerns;

  @ApiModelProperty(value = "Notes on any concerns in summary format")
  private String concernSummary;

  @ApiModelProperty(value = "Any comments made by the responsible officer")
  private String responsibleOfficerComments;

  @ApiModelProperty(value = "Versioning property to allow for optimistic locking, must match the DB value for updates")
  private LocalDateTime amendedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RevalidationDTO idd(Long id) {
    this.id = id;
    return this;
  }

  public Boolean getKnownConcerns() {
    return knownConcerns;
  }

  public void setKnownConcerns(Boolean knownConcerns) {
    this.knownConcerns = knownConcerns;
  }

  public RevalidationDTO knownConcerns(Boolean knownConcerns) {
    this.knownConcerns = knownConcerns;
    return this;
  }

  public String getConcernSummary() {
    return concernSummary;
  }

  public void setConcernSummary(String concernSummary) {
    this.concernSummary = concernSummary;
  }

  public RevalidationDTO concernSummary(String concernSummary) {
    this.concernSummary = concernSummary;
    return this;
  }

  public String getResponsibleOfficerComments() {
    return responsibleOfficerComments;
  }

  public void setResponsibleOfficerComments(String responsibleOfficerComments) {
    this.responsibleOfficerComments = responsibleOfficerComments;
  }

  public RevalidationDTO responsibleOfficerComments(String responsibleOfficerComments) {
    this.responsibleOfficerComments = responsibleOfficerComments;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public RevalidationDTO amendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
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

    RevalidationDTO assessmentDTO = (RevalidationDTO) o;
    if (assessmentDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), assessmentDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "RevalidationDTO{" +
        "id=" + id +
        ", knownConcerns=" + knownConcerns +
        ", concernSummary='" + concernSummary + '\'' +
        ", responsibleOfficerComments='" + responsibleOfficerComments + '\'' +
        ", amendedDate='" + amendedDate + '\'' +
        '}';
  }
}
