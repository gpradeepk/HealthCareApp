package com.app.healthcare.exception;

public class NoDataFoundException extends RuntimeException {

  public NoDataFoundException() {
    super("No data found");
  }

  public NoDataFoundException(String err) {
    super(err);
  }
}