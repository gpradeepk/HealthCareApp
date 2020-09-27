package com.app.healthcare.service.impl;

import com.app.healthcare.dao.EnrolDao;
import com.app.healthcare.dto.EnrolDto;
import com.app.healthcare.entity.DependentEntity;
import com.app.healthcare.entity.EnrolEntity;
import com.app.healthcare.exception.EnrolNotFoundException;
import com.app.healthcare.exception.NoDataFoundException;
import com.app.healthcare.service.EnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrolServiceImpl implements EnrolService {

  @Autowired
  EnrolDao enrolDao;

  @Override
  public List<EnrolDto> getAll() {
    List<EnrolDto> enrolDtos = new ArrayList<>();
    List<EnrolEntity> enrolEntities = enrolDao.getAll();
    if (enrolEntities != null) {
      enrolDtos = enrolEntities.stream().map(e -> {EnrolDto enrolDto = e.toEnrolDto();
                              getDependentsByEnroleId(enrolDto);
                              return enrolDto;}).collect(Collectors.toList());
    } else {
      throw new NoDataFoundException("No enrol found");
    }
    return enrolDtos;
  }

  @Override
  public EnrolDto getById(int id) {
    EnrolDto enrolDto = null;
    try {
      Long id1 = Long.valueOf(id);
      Optional<EnrolEntity> optionalEnrolEntity = enrolDao.getById(id1);
      if (optionalEnrolEntity.isPresent()) {
        enrolDto = optionalEnrolEntity.get().toEnrolDto();
        getDependentsByEnroleId(enrolDto);
      } else {
        throw new EnrolNotFoundException(String.format("Enrol with Id %d not found", id));
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new EnrolNotFoundException(String.format("Enrol with Id %d not found", id));
    }
    return enrolDto;
  }

  @Override
  public EnrolDto create(EnrolDto enrolDto) {
    EnrolDto enrolDtoResult = null;
    try {
      EnrolEntity optionalEnrolEntity = enrolDao.create(enrolDto.toEnrolEntity());
      if (hasDependents(enrolDto)) {
        List<DependentEntity> dependentEntityList =
            enrolDto.getDependentDtoList().stream().map(d -> {
              DependentEntity de = d.toDependentEntity();
              de.setEnrolEntity(optionalEnrolEntity);
              return de;}).collect(Collectors.toList());
        List<DependentEntity> dependentEntityListResult = enrolDao.saveDependents(dependentEntityList);
        enrolDtoResult = optionalEnrolEntity.toEnrolDto();
        if(dependentEntityListResult!=null && !dependentEntityListResult.isEmpty()){
          enrolDtoResult.setDependentDtoList(dependentEntityListResult.stream().map(d -> d.toDependentDto()).collect(
              Collectors.toList()));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return enrolDtoResult;
  }

  @Override
  public EnrolDto update(EnrolDto enrolDto) {
    EnrolDto enrolDtoResult = null;
    try {
      EnrolEntity optionalEnrolEntity = enrolDao.create(enrolDto.toEnrolEntity());
      if (hasDependents(enrolDto)) {
        List<DependentEntity> dependentEntityList =
            enrolDto.getDependentDtoList().stream().map(d -> {
              DependentEntity de = d.toDependentEntity();
              de.setEnrolEntity(optionalEnrolEntity);
              return de;}).collect(Collectors.toList());
        List<DependentEntity> dependentEntityListResult = enrolDao.saveDependents(dependentEntityList);
        enrolDtoResult = optionalEnrolEntity.toEnrolDto();
        getDependentsByEnroleId(enrolDtoResult);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return enrolDtoResult;
  }

  @Override
  public void delete(int id) {
    try {
      enrolDao.delete(Long.valueOf(id));
    } catch (Exception e) {
      throw new EnrolNotFoundException(String.format("Enrol with Id %d not found", id));
    }
  }

  @Override
  public void delete(int enrolId, int dependentId) {
    enrolDao.delete(enrolId, dependentId);
  }

  private boolean hasDependents(EnrolDto enrolDto) {
    if (enrolDto != null && enrolDto.getDependentDtoList() != null && enrolDto.getDependentDtoList().size() > 0) {
      return true;
    }
    return false;
  }

  public void getDependentsByEnroleId(EnrolDto enrolDto){
    List<DependentEntity> dependentEntityList = enrolDao.getDependents(enrolDto.toEnrolEntity());
    if(dependentEntityList!=null && !dependentEntityList.isEmpty()){
      enrolDto.setDependentDtoList(dependentEntityList.stream().map(d -> d.toDependentDto()).collect(Collectors.toList()));
    }
  }
}
