package com.app.healthcare.service;

import com.app.healthcare.dto.EnrolDto;

import java.util.List;

public interface EnrolService {
  List<EnrolDto> getAll();

  EnrolDto getById(int id);

  EnrolDto create(EnrolDto enrolDto);

  EnrolDto update(EnrolDto enrolDto);

  void delete(int id);

  void delete(int enrolId, int dependentId);
}
