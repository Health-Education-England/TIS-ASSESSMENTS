package com.transformuk.hee.tis.assessment.api.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the Outcome entity.
 */
public class AssessmentOutcomeDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new Outcome")
  @NotNull(groups = Update.class, message = "id must be provided when updating an Outcome")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "outcome cannot be null")
  private String outcome;

  @NotNull(groups = {Create.class, Update.class}, message = "outcome id cannot be null")
  private Long outcomeId;

  private Boolean underAppeal;

  private List<AssessmentOutcomeReasonDTO> reasons;

  @Deprecated
  private String reason;

  @Deprecated
  private Long reasonId;

  private String comments;

  private LocalDate trainingCompletionDate;

  private LocalDate extendedTrainingCompletionDate;

  private Integer extendedTrainingTimeInMonths;

  private Boolean tenPercentAudit;

  private Boolean externalTrainer;

  //TODO: uncomment this annotation when we make the switch to use IDs
//  @NotNull(groups = {Create.class, Update.class}, message = "nextRotationGradeId must not be null")
  private Long nextRotationGradeId;

  private String nextRotationGradeAbbr;

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

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @NotNull(groups = Update.class, message = "amendedDate cannot be null when updating")
  private LocalDateTime amendedDate;

  private Boolean legacy;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AssessmentOutcomeDTO id(Long id) {
    this.id = id;
    return this;
  }

  public String getOutcome() {
    return outcome;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  public AssessmentOutcomeDTO outcome(String outcome) {
    this.outcome = outcome;
    return this;
  }


  public Long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(Long outcomeId) {
    this.outcomeId = outcomeId;
  }

  public AssessmentOutcomeDTO outcomeId(Long outcomeId) {
    this.outcomeId = outcomeId;
    return this;
  }

  public List<AssessmentOutcomeReasonDTO> getReasons() {
    return reasons;
  }

  public void setReasons(List<AssessmentOutcomeReasonDTO> reasons) {
    this.reasons = reasons;
  }

  public Long getReasonId() {
    return reasonId;
  }

  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
  }

  public AssessmentOutcomeDTO reasonId(Long reasonId) {
    this.reasonId = reasonId;
    return this;
  }

  public Boolean isUnderAppeal() {
    return underAppeal;
  }

  public AssessmentOutcomeDTO underAppeal(Boolean underAppeal) {
    this.underAppeal = underAppeal;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public AssessmentOutcomeDTO reason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public AssessmentOutcomeDTO comments(String comments) {
    this.comments = comments;
    return this;
  }

  public LocalDate getTrainingCompletionDate() {
    return trainingCompletionDate;
  }

  public void setTrainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
  }

  public AssessmentOutcomeDTO trainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
    return this;
  }

  public LocalDate getExtendedTrainingCompletionDate() {
    return extendedTrainingCompletionDate;
  }

  public void setExtendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
  }

  public AssessmentOutcomeDTO extendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
    return this;
  }

  public Integer getExtendedTrainingTimeInMonths() {
    return extendedTrainingTimeInMonths;
  }

  public void setExtendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
  }

  public AssessmentOutcomeDTO extendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
    return this;
  }

  public Boolean isTenPercentAudit() {
    return tenPercentAudit;
  }

  public AssessmentOutcomeDTO tenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
    return this;
  }

  public Boolean isExternalTrainer() {
    return externalTrainer;
  }

  public AssessmentOutcomeDTO externalTrainer(Boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
    return this;
  }

  public Long getNextRotationGradeId() {
    return nextRotationGradeId;
  }

  public void setNextRotationGradeId(Long nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
  }

  public AssessmentOutcomeDTO nextRotationGradeId(Long nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
    return this;
  }

  public String getNextRotationGradeAbbr() {
    return nextRotationGradeAbbr;
  }

  public void setNextRotationGradeAbbr(String nextRotationGradeAbbr) {
    this.nextRotationGradeAbbr = nextRotationGradeAbbr;
  }

  public AssessmentOutcomeDTO nextRotationGradeAbbr(String nextRotationGradeAbbr) {
    this.nextRotationGradeAbbr = nextRotationGradeAbbr;
    return this;
  }

  public String getNextRotationGradeName() {
    return nextRotationGradeName;
  }

  public void setNextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
  }

  public AssessmentOutcomeDTO nextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
    return this;
  }

  public Boolean isTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public AssessmentOutcomeDTO traineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
    return this;
  }

  public LocalDate getNextReviewDate() {
    return nextReviewDate;
  }

  public void setNextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
  }

  public AssessmentOutcomeDTO nextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public AssessmentOutcomeDTO intrepidId(String intrepidId) {
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

  public AssessmentOutcomeDTO academicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
    return this;
  }

  public String getAcademicOutcome() {
    return academicOutcome;
  }

  public void setAcademicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
  }

  public AssessmentOutcomeDTO academicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
    return this;
  }

  public String getDetailedReasons() {
    return detailedReasons;
  }

  public void setDetailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
  }

  public AssessmentOutcomeDTO detailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
    return this;
  }

  public String getMitigatingCircumstances() {
    return mitigatingCircumstances;
  }

  public void setMitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
  }

  public AssessmentOutcomeDTO mitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
    return this;
  }

  public String getCompetencesToBeDeveloped() {
    return competencesToBeDeveloped;
  }

  public void setCompetencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
  }

  public AssessmentOutcomeDTO competencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
    return this;
  }

  public String getOtherRecommendedActions() {
    return otherRecommendedActions;
  }

  public void setOtherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
  }

  public AssessmentOutcomeDTO otherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
    return this;
  }

  public String getRecommendedAdditionalTrainingTime() {
    return recommendedAdditionalTrainingTime;
  }

  public void setRecommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
  }

  public AssessmentOutcomeDTO recommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
    return this;
  }

  public String getAdditionalCommentsFromPanel() {
    return additionalCommentsFromPanel;
  }

  public void setAdditionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
  }

  public AssessmentOutcomeDTO additionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public Boolean getLegacy() {
    return legacy;
  }

  public void setLegacy(Boolean legacy) {
    this.legacy = legacy;
  }

  public AssessmentOutcomeDTO legacy(Boolean legacy) {
    this.legacy = legacy;
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

    AssessmentOutcomeDTO outcomeDTO = (AssessmentOutcomeDTO) o;
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
    return "AssessmentOutcomeDTO{" +
        "id=" + id +
        ", outcome='" + outcome + '\'' +
        ", outcomeId=" + outcomeId +
        ", underAppeal=" + underAppeal +
        ", reason='" + reason + '\'' +
        ", reasonId=" + reasonId +
        ", comments='" + comments + '\'' +
        ", trainingCompletionDate=" + trainingCompletionDate +
        ", extendedTrainingCompletionDate=" + extendedTrainingCompletionDate +
        ", extendedTrainingTimeInMonths=" + extendedTrainingTimeInMonths +
        ", tenPercentAudit=" + tenPercentAudit +
        ", externalTrainer=" + externalTrainer +
        ", nextRotationGradeId=" + nextRotationGradeId +
        ", nextRotationGradeAbbr='" + nextRotationGradeAbbr + '\'' +
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
        ", legacy=" + legacy +
        '}';
  }
}
