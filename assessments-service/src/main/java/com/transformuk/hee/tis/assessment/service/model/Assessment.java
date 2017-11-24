package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.api.dto.AssessmentType;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * A Assessment.
 */
@Entity
@Table(name = "Assessment")
public class Assessment implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "personId")
  private Long personId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "startDate")
  private LocalDate startDate;

  @Column(name = "endDate")
  private LocalDate endDate;

  @Column(name = "programmeNumber")
  private Long programmeNumber;

  @Column(name = "programmeName")
  private String programmeName;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EventStatus status;

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private AssessmentType type;

  @Column(name = "intrepidId")
  private String intrepidId;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "detailId", unique = true)
  private AssessmentDetail detail;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "AssessmentOutcome",
      joinColumns = @JoinColumn(name = "assessmentId", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "outcomeId", referencedColumnName = "id")
  )
  private List<Outcome> outcome;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  public Assessment personId(Long personId) {
    this.personId = personId;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Assessment firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Assessment lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public Assessment startDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Assessment endDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public Long getProgrammeNumber() {
    return programmeNumber;
  }

  public void setProgrammeNumber(Long programmeNumber) {
    this.programmeNumber = programmeNumber;
  }

  public Assessment programmeNumber(Long programmeNumber) {
    this.programmeNumber = programmeNumber;
    return this;
  }

  public String getProgrammeName() {
    return programmeName;
  }

  public void setProgrammeName(String programmeName) {
    this.programmeName = programmeName;
  }

  public Assessment programmeName(String programmeName) {
    this.programmeName = programmeName;
    return this;
  }

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }

  public Assessment status(EventStatus status) {
    this.status = status;
    return this;
  }

  public AssessmentType getType() {
    return type;
  }

  public void setType(AssessmentType type) {
    this.type = type;
  }

  public Assessment type(AssessmentType type) {
    this.type = type;
    return this;
  }


  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public Assessment intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public AssessmentDetail getDetail() {
    return detail;
  }

  public void setDetail(AssessmentDetail detail) {
    this.detail = detail;
  }

  public Assessment detail(AssessmentDetail detail) {
    this.detail = detail;
    return this;
  }

  public List<Outcome> getOutcome() {
    return outcome;
  }

  public void setOutcome(List<Outcome> outcome) {
    this.outcome = outcome;
  }

  public Assessment outcome(List<Outcome> outcome) {
    this.outcome = outcome;
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
    Assessment assessment = (Assessment) o;
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
    return "Assessment{" +
        "id=" + getId() +
        ", personId='" + getPersonId() + "'" +
        ", firstName='" + getFirstName() + "'" +
        ", lastName='" + getLastName() + "'" +
        ", startDate='" + getStartDate() + "'" +
        ", endDate='" + getEndDate() + "'" +
        ", programmeNumber='" + getProgrammeNumber() + "'" +
        ", programmeName='" + getProgrammeName() + "'" +
        ", status='" + getStatus() + "'" +
        ", type='" + getType() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        "}";
  }
}
