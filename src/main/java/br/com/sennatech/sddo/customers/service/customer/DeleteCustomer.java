package br.com.sennatech.sddo.customers.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DeleteCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    public void run(String documentNumber) {
        if (!customerRepository.existsById(documentNumber)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.deleteById(documentNumber);
    }
}
