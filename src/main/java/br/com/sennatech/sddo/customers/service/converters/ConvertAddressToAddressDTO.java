package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Address;
import br.com.sennatech.sddo.customers.domain.AddressDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class ConvertAddressToAddressDTO {
    private final ModelMapper mapper;

    public AddressDTO convert(Address address){

        return mapper.map(address, AddressDTO.class);
    }
}