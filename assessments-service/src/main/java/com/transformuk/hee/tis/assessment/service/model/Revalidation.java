package com.transformuk.hee.tis.assessment.service.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Revalidation.
 */
@Entity
@Table(name = "Revalidation")
public class Revalidation implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Boolean knownConcerns;

  private String concernSummary;

  private String responsibleOfficerComments;

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
        '}';
  }
}
