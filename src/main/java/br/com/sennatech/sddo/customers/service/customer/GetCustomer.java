package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.function.CustomerToCustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GetCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerToCustomerDTO converter;

    public CustomerDTO run(String documentNumber) {
        Customer customer = customerRepository.findById(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return converter.apply(customer, customer.getCustomerAddress());
    }
}
