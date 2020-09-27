package com.app.healthcare.repository;

import com.app.healthcare.entity.EnrolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolRepository extends CrudRepository<EnrolEntity, Long> {
}
