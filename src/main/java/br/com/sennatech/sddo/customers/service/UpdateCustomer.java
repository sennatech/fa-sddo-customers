package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.InvalidCredentialException;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerDTOToCustomer;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCustomer {

    private final ConvertCustomerDTOToCustomer convertCustomerDTOToCustomer;
    private final ConvertCustomerToCustomerDTO convertCustomerToCustomerDTO;
    private final CustomerRepository repository;
    @Transactional
    public CustomerDTO execute(String documentNumber, CustomerDTO request){
        repository.findById(documentNumber).orElseThrow(() -> new InvalidCredentialException("Customer not found."));
        Customer customer = convertCustomerDTOToCustomer.convert(request);
        return convertCustomerToCustomerDTO.convert(repository.save(customer));
    }
}
