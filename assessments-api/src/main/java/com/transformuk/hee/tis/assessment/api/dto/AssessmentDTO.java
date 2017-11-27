package com.transformuk.hee.tis.assessment.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the Assessment entity.
 */
public class AssessmentDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new assessment")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "personId cannot be null")
  private Long personId;

  @NotNull(groups = {Create.class, Update.class}, message = "first name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "first name should be at least 1 character long")
  private String firstName;

  @NotNull(groups = {Create.class, Update.class}, message = "last name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "last name should be at least 1 character long")
  private String lastName;

  @NotNull(groups = {Create.class, Update.class}, message = "start date cannot be null")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate startDate;

  @NotNull(groups = {Create.class, Update.class}, message = "end date cannot be null")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate endDate;

  @NotNull(groups = {Create.class, Update.class}, message = "programme number cannot be null")
  private Long programmeNumber;

  @NotNull(groups = {Create.class, Update.class}, message = "programme name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "programme name should be at least 1 character long")
  private String programmeName;

  private EventStatus status;

  @NotNull(groups = {Create.class, Update.class}, message = "type cannot be null")
  private AssessmentType type;

  private String intrepidId;

  private AssessmentDetailDTO detail;

  private OutcomeDTO outcome;

  private RevalidationDTO revalidation;

  private LocalDateTime amendedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AssessmentDTO id(Long id) {
    this.id = id;
    return this;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public AssessmentDTO personId(Long personId) {
    this.personId = personId;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public AssessmentDTO firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public AssessmentDTO lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public AssessmentDTO startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public AssessmentDTO endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public Long getProgrammeNumber() {
    return programmeNumber;
  }

  public void setProgrammeNumber(Long programmeNumber) {
    this.programmeNumber = programmeNumber;
  }

  public AssessmentDTO programmeNumber(Long programmeNumber) {
    this.programmeNumber = programmeNumber;
    return this;
  }

  public String getProgrammeName() {
    return programmeName;
  }

  public void setProgrammeName(String programmeName) {
    this.programmeName = programmeName;
  }

  public AssessmentDTO programmeName(String programmeName) {
    this.programmeName = programmeName;
    return this;
  }

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }

  public AssessmentDTO status(EventStatus status) {
    this.status = status;
    return this;
  }

  public AssessmentType getType() {
    return type;
  }

  public void setType(AssessmentType type) {
    this.type = type;
  }

  public AssessmentDTO type(AssessmentType type) {
    this.type = type;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public AssessmentDTO intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public AssessmentDetailDTO getDetail() {
    return detail;
  }

  public void setDetail(AssessmentDetailDTO detail) {
    this.detail = detail;
  }

  public AssessmentDTO detail(AssessmentDetailDTO detail) {
    this.detail = detail;
    return this;
  }

  public OutcomeDTO getOutcome() {
    return outcome;
  }

  public void setOutcome(OutcomeDTO outcome) {
    this.outcome = outcome;
  }

  public AssessmentDTO outcome(OutcomeDTO outcome) {
    this.outcome = outcome;
    return this;
  }

  public RevalidationDTO getRevalidation() {
    return revalidation;
  }

  public void setRevalidation(RevalidationDTO revalidation) {
    this.revalidation = revalidation;
  }

  public AssessmentDTO revalidation(RevalidationDTO revalidation) {
    this.revalidation = revalidation;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public AssessmentDTO amendDate(LocalDateTime amendDate) {
    this.amendedDate = amendDate;
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

    AssessmentDTO assessmentDTO = (AssessmentDTO) o;
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
    return "AssessmentDTO{" +
        "id=" + getId() +
        ", personId='" + getPersonId() + "'" +
        ", firstName='" + getFirstName() + "'" +
        ", lastName='" + getLastName() + "'" +
        ", startDate='" + getStartDate() + "'" +
        ", endDate='" + getEndDate() + "'" +
        ", programmeNumber='" + getProgrammeNumber() + "'" +
        ", programmeName='" + getProgrammeName() + "'" +
        ", status='" + getStatus() + "'" +
        ", type='" + getType() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", detail='" + getDetail() + "'" +
        ", outcome='" + getOutcome() + "'" +
        ", revalidation='" + getRevalidation() + "'" +
        ", amendedDate='" + getAmendedDate() + "'" +
        "}";
  }
}
