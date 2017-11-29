package com.transformuk.hee.tis.assessment.api.dto;

public enum AssessmentType {

  APPRAISAL("Appraisal"),
  ARCP("ARCP"),
  ARCP_DEANS_APPEAL("ARCP - Deans Appeal"),
  ARCP_INTERIM_REVIEW("ARCP - Interim review"),
  ASSESSMENT("Assessment"),
  ATTAINMENT_OF_F1_COMPETENCY("Attainment of F1 Competency"),
  CBD("CbD"),
  CLINICAL_SUPERVISION("Clinical Supervision"),
  COMPETENCY("Competency"),
  DOPS("DOPS"),
  EDUCATIONAL_AGREEMENT("Educational Agreement"),
  EDUCATIONAL_SUPERVISION("Educational Supervision"),
  EDUCATIONAL_SUPERVISOR_ACCREDITATION("Educational Supervisor Accreditation"),
  END_OF_PLACEMENT_REVIEW("End of Placement Review"),
  FACD("FACD"),
  INTERIM_REVIEW("Interim review"),
  JOB_PLAN("Job Plan"),
  MEETING("Meeting"),
  MID_POINT_REVIEW("Mid Point Review"),
  MINI_CEX("Mini-CEX"),
  MINI_PAT("Mini-PAT"),
  PLACEMENT_INDUCTION_MEETING("Placement Induction Meeting"),
  RITA("RITA"),
  SUPERVISION("Supervision"),
  TAB("TAB");

  private String label;

  AssessmentType(String label) {
    this.label = label;
  }

  public String getLabel(){
    return this.label;
  }
}
