package br.com.sennatech.sddo.customers.service.converters;

import br.com.sennatech.sddo.customers.domain.Customer;
import br.com.sennatech.sddo.customers.domain.dto.CustomerDTO;
import br.com.sennatech.sddo.customers.domain.dto.PasswordDTO;
import br.com.sennatech.sddo.customers.domain.dto.PasswordRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ConvertPassordRequestToPasswordDTO {



    public PasswordDTO convert(PasswordRequestDTO request) {
        PasswordDTO passwordDTO = new PasswordDTO(request.getNewPassword());
        return passwordDTO;
    }
}

