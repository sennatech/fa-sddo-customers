package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.CustomerResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
@Data
public class ConvertCustomerDTOToCustomerResponseDTO {

    private final ModelMapper mapper;

    public CustomerResponseDTO convert(CustomerDTO customerDTO){
        return mapper.map(customerDTO, CustomerResponseDTO.class);
    }
}

