package com.app.healthcare.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnrolDependent {
  @NotNull
  private int enrolId;
  @NotNull
  private int dependentId;
}
