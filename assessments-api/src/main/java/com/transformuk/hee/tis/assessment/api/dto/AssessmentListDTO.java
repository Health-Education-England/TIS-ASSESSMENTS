package com.transformuk.hee.tis.assessment.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AssessmentListDTO {

  private Long id;

  private AssessmentType assessmentType;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate reviewDate;

  private String traineeId;

  private String firstName;

  private String lastName;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate periodCoveredFrom;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate periodCoveredTo;

  private String curriculumName;

  private EventStatus status;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AssessmentType getAssessmentType() {
    return assessmentType;
  }

  public void setAssessmentType(AssessmentType assessmentType) {
    this.assessmentType = assessmentType;
  }

  public LocalDate getReviewDate() {
    return reviewDate;
  }

  public void setReviewDate(LocalDate reviewDate) {
    this.reviewDate = reviewDate;
  }

  public String getTraineeId() {
    return traineeId;
  }

  public void setTraineeId(String traineeId) {
    this.traineeId = traineeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getPeriodCoveredFrom() {
    return periodCoveredFrom;
  }

  public void setPeriodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
  }

  public LocalDate getPeriodCoveredTo() {
    return periodCoveredTo;
  }

  public void setPeriodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
  }

  public String getCurriculumName() {
    return curriculumName;
  }

  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AssessmentListDTO that = (AssessmentListDTO) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (assessmentType != that.assessmentType) return false;
    if (reviewDate != null ? !reviewDate.equals(that.reviewDate) : that.reviewDate != null) return false;
    if (traineeId != null ? !traineeId.equals(that.traineeId) : that.traineeId != null) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (periodCoveredFrom != null ? !periodCoveredFrom.equals(that.periodCoveredFrom) : that.periodCoveredFrom != null)
      return false;
    if (periodCoveredTo != null ? !periodCoveredTo.equals(that.periodCoveredTo) : that.periodCoveredTo != null)
      return false;
    if (curriculumName != null ? !curriculumName.equals(that.curriculumName) : that.curriculumName != null)
      return false;
    return status == that.status;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (assessmentType != null ? assessmentType.hashCode() : 0);
    result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
    result = 31 * result + (traineeId != null ? traineeId.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (periodCoveredFrom != null ? periodCoveredFrom.hashCode() : 0);
    result = 31 * result + (periodCoveredTo != null ? periodCoveredTo.hashCode() : 0);
    result = 31 * result + (curriculumName != null ? curriculumName.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AssessmentListDTO{" +
        "id=" + id +
        ", assessmentType=" + assessmentType +
        ", reviewDate=" + reviewDate +
        ", traineeId='" + traineeId + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", periodCoveredFrom=" + periodCoveredFrom +
        ", periodCoveredTo=" + periodCoveredTo +
        ", curriculumName='" + curriculumName + '\'' +
        ", status=" + status +
        '}';
  }
}
