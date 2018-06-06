package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.service.model.reference.Reason;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * A AssessmentOutcomeReason.
 */
@Entity
@Table(name = "AssessmentOutcomeReason")
public class AssessmentOutcomeReason implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "assessmentOutcomeId", nullable = false)
  private AssessmentOutcome assessmentOutcome;

  @ManyToOne
  @JoinColumn(name = "reasonId", nullable = false)
  private Reason reason;

  @Column(name = "other")
  private String other;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AssessmentOutcome getAssessmentOutcome() {
    return assessmentOutcome;
  }

  public void setAssessmentOutcome(AssessmentOutcome assessmentOutcome) {
    this.assessmentOutcome = assessmentOutcome;
  }

  public Reason getReason() {
    return reason;
  }

  public void setReason(Reason reason) {
    this.reason = reason;
  }

  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssessmentOutcomeReason assessmentOutcome = (AssessmentOutcomeReason) o;
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
    return "AssessmentOutcomeReason{" +
        "id=" + id +
        ", assessmentOutcome=" + assessmentOutcome +
        ", reason=" + reason +
        ", other='" + other + '\'' +
        '}';
  }
}
