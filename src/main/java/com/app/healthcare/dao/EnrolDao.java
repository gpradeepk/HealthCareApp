package com.app.healthcare.dao;

import com.app.healthcare.entity.DependentEntity;
import com.app.healthcare.entity.EnrolEntity;

import java.util.List;
import java.util.Optional;

public interface EnrolDao {
  List<EnrolEntity> getAll();

  Optional<EnrolEntity> getById(Long id);

  EnrolEntity create(EnrolEntity enrolDto);

  EnrolEntity update(EnrolEntity enrolDto);

  void delete(Long id);

  List<DependentEntity> saveDependents(List<DependentEntity> dependentEntityList);

  List<DependentEntity> getDependents(EnrolEntity enrolEntity);

  void delete(int enrolId, int dependentId);
}
