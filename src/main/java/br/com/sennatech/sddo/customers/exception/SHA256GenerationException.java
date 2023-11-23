package br.com.sennatech.sddo.customers.exception;

public class SHA256GenerationException extends RuntimeException {
    public SHA256GenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SHA256GenerationException(String message) {
        super(message);
    }
}
