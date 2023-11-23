package br.com.sennatech.sddo.customers.function;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.entity.Customer;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CustomerDTOToCustomer implements Function<CustomerDTO, Customer> {

    private final ModelMapper mapper;

    @Override
    public Customer apply(CustomerDTO request) {
        return mapper.map(request, Customer.class);
    }
}
