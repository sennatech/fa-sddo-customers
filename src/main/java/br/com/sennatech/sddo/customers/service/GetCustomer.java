package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetCustomer {

    private final CustomerRepository repository;
    private final ConvertCustomerToCustomerResponse convertCustomerToCustomerResponse;

    public CustomerDTO execute(String id){
        final var customer = repository
                .findByDocumentNumber(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
        return convertCustomerToCustomerResponse.convert(customer);
    }
}