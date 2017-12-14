package com.transformuk.hee.tis.assessment.service.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A Revalidation.
 */
@Entity
@Table(name = "Revalidation")
public class Revalidation implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  private Long id;

  private Boolean knownConcerns;

  private String concernSummary;

  private String responsibleOfficerComments;

  private String intrepidId;

  @OneToOne
  @JoinColumn(name = "id")
  private Assessment assessment;

  @Version
  private LocalDateTime amendedDate;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Revalidation id(Long id) {
    this.id = id;
    return this;
  }

  public Boolean getKnownConcerns() {
    return knownConcerns;
  }

  public void setKnownConcerns(Boolean knownConcerns) {
    this.knownConcerns = knownConcerns;
  }

  public Revalidation knownConcerns(Boolean knownConcerns) {
    this.knownConcerns = knownConcerns;
    return this;
  }

  public String getConcernSummary() {
    return concernSummary;
  }

  public void setConcernSummary(String concernSummary) {
    this.concernSummary = concernSummary;
  }

  public Revalidation concernSummary(String concernSummary) {
    this.concernSummary = concernSummary;
    return this;
  }

  public String getResponsibleOfficerComments() {
    return responsibleOfficerComments;
  }

  public void setResponsibleOfficerComments(String responsibleOfficerComments) {
    this.responsibleOfficerComments = responsibleOfficerComments;
  }

  public Revalidation responsibleOfficerComments(String responsibleOfficerComments) {
    this.responsibleOfficerComments = responsibleOfficerComments;
    return this;
  }

  public Assessment getAssessment() {
    return assessment;
  }

  public void setAssessment(Assessment assessment) {
    this.assessment = assessment;
  }

  public Revalidation assessment(Assessment assessment) {
    this.assessment = assessment;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public Revalidation intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public Revalidation amendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
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
    Revalidation assessment = (Revalidation) o;
    if (assessment.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), assessment.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Revalidation{" +
        "id=" + id +
        ", knownConcerns=" + knownConcerns +
        ", concernSummary='" + concernSummary + '\'' +
        ", responsibleOfficerComments='" + responsibleOfficerComments + '\'' +
        ", amendedDate='" + amendedDate + '\'' +
        '}';
  }
}
