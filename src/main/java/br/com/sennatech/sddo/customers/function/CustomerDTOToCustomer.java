package br.com.sennatech.sddo.customers.function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.model.Customer;
import br.com.sennatech.sddo.customers.model.dto.CustomerDTO;
import java.util.function.Function;

@Component
public class CustomerDTOToCustomer implements Function<CustomerDTO, Customer> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public Customer apply(CustomerDTO request){
        final var customer = mapper.map(request, Customer.class);
        return customer;
    }

}
