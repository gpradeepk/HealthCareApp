package com.app.healthcare.dto;

import com.app.healthcare.entity.EnrolEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnrolDto {
  private Long id;
  private String name;
  private boolean activeStatus;
  private Date dob;
  private String phoneNumber;
  List<DependentDto> dependentDtoList;

  public EnrolDto(EnrolEntity enrolEntity) {
    this.id = enrolEntity.getId();
    this.name = enrolEntity.getName();
    this.activeStatus = enrolEntity.isActiveStatus();
    this.dob = enrolEntity.getDob();
    this.phoneNumber = enrolEntity.getPhoneNumber();
  }

  public EnrolEntity toEnrolEntity() {
    EnrolEntity enrolDto = new EnrolEntity(this);
    return enrolDto;
  }
}
