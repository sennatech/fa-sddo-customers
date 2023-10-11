package br.com.sennatech.sddo.customers.domain;

public enum Operation {
  CUSTOMER_CREATED,
  CUSTOMER_UPDATED;

  public static Operation get(String functionName) {
    switch (functionName) {
      case "customerCreation":
        return CUSTOMER_CREATED;
      case "customerUpdate":
        return CUSTOMER_UPDATED;
      default:
        throw new UnsupportedOperationException("Non-existent operation: " + functionName);
    }
  }
}
