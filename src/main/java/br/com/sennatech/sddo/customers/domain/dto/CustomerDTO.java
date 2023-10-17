package br.com.sennatech.sddo.customers.domain.dto;

import java.time.LocalDate;

import br.com.sennatech.sddo.customers.domain.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO{
    String documentNumber;
    String name;
    LocalDate birthdate;
    Gender gender;
    String login;
    String password;
    String email;
    String areaCode;
    String phone;
    AddressDTO address;

}

