package br.com.sennatech.sddo.customers.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.customers.model.dto.PasswordDTO;
import br.com.sennatech.sddo.customers.model.dto.PasswordRequestDTO;


@Component
public class PasswordRequestDTOtoPasswordDTO implements Function<PasswordRequestDTO, PasswordDTO> {

    @Override
    public PasswordDTO apply(PasswordRequestDTO request) {
        PasswordDTO passwordDTO = new PasswordDTO(request.getNewPassword());
        return passwordDTO;
    }
}

