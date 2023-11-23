package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.function.CustomerToCustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetCustomer {

    private final CustomerRepository customerRepository;
    private final CustomerToCustomerDTO converter;

    public CustomerDTO run(String documentNumber) {
        Customer customer = customerRepository.findById(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return converter.apply(customer, customer.getCustomerAddress());
    }
}
