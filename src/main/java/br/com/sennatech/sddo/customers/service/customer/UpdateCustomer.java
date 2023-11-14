package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.function.CustomerUpdateEntity;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateCustomer {

  private final CustomerRepository customerRepository;
  private final CustomerUpdateEntity customerEntityUpdate;

  @Transactional
  public void run(CustomerDTO customerDTO) {
    Customer customer = customerRepository.findById(customerDTO.getDocumentNumber()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    customerEntityUpdate.accept(customer, customerDTO);
    customerRepository.save(customer);
  }
}
