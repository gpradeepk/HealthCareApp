package com.app.healthcare.exception;

public class EnrolNotFoundException extends RuntimeException {

  public EnrolNotFoundException(Long id) {
    super(String.format("Enrol with Id %d not found", id));
  }

  public EnrolNotFoundException(String id) {
    super(id);
  }
}