package com.transformuk.hee.tis.assessment.api.dto;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO for the Assessment entity.
 */
public class AssessmentDetailDTO implements Serializable {

  @Null(groups = Create.class, message = "id must be null when creating a new assessment detail")
  @DecimalMin(value = "0", groups = Update.class, message = "id must not be negative")
  @ApiModelProperty(value = "The id of the details component of the assessment, must match the id of the assessment." +
      "Required for PUT, must be null for POST")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "curriculumId cannot be null")
  @ApiModelProperty(required = true, value = "The curriculum id for this assessment")
  private Long curriculumId;

  @NotNull(groups = {Create.class, Update.class}, message = "curriculum name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "curriculum name should be at least 1 character long")
  @ApiModelProperty(required = true, value = "The curriculum name for this assessment")
  private String curriculumName;

  @ApiModelProperty(value = "The start date of the curriculum for this trainee's assessment")
  private LocalDate curriculumStartDate;

  @ApiModelProperty(value = "The end date of the curriculum for this trainee's assessment")
  private LocalDate curriculumEndDate;

  @ApiModelProperty(value = "The curriculum specialty id of the trainee's assessment")
  private String curriculumSpecialtyId;

  @ApiModelProperty(value = "The curriculum specialty for this trainee's assessment")
  private String curriculumSpecialty;

  @ApiModelProperty(value = "The curriculum sub type for this trainee's assessment")
  private String curriculumSubType;

  @ApiModelProperty(value = "The membership type for this trainee's assessment")
  private String membershipType;

  //TODO: uncomment the validation to grade id when ready to migrate
//  @NotNull(groups = {Create.class, Update.class}, message = "gradeId cannot be null")
//  @ApiModelProperty(required = true, value = "The grade id of the assessments grade")
  private Long gradeId;

  @NotNull(groups = {Create.class, Update.class}, message = "gradeAbbreviation cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "gradeAbbreviation should be at least 1 character long")
  @ApiModelProperty(required = true, value = "The grade abbreviation of the assessments grade")
  private String gradeAbbreviation;

  @NotNull(groups = {Create.class, Update.class}, message = "grade name cannot be null")
  @Length(min = 1, groups = {Create.class, Update.class}, message = "grade name should be at least 1 character long")
  @ApiModelProperty(required = true, value = "The grade name of the assessments grade")
  private String gradeName;

  @ApiModelProperty(required = true, value = "The period in which this covers from")
  private LocalDate periodCoveredFrom;

  @ApiModelProperty(required = true, value = "The period in which this covers to")
  private LocalDate periodCoveredTo;

  @ApiModelProperty(value = "The date in which the portfolio was reviewed")
  private LocalDate portfolioReviewDate;

  @NotNull(groups = {Create.class, Update.class}, message = "monthsWTEDuringPeriod cannot be null")
  @ApiModelProperty(value = "Months work time equivalent during the period")
  private Integer monthsWTEDuringPeriod;

  @ApiModelProperty(value = "The amount of months that are counted towards training")
  private Integer monthsCountedToTraining;

  @ApiModelProperty(value = "The amound of days which were out of training")
  private Integer daysOutOfTraining;

  @ApiModelProperty(value = "The trainee's National Training Number")
  private String traineeNTN;

  @ApiModelProperty(value = "pya")
  private Boolean pya;

  @ApiModelProperty(value = "The intrepid id of this detail if the assessment was initiated from Intrepid")
  private String intrepidId;

  @ApiModelProperty(value = "Version property to allow optimistic locking")
  private LocalDateTime amendedDate;

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

  public String getCurriculumSpecialtyId() {
    return curriculumSpecialtyId;
  }

  public void setCurriculumSpecialtyId(String curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
  }

  public AssessmentDetailDTO curriculumSpecialtyId(String curriculumSpecialtyId) {
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

  public Long getGradeId() {
    return gradeId;
  }

  public void setGradeId(Long gradeId) {
    this.gradeId = gradeId;
  }

  public AssessmentDetailDTO gradeId(Long gradeId) {
    this.gradeId = gradeId;
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

  public Integer getDaysOutOfTraining() {
    return daysOutOfTraining;
  }

  public void setDaysOutOfTraining(Integer daysOutOfTraining) {
    this.daysOutOfTraining = daysOutOfTraining;
  }

  public AssessmentDetailDTO daysOutOfTraining(Integer daysOutOfTraining) {
    this.daysOutOfTraining = daysOutOfTraining;
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

  public Boolean getPya() {
    return pya;
  }

  public void setPya(Boolean pya) {
    this.pya = pya;
  }

  public AssessmentDetailDTO pya(Boolean pya) {
    this.pya = pya;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public AssessmentDetailDTO intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
    return this;
  }

  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public AssessmentDetailDTO amendedDate(LocalDateTime amendedDate) {
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
    return "AssessmentDetailDTO{" +
        "id=" + id +
        ", curriculumId=" + curriculumId +
        ", curriculumName='" + curriculumName + '\'' +
        ", curriculumStartDate=" + curriculumStartDate +
        ", curriculumEndDate=" + curriculumEndDate +
        ", curriculumSpecialtyId='" + curriculumSpecialtyId + '\'' +
        ", curriculumSpecialty='" + curriculumSpecialty + '\'' +
        ", curriculumSubType='" + curriculumSubType + '\'' +
        ", membershipType='" + membershipType + '\'' +
        ", gradeId=" + gradeId +
        ", gradeAbbreviation='" + gradeAbbreviation + '\'' +
        ", gradeName='" + gradeName + '\'' +
        ", periodCoveredFrom=" + periodCoveredFrom +
        ", periodCoveredTo=" + periodCoveredTo +
        ", portfolioReviewDate=" + portfolioReviewDate +
        ", monthsWTEDuringPeriod=" + monthsWTEDuringPeriod +
        ", monthsCountedToTraining=" + monthsCountedToTraining +
        ", daysOutOfTraining=" + daysOutOfTraining +
        ", traineeNTN='" + traineeNTN + '\'' +
        ", pya=" + pya +
        ", intrepidId='" + intrepidId + '\'' +
        ", amendedDate=" + amendedDate +
        '}';
  }
}
