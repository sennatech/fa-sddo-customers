package br.com.sennatech.sddo.customers.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.CustomerListDTO;

@Component
public class CustomerDTOToCustomerListDTO implements Function<CustomerDTO, CustomerListDTO> {

  @Override
  public CustomerListDTO apply(CustomerDTO customerDTO) {
    return new CustomerListDTO(customerDTO.getDocumentNumber(), customerDTO.getName());
  }
}
