package br.com.sennatech.sddo.customers.function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;

import java.util.function.Function;

@Component
public class CustomerDTOToCustomer implements Function<CustomerDTO, Customer> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Customer apply(CustomerDTO request) {
        return mapper.map(request, Customer.class);
    }
}
