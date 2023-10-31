package br.com.sennatech.sddo.customers.service.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerListDTO;
import br.com.sennatech.sddo.customers.function.CustomerToCustomerListDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;

@Service
public class ListCustomers {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerToCustomerListDTO toListDTO;

    public List<CustomerListDTO> run() {
        return customerRepository.findAll().stream().map(customer -> toListDTO.apply(customer))
                .collect(Collectors.toList());
    }
}
