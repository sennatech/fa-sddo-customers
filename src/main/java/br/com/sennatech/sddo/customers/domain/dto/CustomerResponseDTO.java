package br.com.sennatech.sddo.customers.domain.dto;

import java.time.LocalDate;

import br.com.sennatech.sddo.customers.domain.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponseDTO {
    String documentNumber;
    String name;
    LocalDate birthdate;
    Gender gender;
    String login;
    String email;
    String areaCode;
    String phone;
    AddressDTO address;
}

