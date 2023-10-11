package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ConvertCustomerDTOToCustomer {

    private final ModelMapper mapper;

    public Customer convert(CustomerDTO request){
        final var customer = mapper.map(request, Customer.class);
        customer.setBirthdate( LocalDate.parse(request.getBirthdate()));
        return customer;
    }

}
