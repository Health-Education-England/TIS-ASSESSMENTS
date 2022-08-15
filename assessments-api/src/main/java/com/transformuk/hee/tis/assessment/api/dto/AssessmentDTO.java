package com.transformuk.hee.tis.assessment.api.dto;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
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
  @ApiModelProperty(value = "The id of the assessment. Must be null for POST and required for PUT")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "traineeId cannot be null")
  @ApiModelProperty(required = true, value = "The id of the trainee")
  private Long traineeId;

  @NotNull(groups = {Create.class, Update.class}, message = "first name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "first name should be at least 1 character long")
  @ApiModelProperty(required = true, value = "The first name of the trainee")
  private String firstName;

  @NotNull(groups = {Create.class, Update.class}, message = "last name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "last name should be at least 1 character long")
  @ApiModelProperty(required = true, value = "The last name of the trainee")
  private String lastName;

  private String gmcNumber;

  private String gdcNumber;

  private String publicHealthNumber;

  @ApiModelProperty(required = true, value = "The review date of the assessment event")
  private LocalDate reviewDate;

  @NotNull(groups = {Create.class, Update.class}, message = "programme number cannot be null")
  @ApiModelProperty(required = true, value = "The programme number of this trainee's assessment")
  private String programmeNumber;

  private Long programmeId;

  @NotNull(groups = {Create.class, Update.class}, message = "programme name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "programme name should be at least 1 character long")
  @ApiModelProperty(required = true, value = "The programme name for this trainee's assessment")
  private String programmeName;

  @ApiModelProperty(required = true, value = "The curriculum membership id")
  private Long curriculumMembershipId;

  @ApiModelProperty(value = "The current eventStatus of the assessment event")
  private EventStatus eventStatus;

  @NotNull(groups = {Create.class, Update.class}, message = "type cannot be null")
  @ApiModelProperty(value = "The type of assessment")
  private String type;

  @ApiModelProperty(value = "The original id from Intrepid if it was migrated from Intrepid")
  private String intrepidId;

  @ApiModelProperty(value = "Additional details related to this assessment")
  private AssessmentDetailDTO detail;

  @ApiModelProperty(value = "The outcome of this assessment")
  private AssessmentOutcomeDTO outcome;

  @ApiModelProperty(value = "Revalidation information for this assessment")
  private RevalidationDTO revalidation;

  @ApiModelProperty(value = "Version number used for optimistic locking")
  private LocalDateTime amendedDate;

  public List<String> getMessageList() {
    return messageList;
  }

  private List<String> messageList = new ArrayList<>();

  public void addMessage(String message) {
    messageList.add(message);
  }

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

  public Long getTraineeId() {
    return traineeId;
  }

  public void setTraineeId(Long traineeId) {
    this.traineeId = traineeId;
  }

  public AssessmentDTO personId(Long personId) {
    this.traineeId = personId;
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

  public String getGmcNumber() {
    return gmcNumber;
  }

  public void setGmcNumber(String gmcNumber) {
    this.gmcNumber = gmcNumber;
  }

  public AssessmentDTO gmcNumber(String gmcNumber) {
    this.gmcNumber = gmcNumber;
    return this;
  }

  public String getGdcNumber() {
    return gdcNumber;
  }

  public void setGdcNumber(String gdcNumber) {
    this.gdcNumber = gdcNumber;
  }

  public AssessmentDTO gdcNumber(String gdcNumber) {
    this.gdcNumber = gdcNumber;
    return this;
  }

  public String getPublicHealthNumber() {
    return publicHealthNumber;
  }

  public void setPublicHealthNumber(String publicHealthNumber) {
    this.publicHealthNumber = publicHealthNumber;
  }

  public AssessmentDTO publicHealthNumber(String publicHealthNumber) {
    this.publicHealthNumber = publicHealthNumber;
    return this;
  }

  public LocalDate getReviewDate() {
    return reviewDate;
  }

  public void setReviewDate(LocalDate reviewDate) {
    this.reviewDate = reviewDate;
  }

  public AssessmentDTO startDate(LocalDate startDate) {
    this.reviewDate = startDate;
    return this;
  }

  public String getProgrammeNumber() {
    return programmeNumber;
  }

  public void setProgrammeNumber(String programmeNumber) {
    this.programmeNumber = programmeNumber;
  }

  public AssessmentDTO programmeNumber(String programmeNumber) {
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

  public Long getCurriculumMembershipId() {
    return curriculumMembershipId;
  }

  public void setCurriculumMembershipId(Long curriculumMembershipId) {
    this.curriculumMembershipId = curriculumMembershipId;
  }

  public AssessmentDTO curriculumMembershipId(Long curriculumMembershipId) {
    this.curriculumMembershipId = curriculumMembershipId;
    return this;
  }

  public EventStatus getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(EventStatus eventStatus) {
    this.eventStatus = eventStatus;
  }

  public AssessmentDTO eventStatus(EventStatus eventStatus) {
    this.eventStatus = eventStatus;
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AssessmentDTO type(String type) {
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

  public Long getProgrammeId() {
    return programmeId;
  }

  public void setProgrammeId(Long programmeId) {
    this.programmeId = programmeId;
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

  public AssessmentOutcomeDTO getOutcome() {
    return outcome;
  }

  public void setOutcome(AssessmentOutcomeDTO outcome) {
    this.outcome = outcome;
  }

  public AssessmentDTO outcome(AssessmentOutcomeDTO outcome) {
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
        ", traineeId='" + getTraineeId() + "'" +
        ", firstName='" + getFirstName() + "'" +
        ", lastName='" + getLastName() + "'" +
        ", gmcNumber='" + getGmcNumber() + "'" +
        ", gdcNumber='" + getGdcNumber() + "'" +
        ", publicHealthNumber='" + getPublicHealthNumber() + "'" +
        ", reviewDate='" + getReviewDate() + "'" +
        ", curriculumMembershipId='" + getCurriculumMembershipId() + "'" +
        ", programmeNumber='" + getProgrammeNumber() + "'" +
        ", programmeName='" + getProgrammeName() + "'" +
        ", programmeId='" + getProgrammeId() + "'" +
        ", eventStatus='" + getEventStatus() + "'" +
        ", type='" + getType() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", detail='" + getDetail() + "'" +
        ", outcome='" + getOutcome() + "'" +
        ", revalidation='" + getRevalidation() + "'" +
        ", amendedDate='" + getAmendedDate() + "'" +
        "}";
  }
}
