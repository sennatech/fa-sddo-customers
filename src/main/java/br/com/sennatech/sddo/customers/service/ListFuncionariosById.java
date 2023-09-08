package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ListFuncionariosById {

    private final CustomerRepository repository;
    private final ConvertCustomerToCustomerResponse convertCustomerToCustomerResponse;
    public CustomerDTO execute(String id){
        final var customer = repository.findByDocumentNumber(id).orElseThrow(() ->new EntityNotFoundException("Desculpe, dado não encontrado."));
        return convertCustomerToCustomerResponse.responseConvert(customer);
    }
}