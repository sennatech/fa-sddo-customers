package br.com.sennatech.sddo.customers.function;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.CustomerResponseDTO;

@Component
public class CustomerDTOToCustomerResponseDTO implements Function<CustomerDTO, CustomerResponseDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public CustomerResponseDTO apply(CustomerDTO customerDTO){
        return mapper.map(customerDTO, CustomerResponseDTO.class);
    }
}

