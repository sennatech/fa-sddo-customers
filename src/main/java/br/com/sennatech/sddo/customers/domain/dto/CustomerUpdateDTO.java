package br.com.sennatech.sddo.customers.domain.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {
    String name;
    Date birthdate;
    String gender;
    String login;
    String password;
    String email;
    String areaCode;
    String phone;
    AddressDTO address;
}

