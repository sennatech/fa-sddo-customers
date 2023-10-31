package br.com.sennatech.sddo.customers.domain.dto;

import java.time.LocalDate;

import br.com.sennatech.sddo.customers.domain.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO{
    private String documentNumber;
    private String name;
    private LocalDate birthdate;
    private Gender gender;
    private String login;
    private String password;
    private String email;
    private String areaCode;
    private String phone;
    private CustomerAddressDTO address;
}

