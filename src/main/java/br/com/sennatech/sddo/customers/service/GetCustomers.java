package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetCustomers {

    private final CustomerRepository repository;
    private final ConvertCustomerToCustomerDTO converter;

    public List<CustomerDTO> execute(){
        return repository
                .findAll()
                .stream()
                .map(customer -> converter.convert(customer))
                .collect(Collectors.toList());
    }
}
