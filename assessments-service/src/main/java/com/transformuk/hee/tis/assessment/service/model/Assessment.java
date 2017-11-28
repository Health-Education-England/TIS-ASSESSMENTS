package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.api.dto.AssessmentType;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;

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
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

  @Column(name = "traineeId")
  private String traineeId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "startDate")
  private LocalDate startDate;

  @Column(name = "endDate")
  private LocalDate endDate;

  @Column(name = "programmeNumber")
  private String programmeNumber;

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

  @Version
  private LocalDateTime amendedDate;

  @OneToOne
  @JoinColumn(name = "detailId", unique = true)
  private AssessmentDetail detail;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "AssessmentOutcome",
      joinColumns = @JoinColumn(name = "assessmentId", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "outcomeId", referencedColumnName = "id")
  )
  private List<Outcome> outcome;

  @OneToOne
  @JoinColumn(name = "revalidationId", unique = true)
  private Revalidation revalidation;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTraineeId() {
    return traineeId;
  }

  public void setTraineeId(String traineeId) {
    this.traineeId = traineeId;
  }

  public Assessment traineeId(String trainee) {
    this.traineeId = trainee;
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

  public String getProgrammeNumber() {
    return programmeNumber;
  }

  public void setProgrammeNumber(String programmeNumber) {
    this.programmeNumber = programmeNumber;
  }

  public Assessment programmeNumber(String programmeNumber) {
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

  public Revalidation getRevalidation() {
    return revalidation;
  }

  public void setRevalidation(Revalidation revalidation) {
    this.revalidation = revalidation;
  }

  public Assessment revalidation(Revalidation revalidation) {
    this.revalidation = revalidation;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public Assessment amendedDate(LocalDateTime amendedDate) {
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
        ", traineeId='" + getTraineeId() + "'" +
        ", firstName='" + getFirstName() + "'" +
        ", lastName='" + getLastName() + "'" +
        ", startDate='" + getStartDate() + "'" +
        ", endDate='" + getEndDate() + "'" +
        ", programmeNumber='" + getProgrammeNumber() + "'" +
        ", programmeName='" + getProgrammeName() + "'" +
        ", status='" + getStatus() + "'" +
        ", type='" + getType() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", amendedDate='" + getAmendedDate() + "'" +
        "}";
  }
}
