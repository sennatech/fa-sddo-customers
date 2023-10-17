package br.com.sennatech.sddo.customers.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    String street;
    String number;
    String neighbourhood;
    String city;
    String state;
    String country;
    String zipCode;
    String complement;
}

