package com.app.healthcare.repository;

import com.app.healthcare.dto.DependentDto;
import com.app.healthcare.entity.DependentEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependentRepository extends CrudRepository<DependentEntity, Long> {

//  @Query(value = "find DependentEntity d where d.enrol=:id", nativeQuery = true)
//  List<DependentEntity> findByEnrol(Long id);
}
