package br.com.sennatech.sddo.customers.exception;

public class InvalidRecoveryHashException extends RuntimeException {
  public InvalidRecoveryHashException(String message) {
    super(message);
  }

}
