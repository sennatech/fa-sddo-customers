package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import br.com.sennatech.sddo.customers.exception.DuplicatedEntityException;
import br.com.sennatech.sddo.customers.function.CustomerDTOToCustomer;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;

@Service
public class CreateCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerDTOToCustomer customerDTOToCustomer;

    public Customer run(CustomerDTO customerDTO) {
        if (customerRepository.existsById(customerDTO.getDocumentNumber()))
            throw new DuplicatedEntityException("Customer already exists");
        Customer customer = customerDTOToCustomer.apply(customerDTO);
        customerRepository.save(customer);
        return customer;
    }
}
