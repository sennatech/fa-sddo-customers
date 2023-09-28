package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.PasswordDTO;
import br.com.sennatech.sddo.customers.domain.dto.PasswordRequestDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class ConvertPassordRequestToPasswordDTO {

    private final ModelMapper mapper;

    public PasswordDTO convert(PasswordRequestDTO request){
        return mapper.map(request, PasswordDTO.class);
    }
    }

