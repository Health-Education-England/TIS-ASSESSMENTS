package com.transformuk.hee.tis.assessment.api.dto;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the Outcome entity.
 */
public class OutcomeDTO implements Serializable {

  private Long id;

  private OutcomeStatus outcome;

  private Boolean underAppeal;

  private OutcomeReason reason;

  private String comments;

  private ZonedDateTime trainingCompletionDate;

  private ZonedDateTime extendedTrainingCompletionDate;

  private Integer extendedTrainingTimeInMonths;

  private Boolean tenPercentAudit;

  private Boolean externalTrainer;

  private Long nextRotationGradeId;

  private String nextRotationGradeName;

  private Boolean traineeNotifiedOfOutcome;

  private ZonedDateTime nextReviewDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OutcomeStatus getOutcome() {
    return outcome;
  }

  public void setOutcome(OutcomeStatus outcome) {
    this.outcome = outcome;
  }

  public Boolean isUnderAppeal() {
    return underAppeal;
  }

  public void setUnderAppeal(Boolean underAppeal) {
    this.underAppeal = underAppeal;
  }

  public OutcomeReason getReason() {
    return reason;
  }

  public void setReason(OutcomeReason reason) {
    this.reason = reason;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public ZonedDateTime getTrainingCompletionDate() {
    return trainingCompletionDate;
  }

  public void setTrainingCompletionDate(ZonedDateTime trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
  }

  public ZonedDateTime getExtendedTrainingCompletionDate() {
    return extendedTrainingCompletionDate;
  }

  public void setExtendedTrainingCompletionDate(ZonedDateTime extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
  }

  public Integer getExtendedTrainingTimeInMonths() {
    return extendedTrainingTimeInMonths;
  }

  public void setExtendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
  }

  public Boolean isTenPercentAudit() {
    return tenPercentAudit;
  }

  public void setTenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
  }

  public Boolean isExternalTrainer() {
    return externalTrainer;
  }

  public void setExternalTrainer(Boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
  }

  public Long getNextRotationGradeId() {
    return nextRotationGradeId;
  }

  public void setNextRotationGradeId(Long nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
  }

  public String getNextRotationGradeName() {
    return nextRotationGradeName;
  }

  public void setNextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
  }

  public Boolean isTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public void setTraineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
  }

  public ZonedDateTime getNextReviewDate() {
    return nextReviewDate;
  }

  public void setNextReviewDate(ZonedDateTime nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    OutcomeDTO outcomeDTO = (OutcomeDTO) o;
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
    return "OutcomeDTO{" +
        "id=" + getId() +
        ", outcome='" + getOutcome() + "'" +
        ", underAppeal='" + isUnderAppeal() + "'" +
        ", reason='" + getReason() + "'" +
        ", comments='" + getComments() + "'" +
        ", trainingCompletionDate='" + getTrainingCompletionDate() + "'" +
        ", extendedTrainingCompletionDate='" + getExtendedTrainingCompletionDate() + "'" +
        ", extendedTrainingTimeInMonths='" + getExtendedTrainingTimeInMonths() + "'" +
        ", tenPercentAudit='" + isTenPercentAudit() + "'" +
        ", externalTrainer='" + isExternalTrainer() + "'" +
        ", nextRotationGradeId='" + getNextRotationGradeId() + "'" +
        ", nextRotationGradeName='" + getNextRotationGradeName() + "'" +
        ", traineeNotifiedOfOutcome='" + isTraineeNotifiedOfOutcome() + "'" +
        ", nextReviewDate='" + getNextReviewDate() + "'" +
        "}";
  }
}
