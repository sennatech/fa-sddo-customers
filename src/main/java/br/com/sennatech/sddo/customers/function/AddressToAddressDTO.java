package br.com.sennatech.sddo.customers.function;

import br.com.sennatech.sddo.customers.domain.dto.CustomerAddressDTO;
import br.com.sennatech.sddo.customers.domain.entity.CustomerAddress;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressToAddressDTO implements Function<CustomerAddress, CustomerAddressDTO> {

    private final ModelMapper mapper;

    @Override
    public CustomerAddressDTO apply(CustomerAddress address){
        return mapper.map(address, CustomerAddressDTO.class);
    }
}