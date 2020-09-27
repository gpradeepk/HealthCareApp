package com.app.healthcare.dto;

import com.app.healthcare.entity.DependentEntity;
import com.app.healthcare.entity.EnrolEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
public class DependentDto {
  private Long id;
  private Date dob;
  private String name;

  public DependentDto(DependentEntity dependentEntity) {
    this.id = dependentEntity.getId();
    this.name = dependentEntity.getName();
    this.dob = dependentEntity.getDob();
  }

  public DependentEntity toDependentEntity() {
    DependentEntity dependentEntity = new DependentEntity(this);
    return dependentEntity;
  }
}
