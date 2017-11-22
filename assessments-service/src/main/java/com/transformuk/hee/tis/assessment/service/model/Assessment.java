package com.transformuk.hee.tis.assessment.service.model;


import com.transformuk.hee.tis.assessment.api.dto.AssessmentType;
import com.transformuk.hee.tis.assessment.api.dto.EventStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
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

  @Column(name = "curriculumId")
  private Long curriculumId;

  @Column(name = "curriculumName")
  private String curriculumName;

  @Column(name = "curriculumStartDate")
  private LocalDate curriculumStartDate;

  @Column(name = "curriculumEndDate")
  private LocalDate curriculumEndDate;

  @Column(name = "curriculumSpecialtyId")
  private Long curriculumSpecialtyId;

  @Column(name = "curriculumSpecialty")
  private String curriculumSpecialty;

  @Column(name = "curriculumSubType")
  private String curriculumSubType;

  @Column(name = "membershipType")
  private String membershipType;

  @Column(name = "gradeAbbreviation")
  private String gradeAbbreviation;

  @Column(name = "gradeName")
  private String gradeName;

  @Column(name = "periodCoveredFrom")
  private LocalDate periodCoveredFrom;

  @Column(name = "periodCoveredTo")
  private LocalDate periodCoveredTo;

  @Column(name = "portfolioReviewDate")
  private LocalDate portfolioReviewDate;

  @Column(name = "monthsWteDuringPeriod")
  private Integer monthsWTEDuringPeriod;

  @Column(name = "monthsCountedToTraining")
  private Integer monthsCountedToTraining;

  @Column(name = "traineeNtn")
  private String traineeNTN;

  @Column(name = "pya")
  private String pya;

  @Column(name = "intrepidId")
  private String intrepidId;

  @OneToOne
  @JoinColumn(name = "outcomeId", unique = true)
  private Outcome outcome;

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

  public Long getCurriculumId() {
    return curriculumId;
  }

  public void setCurriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
  }

  public Assessment curriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
    return this;
  }

  public String getCurriculumName() {
    return curriculumName;
  }

  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }

  public Assessment curriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
    return this;
  }

  public LocalDate getCurriculumStartDate() {
    return curriculumStartDate;
  }

  public void setCurriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
  }

  public Assessment curriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
    return this;
  }

  public LocalDate getCurriculumEndDate() {
    return curriculumEndDate;
  }

  public void setCurriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
  }

  public Assessment curriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
    return this;
  }

  public Long getCurriculumSpecialtyId() {
    return curriculumSpecialtyId;
  }

  public void setCurriculumSpecialtyId(Long curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
  }

  public Assessment curriculumSpecialtyId(Long curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
    return this;
  }

  public String getCurriculumSpecialty() {
    return curriculumSpecialty;
  }

  public void setCurriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
  }

  public Assessment curriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
    return this;
  }

  public String getCurriculumSubType() {
    return curriculumSubType;
  }

  public void setCurriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
  }

  public Assessment curriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
    return this;
  }

  public String getMembershipType() {
    return membershipType;
  }

  public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
  }

  public Assessment membershipType(String membershipType) {
    this.membershipType = membershipType;
    return this;
  }

  public String getGradeAbbreviation() {
    return gradeAbbreviation;
  }

  public void setGradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
  }

  public Assessment gradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
    return this;
  }

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public Assessment gradeName(String gradeName) {
    this.gradeName = gradeName;
    return this;
  }

  public LocalDate getPeriodCoveredFrom() {
    return periodCoveredFrom;
  }

  public void setPeriodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
  }

  public Assessment periodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
    return this;
  }

  public LocalDate getPeriodCoveredTo() {
    return periodCoveredTo;
  }

  public void setPeriodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
  }

  public Assessment periodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
    return this;
  }

  public LocalDate getPortfolioReviewDate() {
    return portfolioReviewDate;
  }

  public void setPortfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
  }

  public Assessment portfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
    return this;
  }

  public Integer getMonthsWTEDuringPeriod() {
    return monthsWTEDuringPeriod;
  }

  public void setMonthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
  }

  public Assessment monthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
    return this;
  }

  public Integer getMonthsCountedToTraining() {
    return monthsCountedToTraining;
  }

  public void setMonthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
  }

  public Assessment monthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
    return this;
  }

  public String getTraineeNTN() {
    return traineeNTN;
  }

  public void setTraineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
  }

  public Assessment traineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
    return this;
  }

  public String getPya() {
    return pya;
  }

  public void setPya(String pya) {
    this.pya = pya;
  }

  public Assessment pya(String pya) {
    this.pya = pya;
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

  public Outcome getOutcome() {
    return outcome;
  }

  public void setOutcome(Outcome outcome) {
    this.outcome = outcome;
  }

  public Assessment outcome(Outcome outcome) {
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
        ", curriculumId='" + getCurriculumId() + "'" +
        ", curriculumName='" + getCurriculumName() + "'" +
        ", curriculumStartDate='" + getCurriculumStartDate() + "'" +
        ", curriculumEndDate='" + getCurriculumEndDate() + "'" +
        ", curriculumSpecialtyId='" + getCurriculumSpecialtyId() + "'" +
        ", curriculumSpecialty='" + getCurriculumSpecialty() + "'" +
        ", curriculumSubType='" + getCurriculumSubType() + "'" +
        ", membershipType='" + getMembershipType() + "'" +
        ", gradeAbbreviation='" + getGradeAbbreviation() + "'" +
        ", gradeName='" + getGradeName() + "'" +
        ", periodCoveredFrom='" + getPeriodCoveredFrom() + "'" +
        ", periodCoveredTo='" + getPeriodCoveredTo() + "'" +
        ", portfolioReviewDate='" + getPortfolioReviewDate() + "'" +
        ", monthsWTEDuringPeriod='" + getMonthsWTEDuringPeriod() + "'" +
        ", monthsCountedToTraining='" + getMonthsCountedToTraining() + "'" +
        ", traineeNTN='" + getTraineeNTN() + "'" +
        ", pya='" + getPya() + "'" +
        "}";
  }
}
