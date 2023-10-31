package br.com.sennatech.sddo.customers.function;

import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.CustomerReducedDTO;

@Component
public class CustomerReducedDTOtoCustomerDTO implements BiFunction<CustomerReducedDTO, String, CustomerDTO> {

  @Override
  public CustomerDTO apply(CustomerReducedDTO customerReducedDTO, String documentNumber) {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setDocumentNumber(documentNumber);
    customerDTO.setAddress(customerReducedDTO.getAddress());
    customerDTO.setGender(customerReducedDTO.getGender());
    customerDTO.setLogin(customerReducedDTO.getLogin());
    customerDTO.setAreaCode(customerReducedDTO.getAreaCode());
    customerDTO.setBirthdate(customerReducedDTO.getBirthdate());
    customerDTO.setEmail(customerReducedDTO.getEmail());
    customerDTO.setName(customerReducedDTO.getName());
    customerDTO.setPassword(customerReducedDTO.getPassword());
    customerDTO.setPhone(customerReducedDTO.getPhone());
    return customerDTO;
  }
}
