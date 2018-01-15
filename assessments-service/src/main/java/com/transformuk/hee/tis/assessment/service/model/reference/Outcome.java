package com.transformuk.hee.tis.assessment.service.model.reference;


import com.transformuk.hee.tis.assessment.api.dto.validation.Create;
import com.transformuk.hee.tis.assessment.api.dto.validation.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * An Outcome.
 */
@Entity
@Table(name = "Outcome")
@ApiModel(description = "Reference type of data that can be managed via the Outcome endpoint. Directly related to an " +
    "assessment outcome")
public class Outcome implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "System generated ID that is assigned to the outcome upon creation. Required for updates")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "code cannot be null")
  @Column(name = "code")
  @ApiModelProperty(value = "A user friendly code that end users may know this outcome by", required = true)
  private String code;

  @NotNull(groups = {Create.class, Update.class}, message = "label cannot be null")
  @Column(name = "label")
  @ApiModelProperty(value = "A human readable label that represents the Outcome", required = true)
  private String label;

  @ManyToMany
  @JoinTable(
      name = "OutcomeReasons",
      joinColumns = @JoinColumn(name = "outcomeId", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "reasonId", referencedColumnName = "id")
  )
  private Set<Reason> reasons;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Outcome id(Long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Outcome code(String code) {
    this.code = code;
    return this;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Outcome label(String label) {
    this.label = label;
    return this;
  }

  public Set<Reason> getReasons() {
    return reasons;
  }

  public void setReasons(Set<Reason> reasons) {
    this.reasons = reasons;
  }

  public Outcome reasons(Set<Reason> reasons) {
    this.reasons = reasons;
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
    Outcome assessment = (Outcome) o;
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
        '}';
  }
}
