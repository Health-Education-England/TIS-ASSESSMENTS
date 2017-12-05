package com.transformuk.hee.tis.assessment.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "AssessmentDetail")
public class AssessmentDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "curriculumId")
  private Long curriculumId;

  @Column(name = "curriculumName")
  private String curriculumName;

  @Column(name = "curriculumStartDate")
  private LocalDate curriculumStartDate;

  @Column(name = "curriculumEndDate")
  private LocalDate curriculumEndDate;

  @Column(name = "curriculumSpecialtyId")
  private String curriculumSpecialtyId;

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

  @Column(name = "daysOutOfTraining")
  private Integer daysOutOfTraining;

  @Column(name = "traineeNtn")
  private String traineeNTN;

  @Column(name = "pya")
  private String pya;

  @Version
  private LocalDateTime amendedDate;

  @Column(name = "intrepidId")
  private String intrepidId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getCurriculumId() {
    return curriculumId;
  }

  public void setCurriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
  }

  public AssessmentDetail curriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
    return this;
  }

  public String getCurriculumName() {
    return curriculumName;
  }

  public void setCurriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
  }

  public AssessmentDetail curriculumName(String curriculumName) {
    this.curriculumName = curriculumName;
    return this;
  }

  public LocalDate getCurriculumStartDate() {
    return curriculumStartDate;
  }

  public void setCurriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
  }

  public AssessmentDetail curriculumStartDate(LocalDate curriculumStartDate) {
    this.curriculumStartDate = curriculumStartDate;
    return this;
  }

  public LocalDate getCurriculumEndDate() {
    return curriculumEndDate;
  }

  public void setCurriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
  }

  public AssessmentDetail curriculumEndDate(LocalDate curriculumEndDate) {
    this.curriculumEndDate = curriculumEndDate;
    return this;
  }

  public String getCurriculumSpecialtyId() {
    return curriculumSpecialtyId;
  }

  public void setCurriculumSpecialtyId(String curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
  }

  public AssessmentDetail curriculumSpecialtyId(String curriculumSpecialtyId) {
    this.curriculumSpecialtyId = curriculumSpecialtyId;
    return this;
  }

  public String getCurriculumSpecialty() {
    return curriculumSpecialty;
  }

  public void setCurriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
  }

  public AssessmentDetail curriculumSpecialty(String curriculumSpecialty) {
    this.curriculumSpecialty = curriculumSpecialty;
    return this;
  }

  public String getCurriculumSubType() {
    return curriculumSubType;
  }

  public void setCurriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
  }

  public AssessmentDetail curriculumSubType(String curriculumSubType) {
    this.curriculumSubType = curriculumSubType;
    return this;
  }

  public String getMembershipType() {
    return membershipType;
  }

  public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
  }

  public AssessmentDetail membershipType(String membershipType) {
    this.membershipType = membershipType;
    return this;
  }

  public String getGradeAbbreviation() {
    return gradeAbbreviation;
  }

  public void setGradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
  }

  public AssessmentDetail gradeAbbreviation(String gradeAbbreviation) {
    this.gradeAbbreviation = gradeAbbreviation;
    return this;
  }

  public String getGradeName() {
    return gradeName;
  }

  public void setGradeName(String gradeName) {
    this.gradeName = gradeName;
  }

  public AssessmentDetail gradeName(String gradeName) {
    this.gradeName = gradeName;
    return this;
  }

  public LocalDate getPeriodCoveredFrom() {
    return periodCoveredFrom;
  }

  public void setPeriodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
  }

  public AssessmentDetail periodCoveredFrom(LocalDate periodCoveredFrom) {
    this.periodCoveredFrom = periodCoveredFrom;
    return this;
  }

  public LocalDate getPeriodCoveredTo() {
    return periodCoveredTo;
  }

  public void setPeriodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
  }

  public AssessmentDetail periodCoveredTo(LocalDate periodCoveredTo) {
    this.periodCoveredTo = periodCoveredTo;
    return this;
  }

  public LocalDate getPortfolioReviewDate() {
    return portfolioReviewDate;
  }

  public void setPortfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
  }

  public AssessmentDetail portfolioReviewDate(LocalDate portfolioReviewDate) {
    this.portfolioReviewDate = portfolioReviewDate;
    return this;
  }

  public Integer getMonthsWTEDuringPeriod() {
    return monthsWTEDuringPeriod;
  }

  public void setMonthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
  }

  public AssessmentDetail monthsWTEDuringPeriod(Integer monthsWTEDuringPeriod) {
    this.monthsWTEDuringPeriod = monthsWTEDuringPeriod;
    return this;
  }

  public Integer getMonthsCountedToTraining() {
    return monthsCountedToTraining;
  }

  public void setMonthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
  }

  public AssessmentDetail monthsCountedToTraining(Integer monthsCountedToTraining) {
    this.monthsCountedToTraining = monthsCountedToTraining;
    return this;
  }

  public Integer getDaysOutOfTraining() {
    return daysOutOfTraining;
  }

  public void setDaysOutOfTraining(Integer daysOutOfTraining) {
    this.daysOutOfTraining = daysOutOfTraining;
  }

  public AssessmentDetail daysOutOfTraining(Integer daysOutOfTraining) {
    this.daysOutOfTraining = daysOutOfTraining;
    return this;
  }

  public String getTraineeNTN() {
    return traineeNTN;
  }

  public void setTraineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
  }

  public AssessmentDetail traineeNTN(String traineeNTN) {
    this.traineeNTN = traineeNTN;
    return this;
  }

  public String getPya() {
    return pya;
  }

  public void setPya(String pya) {
    this.pya = pya;
  }

  public AssessmentDetail pya(String pya) {
    this.pya = pya;
    return this;
  }


  public LocalDateTime getAmendedDate() {
    return amendedDate;
  }

  public void setAmendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
  }

  public AssessmentDetail amendedDate(LocalDateTime amendedDate) {
    this.amendedDate = amendedDate;
    return this;
  }

  public String getIntrepidId() {
    return intrepidId;
  }

  public void setIntrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
  }

  public AssessmentDetail intrepidId(String intrepidId) {
    this.intrepidId = intrepidId;
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
    return "AssessmentDetail{" +
        "id=" + id +
        ", curriculumId=" + curriculumId +
        ", curriculumName='" + curriculumName + '\'' +
        ", curriculumStartDate=" + curriculumStartDate +
        ", curriculumEndDate=" + curriculumEndDate +
        ", curriculumSpecialtyId='" + curriculumSpecialtyId + '\'' +
        ", curriculumSpecialty='" + curriculumSpecialty + '\'' +
        ", curriculumSubType='" + curriculumSubType + '\'' +
        ", membershipType='" + membershipType + '\'' +
        ", gradeAbbreviation='" + gradeAbbreviation + '\'' +
        ", gradeName='" + gradeName + '\'' +
        ", periodCoveredFrom=" + periodCoveredFrom +
        ", periodCoveredTo=" + periodCoveredTo +
        ", portfolioReviewDate=" + portfolioReviewDate +
        ", monthsWTEDuringPeriod=" + monthsWTEDuringPeriod +
        ", monthsCountedToTraining=" + monthsCountedToTraining +
        ", daysOutOfTraining=" + daysOutOfTraining +
        ", traineeNTN='" + traineeNTN + '\'' +
        ", pya='" + pya + '\'' +
        ", amendedDate=" + amendedDate +
        ", intrepidId='" + intrepidId + '\'' +
        '}';
  }
}
