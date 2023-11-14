package br.com.sennatech.sddo.customers.service.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerListDTO;
import br.com.sennatech.sddo.customers.function.CustomerToCustomerListDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListCustomers {

    private final CustomerRepository customerRepository;
    private final CustomerToCustomerListDTO toListDTO;

    public List<CustomerListDTO> run() {
        return customerRepository.findAll().stream().map(toListDTO::apply)
                .collect(Collectors.toList());
    }
}
