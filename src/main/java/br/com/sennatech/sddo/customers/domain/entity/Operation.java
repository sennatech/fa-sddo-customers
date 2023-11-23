package br.com.sennatech.sddo.customers.domain.entity;

public enum Operation {
  CUSTOMER_CREATED,
  CUSTOMER_DELETED,
  CUSTOMER_UPDATED;

  public static Operation get(String functionName) {
    switch (functionName) {
      case "customers-create":
        return CUSTOMER_CREATED;
      case "customers-update":
        return CUSTOMER_UPDATED;
      case "customers-delete":
        return CUSTOMER_DELETED;
      default:
        throw new UnsupportedOperationException("Non-existent operation: " + functionName);
    }
  }
}
