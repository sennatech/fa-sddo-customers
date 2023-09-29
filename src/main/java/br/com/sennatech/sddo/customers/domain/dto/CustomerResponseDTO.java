package br.com.sennatech.sddo.customers.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponseDTO {
    String documentNumber;
    String name;
    String birthdate;
    String gender;
    String login;
    String email;
    String areaCode;
    String phone;
    AddressDTO address;
}

