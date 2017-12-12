package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.api.dto.OutcomeStatus;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A Outcome.
 */
@Entity
@Table(name = "Outcome")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Outcome implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
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
  private String nextRotationGradeId;

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

  @Version
  @Column(name = "amendedDate")
  private LocalDateTime amendedDate;

  @OneToOne
  @JoinColumn(name = "id")
  private Assessment assessment;

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

  public Boolean isExternalTrainer() {
    return externalTrainer;
  }

  public Outcome externalTrainer(Boolean externalTrainer) {
    this.externalTrainer = externalTrainer;
    return this;
  }

  public String getNextRotationGradeId() {
    return nextRotationGradeId;
  }

  public void setNextRotationGradeId(String nextRotationGradeId) {
    this.nextRotationGradeId = nextRotationGradeId;
  }

  public Outcome nextRotationGradeId(String nextRotationGradeId) {
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

  public void setDetailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
  }

  public Outcome detailedReasons(String detailedReasons) {
    this.detailedReasons = detailedReasons;
    return this;
  }

  public String getMitigatingCircumstances() {
    return mitigatingCircumstances;
  }

  public void setMitigatingCircumstances(String mitigatingCircumstances) {
    this.mitigatingCircumstances = mitigatingCircumstances;
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

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public Assessment getAssessment() {
    return assessment;
  }

  public void setAssessment(Assessment assessment) {
    this.assessment = assessment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Outcome outcome1 = (Outcome) o;

    if (id != null ? !id.equals(outcome1.id) : outcome1.id != null) return false;
    if (outcome != outcome1.outcome) return false;
    if (underAppeal != null ? !underAppeal.equals(outcome1.underAppeal) : outcome1.underAppeal != null) return false;
    if (reason != null ? !reason.equals(outcome1.reason) : outcome1.reason != null) return false;
    if (trainingCompletionDate != null ? !trainingCompletionDate.equals(outcome1.trainingCompletionDate) : outcome1.trainingCompletionDate != null)
      return false;
    if (extendedTrainingCompletionDate != null ? !extendedTrainingCompletionDate.equals(outcome1.extendedTrainingCompletionDate) : outcome1.extendedTrainingCompletionDate != null)
      return false;
    if (extendedTrainingTimeInMonths != null ? !extendedTrainingTimeInMonths.equals(outcome1.extendedTrainingTimeInMonths) : outcome1.extendedTrainingTimeInMonths != null)
      return false;
    if (tenPercentAudit != null ? !tenPercentAudit.equals(outcome1.tenPercentAudit) : outcome1.tenPercentAudit != null)
      return false;
    if (externalTrainer != null ? !externalTrainer.equals(outcome1.externalTrainer) : outcome1.externalTrainer != null)
      return false;
    if (nextRotationGradeId != null ? !nextRotationGradeId.equals(outcome1.nextRotationGradeId) : outcome1.nextRotationGradeId != null)
      return false;
    if (nextRotationGradeName != null ? !nextRotationGradeName.equals(outcome1.nextRotationGradeName) : outcome1.nextRotationGradeName != null)
      return false;
    if (traineeNotifiedOfOutcome != null ? !traineeNotifiedOfOutcome.equals(outcome1.traineeNotifiedOfOutcome) : outcome1.traineeNotifiedOfOutcome != null)
      return false;
    if (nextReviewDate != null ? !nextReviewDate.equals(outcome1.nextReviewDate) : outcome1.nextReviewDate != null)
      return false;
    if (comments != null ? !comments.equals(outcome1.comments) : outcome1.comments != null) return false;
    if (intrepidId != null ? !intrepidId.equals(outcome1.intrepidId) : outcome1.intrepidId != null) return false;
    if (academicCurriculumAssessed != null ? !academicCurriculumAssessed.equals(outcome1.academicCurriculumAssessed) : outcome1.academicCurriculumAssessed != null)
      return false;
    if (academicOutcome != null ? !academicOutcome.equals(outcome1.academicOutcome) : outcome1.academicOutcome != null)
      return false;
    if (detailedReasons != null ? !detailedReasons.equals(outcome1.detailedReasons) : outcome1.detailedReasons != null)
      return false;
    if (mitigatingCircumstances != null ? !mitigatingCircumstances.equals(outcome1.mitigatingCircumstances) : outcome1.mitigatingCircumstances != null)
      return false;
    if (competencesToBeDeveloped != null ? !competencesToBeDeveloped.equals(outcome1.competencesToBeDeveloped) : outcome1.competencesToBeDeveloped != null)
      return false;
    if (otherRecommendedActions != null ? !otherRecommendedActions.equals(outcome1.otherRecommendedActions) : outcome1.otherRecommendedActions != null)
      return false;
    if (recommendedAdditionalTrainingTime != null ? !recommendedAdditionalTrainingTime.equals(outcome1.recommendedAdditionalTrainingTime) : outcome1.recommendedAdditionalTrainingTime != null)
      return false;
    if (additionalCommentsFromPanel != null ? !additionalCommentsFromPanel.equals(outcome1.additionalCommentsFromPanel) : outcome1.additionalCommentsFromPanel != null)
      return false;
    if (amendedDate != null ? !amendedDate.equals(outcome1.amendedDate) : outcome1.amendedDate != null) return false;
    return assessment != null ? assessment.equals(outcome1.assessment) : outcome1.assessment == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
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
    result = 31 * result + (amendedDate != null ? amendedDate.hashCode() : 0);
    result = 31 * result + (assessment != null ? assessment.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Outcome{" +
        "id=" + id +
        ", outcome=" + outcome +
        ", underAppeal=" + underAppeal +
        ", reason='" + reason + '\'' +
        ", trainingCompletionDate=" + trainingCompletionDate +
        ", extendedTrainingCompletionDate=" + extendedTrainingCompletionDate +
        ", extendedTrainingTimeInMonths=" + extendedTrainingTimeInMonths +
        ", tenPercentAudit=" + tenPercentAudit +
        ", externalTrainer=" + externalTrainer +
        ", nextRotationGradeId='" + nextRotationGradeId + '\'' +
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
        ", assessment=" + assessment +
        '}';
  }
}
