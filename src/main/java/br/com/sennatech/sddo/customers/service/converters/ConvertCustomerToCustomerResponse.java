package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConvertCustomerToCustomerResponse {

    private final ModelMapper mapper;

    public CustomerDTO responseConvert(Customer customer) {
        return mapper.map(customer, CustomerDTO.class);
    }
}
