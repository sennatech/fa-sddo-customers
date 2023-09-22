package br.com.sennatech.sddo.customers.domain.dto;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
