package com.transformuk.hee.tis.assessment.api.dto;

import java.time.LocalDate;

public class AssessmentListDTO {

  private Long id;

  private String type;

  private LocalDate reviewDate;

  private Long traineeId;

  private String firstName;

  private String lastName;

  private String gmcNumber;
  
  private String gdcNumber;
  
  private String publicHealthNumber;

  private LocalDate periodCoveredFrom;

  private LocalDate periodCoveredTo;

  private String curriculumName;

  private EventStatus eventStatus;

  private String outcome;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public LocalDate getReviewDate() {
    return reviewDate;
  }

  public void setReviewDate(LocalDate reviewDate) {
    this.reviewDate = reviewDate;
  }

  public Long getTraineeId() {
    return traineeId;
  }

  public void setTraineeId(Long traineeId) {
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

  public String getGmcNumber() {
      return gmcNumber;
  }

  public void setGmcNumber(String gmcNumber) {
      this.gmcNumber = gmcNumber;
  }

  public String getGdcNumber() {
    return gdcNumber;
  }

  public void setGdcNumber(String gdcNumber) {
    this.gdcNumber = gdcNumber;
  }

  public String getPublicHealthNumber() {
    return publicHealthNumber;
  }

  public void setPublicHealthNumber(String publicHealthNumber) {
    this.publicHealthNumber = publicHealthNumber;
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

  public EventStatus getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(EventStatus eventStatus) {
    this.eventStatus = eventStatus;
  }

  public String getOutcome() {
    return outcome;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AssessmentListDTO that = (AssessmentListDTO) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (type != null ? !type.equals(that.type) : that.type != null)
      return false;
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
    if (eventStatus != that.eventStatus) return false;
    return outcome != null ? outcome.equals(that.outcome) : that.outcome == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (type != null ? type.hashCode() : 0);
    result = 31 * result + (reviewDate != null ? reviewDate.hashCode() : 0);
    result = 31 * result + (traineeId != null ? traineeId.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (periodCoveredFrom != null ? periodCoveredFrom.hashCode() : 0);
    result = 31 * result + (periodCoveredTo != null ? periodCoveredTo.hashCode() : 0);
    result = 31 * result + (curriculumName != null ? curriculumName.hashCode() : 0);
    result = 31 * result + (eventStatus != null ? eventStatus.hashCode() : 0);
    result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "AssessmentListDTO{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", reviewDate=" + reviewDate +
        ", traineeId='" + traineeId + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", gmcNumber='" + gmcNumber + '\'' +
        ", periodCoveredFrom=" + periodCoveredFrom +
        ", periodCoveredTo=" + periodCoveredTo +
        ", curriculumName='" + curriculumName + '\'' +
        ", eventStatus=" + eventStatus +
        ", outcome='" + outcome + '\'' +
        '}';
  }
}
