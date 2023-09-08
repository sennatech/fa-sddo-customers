package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCustomerByDocNumber {

    private final CustomerRepository repository;

    public void execute(String documentNumber){
        repository.deleteByDocumentNumber(documentNumber);
    }
}
