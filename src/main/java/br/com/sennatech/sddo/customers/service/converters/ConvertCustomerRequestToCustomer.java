package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class ConvertCustomerRequestToCustomer {

    private final ModelMapper mapper;

    public Customer convert(CustomerDTO request){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        final var customer = mapper.map(request, Customer.class);
        customer.setBirthdate( LocalDate.parse(request.getBirthdate(), formatter));
        return customer;
    }

}
