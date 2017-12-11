package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * An Outcome History.
 *
 * All previous version of an outcome are stored here
 */
@Entity
@Table(name = "OutcomeHistory")
public class OutcomeHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "outcomeId")
  private Long originalOutcomeId;

  @Enumerated(EnumType.STRING)
  @Column(name = "outcome")
  private OutcomeStatus outcome;

  @Column(name = "underAppeal")
  private Boolean underAppeal;

  @Column(name = "reason")
  private String reason;

  @Column(name = "trainingCompletionDate")
  private LocalDate trainingCompletionDate;

  @Column(name = "extendedTrainingCompletionDate")
  private LocalDate extendedTrainingCompletionDate;

  @Column(name = "extendedTrainingTimeInMonths")
  private Integer extendedTrainingTimeInMonths;

  @Column(name = "tenPercentAudit")
  private Boolean tenPercentAudit;

  @Column(name = "externalTrainer")
  private Boolean externalTrainer;

  @Column(name = "nextRotationGradeId")
  private Long nextRotationGradeId;

  @Column(name = "nextRotationGradeName")
  private String nextRotationGradeName;

  @Column(name = "traineeNotifiedOfOutcome")
  private Boolean traineeNotifiedOfOutcome;

  @Column(name = "nextReviewDate")
  private LocalDate nextReviewDate;

  @Column(name = "comments")
  private String comments;

  @Column(name = "intrepidId")
  private String intrepidId;

  //Supplementary detail
  @Column(name = "academicCurriculumAssessed")
  private String academicCurriculumAssessed;

  @Column(name = "academicOutcome")
  private String academicOutcome;

  @Column(name = "detailedReasons")
  private String detailedReasons;

  @Column(name = "mitigatingCircumstances")
  private String mitigatingCircumstances;

  @Column(name = "competencesToBeDeveloped")
  private String competencesToBeDeveloped;

  @Column(name = "otherRecommendedActions")
  private String otherRecommendedActions;

  @Column(name = "recommendedAddtnlTrainingTime")
  private String recommendedAdditionalTrainingTime;

  @Column(name = "addCommentsFromPanel")
  private String additionalCommentsFromPanel;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOriginalOutcomeId() {
    return originalOutcomeId;
  }

  public void setOriginalOutcomeId(Long originalOutcomeId) {
    this.originalOutcomeId = originalOutcomeId;
  }

  public void setDetailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
  }

  public void setMitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
  }

  public OutcomeStatus getOutcome() {
    return outcome;
  }

  public void setOutcome(OutcomeStatus outcome) {
    this.outcome = outcome;
  }

  public OutcomeHistory outcome(OutcomeStatus outcome) {
    this.outcome = outcome;
    return this;
  }

  public Boolean isUnderAppeal() {
    return underAppeal;
  }

  public OutcomeHistory underAppeal(Boolean underAppeal) {
    this.underAppeal = underAppeal;
    return this;
  }

  public void setUnderAppeal(Boolean underAppeal) {
    this.underAppeal = underAppeal;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public OutcomeHistory reason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public OutcomeHistory comments(String comments) {
    this.comments = comments;
    return this;
  }

  public LocalDate getTrainingCompletionDate() {
    return trainingCompletionDate;
  }

  public void setTrainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
  }

  public OutcomeHistory trainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
    return this;
  }

  public LocalDate getExtendedTrainingCompletionDate() {
    return extendedTrainingCompletionDate;
  }

  public void setExtendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
  }

  public OutcomeHistory extendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
    return this;
  }

  public Integer getExtendedTrainingTimeInMonths() {
    return extendedTrainingTimeInMonths;
  }

  public void setExtendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
  }

  public OutcomeHistory extendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
    return this;
  }

  public Boolean isTenPercentAudit() {
    return tenPercentAudit;
  }

  public OutcomeHistory tenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
    return this;
  }

  public void setTenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
  }

  public Boolean isExternalTrainer() {
    return externalTrainer;
  }

  public OutcomeHistory externalTrainer(Boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
    return this;
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

  public OutcomeHistory nextRotationGradeId(Long nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
    return this;
  }

  public String getNextRotationGradeName() {
    return nextRotationGradeName;
  }

  public void setNextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
  }

  public OutcomeHistory nextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
    return this;
  }

  public Boolean isTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public OutcomeHistory traineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
    return this;
  }

  public void setTraineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
  }

  public LocalDate getNextReviewDate() {
    return nextReviewDate;
  }

  public void setNextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
  }

  public OutcomeHistory nextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public OutcomeHistory intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public Boolean getUnderAppeal() {
    return underAppeal;
  }

  public Boolean getTenPercentAudit() {
    return tenPercentAudit;
  }

  public Boolean getExternalTrainer() {
    return externalTrainer;
  }

  public Boolean getTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public String getAcademicCurriculumAssessed() {
    return academicCurriculumAssessed;
  }

  public void setAcademicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
  }

  public OutcomeHistory academicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
    return this;
  }

  public String getAcademicOutcome() {
    return academicOutcome;
  }

  public void setAcademicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
  }

  public OutcomeHistory academicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
    return this;
  }

  public String getDetailedReasons() {
    return detailedReasons;
  }

  public OutcomeHistory detailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
    return this;
  }

  public String getMitigatingCircumstances() {
    return mitigatingCircumstances;
  }

  public OutcomeHistory mitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
    return this;
  }

  public String getCompetencesToBeDeveloped() {
    return competencesToBeDeveloped;
  }

  public void setCompetencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
  }

  public OutcomeHistory competencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
    return this;
  }

  public String getOtherRecommendedActions() {
    return otherRecommendedActions;
  }

  public void setOtherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
  }

  public OutcomeHistory otherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
    return this;
  }

  public String getRecommendedAdditionalTrainingTime() {
    return recommendedAdditionalTrainingTime;
  }

  public void setRecommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
  }

  public OutcomeHistory recommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
    return this;
  }

  public String getAdditionalCommentsFromPanel() {
    return additionalCommentsFromPanel;
  }

  public void setAdditionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
  }

  public OutcomeHistory additionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    OutcomeHistory that = (OutcomeHistory) o;

    if (originalOutcomeId != null ? !originalOutcomeId.equals(that.originalOutcomeId) : that.originalOutcomeId != null)
      return false;
    if (outcome != that.outcome) return false;
    if (underAppeal != null ? !underAppeal.equals(that.underAppeal) : that.underAppeal != null) return false;
    if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
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
    if (comments != null ? !comments.equals(that.comments) : that.comments != null) return false;
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
    return additionalCommentsFromPanel != null ? additionalCommentsFromPanel.equals(that.additionalCommentsFromPanel) : that.additionalCommentsFromPanel == null;
  }

  @Override
  public int hashCode() {
    int result = originalOutcomeId != null ? originalOutcomeId.hashCode() : 0;
    result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
    result = 31 * result + (underAppeal != null ? underAppeal.hashCode() : 0);
    result = 31 * result + (reason != null ? reason.hashCode() : 0);
    result = 31 * result + (trainingCompletionDate != null ? trainingCompletionDate.hashCode() : 0);
    result = 31 * result + (extendedTrainingCompletionDate != null ? extendedTrainingCompletionDate.hashCode() : 0);
    result = 31 * result + (extendedTrainingTimeInMonths != null ? extendedTrainingTimeInMonths.hashCode() : 0);
    result = 31 * result + (tenPercentAudit != null ? tenPercentAudit.hashCode() : 0);
    result = 31 * result + (externalTrainer != null ? externalTrainer.hashCode() : 0);
    result = 31 * result + (nextRotationGradeId != null ? nextRotationGradeId.hashCode() : 0);
    result = 31 * result + (nextRotationGradeName != null ? nextRotationGradeName.hashCode() : 0);
    result = 31 * result + (traineeNotifiedOfOutcome != null ? traineeNotifiedOfOutcome.hashCode() : 0);
    result = 31 * result + (nextReviewDate != null ? nextReviewDate.hashCode() : 0);
    result = 31 * result + (comments != null ? comments.hashCode() : 0);
    result = 31 * result + (intrepidId != null ? intrepidId.hashCode() : 0);
    result = 31 * result + (academicCurriculumAssessed != null ? academicCurriculumAssessed.hashCode() : 0);
    result = 31 * result + (academicOutcome != null ? academicOutcome.hashCode() : 0);
    result = 31 * result + (detailedReasons != null ? detailedReasons.hashCode() : 0);
    result = 31 * result + (mitigatingCircumstances != null ? mitigatingCircumstances.hashCode() : 0);
    result = 31 * result + (competencesToBeDeveloped != null ? competencesToBeDeveloped.hashCode() : 0);
    result = 31 * result + (otherRecommendedActions != null ? otherRecommendedActions.hashCode() : 0);
    result = 31 * result + (recommendedAdditionalTrainingTime != null ? recommendedAdditionalTrainingTime.hashCode() : 0);
    result = 31 * result + (additionalCommentsFromPanel != null ? additionalCommentsFromPanel.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Outcome{" +
        "outcome='" + getOutcome() + "'" +
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
        ", intrepidId='" + getIntrepidId() + "'" +
        ", academicCurriculumAssessed='" + getAcademicCurriculumAssessed() + "'" +
        ", academicOutcome='" + getAcademicOutcome() + "'" +
        ", detailedReasons='" + getDetailedReasons() + "'" +
        ", mitigatingCircumstances='" + getMitigatingCircumstances() + "'" +
        ", competencesToBeDeveloped='" + getCompetencesToBeDeveloped() + "'" +
        ", otherRecommendedActions='" + getOtherRecommendedActions() + "'" +
        ", recommendedAdditionalTrainingTime='" + getRecommendedAdditionalTrainingTime() + "'" +
        ", additionalCommentsFromPanel='" + getAdditionalCommentsFromPanel() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        "}";
  }
}
