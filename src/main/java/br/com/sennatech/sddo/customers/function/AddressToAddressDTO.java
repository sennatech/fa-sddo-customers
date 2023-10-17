package br.com.sennatech.sddo.customers.function;

import br.com.sennatech.sddo.customers.domain.dto.AddressDTO;
import br.com.sennatech.sddo.customers.domain.entity.Address;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressToAddressDTO implements Function<Address, AddressDTO> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public AddressDTO apply(Address address){
        return mapper.map(address, AddressDTO.class);
    }
}