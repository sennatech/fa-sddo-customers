package br.com.sennatech.sddo.customers.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO{
    String documentNumber;
    @NotBlank
    String name;
    String birthdate;
    String gender;
    String login;
    String password;
    String email;
    String areaCode;
    String phone;
    AddressDTO address;

}

