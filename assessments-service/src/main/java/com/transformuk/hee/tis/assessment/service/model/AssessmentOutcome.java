package com.transformuk.hee.tis.assessment.service.model;


import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * A AssessmentOutcome.
 */
@Entity
@Table(name = "AssessmentOutcome")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class AssessmentOutcome implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  //This superceeds the outcome id and reason fields on this entity
  @NotAudited
  @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "assessmentOutcome")
  private List<AssessmentOutcomeReason> reasons;

  @Column(name = "outcomeId")
  private Long outcomeId;

  @Column(name = "outcome")
  private String outcome;

  @Column(name = "reasonId")
  private Long reasonId;

  @Column(name = "reason")
  private String reason;

  @Column(name = "underAppeal")
  private boolean underAppeal;

  @Column(name = "trainingCompletionDate")
  private LocalDate trainingCompletionDate;

  @Column(name = "extendedTrainingCompletionDate")
  private LocalDate extendedTrainingCompletionDate;

  @Column(name = "extendedTrainingTimeInMonths")
  private Integer extendedTrainingTimeInMonths;

  @Column(name = "tenPercentAudit")
  private boolean tenPercentAudit;

  @Column(name = "externalTrainer")
  private boolean externalTrainer;

  @Column(name = "nextRotationGradeId")
  private String nextRotationGradeId;

  @Column(name = "nextRotationGradeAbbr")
  private String nextRotationGradeAbbr;

  @Column(name = "nextRotationGradeName")
  private String nextRotationGradeName;

  @Column(name = "traineeNotifiedOfOutcome")
  private boolean traineeNotifiedOfOutcome;

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

  @Version
  @Column(name = "amendedDate")
  private LocalDateTime amendedDate;

  @Column(name = "legacy")
  private Boolean legacy;

  @OneToOne
  @JoinColumn(name = "id")
  private Assessment assessment;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<AssessmentOutcomeReason> getReasons() {
    return reasons;
  }

  public void setReasons(List<AssessmentOutcomeReason> reasons) {
    this.reasons = reasons;
  }

  public String getOutcome() {
    return outcome;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  public AssessmentOutcome outcome(String outcome) {
    this.outcome = outcome;
    return this;
  }

  public boolean isUnderAppeal() {
    return underAppeal;
  }

  public AssessmentOutcome underAppeal(boolean underAppeal) {
    this.underAppeal = underAppeal;
    return this;
  }

  public Long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(Long outcomeId) {
    this.outcomeId = outcomeId;
  }

  public AssessmentOutcome outcomeId(Long outcomeId) {
    this.outcomeId = outcomeId;
    return this;
  }

  public Long getReasonId() {
    return reasonId;
  }

  public void setReasonId(Long reasonId) {
    this.reasonId = reasonId;
  }

  public AssessmentOutcome reasonId(Long reasonId) {
    this.reasonId = reasonId;
    return this;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public AssessmentOutcome reason(String reason) {
    this.reason = reason;
    return this;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public AssessmentOutcome comments(String comments) {
    this.comments = comments;
    return this;
  }

  public LocalDate getTrainingCompletionDate() {
    return trainingCompletionDate;
  }

  public void setTrainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
  }

  public AssessmentOutcome trainingCompletionDate(LocalDate trainingCompletionDate) {
    this.trainingCompletionDate = trainingCompletionDate;
    return this;
  }

  public LocalDate getExtendedTrainingCompletionDate() {
    return extendedTrainingCompletionDate;
  }

  public void setExtendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
  }

  public AssessmentOutcome extendedTrainingCompletionDate(LocalDate extendedTrainingCompletionDate) {
    this.extendedTrainingCompletionDate = extendedTrainingCompletionDate;
    return this;
  }

  public Integer getExtendedTrainingTimeInMonths() {
    return extendedTrainingTimeInMonths;
  }

  public void setExtendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
  }

  public AssessmentOutcome extendedTrainingTimeInMonths(Integer extendedTrainingTimeInMonths) {
    this.extendedTrainingTimeInMonths = extendedTrainingTimeInMonths;
    return this;
  }

  public boolean isTenPercentAudit() {
    return tenPercentAudit;
  }

  public AssessmentOutcome tenPercentAudit(boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
    return this;
  }

  public boolean isExternalTrainer() {
    return externalTrainer;
  }

  public AssessmentOutcome externalTrainer(boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
    return this;
  }

  public String getNextRotationGradeAbbr() {
    return nextRotationGradeAbbr;
  }

  public void setNextRotationGradeAbbr(String nextRotationGradeAbbr) {
    this.nextRotationGradeAbbr = nextRotationGradeAbbr;
  }

  public AssessmentOutcome nextRotationGradeAbbr(String nextRotationGradeAbbr) {
    this.nextRotationGradeAbbr = nextRotationGradeAbbr;
    return this;
  }

  public String getNextRotationGradeId() {
    return nextRotationGradeId;
  }

  public void setNextRotationGradeId(String nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
  }

  public AssessmentOutcome nextRotationGradeId(String nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
    return this;
  }

  public String getNextRotationGradeName() {
    return nextRotationGradeName;
  }

  public void setNextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
  }

  public AssessmentOutcome nextRotationGradeName(String nextRotationGradeName) {
    this.nextRotationGradeName = nextRotationGradeName;
    return this;
  }

  public boolean isTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public AssessmentOutcome traineeNotifiedOfOutcome(boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
    return this;
  }

  public LocalDate getNextReviewDate() {
    return nextReviewDate;
  }

  public void setNextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
  }

  public AssessmentOutcome nextReviewDate(LocalDate nextReviewDate) {
    this.nextReviewDate = nextReviewDate;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public AssessmentOutcome intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public boolean getUnderAppeal() {
    return underAppeal;
  }

  public void setUnderAppeal(boolean underAppeal) {
    this.underAppeal = underAppeal;
  }

  public boolean getTenPercentAudit() {
    return tenPercentAudit;
  }

  public void setTenPercentAudit(boolean tenPercentAudit) {
    this.tenPercentAudit = tenPercentAudit;
  }

  public boolean getExternalTrainer() {
    return externalTrainer;
  }

  public void setExternalTrainer(boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
  }

  public boolean getTraineeNotifiedOfOutcome() {
    return traineeNotifiedOfOutcome;
  }

  public void setTraineeNotifiedOfOutcome(boolean traineeNotifiedOfOutcome) {
    this.traineeNotifiedOfOutcome = traineeNotifiedOfOutcome;
  }

  public String getAcademicCurriculumAssessed() {
    return academicCurriculumAssessed;
  }

  public void setAcademicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
  }

  public AssessmentOutcome academicCurriculumAssessed(String academicCurriculumAssessed) {
    this.academicCurriculumAssessed = academicCurriculumAssessed;
    return this;
  }

  public String getAcademicOutcome() {
    return academicOutcome;
  }

  public void setAcademicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
  }

  public AssessmentOutcome academicOutcome(String academicOutcome) {
    this.academicOutcome = academicOutcome;
    return this;
  }

  public String getDetailedReasons() {
    return detailedReasons;
  }

  public void setDetailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
  }

  public AssessmentOutcome detailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
    return this;
  }

  public String getMitigatingCircumstances() {
    return mitigatingCircumstances;
  }

  public void setMitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
  }

  public AssessmentOutcome mitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
    return this;
  }

  public String getCompetencesToBeDeveloped() {
    return competencesToBeDeveloped;
  }

  public void setCompetencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
  }

  public AssessmentOutcome competencesToBeDeveloped(String competencesToBeDeveloped) {
    this.competencesToBeDeveloped = competencesToBeDeveloped;
    return this;
  }

  public String getOtherRecommendedActions() {
    return otherRecommendedActions;
  }

  public void setOtherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
  }

  public AssessmentOutcome otherRecommendedActions(String otherRecommendedActions) {
    this.otherRecommendedActions = otherRecommendedActions;
    return this;
  }

  public String getRecommendedAdditionalTrainingTime() {
    return recommendedAdditionalTrainingTime;
  }

  public void setRecommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
  }

  public AssessmentOutcome recommendedAdditionalTrainingTime(String recommendedAdditionalTrainingTime) {
    this.recommendedAdditionalTrainingTime = recommendedAdditionalTrainingTime;
    return this;
  }

  public String getAdditionalCommentsFromPanel() {
    return additionalCommentsFromPanel;
  }

  public void setAdditionalCommentsFromPanel(String additionalCommentsFromPanel) {
    this.additionalCommentsFromPanel = additionalCommentsFromPanel;
  }

  public AssessmentOutcome additionalCommentsFromPanel(String additionalCommentsFromPanel) {
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

  public AssessmentOutcome legacy(Boolean legacy) {
    this.legacy = legacy;
    return this;
  }

  public Assessment getAssessment() {
    return assessment;
  }

  public void setAssessment(Assessment assessment) {
    this.assessment = assessment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssessmentOutcome assessmentOutcome = (AssessmentOutcome) o;
    if (assessmentOutcome.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), assessmentOutcome.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }


  @Override
  public String toString() {
    return "AssessmentOutcome{" +
        "id=" + id +
        ", outcomeId=" + outcomeId +
        ", outcome='" + outcome + '\'' +
        ", underAppeal=" + underAppeal +
        ", reasonId=" + reasonId +
        ", reason='" + reason + '\'' +
        ", trainingCompletionDate=" + trainingCompletionDate +
        ", extendedTrainingCompletionDate=" + extendedTrainingCompletionDate +
        ", extendedTrainingTimeInMonths=" + extendedTrainingTimeInMonths +
        ", tenPercentAudit=" + tenPercentAudit +
        ", externalTrainer=" + externalTrainer +
        ", nextRotationGradeId='" + nextRotationGradeId + '\'' +
        ", nextRotationGradeAbbr='" + nextRotationGradeAbbr + '\'' +
        ", nextRotationGradeName='" + nextRotationGradeName + '\'' +
        ", traineeNotifiedOfOutcome=" + traineeNotifiedOfOutcome +
        ", nextReviewDate=" + nextReviewDate +
        ", comments='" + comments + '\'' +
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
        ", assessment=" + assessment +
        '}';
  }
}
