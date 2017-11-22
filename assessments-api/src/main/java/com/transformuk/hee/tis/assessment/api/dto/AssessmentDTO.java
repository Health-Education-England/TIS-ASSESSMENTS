package com.transformuk.hee.tis.assessment.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Assessment entity.
 */
public class AssessmentDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new assessment")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "personId cannot be null")
  private Long personId;

  @NotNull(groups = {Create.class, Update.class}, message = "first name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "first name should be at least 1 character long")
  private String firstName;

  @NotNull(groups = {Create.class, Update.class}, message = "last name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "last name should be at least 1 character long")
  private String lastName;

  @NotNull(groups = {Create.class, Update.class}, message = "start date cannot be null")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate startDate;

  @NotNull(groups = {Create.class, Update.class}, message = "end date cannot be null")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate endDate;

  @NotNull(groups = {Create.class, Update.class}, message = "programme number cannot be null")
  private Long programmeNumber;

  @NotNull(groups = {Create.class, Update.class}, message = "programme name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "programme name should be at least 1 character long")
  private String programmeName;

  private EventStatus status;

  @NotNull(groups = {Create.class, Update.class}, message = "type cannot be null")
  private AssessmentType type;

  @NotNull(groups = {Create.class, Update.class}, message = "curriculumId cannot be null")
  private Long curriculumId;

  @NotNull(groups = {Create.class, Update.class}, message = "curriculum name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "curriculum name should be at least 1 character long")
  private String curriculumName;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate curriculumStartDate;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate curriculumEndDate;

  private Long curriculumSpecialtyId;

  private String curriculumSpecialty;

  private String curriculumSubType;

  private String membershipType;

  @NotNull(groups = {Create.class, Update.class}, message = "gradeAbbreviation cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "gradeAbbreviation should be at least 1 character long")
  private String gradeAbbreviation;

  @NotNull(groups = {Create.class, Update.class}, message = "grade name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "grade name should be at least 1 character long")
  private String gradeName;

  @NotNull(groups = {Create.class, Update.class}, message = "period covered from cannot be null")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate periodCoveredFrom;

  @NotNull(groups = {Create.class, Update.class}, message = "period covered to cannot be null")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate periodCoveredTo;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate portfolioReviewDate;

  private Integer monthsWTEDuringPeriod;

  private Integer monthsCountedToTraining;

  private String traineeNTN;

  private String pya;

  private String intrepidId;

  private OutcomeDTO outcome;

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public Long getProgrammeNumber() {
    return programmeNumber;
  }

  public void setProgrammeNumber(Long programmeNumber) {
    this.programmeNumber = programmeNumber;
  }

  public String getProgrammeName() {
    return programmeName;
  }

  public void setProgrammeName(String programmeName) {
    this.programmeName = programmeName;
  }

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }

  public AssessmentType getType() {
    return type;
  }

  public void setType(AssessmentType type) {
    this.type = type;
  }

  public Long getCurriculumId() {
    return curriculumId;
  }

  public void setCurriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
  }

  public String getCurriculumName() {
    return curriculumName;
  }

  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }

  public LocalDate getCurriculumStartDate() {
    return curriculumStartDate;
  }

  public void setCurriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
  }

  public LocalDate getCurriculumEndDate() {
    return curriculumEndDate;
  }

  public void setCurriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
  }

  public Long getCurriculumSpecialtyId() {
    return curriculumSpecialtyId;
  }

  public void setCurriculumSpecialtyId(Long curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
  }

  public String getCurriculumSpecialty() {
    return curriculumSpecialty;
  }

  public void setCurriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
  }

  public String getCurriculumSubType() {
    return curriculumSubType;
  }

  public void setCurriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
  }

  public String getMembershipType() {
    return membershipType;
  }

  public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
  }

  public String getGradeAbbreviation() {
    return gradeAbbreviation;
  }

  public void setGradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
  }

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public LocalDate getPeriodCoveredFrom() {
    return periodCoveredFrom;
  }

  public void setPeriodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
  }

  public LocalDate getPeriodCoveredTo() {
    return periodCoveredTo;
  }

  public void setPeriodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
  }

  public LocalDate getPortfolioReviewDate() {
    return portfolioReviewDate;
  }

  public void setPortfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
  }

  public Integer getMonthsWTEDuringPeriod() {
    return monthsWTEDuringPeriod;
  }

  public void setMonthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
  }

  public Integer getMonthsCountedToTraining() {
    return monthsCountedToTraining;
  }

  public void setMonthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
  }

  public String getTraineeNTN() {
    return traineeNTN;
  }

  public void setTraineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
  }

  public String getPya() {
    return pya;
  }

  public void setPya(String pya) {
    this.pya = pya;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public OutcomeDTO getOutcome() {
    return outcome;
  }

  public void setOutcome(OutcomeDTO outcome) {
    this.outcome = outcome;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AssessmentDTO assessmentDTO = (AssessmentDTO) o;
    if (assessmentDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), assessmentDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "AssessmentDTO{" +
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
