package com.transformuk.hee.tis.assessment.api.dto;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import io.swagger.annotations.ApiModelProperty;

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
  @ApiModelProperty(value = "The id of this outcome, must match the id of the assessment, optional for POST, required for PUT")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "outcome cannot be null")
  @ApiModelProperty(required = true, value = "The outcome status of this assessment")
  private OutcomeStatus outcome;

  @ApiModelProperty(value = "Whether the outcome is currently under appeal")
  private Boolean underAppeal;

  @NotNull(groups = {Create.class, Update.class}, message = "reason must not be null")
  @ApiModelProperty(required = true, value = "The reasons behind this outcome")
  private String reason;

  @ApiModelProperty(value = "Additional comments relating to the outcome")
  private String comments;

  @ApiModelProperty(value = "Date when training was completed")
  private LocalDate trainingCompletionDate;

  @ApiModelProperty(value = "Date when additional training was completed")
  private LocalDate extendedTrainingCompletionDate;

  @ApiModelProperty(value = "Additional months in training")
  private Integer extendedTrainingTimeInMonths;

  @ApiModelProperty(value = "The id of this outcome, must match the id of the assessment")
  private Boolean tenPercentAudit;

  @ApiModelProperty(value = "true if external trainer")
  private Boolean externalTrainer;

  @NotNull(groups = {Create.class, Update.class}, message = "nextRotationGradeId must not be null")
  @ApiModelProperty(required = true, value = "The next rotation grade id")
  private String nextRotationGradeId;

  @NotNull(groups = {Create.class, Update.class}, message = "nextRotationGradeName must not be null")
  @ApiModelProperty(required = true, value = "The next rotation grade name")
  private String nextRotationGradeName;

  @ApiModelProperty(value = "True if the trainee has been notified of the outcome")
  private Boolean traineeNotifiedOfOutcome;

  @ApiModelProperty(value = "The date of the next review")
  private LocalDate nextReviewDate;

  @ApiModelProperty(value = "The original Id if this outcome was migrated from Intrepid")
  private String intrepidId;

  @ApiModelProperty(value = "Academic curriculum assessed")
  private String academicCurriculumAssessed;

  @ApiModelProperty(value = "academic outcome")
  private String academicOutcome;

  @ApiModelProperty(value = "Detailed reasons for this outcome")
  private String detailedReasons;

  @ApiModelProperty(value = "Any mitigating circumstances that have been taken into account")
  private String mitigatingCircumstances;

  @ApiModelProperty(value = "Any competencies that ned to be developed")
  private String competencesToBeDeveloped;

  @ApiModelProperty(value = "Any other recommended actions that need to take place")
  private String otherRecommendedActions;

  @ApiModelProperty(value = "The amount of additional training time")
  private String recommendedAdditionalTrainingTime;

  @ApiModelProperty(value = "Addtional comments from the panel")
  private String additionalCommentsFromPanel;

  @NotNull(groups = Update.class, message = "amendedDate cannot be null when updating")
  @ApiModelProperty(required = true, value = "version property, when updating the outcome, this much match the database value")
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
