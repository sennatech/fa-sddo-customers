package br.com.sennatech.sddo.customers.service;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.repository.CustomerRepository;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerDTOToCustomer;
import br.com.sennatech.sddo.customers.service.converters.ConvertCustomerToCustomerDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SaveCustomer {

    private final ConvertCustomerDTOToCustomer convertRequestToCustomer;
    private final CustomerRepository repository;
    private final ConvertCustomerToCustomerDTO convertCustomerToResponse;

    public CustomerDTO responseConvert(CustomerDTO request){
        final var customer = convertRequestToCustomer.convert(request);
        return convertCustomerToResponse.convert(repository.save(customer));
    }
}
