package com.transformuk.hee.tis.assessment.api.dto;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the Outcome entity.
 */
public class OutcomeDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new Outcome")
  @NotNull(groups = Update.class, message = "id must be provided when updating an Outcome")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "outcome cannot be null")
  private OutcomeStatus outcome;

  private Boolean underAppeal;

  @NotNull(groups = {Create.class, Update.class}, message = "reason must not be null")
  private String reason;

  private String comments;

  private LocalDate trainingCompletionDate;

  private LocalDate extendedTrainingCompletionDate;

  private Integer extendedTrainingTimeInMonths;

  private Boolean tenPercentAudit;

  private Boolean externalTrainer;

  @NotNull(groups = {Create.class, Update.class}, message = "nextRotationGradeId must not be null")
  private String nextRotationGradeId;

  @NotNull(groups = {Create.class, Update.class}, message = "nextRotationGradeName must not be null")
  private String nextRotationGradeName;

  private Boolean traineeNotifiedOfOutcome;

  private LocalDate nextReviewDate;

  private String intrepidId;

  private String academicCurriculumAssessed;

  private String academicOutcome;

  private String detailedReasons;

  private String mitigatingCircumstances;

  private String competencesToBeDeveloped;

  private String otherRecommendedActions;

  private String recommendedAdditionalTrainingTime;

  private String additionalCommentsFromPanel;

  @NotNull(groups = Update.class, message = "amendedDate cannot be null when updating")
  private LocalDateTime amendedDate;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OutcomeDTO id(Long id) {
    this.id = id;
    return this;
  }

  public OutcomeStatus getOutcome() {
    return outcome;
  }

  public void setOutcome(OutcomeStatus outcome) {
    this.outcome = outcome;
  }

  public OutcomeDTO outcome(OutcomeStatus outcome) {
    this.outcome = outcome;
    return this;
  }

  public Boolean isUnderAppeal() {
    return underAppeal;
  }

  public OutcomeDTO underAppeal(Boolean underAppeal) {
    this.underAppeal = underAppeal;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public OutcomeDTO reason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public OutcomeDTO comments(String comments) {
    this.comments = comments;
    return this;
  }

  public LocalDate getTrainingCompletionDate() {
    return trainingCompletionDate;
  }

  public void setTrainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
  }

  public OutcomeDTO trainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
    return this;
  }

  public LocalDate getExtendedTrainingCompletionDate() {
    return extendedTrainingCompletionDate;
  }

  public void setExtendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
  }

  public OutcomeDTO extendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
    return this;
  }

  public Integer getExtendedTrainingTimeInMonths() {
    return extendedTrainingTimeInMonths;
  }

  public void setExtendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
  }

  public OutcomeDTO extendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
    return this;
  }

  public Boolean isTenPercentAudit() {
    return tenPercentAudit;
  }

  public OutcomeDTO tenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
    return this;
  }

  public Boolean isExternalTrainer() {
    return externalTrainer;
  }

  public OutcomeDTO externalTrainer(Boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
    return this;
  }

  public String getNextRotationGradeId() {
    return nextRotationGradeId;
  }

  public void setNextRotationGradeId(String nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
  }

  public OutcomeDTO nextRotationGradeId(String nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
    return this;
  }

  public String getNextRotationGradeName() {
    return nextRotationGradeName;
  }

  public void setNextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
  }

  public OutcomeDTO nextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
    return this;
  }

  public Boolean isTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public OutcomeDTO traineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
    return this;
  }

  public LocalDate getNextReviewDate() {
    return nextReviewDate;
  }

  public void setNextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
  }

  public OutcomeDTO nextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public OutcomeDTO intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public Boolean getUnderAppeal() {
    return underAppeal;
  }

  public void setUnderAppeal(Boolean underAppeal) {
    this.underAppeal = underAppeal;
  }

  public Boolean getTenPercentAudit() {
    return tenPercentAudit;
  }

  public void setTenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
  }

  public Boolean getExternalTrainer() {
    return externalTrainer;
  }

  public void setExternalTrainer(Boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
  }

  public Boolean getTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public void setTraineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
  }

  public String getAcademicCurriculumAssessed() {
    return academicCurriculumAssessed;
  }

  public void setAcademicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
  }

  public OutcomeDTO academicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
    return this;
  }

  public String getAcademicOutcome() {
    return academicOutcome;
  }

  public void setAcademicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
  }

  public OutcomeDTO academicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
    return this;
  }

  public String getDetailedReasons() {
    return detailedReasons;
  }

  public void setDetailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
  }

  public OutcomeDTO detailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
    return this;
  }

  public String getMitigatingCircumstances() {
    return mitigatingCircumstances;
  }

  public void setMitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
  }

  public OutcomeDTO mitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
    return this;
  }

  public String getCompetencesToBeDeveloped() {
    return competencesToBeDeveloped;
  }

  public void setCompetencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
  }

  public OutcomeDTO competencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
    return this;
  }

  public String getOtherRecommendedActions() {
    return otherRecommendedActions;
  }

  public void setOtherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
  }

  public OutcomeDTO otherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
    return this;
  }

  public String getRecommendedAdditionalTrainingTime() {
    return recommendedAdditionalTrainingTime;
  }

  public void setRecommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
  }

  public OutcomeDTO recommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
    return this;
  }

  public String getAdditionalCommentsFromPanel() {
    return additionalCommentsFromPanel;
  }

  public void setAdditionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
  }

  public OutcomeDTO additionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OutcomeDTO that = (OutcomeDTO) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (outcome != that.outcome) return false;
    if (underAppeal != null ? !underAppeal.equals(that.underAppeal) : that.underAppeal != null) return false;
    if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
    if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
    if (trainingCompletionDate != null ? !trainingCompletionDate.equals(that.trainingCompletionDate) : that.trainingCompletionDate != null)
      return false;
    if (extendedTrainingCompletionDate != null ? !extendedTrainingCompletionDate.equals(that.extendedTrainingCompletionDate) : that.extendedTrainingCompletionDate != null)
      return false;
    if (extendedTrainingTimeInMonths != null ? !extendedTrainingTimeInMonths.equals(that.extendedTrainingTimeInMonths) : that.extendedTrainingTimeInMonths != null)
      return false;
    if (tenPercentAudit != null ? !tenPercentAudit.equals(that.tenPercentAudit) : that.tenPercentAudit != null)
      return false;
    if (externalTrainer != null ? !externalTrainer.equals(that.externalTrainer) : that.externalTrainer != null)
      return false;
    if (nextRotationGradeId != null ? !nextRotationGradeId.equals(that.nextRotationGradeId) : that.nextRotationGradeId != null)
      return false;
    if (nextRotationGradeName != null ? !nextRotationGradeName.equals(that.nextRotationGradeName) : that.nextRotationGradeName != null)
      return false;
    if (traineeNotifiedOfOutcome != null ? !traineeNotifiedOfOutcome.equals(that.traineeNotifiedOfOutcome) : that.traineeNotifiedOfOutcome != null)
      return false;
    if (nextReviewDate != null ? !nextReviewDate.equals(that.nextReviewDate) : that.nextReviewDate != null)
      return false;
    if (intrepidId != null ? !intrepidId.equals(that.intrepidId) : that.intrepidId != null) return false;
    if (academicCurriculumAssessed != null ? !academicCurriculumAssessed.equals(that.academicCurriculumAssessed) : that.academicCurriculumAssessed != null)
      return false;
    if (academicOutcome != null ? !academicOutcome.equals(that.academicOutcome) : that.academicOutcome != null)
      return false;
    if (detailedReasons != null ? !detailedReasons.equals(that.detailedReasons) : that.detailedReasons != null)
      return false;
    if (mitigatingCircumstances != null ? !mitigatingCircumstances.equals(that.mitigatingCircumstances) : that.mitigatingCircumstances != null)
      return false;
    if (competencesToBeDeveloped != null ? !competencesToBeDeveloped.equals(that.competencesToBeDeveloped) : that.competencesToBeDeveloped != null)
      return false;
    if (otherRecommendedActions != null ? !otherRecommendedActions.equals(that.otherRecommendedActions) : that.otherRecommendedActions != null)
      return false;
    if (recommendedAdditionalTrainingTime != null ? !recommendedAdditionalTrainingTime.equals(that.recommendedAdditionalTrainingTime) : that.recommendedAdditionalTrainingTime != null)
      return false;
    if (additionalCommentsFromPanel != null ? !additionalCommentsFromPanel.equals(that.additionalCommentsFromPanel) : that.additionalCommentsFromPanel != null)
      return false;
    return amendedDate != null ? amendedDate.equals(that.amendedDate) : that.amendedDate == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
    result = 31 * result + (underAppeal != null ? underAppeal.hashCode() : 0);
    result = 31 * result + (reason != null ? reason.hashCode() : 0);
    result = 31 * result + (comments != null ? comments.hashCode() : 0);
    result = 31 * result + (trainingCompletionDate != null ? trainingCompletionDate.hashCode() : 0);
    result = 31 * result + (extendedTrainingCompletionDate != null ? extendedTrainingCompletionDate.hashCode() : 0);
    result = 31 * result + (extendedTrainingTimeInMonths != null ? extendedTrainingTimeInMonths.hashCode() : 0);
    result = 31 * result + (tenPercentAudit != null ? tenPercentAudit.hashCode() : 0);
    result = 31 * result + (externalTrainer != null ? externalTrainer.hashCode() : 0);
    result = 31 * result + (nextRotationGradeId != null ? nextRotationGradeId.hashCode() : 0);
    result = 31 * result + (nextRotationGradeName != null ? nextRotationGradeName.hashCode() : 0);
    result = 31 * result + (traineeNotifiedOfOutcome != null ? traineeNotifiedOfOutcome.hashCode() : 0);
    result = 31 * result + (nextReviewDate != null ? nextReviewDate.hashCode() : 0);
    result = 31 * result + (intrepidId != null ? intrepidId.hashCode() : 0);
    result = 31 * result + (academicCurriculumAssessed != null ? academicCurriculumAssessed.hashCode() : 0);
    result = 31 * result + (academicOutcome != null ? academicOutcome.hashCode() : 0);
    result = 31 * result + (detailedReasons != null ? detailedReasons.hashCode() : 0);
    result = 31 * result + (mitigatingCircumstances != null ? mitigatingCircumstances.hashCode() : 0);
    result = 31 * result + (competencesToBeDeveloped != null ? competencesToBeDeveloped.hashCode() : 0);
    result = 31 * result + (otherRecommendedActions != null ? otherRecommendedActions.hashCode() : 0);
    result = 31 * result + (recommendedAdditionalTrainingTime != null ? recommendedAdditionalTrainingTime.hashCode() : 0);
    result = 31 * result + (additionalCommentsFromPanel != null ? additionalCommentsFromPanel.hashCode() : 0);
    result = 31 * result + (amendedDate != null ? amendedDate.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "OutcomeDTO{" +
        "id=" + id +
        ", outcome=" + outcome +
        ", underAppeal=" + underAppeal +
        ", reason='" + reason + '\'' +
        ", comments='" + comments + '\'' +
        ", trainingCompletionDate=" + trainingCompletionDate +
        ", extendedTrainingCompletionDate=" + extendedTrainingCompletionDate +
        ", extendedTrainingTimeInMonths=" + extendedTrainingTimeInMonths +
        ", tenPercentAudit=" + tenPercentAudit +
        ", externalTrainer=" + externalTrainer +
        ", nextRotationGradeId='" + nextRotationGradeId + '\'' +
        ", nextRotationGradeName='" + nextRotationGradeName + '\'' +
        ", traineeNotifiedOfOutcome=" + traineeNotifiedOfOutcome +
        ", nextReviewDate=" + nextReviewDate +
        ", intrepidId='" + intrepidId + '\'' +
        ", academicCurriculumAssessed='" + academicCurriculumAssessed + '\'' +
        ", academicOutcome='" + academicOutcome + '\'' +
        ", detailedReasons='" + detailedReasons + '\'' +
        ", mitigatingCircumstances='" + mitigatingCircumstances + '\'' +
        ", competencesToBeDeveloped='" + competencesToBeDeveloped + '\'' +
        ", otherRecommendedActions='" + otherRecommendedActions + '\'' +
        ", recommendedAdditionalTrainingTime='" + recommendedAdditionalTrainingTime + '\'' +
        ", additionalCommentsFromPanel='" + additionalCommentsFromPanel + '\'' +
        ", amendedDate=" + amendedDate +
        '}';
  }
}
