package br.com.sennatech.sddo.customers.service;

import java.util.function.Supplier;

public class CustomerCreate implements Supplier<String> {

  @Override
  public String get() {
    return "hello world";
  }
  
}
