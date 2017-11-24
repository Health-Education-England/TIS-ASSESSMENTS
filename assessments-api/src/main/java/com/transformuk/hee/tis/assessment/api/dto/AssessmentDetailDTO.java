package com.transformuk.hee.tis.assessment.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Assessment entity.
 */
public class AssessmentDetailDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new assessment detail")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  private Long id;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AssessmentDetailDTO id(Long id) {
    this.id = id;
    return this;
  }

  public Long getCurriculumId() {
    return curriculumId;
  }

  public void setCurriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
  }

  public AssessmentDetailDTO curriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
    return this;
  }

  public String getCurriculumName() {
    return curriculumName;
  }

  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }

  public AssessmentDetailDTO curriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
    return this;
  }

  public LocalDate getCurriculumStartDate() {
    return curriculumStartDate;
  }

  public void setCurriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
  }

  public AssessmentDetailDTO curriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
    return this;
  }

  public LocalDate getCurriculumEndDate() {
    return curriculumEndDate;
  }

  public void setCurriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
  }

  public AssessmentDetailDTO curriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
    return this;
  }

  public Long getCurriculumSpecialtyId() {
    return curriculumSpecialtyId;
  }

  public void setCurriculumSpecialtyId(Long curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
  }

  public AssessmentDetailDTO curriculumSpecialtyId(Long curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
    return this;
  }

  public String getCurriculumSpecialty() {
    return curriculumSpecialty;
  }

  public void setCurriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
  }

  public AssessmentDetailDTO curriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
    return this;
  }

  public String getCurriculumSubType() {
    return curriculumSubType;
  }

  public void setCurriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
  }

  public AssessmentDetailDTO curriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
    return this;
  }

  public String getMembershipType() {
    return membershipType;
  }

  public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
  }

  public AssessmentDetailDTO membershipType(String membershipType) {
    this.membershipType = membershipType;
    return this;
  }

  public String getGradeAbbreviation() {
    return gradeAbbreviation;
  }

  public void setGradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
  }

  public AssessmentDetailDTO gradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
    return this;
  }

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public AssessmentDetailDTO gradeName(String gradeName) {
    this.gradeName = gradeName;
    return this;
  }

  public LocalDate getPeriodCoveredFrom() {
    return periodCoveredFrom;
  }

  public void setPeriodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
  }

  public AssessmentDetailDTO periodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
    return this;
  }

  public LocalDate getPeriodCoveredTo() {
    return periodCoveredTo;
  }

  public void setPeriodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
  }

  public AssessmentDetailDTO periodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
    return this;
  }

  public LocalDate getPortfolioReviewDate() {
    return portfolioReviewDate;
  }

  public void setPortfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
  }

  public AssessmentDetailDTO portfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
    return this;
  }

  public Integer getMonthsWTEDuringPeriod() {
    return monthsWTEDuringPeriod;
  }

  public void setMonthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
  }

  public AssessmentDetailDTO monthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
    return this;
  }

  public Integer getMonthsCountedToTraining() {
    return monthsCountedToTraining;
  }

  public void setMonthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
  }

  public AssessmentDetailDTO monthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
    return this;
  }

  public String getTraineeNTN() {
    return traineeNTN;
  }

  public void setTraineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
  }

  public AssessmentDetailDTO traineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
    return this;
  }

  public String getPya() {
    return pya;
  }

  public void setPya(String pya) {
    this.pya = pya;
  }

  public AssessmentDetailDTO pya(String pya) {
    this.pya = pya;
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

    AssessmentDetailDTO assessmentDTO = (AssessmentDetailDTO) o;
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
