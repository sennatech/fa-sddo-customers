package br.com.sennatech.sddo.customers.service;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class CustomerCreate implements Supplier<String> {

  @Override
  public String get() {
    return "teste";
  }
}
