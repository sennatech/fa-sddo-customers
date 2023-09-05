package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConvertCustomerRequestToCustomer {

    private final ModelMapper mapper;

    public Customer convert(CustomerDTO request){
        return mapper.map(request, Customer.class);
    }

}
