package br.com.sennatech.sddo.customers.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerListDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;

@Component
public class CustomerToCustomerListDTO implements Function<Customer, CustomerListDTO> {

  @Override
  public CustomerListDTO apply(Customer customer) {
    return CustomerListDTO.builder().documentNumber(customer.getDocumentNumber()).email(customer.getEmail()).name(customer.getName()).build();
  }
}
