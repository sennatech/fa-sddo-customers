package br.com.sennatech.sddo.customers.function;

import java.util.function.BiFunction;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.CustomerUpdateDTO;

@Component
public class CustomerUpdateDTOtoCustomerDTO implements BiFunction<CustomerUpdateDTO, String, CustomerDTO> {

  @Override
  public CustomerDTO apply(CustomerUpdateDTO customerUpdateDTO, String documentNumber) {
    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setDocumentNumber(documentNumber);
    customerDTO.setAddress(customerUpdateDTO.getAddress());
    customerDTO.setGender(customerUpdateDTO.getGender());
    customerDTO.setLogin(customerUpdateDTO.getLogin());
    customerDTO.setAreaCode(customerUpdateDTO.getAreaCode());
    customerDTO.setBirthdate(customerUpdateDTO.getBirthdate());
    customerDTO.setEmail(customerUpdateDTO.getEmail());
    customerDTO.setName(customerUpdateDTO.getName());
    customerDTO.setPassword(customerUpdateDTO.getPassword());
    customerDTO.setPhone(customerUpdateDTO.getPhone());
    return customerDTO;
  }
  
}
