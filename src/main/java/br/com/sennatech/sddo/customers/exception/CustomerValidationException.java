package br.com.sennatech.sddo.customers.exception;

public class CustomerValidationException extends RuntimeException {
  public CustomerValidationException(String message) {
    super(message);
  }
}
