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
import java.util.Objects;

/**
 * A Outcome.
 */
@Entity
@Table(name = "Outcome")
public class Outcome implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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


  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
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

  public Outcome outcome(OutcomeStatus outcome) {
    this.outcome = outcome;
    return this;
  }

  public Boolean isUnderAppeal() {
    return underAppeal;
  }

  public Outcome underAppeal(Boolean underAppeal) {
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

  public Outcome reason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Outcome comments(String comments) {
    this.comments = comments;
    return this;
  }

  public LocalDate getTrainingCompletionDate() {
    return trainingCompletionDate;
  }

  public void setTrainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
  }

  public Outcome trainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
    return this;
  }

  public LocalDate getExtendedTrainingCompletionDate() {
    return extendedTrainingCompletionDate;
  }

  public void setExtendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
  }

  public Outcome extendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
    return this;
  }

  public Integer getExtendedTrainingTimeInMonths() {
    return extendedTrainingTimeInMonths;
  }

  public void setExtendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
  }

  public Outcome extendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
    return this;
  }

  public Boolean isTenPercentAudit() {
    return tenPercentAudit;
  }

  public Outcome tenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
    return this;
  }

  public void setTenPercentAudit(Boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
  }

  public Boolean isExternalTrainer() {
    return externalTrainer;
  }

  public Outcome externalTrainer(Boolean externalTrainer) {
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

  public Outcome nextRotationGradeId(Long nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
    return this;
  }

  public String getNextRotationGradeName() {
    return nextRotationGradeName;
  }

  public void setNextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
  }

  public Outcome nextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
    return this;
  }

  public Boolean isTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public Outcome traineeNotifiedOfOutcome(Boolean traineeNotifiedOfOutcome) {
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

  public Outcome nextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public Outcome intrepidId(String intrepidId) {
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

  public Outcome academicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
    return this;
  }

  public String getAcademicOutcome() {
    return academicOutcome;
  }

  public void setAcademicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
  }

  public Outcome academicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
    return this;
  }

  public String getDetailedReasons() {
    return detailedReasons;
  }

  public Outcome detailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
    return this;
  }

  public String getMitigatingCircumstances() {
    return mitigatingCircumstances;
  }

  public Outcome mitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
    return this;
  }

  public String getCompetencesToBeDeveloped() {
    return competencesToBeDeveloped;
  }

  public void setCompetencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
  }

  public Outcome competencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
    return this;
  }

  public String getOtherRecommendedActions() {
    return otherRecommendedActions;
  }

  public void setOtherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
  }

  public Outcome otherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
    return this;
  }

  public String getRecommendedAdditionalTrainingTime() {
    return recommendedAdditionalTrainingTime;
  }

  public void setRecommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
  }

  public Outcome recommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
    return this;
  }

  public String getAdditionalCommentsFromPanel() {
    return additionalCommentsFromPanel;
  }

  public void setAdditionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
  }

  public Outcome additionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Outcome outcome = (Outcome) o;
    if (outcome.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), outcome.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Outcome{" +
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
