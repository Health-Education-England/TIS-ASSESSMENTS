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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Reason.
 */
@Entity
@Table(name = "Reason")
@ApiModel(description = "Reference type of data that can be managed via the Reason endpoint. Directly related to an " +
    "outcome")
public class Reason implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "System generated ID that is assigned to the reason upon creation. Required for updates")
  private Long id;

  @NotNull(groups = {Create.class, Update.class}, message = "code cannot be null")
  @Column(name = "code")
  @ApiModelProperty(value = "A user friendly code that end users may know this reason by", required = true)
  private String code;

  @NotNull(groups = {Create.class, Update.class}, message = "label cannot be null")
  @Column(name = "label")
  @ApiModelProperty(value = "A human readable label that represents the Reason", required = true)
  private String label;

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
        '}';
  }
}
