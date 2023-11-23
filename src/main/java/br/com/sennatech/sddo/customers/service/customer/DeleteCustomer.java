package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteCustomer {

    private final CustomerRepository customerRepository;

    public void run(String documentNumber) {
        if (!customerRepository.existsById(documentNumber)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.deleteById(documentNumber);
    }
}
