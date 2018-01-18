package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.api.dto.EventStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
  private Long traineeId;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "reviewDate")
  private LocalDate reviewDate;

  @Column(name = "programmeNumber")
  private String programmeNumber;

  @Column(name = "programmeName")
  private String programmeName;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private EventStatus eventStatus;

  @Column(name = "type")
  private String type;

  @Column(name = "intrepidId")
  private String intrepidId;

  @Version
  private LocalDateTime amendedDate;

  @OneToOne(mappedBy = "assessment")
  private AssessmentDetail detail;

  @OneToOne(mappedBy = "assessment")
  private AssessmentOutcome outcome;

  @OneToOne(mappedBy = "assessment")
  private Revalidation revalidation;

  // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Assessment id(Long id) {
    this.id = id;
    return this;
  }

  public Long getTraineeId() {
    return traineeId;
  }

  public void setTraineeId(Long traineeId) {
    this.traineeId = traineeId;
  }

  public Assessment traineeId(Long trainee) {
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

  public LocalDate getReviewDate() {
    return reviewDate;
  }

  public void setReviewDate(LocalDate reviewDate) {
    this.reviewDate = reviewDate;
  }

  public Assessment reviewDate(LocalDate reviewDate) {
    this.reviewDate = reviewDate;
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

  public EventStatus getEventStatus() {
    return eventStatus;
  }

  public void setEventStatus(EventStatus eventStatus) {
    this.eventStatus = eventStatus;
  }

  public Assessment status(EventStatus status) {
    this.eventStatus = status;
    return this;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Assessment type(String type) {
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

  public AssessmentOutcome getOutcome() {
    return outcome;
  }

  public void setOutcome(AssessmentOutcome outcome) {
    this.outcome = outcome;
  }

  public Assessment outcome(AssessmentOutcome assessmentOutcome) {
    this.outcome = assessmentOutcome;
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
        ", reviewDate='" + getReviewDate() + "'" +
        ", programmeNumber='" + getProgrammeNumber() + "'" +
        ", programmeName='" + getProgrammeName() + "'" +
        ", eventStatus='" + getEventStatus() + "'" +
        ", type='" + getType() + "'" +
        ", intrepidId='" + getIntrepidId() + "'" +
        ", amendedDate='" + getAmendedDate() + "'" +
        "}";
  }
}
