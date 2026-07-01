package com.ravindra.product_management.exception;

public class MethodArgumentNotValidException extends RuntimeException {

  public MethodArgumentNotValidException(String message)
  {
      super(message);
  }
}
