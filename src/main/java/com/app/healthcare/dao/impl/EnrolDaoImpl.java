package com.app.healthcare.dao.impl;

import com.app.healthcare.dao.EnrolDao;
import com.app.healthcare.entity.DependentEntity;
import com.app.healthcare.entity.EnrolEntity;
import com.app.healthcare.repository.DependentRepository;
import com.app.healthcare.repository.EnrolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Component
public class EnrolDaoImpl implements EnrolDao {

  @Autowired
  EnrolRepository enrolRepository;

  @Autowired
  DependentRepository dependentRepository;

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<EnrolEntity> getAll() {
    return (List<EnrolEntity>) enrolRepository.findAll();
  }

  @Override
  public Optional<EnrolEntity> getById(Long id) {
    return enrolRepository.findById(id);
  }

  @Override
  public EnrolEntity create(EnrolEntity enrolEntity) {
    return enrolRepository.save(enrolEntity);
  }

  @Override
  public EnrolEntity update(EnrolEntity enrolEntity) {
    return enrolRepository.save(enrolEntity);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Query query= manager.createNativeQuery("delete from dependent a where a.enrol= ?");
    query.setParameter(1, id);
    query.executeUpdate();
    enrolRepository.deleteById(id);
  }

  @Override
  public List<DependentEntity> saveDependents(List<DependentEntity> dependentEntityList) {
    return (List<DependentEntity>) dependentRepository.saveAll(dependentEntityList);
  }

  @Override
  public List<DependentEntity> getDependents(EnrolEntity enrolEntity) {
    List<DependentEntity> dependentEntityList = new ArrayList<>();
    Query query= manager.createNativeQuery("select * from dependent a where a.enrol= ?", DependentEntity.class);
    query.setParameter(1, enrolEntity.getId());
    List<DependentEntity> result = query.getResultList();
    dependentEntityList = result;
//    result.stream().forEach(o -> System.out.println(o[0]));
//    return dependentRepository.findByEnrol(enrolEntity.getId());
    return dependentEntityList;
  }

  @Override
  @Transactional
  public void delete(int enrolId, int dependentId) {
    Query query= manager.createNativeQuery("delete from dependent a where a.enrol= ? and id=? ");
    query.setParameter(1, enrolId);
    query.setParameter(2, enrolId);
    query.executeUpdate();
  }
}
