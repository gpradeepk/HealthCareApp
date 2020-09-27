package com.app.healthcare.controller;

import com.app.healthcare.dto.EnrolDependent;
import com.app.healthcare.dto.EnrolDto;
import com.app.healthcare.service.EnrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrol")
public class EnrolerController {

  @Autowired
  EnrolService enrolService;

  @PostMapping
  public EnrolDto createEnrol(@RequestBody EnrolDto enrolDto) {
    return enrolService.create(enrolDto);
  }

  @GetMapping("/{id}")
  public EnrolDto getByIdEnrol(@PathVariable("id") int id) {
    return enrolService.getById(id);
  }

  @GetMapping
  public List<EnrolDto> getAllEnrol() {
    return enrolService.getAll();
  }

  @DeleteMapping("/{id}")
  public void deleteEnrol(@PathVariable("id") int id) {
    enrolService.delete(id);
  }

  @PutMapping
  public EnrolDto updateEnrol(@RequestBody EnrolDto enrolDto) {
    return enrolService.update(enrolDto);
  }

  @DeleteMapping
  public void deleteDependent(@RequestBody EnrolDependent enrolDependent) {
    enrolService.delete(enrolDependent.getEnrolId(), enrolDependent.getDependentId());
  }


}
