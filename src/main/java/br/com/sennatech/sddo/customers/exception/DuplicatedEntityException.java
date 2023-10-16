package br.com.sennatech.sddo.customers.exception;

public class DuplicatedEntityException extends RuntimeException {
  public DuplicatedEntityException(String message) {
    super(message);
  }
}
