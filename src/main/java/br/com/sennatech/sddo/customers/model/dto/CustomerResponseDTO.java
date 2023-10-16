package br.com.sennatech.sddo.customers.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponseDTO {
    String documentNumber;
    String name;
    Date birthdate;
    String gender;
    String login;
    String email;
    String areaCode;
    String phone;
    AddressDTO address;
}

