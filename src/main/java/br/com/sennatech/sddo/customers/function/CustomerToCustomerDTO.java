package br.com.sennatech.sddo.customers.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;

@Component
public class CustomerToCustomerDTO implements Function<Customer, CustomerDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerDTO apply(Customer customer){
        return mapper.map(customer, CustomerDTO.class);
    }
}