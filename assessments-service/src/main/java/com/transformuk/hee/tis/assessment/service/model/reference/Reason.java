package com.transformuk.hee.tis.assessment.service.model.reference;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A Reason.
 */
@Entity
@Table(name = "Reason")
public class Reason implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "code cannot be null")
  @Column(name = "code")
  private String code;

  @NotNull(groups = {Create.class, Update.class}, message = "label cannot be null")
  @Column(name = "label")
  private String label;

  @ManyToMany(mappedBy = "reasons")
  private Set<Outcome> outcomes;

  @NotNull(groups = {Create.class, Update.class}, message = "requireOther cannot be null")
  private boolean requireOther;

  private boolean isLegacy;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Reason id(Long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Reason code(String code) {
    this.code = code;
    return this;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Reason label(String label) {
    this.label = label;
    return this;
  }

  public Set<Outcome> getOutcomes() {
    return outcomes;
  }

  public void setOutcomes(Set<Outcome> outcomes) {
    this.outcomes = outcomes;
  }

  public boolean isRequireOther() {
    return requireOther;
  }

  public void setRequireOther(boolean requireOther) {
    this.requireOther = requireOther;
  }

  public boolean isLegacy() {
    return isLegacy;
  }

  public void setLegacy(boolean legacy) {
    isLegacy = legacy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Reason assessment = (Reason) o;
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
    return "Reason{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", label='" + label + '\'' +
        ", outcomes=" + outcomes +
        ", requireOther=" + requireOther +
        ", isLegacy=" + isLegacy +
        '}';
  }
}
