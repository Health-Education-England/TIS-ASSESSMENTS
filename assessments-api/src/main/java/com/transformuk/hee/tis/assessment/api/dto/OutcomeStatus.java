package com.transformuk.hee.tis.assessment.api.dto;

/**
 * The OutcomeStatus enumeration.
 */
public enum OutcomeStatus {
  OUTCOME_1("1"),
  OUTCOME_2("2"),
  OUTCOME_3("3"),
  OUTCOME_4("4"),
  OUTCOME_5("5"),
  OUTCOME_6("6"),
  OUTCOME_7("7");

  private String label;

  OutcomeStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
