package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.CustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerRequestToCustomer;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddCustomer {

    private final ConvertCustomerRequestToCustomer convertRequestToCustomer;
    private final CustomerRepository repository;
    private final ConvertCustomerToCustomerResponse convertCustomerToResponse;

    public CustomerDTO responseConvert(CustomerDTO request){
        final var customer = convertRequestToCustomer.convert(request);
        return convertCustomerToResponse.responseConvert(repository.save(customer));
    }

}
