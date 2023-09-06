package br.com.sennatech.sddo.customers.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO{
    String documentNumber;
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

