package com.app.healthcare.entity;

import com.app.healthcare.dto.EnrolDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "enrol")
@Data
@ToString
@NoArgsConstructor
public class EnrolEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private boolean activeStatus;
  private Date dob;
  private String phoneNumber;

  public EnrolDto toEnrolDto() {
    EnrolDto enrolDto = new EnrolDto(this);
    return enrolDto;
  }

  public EnrolEntity(EnrolDto enrolDto){
    this.id = enrolDto.getId();
    this.name = enrolDto.getName();
    this.activeStatus = enrolDto.isActiveStatus();
    this.dob = enrolDto.getDob();
    this.phoneNumber = enrolDto.getPhoneNumber();
  }
}
